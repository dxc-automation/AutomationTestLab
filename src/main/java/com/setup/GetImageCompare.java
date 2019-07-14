package com.setup;

import com.aventstack.extentreports.MediaEntityBuilder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static com.setup.ExtentManager.test;
import static com.test.TC_05_Sumo.UI.ID_01_Promotions.*;


public class GetImageCompare extends BasicSetup {

    public static GetImageCompare GetCompare() throws IOException {
        long start = System.currentTimeMillis();
        int q = 0;
        File file1 = new File(filePath + "/" + imageFile + ".txt");

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

        if (x > 95) {
            test.pass("Image comparison successfully completed"
                    + "<br />"
                    + "Success rate = " + x + "%"
                    + "<br />"
                    + "Time(ms) for visualization check = " + (stop - start)
                    + "<br />"
                    + "Number of pixels gets varied = " + q
                    + "<br />"
                    + "Number of pixels gets matched = " + p, MediaEntityBuilder.createScreenCaptureFromPath(actualImage).build());
        }
        if (x == 95) {
            test.warning("| WARNING | Results from comparison needs to be checked"
                    + "<br />"
                    + "Success rate = " + x + "%"
                    + "<br />"
                    + "Time(ms) for visualization check = " + (stop - start)
                    + "<br />"
                    + "Number of pixels gets varied = " + q
                    + "<br />"
                    + "Number of pixels gets matched = " + p, MediaEntityBuilder.createScreenCaptureFromPath(actualImage).build());
        }
        if (x < 95) {
            test.fail("Compare actual screenshot with screenshot from the data base has failed"
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