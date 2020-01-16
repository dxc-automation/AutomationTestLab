package com.demo.utilities.user_interface;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.demo.config.BasicConfiguration;
import com.demo.test_properties.FilePaths;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static com.demo.config.ReporterConfig.test;
import static com.demo.test_properties.FilePaths.screenshots_actual_folder;
import static com.demo.test_properties.FilePaths.screenshots_buffer_folder;
import static com.demo.test_properties.FilePaths.screenshots_expected_folder;


public class ImageCompare extends BasicConfiguration {

    private static String textFile;

    public static ImageCompare imageCompare(String actualImage, String expectedImage) throws IOException {
        long start = System.currentTimeMillis();
        int q = 0;
        File file1 = new File(screenshots_buffer_folder + textFile + ".txt");

        FileWriter fw = new FileWriter(file1.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);

        File fileA = new File(screenshots_actual_folder + actualImage + ".png");
        BufferedImage image = ImageIO.read(fileA);
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        int[][] clr = new int[width][height];

        File fileB = new File(screenshots_expected_folder + expectedImage + ".png");
        BufferedImage images = ImageIO.read(fileB);
        int widthe = images.getWidth(null);
        int heighte = images.getHeight(null);
        int[][] clre = new int[widthe][heighte];
        int smw = 0;
        int smh = 0;
        int p = 0;

        if (width > widthe) {
            smw = widthe;
        } else {
            smw = width;
        }
        if (height > heighte) {
            smh = heighte;
        } else {
            smh = height;
        }

        for (int a = 0; a < smw; a++) {
            for (int b = 0; b < smh; b++) {
                clre[a][b] = images.getRGB(a, b);
                clr[a][b] = image.getRGB(a, b);
                if (clr[a][b] == clre[a][b]) {
                    p = p + 1;
                    bw.write("\t");
                    bw.write(Integer.toString(a));
                    bw.write("\t");
                    bw.write(Integer.toString(b));
                    bw.write("\n");
                } else
                    q = q + 1;
            }
        }

        float w, h = 0;
        if (width > widthe) {
            w = width;
        } else {
            w = widthe;
        }
        if (height > heighte) {
            h = height;
        } else {
            h = heighte;
        }
        float s = (smw * smh);
        float x = (100 * p) / s;

        long stop = System.currentTimeMillis();

        if (x == 100) {
            test.pass("<b>Image comparison successfully completed</b>"
                    + "<br>"
                    + "Image comparison success rate    = " + x + "%"
                    + "<br>"
                    + "Number of  pixels gets varied    = " + q
                    + "<br>"
                    + "Time(ms) for visualization check = " + (stop - start)
                    + "<br>"
                    + "Number of pixels gets matched    = " + p
                    + "</br>",
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshots_actual_folder + actualImage + ".png").build());
        }
        if (x > 98 && x < 100) {
            test.warning("<b>Results from comparison needs to be checked</b>"
                    + "<br>"
                    + "Image comparison success rate    = " + x + "%"
                    + "<br>"
                    + "Number of  pixels gets varied    = " + q
                    + "<br>"
                    + "Time(ms) for visualization check = " + (stop - start)
                    + "<br>"
                    + "Number of pixels gets matched    = " + p
                    + "<br>",
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshots_actual_folder + actualImage + ".png").build());
        }
        if (x < 98) {
            test.fail("<b>Compare actual screenshot with screenshot from the data base has failed</b>"
                    + "<br>"
                    + "Image comparison success rate    = " + x + "%"
                    + "<br>"
                    + "Number of  pixels gets varied    = " + q
                    + "<br>"
                    + "Time(ms) for visualization check = " + (stop - start)
                    + "<br>"
                    + "Number of pixels gets matched    = " + p
                    + "<br>",
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshots_actual_folder + actualImage + ".png").build());
        }
        return null;
    }
}