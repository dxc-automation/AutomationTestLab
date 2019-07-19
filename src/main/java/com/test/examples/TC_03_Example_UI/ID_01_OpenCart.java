package com.test.examples.TC_03_Example_UI;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import static com.setup.ExtentManager.extent;
import static com.setup.ExtentManager.test;
import static org.testng.Assert.assertEquals;

public class ID_01_OpenCart extends BasicSetup {

    protected static Logger LOG = LoggerFactory.getLogger(ID_01_OpenCart.class);
    public static String requestData = "";
    public static String requestURL  = "";

    protected static String result;
    private String baseUrl = "https://www.amazon.com/";
    public String articleName;



    @BeforeClass
    public void startTest() throws Exception {
        extent = ExtentManager.GetExtent();
        test = extent.createTest("[UI] Open Cart", "The purpose of this test is to verify that the user can add item to cart.");
        test.assignAuthor("Pavel Popov");
        test.assignCategory("");
        extent.setAnalysisStrategy(AnalysisStrategy.TEST);
    }

    @Parameters({"browser"})
    @Test
    public void openHomePage() throws Exception{
        driver.get(baseUrl);
        System.out.println("Chrome browser has been launched successfully");
        driver.manage().window().maximize();
        System.out.println("Browser has been maximized successfully");
        String url = driver.getCurrentUrl();
        Assert.assertEquals(url, baseUrl);
        System.out.println("Expected URL address has been opened successfully");
    }


    @Test
    public void searchItem() throws Exception {
        String expectedSearchResult = "Harry Potter";

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("twotabsearchtextbox")));

        WebElement searchTextField = driver.findElement(By.id("twotabsearchtextbox"));
        searchTextField.sendKeys("harry potter");
        searchTextField.submit();

        String actualSearchResult = driver.findElement(By.id("twotabsearchtextbox")).getText();

        if (actualSearchResult.contentEquals("harry potter")) {
            System.out.println("Search for item has been completed successfully");
        } else {
            System.out.println("WARNING ! The string in 'Search Text' field is:" + actualSearchResult);
        }
    }

    @Test
    public void checkCart() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("nav-cart-count")));

        WebElement cartBtn = driver.findElement(By.id("nav-cart-count"));
        cartBtn.click();

        takeScreenshot(driver, "Article_Actual");

        File filePath = new File(System.getProperty("user.dir")).getParentFile();

        long start = System.currentTimeMillis();
        int q = 0;
        File file1 = new File(filePath + "/" + "Screenshots/BufferedWriter_Article.txt");

        FileWriter fw = new FileWriter(file1.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);

        File fileA = new File(filePath + "/" + "Screenshots/Expected/Article_Expected.png");
        BufferedImage image = ImageIO.read(fileA);
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        int[][] clr = new int[width][height];

        File fileB = new File(filePath + "/" + "Screenshots/Actual/Article_Actual.png");
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
            test.pass("[ ACTUAL PAGE VIEW ]", MediaEntityBuilder.createScreenCaptureFromPath("../Screenshots/Actual/Article_Actual.png").build());
            test.pass("[ EXPECTED PAGE VIEW ]", MediaEntityBuilder.createScreenCaptureFromPath("../Screenshots/Expected/Article_Expected.png").build());

        }
        if (x == 95) {
            test.warning(MarkupHelper.createLabel("Compare actual screenshot with screenshot from the data base", ExtentColor.ORANGE));
            test.warning("<pre>"
                    + "Success rate = " + x + "%" + "\n"
                    + "Time(ms) for visualization check = " + (stop - start) + "\n"
                    + "Number of pixels gets varied = " + q + "\n"
                    + "Number of pixels gets matched = " + p + "\n"
                    + "</pre>");
            test.warning("[ ACTUAL PAGE VIEW ]", MediaEntityBuilder.createScreenCaptureFromPath("../Screenshots/Actual/Article_Actual.png").build());
            test.warning("[ EXPECTED PAGE VIEW ]", MediaEntityBuilder.createScreenCaptureFromPath("../Screenshots/Expected/Article_Expected.png").build());
        }
        if (x < 95) {
            test.fail(MarkupHelper.createLabel("Compare actual screenshot with screenshot from the data base has failed", ExtentColor.RED));
            test.fail("<pre>"
                    + "Success rate = " + x + "%" + "\n"
                    + "Time(ms) for visualization check = " + (stop - start) + "\n"
                    + "Number of pixels gets varied = " + q + "\n"
                    + "Number of pixels gets matched = " + p + "\n"
                    + "</pre>");
            test.fail("[ ACTUAL PAGE VIEW ]", MediaEntityBuilder.createScreenCaptureFromPath("../Screenshots/Actual/Article_Actual.png").build());
            test.fail("[ EXPECTED PAGE VIEW ]", MediaEntityBuilder.createScreenCaptureFromPath("../Screenshots/Expected/Article_Expected.png").build());
        }
    }
}