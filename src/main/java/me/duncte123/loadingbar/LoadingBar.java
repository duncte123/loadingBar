/*
 *    Copyright 2018 - 2019 Duncan "duncte123" Sterken
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package me.duncte123.loadingbar;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class LoadingBar {

    private LoadingBar() {
    }

    /**
     * Returns the percentage of how much has passed from this year
     *
     * @return The percentage of how much has passed from this year
     */
    public static double getPercentage() {
        return getPercentage(19);
    }
    
    public static double getPercentage(int precision) {
        if (precision > 19 || precision < 3) precision = 19;
        long now = new Date().getTime();

        Calendar startCalendar = Calendar.getInstance();
        startCalendar.set(startCalendar.get(Calendar.YEAR), Calendar.JANUARY, 1, 0, 0, 0);
        long yearStart = startCalendar.getTime().getTime();

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.set(startCalendar.get(Calendar.YEAR), Calendar.DECEMBER, 31, 0, 0, 0);
        long yearEnd = endCalendar.getTime().getTime();

        double percentage = 100.0 * (now - yearStart) / (yearEnd - yearStart);
        double factor = Math.pow(10.0, (double) precison);
        long percents = (long) (percentage * factor);
        percentage = ((double) percents) / factor;
        return percentage;
    }

    /**
     * Generates the progress bar
     *
     * @param percentage
     *         a percentage
     *
     * @return The image in a byte array
     *
     * @throws IOException
     *         when things break
     */
    public static byte[] generateImage(double percentage) throws IOException {
        return generateImage(percentage, LoadingBarConfig.defaultConfig());
    }

    /**
     * Generates the progress bar
     *
     * @param percentage
     *         a percentage
     * @param config
     *         The configuration for this bar
     *
     * @return The image in a byte array
     *
     * @throws IOException
     *         when things break
     */
    public static byte[] generateImage(double percentage, LoadingBarConfig config) throws IOException {
        int width = config.getWidth();
        int height = config.getHeight();
        double factor = Math.pow(10.0, (double) config.getPrecision());
        long percents = (long) (percentage * factor);
        percentage = ((double) percents) / factor; 
        int type = BufferedImage.TYPE_INT_RGB;

        BufferedImage image = new BufferedImage(width, height, type);
        Graphics2D graphics = image.createGraphics();

        // Fill the background
        graphics.setPaint(config.getBorderColor());
        graphics.fillRect(0, 0, image.getWidth(), image.getHeight());

        int innerX = config.getBorderWidth();
        int innerY = config.getBorderWidth();
        int maxInnerW = image.getWidth() - (innerX * 2);
        int maxInnerH = image.getHeight() - (innerY * 2);

        // Fill the gray area
        graphics.setPaint(config.getInnerColor());
        graphics.fillRect(innerX, innerY, maxInnerW, maxInnerH);

        int greenFill = (int) ((maxInnerW / 100.0) * percentage);

        // Fill the bar
        graphics.setPaint(config.getFillColor());
        graphics.fillRect(innerX, innerY, greenFill, maxInnerH);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "png", outputStream);

        return outputStream.toByteArray();
    }
}
