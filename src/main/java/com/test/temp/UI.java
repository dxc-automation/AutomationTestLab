package com.test.temp;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.setup.BasicSetup;
import com.setup.ExtentManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import static com.setup.ExtentManager.extent;
import static com.setup.ExtentManager.test;
import static org.testng.Assert.assertEquals;

public class UI extends BasicSetup {

    // Start report session
    @BeforeClass
    public void startTest() throws Exception {
        extent = ExtentManager.GetExtent();
        test = extent.createTest("[UI] TEST NAME", "DESCRIPTION");
        test.assignAuthor("YOUR NAME");
        test.assignCategory("FrontEnd");
        extent.setAnalysisStrategy(AnalysisStrategy.TEST);
    }

    // Test example that compare actual screenshot and image from data base
    @Test
    public void checkCart() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("hlb-view-cart-announce")));

        WebElement cartBtn = driver.findElement(By.id("hlb-view-cart-announce"));
        cartBtn.click();

        WebElement cartArticle = driver.findElement(By.cssSelector(".sc-product-title"));
        String cartArticleName = cartArticle.getText();

        takeScreenshot(driver, "Article_Actual");

        long start = System.currentTimeMillis();
        int q = 0;
        File file1 = new File("Screenshots/BufferedWriter_Article.txt");

        FileWriter fw = new FileWriter(file1.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);

        File fileA = new File("Screenshots/Expected/Article_Expected.png");
        BufferedImage image = ImageIO.read(fileA);
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        int[][] clr = new int[width][height];

        File fileB = new File("Screenshots/Actual/Article_Actual.png");
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
            test.pass(MarkupHelper.createLabel("[PASSED] STEP 9 - Compare actual screenshot with screenshot from data base", ExtentColor.GREEN));
            test.pass(String.valueOf("Success rate = " + x + "%"));
            test.pass(String.valueOf("Time for visualization check = " + (stop - start)));
            test.pass(String.valueOf("Number of pixels gets varied = " + q));
            test.pass(String.valueOf("Number of pixels gets matched = " + p));
            test.pass("[ ACTUAL PAGE VIEW ]", MediaEntityBuilder.createScreenCaptureFromPath("../Screenshots/Actual/Article_Actual.png").build());
        }
        if (x == 95) {
            test.warning(MarkupHelper.createLabel("[WARNING] STEP 9 - Compare actual screenshot with screenshot from data base", ExtentColor.ORANGE));
            test.warning(String.valueOf("Success rate = " + x + "%"));
            test.warning(String.valueOf("Time for visualization check = " + (stop - start)));
            test.warning(String.valueOf("Number of pixels gets varied = " + q));
            test.warning(String.valueOf("Number of pixels gets matched = " + p));
            test.warning("[ ACTUAL PAGE VIEW ]", MediaEntityBuilder.createScreenCaptureFromPath("../Screenshots/Actual/Article_Actual.png").build());
            test.warning("[ EXPECTED PAGE VIEW ]", MediaEntityBuilder.createScreenCaptureFromPath("../Screenshots/Expected/Article_Expected.png").build());
        }
        if (x < 95) {
            test.fail(MarkupHelper.createLabel("[FAILED] STEP 9 - Compare actual screenshot with screenshot from data base", ExtentColor.RED));
            test.fail(String.valueOf("Success rate = " + x + "%"));
            test.fail(String.valueOf("Time for visualization check = " + (stop - start)));
            test.fail(String.valueOf("Number of pixels gets varied = " + q));
            test.fail(String.valueOf("Number of pixels gets matched = " + p));
            test.fail("[ ACTUAL PAGE VIEW ]", MediaEntityBuilder.createScreenCaptureFromPath("../Screenshots/Actual/Article_Actual.png").build());
            test.fail("[ EXPECTED PAGE VIEW ]", MediaEntityBuilder.createScreenCaptureFromPath("../Screenshots/Expected/Article_Expected.png").build());
        }
    }
}
