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
import java.time.*;

import static me.duncte123.loadingbar.LoadingBarConfig.parsePrecision;

public class LoadingBar {

    private LoadingBar() {
    }

    /**
     * Returns the percentage of how much has passed from this year
     *
     * @return The percentage of how much has passed from this year
     */
    public static double getPercentage() {
        return getPercentage(17);
    }

    /**
     * Returns the percentage of how much has passed from this year
     *
     * @param precision The decimal points to display, min is 0 and max is 17
     *
     * @return The percentage of how much has passed from this year
     */
    public static double getPercentage(int precision) {
        final int finalPrecision = parsePrecision(precision);

        final LocalDateTime nowDateTime = LocalDateTime.now();
        final int currentYear = nowDateTime.getYear();
        final Instant instant = Instant.now(); //can be LocalDateTime
        final ZoneId systemZone = ZoneId.systemDefault(); // my timezone
        final ZoneOffset currentOffsetForMyZone = systemZone.getRules().getOffset(instant);

        final long now = nowDateTime.toEpochSecond(currentOffsetForMyZone);

        final LocalDateTime start = LocalDateTime.of(currentYear, Month.JANUARY, 1, 0, 0, 0);
        final long yearStart = start.toEpochSecond(currentOffsetForMyZone);

        final LocalDateTime end = LocalDateTime.of(currentYear, Month.DECEMBER, 31, 0, 0, 0);
        final long yearEnd = end.toEpochSecond(currentOffsetForMyZone);

        double percentage = 100.0 * (now - yearStart) / (yearEnd - yearStart);

        return round(finalPrecision, percentage);
    }

    /**
     * Generates the progress bar
     *
     * @param percentage
     *         a percentage
     * @param config
     *         The configuration for this bar
     *
     * @return The generated image as {@link BufferedImage} for you to modify further
     */
    public static BufferedImage generateImageRaw(double percentage, LoadingBarConfig config) {
        final int width = config.getWidth();
        final int height = config.getHeight();
        final double finalPercentage = round(config.getPrecision(), percentage);

        final BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        final Graphics2D graphics = image.createGraphics();

        final int innerX;
        final int innerY;
        final int maxInnerW;
        final int maxInnerH;

        if (config.isDrawBorder()) {
            // Fill the background
            graphics.setPaint(config.getBorderColor());
            graphics.fillRect(0, 0, image.getWidth(), image.getHeight());

            innerX = config.getBorderWidth();
            innerY = config.getBorderWidth();
            maxInnerW = image.getWidth() - (innerX * 2);
            maxInnerH = image.getHeight() - (innerY * 2);
        } else {
            innerX = 0;
            innerY = 0;
            maxInnerW = image.getWidth();
            maxInnerH = image.getHeight();
        }

        // Fill the gray area
        graphics.setPaint(config.getInnerColor());
        graphics.fillRect(innerX, innerY, maxInnerW, maxInnerH);

        final int greenFill = (int) ((maxInnerW / 100.0) * finalPercentage);

        // Fill the bar
        graphics.setPaint(config.getFillColor());
        graphics.fillRect(innerX, innerY, greenFill, maxInnerH);

        return image;
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
        final BufferedImage image = generateImageRaw(percentage, config);

        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "png", outputStream);

        return outputStream.toByteArray();
    }

    // Only pubic for the tests
    public static double round(double precision, double percentage) {
        final double factor = Math.pow(10.0D, precision);
        final long percents = (long) (percentage * factor);

        return ((double) percents) / factor;
    }
}
