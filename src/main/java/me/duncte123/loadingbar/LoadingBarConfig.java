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

import java.awt.*;

public class LoadingBarConfig {

    private int width;
    private int height;
    private int borderWidth;
    private int precision;
    private Color borderColor;
    private Color innerColor;
    private Color fillColor;
    private boolean drawBorder;

    /**
     * Constructs a new config for the bar
     *
     * @param width
     *         The width of the image
     * @param height
     *         The height of the image
     * @param borderWidth
     *         The width of the border (black part)
     * @param borderColor
     *         The color of the border
     * @param innerColor
     *         The inner color of the bar (gray part)
     * @param fillColor
     *         The color of the fill (green part)
     * @param drawBorder
     *         {@code true} to draw a border with the border color around the image, {@code false} to disable
     */
    public LoadingBarConfig(int width, int height, int borderWidth, int precision, Color borderColor, Color innerColor, Color fillColor, boolean drawBorder) {
        this.width = width;
        this.height = height;
        this.borderWidth = borderWidth;
        this.precision = parsePrecision(precision);
        this.borderColor = borderColor;
        this.innerColor = innerColor;
        this.fillColor = fillColor;
        this.drawBorder = drawBorder;
    }

    public int getWidth() {
        return width;
    }

    public LoadingBarConfig setWidth(int width) {
        this.width = width;
        return this;
    }

    public int getHeight() {
        return height;
    }

    public LoadingBarConfig setHeight(int height) {
        this.height = height;
        return this;
    }

    public int getBorderWidth() {
        return borderWidth;
    }

    public LoadingBarConfig setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
        return this;
    }

    public int getPrecision() {
        return precision;
    }

    public LoadingBarConfig setPrecision(int precision) {
        this.precision = parsePrecision(precision);

        return this;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public LoadingBarConfig setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
        return this;
    }

    public Color getInnerColor() {
        return innerColor;
    }

    public LoadingBarConfig setInnerColor(Color innerColor) {
        this.innerColor = innerColor;
        return this;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public LoadingBarConfig setFillColor(Color fillColor) {
        this.fillColor = fillColor;
        return this;
    }

    public boolean isDrawBorder() {
        return drawBorder;
    }

    public LoadingBarConfig setDrawBorder(boolean drawBorder) {
        this.drawBorder = drawBorder;
        return this;
    }

    public static int parsePrecision(int precision) {
        if (precision > 17 || precision < 0) {
           return 17;
        }

        return precision;
    }

    /**
     * Returns the default config for the bar
     *
     * @return The default config for the bar
     */
    public static LoadingBarConfig defaultConfig() {
        return new LoadingBarConfig(
                360,
                40,
                4,
                17,
                Color.BLACK,
                new Color(0x737E8D),
                new Color(0x42B481),
                true
        );
    }
}
