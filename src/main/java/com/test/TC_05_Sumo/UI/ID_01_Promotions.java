package com.test.TC_05_Sumo.UI;

import com.aventstack.extentreports.AnalysisStrategy;
import com.setup.BasicSetup;
import com.setup.ExtentManager;
import com.setup.GetCompare;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.reflect.Method;

import static com.constants.SumoPageElements.*;
import static com.setup.ExtentManager.extent;
import static com.setup.ExtentManager.test;

public class ID_01_Promotions extends BasicSetup {

    private static BufferedImage bufferedImage;
    private static Point point;
    private static int height;
    private static int width;
    public static String actualImage;
    public static String expectedImage;
    public static String imageFile;



    // Start report session
    @BeforeClass
    public void startTest() throws Exception {
        extent = ExtentManager.GetExtent();
        test = extent.createTest("[TC_01] Promotions Page", "DESCRIPTION");
        test.assignAuthor("SUMO");
        test.assignCategory("Front-End");
        extent.setAnalysisStrategy(AnalysisStrategy.TEST);
    }



    @Test
    public void openSumoHomePage() throws Exception {
        driver.get("https://sumo-qa.pokerstarsdev.com/matrix/promotions");
        driver.manage().window().maximize();

        WebElement username = driver.findElement(By.cssSelector(usernameField));
        WebElement password = driver.findElement(By.cssSelector(passwordField));
        WebElement loginBtn = driver.findElement(By.cssSelector(loginButton));

        username.sendKeys("test1");
        password.sendKeys("test1");
        loginBtn.click();

        Thread.sleep(1000);

        String url = driver.getCurrentUrl();
        Assert.assertEquals(url, "https://sumo-qa.pokerstarsdev.com/matrix/promotions");

    }

    @Test
    public void checkTable(Method testMethod) throws Exception {
        Thread.sleep(1000);
        WebDriverWait wait = new WebDriverWait(driver, 15);
        WebElement addHeaderBtn = driver.findElement(By.cssSelector(addPageHeader));
        wait.until(ExpectedConditions.elementToBeClickable(addHeaderBtn));

        imageFile = testMethod.getName();
        takeScreenshot(driver, "promotions");
        BufferedImage img = ImageIO.read(screenshotFile);

        WebElement table = driver.findElement(By.cssSelector(promoTable));

        point = table.getLocation();
        width = table.getSize().getWidth();
        height = table.getSize().getHeight();
        bufferedImage = img.getSubimage(point.getX(), point.getY(), width, height);
        ImageIO.write(bufferedImage, "png", new File(filePath + "/" + "Screenshots/Actual/table.png"));


        WebElement tableHeader = driver.findElement(By.cssSelector(promoTableHeader));

        point = tableHeader.getLocation();
        width = tableHeader.getSize().getWidth();
        height = tableHeader.getSize().getHeight();
        bufferedImage = img.getSubimage(point.getX(), point.getY(), width, height);
        ImageIO.write(bufferedImage, "png", new File(filePath + "/" + "Screenshots/Actual/actualTableHeader.png"));

        actualImage = filePath + "/" + "Screenshots/Actual/actualTableHeader.png";
        expectedImage = filePath + "/" + "Screenshots/Expected/expectedTableHeader.png";

        GetCompare.GetCompare();
    }
}