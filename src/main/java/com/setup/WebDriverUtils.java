package com.setup;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import static com.setup.BasicSetup.driver;
import static com.setup.ExtentManager.test;


public class WebDriverUtils extends BasicSetup {

    private static String actual;
    private static String expected;

    public WebDriverUtils(String fileName, String actualImage, String expectedImage) throws Exception {

        long start = System.currentTimeMillis();
        int q = 0;
        File file1 = new File(filePath + "/" + fileName + ".txt");

        FileWriter fw = new FileWriter(file1.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);

        File fileA = new File(filePath + "/" + actualImage + ".png");
        actual = filePath + "/" + actualImage + ".png";
        BufferedImage image = ImageIO.read(fileA);
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        int[][] clr = new int[width][height];

        File fileB = new File(filePath + "/" + expectedImage + ".png");
        expected = filePath + "/" + expectedImage + ".png";
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
            test.pass(MarkupHelper.createLabel("Compare actual screenshot with screenshot from the data base", ExtentColor.GREEN));
            test.pass("<pre>"
                    + "Success rate = " + x + "%" + "\n"
                    + "Time(ms) for visualization check = " + (stop - start) + "\n"
                    + "Number of pixels gets varied = " + q + "\n"
                    + "Number of pixels gets matched = " + p + "\n"
                    + "</pre>");
            test.pass("[ ACTUAL PAGE VIEW ]", MediaEntityBuilder.createScreenCaptureFromPath(actual).build());
            test.pass("[ EXPECTED PAGE VIEW ]", MediaEntityBuilder.createScreenCaptureFromPath(expected).build());

        }
        if (x == 95) {
            test.warning(MarkupHelper.createLabel("Compare actual screenshot with screenshot from the data base", ExtentColor.ORANGE));
            test.warning("<pre>"
                    + "Success rate = " + x + "%" + "\n"
                    + "Time(ms) for visualization check = " + (stop - start) + "\n"
                    + "Number of pixels gets varied = " + q + "\n"
                    + "Number of pixels gets matched = " + p + "\n"
                    + "</pre>");
            test.warning("[ ACTUAL PAGE VIEW ]", MediaEntityBuilder.createScreenCaptureFromPath(actual).build());
            test.warning("[ EXPECTED PAGE VIEW ]", MediaEntityBuilder.createScreenCaptureFromPath(expected).build());
        }
        if (x < 95) {
            test.fail(MarkupHelper.createLabel("Compare actual screenshot with screenshot from the data base has failed", ExtentColor.RED));
            test.fail("<pre>"
                    + "Success rate = " + x + "%" + "\n"
                    + "Time(ms) for visualization check = " + (stop - start) + "\n"
                    + "Number of pixels gets varied = " + q + "\n"
                    + "Number of pixels gets matched = " + p + "\n"
                    + "</pre>");
            test.fail("[ ACTUAL PAGE VIEW ]", MediaEntityBuilder.createScreenCaptureFromPath(actual).build());
            test.fail("[ EXPECTED PAGE VIEW ]", MediaEntityBuilder.createScreenCaptureFromPath(expected).build());
        }
    }
}


