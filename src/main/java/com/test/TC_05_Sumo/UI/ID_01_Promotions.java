package com.test.TC_05_Sumo.UI;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.setup.BasicSetup;
import com.setup.ExtentManager;
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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import static com.setup.ExtentManager.extent;
import static com.setup.ExtentManager.test;
import static com.constants.SumoPageElements.*;
import static com.setup.BasicSetup.*;

public class ID_01_Promotions extends BasicSetup {

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
    public void checkTable() throws Exception {
        takeScreenshot(driver, "promoTable");
        BufferedImage img = ImageIO.read(screenshotFile);

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(promoTable)));

        WebElement table = driver.findElement(By.cssSelector(promoTable));

        Point p = table.getLocation();
        int w = table.getSize().getWidth();
        int h = table.getSize().getHeight();

        BufferedImage bufferedImage = img.getSubimage(p.getX(), p.getY(), w, h);
        ImageIO.write(bufferedImage, "png", new File(filePath + "/" + "Screenshots/Actual/promoTable.png"));

        test.info("[ TABLE VIEW ]", MediaEntityBuilder.createScreenCaptureFromPath(filePath + "/" + "Screenshots/Actual/promoTable.png").build());
    }
}