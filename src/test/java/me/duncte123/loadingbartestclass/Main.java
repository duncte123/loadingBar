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

package me.duncte123.loadingbartestclass;

import me.duncte123.loadingbar.LoadingBar;
import me.duncte123.loadingbar.LoadingBarConfig;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {
        double percentage = LoadingBar.getPercentage(5);
        int year = LocalDateTime.now().getYear();

        System.out.printf("%s is %s%% complete.\n", year, percentage);

        double testPercentage = 16.123456D;
        final double makePrecise = LoadingBar.round(6, testPercentage);

        if (makePrecise == testPercentage) {
            System.out.println("makePrecise works properly, output is " + makePrecise);
        } else {
            System.out.println("Output from makePrecise is " + makePrecise);
        }

        double testPercentage2 = 16.123456789D;
        final double makePrecise2 = LoadingBar.round(5, testPercentage2);

        if (makePrecise2 == 16.12345D) {
            System.out.println("makePrecise2 works properly, output is " + makePrecise2);
        } else {
            System.out.println("Output from makePrecise2 is " + makePrecise2);
        }

        try (FileOutputStream outputStream = new FileOutputStream("loadingBarExample.png")) {
            outputStream.write(LoadingBar.generateImage(69.69));
        } catch (IOException e) {
            e.printStackTrace();
        }

        LoadingBarConfig config = LoadingBarConfig.defaultConfig()
                .setFillColor(Color.RED)
                .setBorderColor(Color.PINK)
                .setPrecision(3);

        try (FileOutputStream outputStream = new FileOutputStream("loadingBarExample-color.png")) {
            outputStream.write(LoadingBar.generateImage(69.6954678795, config));
        } catch (IOException e) {
            e.printStackTrace();
        }

        LoadingBarConfig configNoBorder = LoadingBarConfig.defaultConfig()
                .setBorderColor(Color.PINK)
                .setDrawBorder(false)
                .setPrecision(3);

        try (FileOutputStream outputStream = new FileOutputStream("loadingBarExample-noBorder.png")) {
            outputStream.write(LoadingBar.generateImage(69.6954678795, configNoBorder));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
