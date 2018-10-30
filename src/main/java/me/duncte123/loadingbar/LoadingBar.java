/*
 *    Copyright 2018 Duncan "duncte123" Sterken
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

    /**
     * Returns a percentage
     * @return a percentage
     */
    public double getPercentage() {
        long now = new Date().getTime();

        Calendar startCalendar = Calendar.getInstance();
        startCalendar.set(startCalendar.get(Calendar.YEAR), Calendar.JANUARY, 1, 0, 0, 0);
        long yearStart = startCalendar.getTime().getTime();

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.set(startCalendar.get(Calendar.YEAR) + 1, Calendar.JANUARY, 1, 0, 0, 0);
        long yearEnd = endCalendar.getTime().getTime();

        return 100.0 * ( now - yearStart ) / ( yearEnd - yearStart );
    }

    /**
     * Generates an image
     * @param percentage a percentage
     * @return some bytes
     * @throws IOException when things break
     */
    public byte[] generateImage(double percentage) throws IOException {
        int width = 360;
        int height = 40;
        int type = BufferedImage.TYPE_INT_RGB;

        BufferedImage image = new BufferedImage(width, height, type);
        Graphics2D graphics = image.createGraphics();

        // Fill the background
        graphics.setPaint(Color.BLACK);
        graphics.fillRect(0, 0, image.getWidth(), image.getHeight());

        int innerX = 4;
        int innerY = 4;
        int maxInnerW = image.getWidth() - (innerX * 2);
        int maxInnerH = image.getHeight() - (innerY * 2);

        // Fill the gray area
        graphics.setPaint(new Color(0x737E8D));
        graphics.fillRect(innerX, innerY, maxInnerW, maxInnerH);

        int greenFill = (int)(maxInnerW - percentage);

        // Fill the bar
        graphics.setPaint(new Color(0x42B481));
        graphics.fillRect(innerX, innerY, greenFill, maxInnerH);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "png", outputStream);

        return outputStream.toByteArray();
    }

    /*public static void main(String[] args) {

        double percentage = getPercentage();
        int year = Calendar.getInstance().getWeekYear();

        System.out.printf("%s is %s%% complete.", year, percentage);

        try (FileOutputStream outputStream = new FileOutputStream("loadingBar.png")) {
            outputStream.write(generateImage(percentage));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
