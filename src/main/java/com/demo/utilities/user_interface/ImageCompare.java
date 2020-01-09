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


public class ImageCompare extends BasicConfiguration {

    private static String textFile;



    public static void getElementScreenshot(WebElement element, String image) throws Exception {
        textFile  = element.toString();
        File file = element.getScreenshotAs(OutputType.FILE);
        File dest = new File(FilePaths.screenshots_actual_folder + image + ".png");
        FileUtils.copyFile(file, dest);

        try {
            Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(driver, element);
            ImageIO.write(screenshot.getImage(), "png", new File(FilePaths.screenshots_actual_folder + image + ".png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static ImageCompare getImageCompare(String actualImage, String expectedImage) throws IOException {
        long start = System.currentTimeMillis();
        int q = 0;
        File file1 = new File(FilePaths.screenshots_buffer_folder + textFile + ".txt");

        FileWriter fw = new FileWriter(file1.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);

        File fileA = new File(actualImage);
        BufferedImage image = ImageIO.read(fileA);
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        int[][] clr = new int[width][height];

        File fileB = new File(expectedImage);
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
            test.pass("Image comparison successfully completed"
                    + "<br />"
                    + "<br />"
                    + "Success rate = " + x + "%"
                    + "<br />"
                    + "Time(ms) for visualization check = " + (stop - start)
                    + "<br />"
                    + "Number of pixels gets varied = " + q
                    + "<br />"
                    + "Number of pixels gets matched = " + p, MediaEntityBuilder.createScreenCaptureFromPath(actualImage).build());
        }
        if (x > 98 && x < 100) {
            test.warning("| WARNING | Results from comparison needs to be checked"
                    + "<br />"
                    + "<br />"
                    + "Success rate = " + x + "%"
                    + "<br />"
                    + "Time(ms) for visualization check = " + (stop - start)
                    + "<br />"
                    + "Number of pixels gets varied = " + q
                    + "<br />"
                    + "Number of pixels gets matched = " + p, MediaEntityBuilder.createScreenCaptureFromPath(actualImage).build());
        }
        if (x < 98) {
            test.fail("Compare actual screenshot with screenshot from the data base has failed"
                    + "<br />"
                    + "<br />"
                    + "Success rate = " + x + "%"
                    + "<br />"
                    + "Time(ms) for visualization check = " + (stop - start)
                    + "<br />"
                    + "Number of pixels gets varied = " + q
                    + "<br />"
                    + "Number of pixels gets matched = " + p, MediaEntityBuilder.createScreenCaptureFromPath(actualImage).build());
        }
        return null;
    }
}