package me.duncte123;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class Main {

    private static double getPercentage() {
        long now = new Date().getTime();

        Calendar startCalendar = Calendar.getInstance();
        startCalendar.set(startCalendar.get(Calendar.YEAR), Calendar.JANUARY, 1, 0, 0, 0);
        long yearStart = startCalendar.getTime().getTime();

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.set(startCalendar.get(Calendar.YEAR) + 1, Calendar.JANUARY, 1, 0, 0, 0);
        long yearEnd = endCalendar.getTime().getTime();

        double done = 100.0 * ( now - yearStart ) / ( yearEnd - yearStart );

        System.out.println(now);
        System.out.println(yearStart);
        System.out.println(yearEnd);
        System.out.println(done);
        System.out.println(360 - done);

        return done;
    }

    public static void main(String[] args) throws IOException {
        int width = 360;
        int height = 40;
        int type = BufferedImage.TYPE_INT_RGB;

        BufferedImage image = new BufferedImage(width, height, type);
        Graphics2D graphics = image.createGraphics();

        // Fill the background
        graphics.setPaint(Color.BLACK);
        graphics.fillRect(0, 0, image.getWidth(), image.getHeight());

        int innerX = 3;
        int innerY = 3;
        int maxInnerW = image.getWidth() - (innerX * 2);
        int maxInnerH = image.getHeight() - (innerY * 2);

        // Fill the gray area
        graphics.setPaint(Color.LIGHT_GRAY);
        graphics.fillRect(innerX, innerY, maxInnerW, maxInnerH);

        double percentage = getPercentage();
        int greenFill = (int)(maxInnerW - percentage);

        // Fill the bar
        graphics.setPaint(new Color(0x98ff98));
        graphics.fillRect(innerX, innerY, greenFill, maxInnerH);


        File outputfile = new File("loadingBar.png");
        ImageIO.write(image, "png", outputfile);
    }
}
