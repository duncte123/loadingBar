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

import java.awt.*;

public class LoadingBarConfig {

    private int width;
    private int height;
    private int borderWidth;
    private Color borderColor;
    private Color innerColor;
    private Color fillColor;

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
     */
    public LoadingBarConfig(int width, int height, int borderWidth, Color borderColor, Color innerColor, Color fillColor) {
        this.width = width;
        this.height = height;
        this.borderWidth = borderWidth;
        this.borderColor = borderColor;
        this.innerColor = innerColor;
        this.fillColor = fillColor;
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
                Color.BLACK,
                new Color(0x737E8D),
                new Color(0x42B481)
        );
    }
}
