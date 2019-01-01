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
import java.util.Calendar;

public class Main {

    public static void main(String[] args) {
        double percentage = LoadingBar.getPercentage();
        int year = Calendar.getInstance().getWeekYear();

        System.out.printf("%s is %s%% complete.", year, percentage);

        try (FileOutputStream outputStream = new FileOutputStream("loadingBarExample.png")) {
            outputStream.write(LoadingBar.generateImage(69.69));
        } catch (IOException e) {
            e.printStackTrace();
        }

        LoadingBarConfig config = LoadingBarConfig.defaultConfig()
                .setFillColor(Color.RED)
                .setBorderColor(Color.PINK);

        try (FileOutputStream outputStream = new FileOutputStream("loadingBarExample-color.png")) {
            outputStream.write(LoadingBar.generateImage(69.69, config));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
