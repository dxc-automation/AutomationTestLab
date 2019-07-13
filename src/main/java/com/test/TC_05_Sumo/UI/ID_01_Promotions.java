package com.test.TC_05_Sumo.UI;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.setup.BasicSetup;
import com.setup.ExtentManager;
import com.setup.GetImageCompare;
import com.setup.WebDriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
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
        test = extent.createTest("[TC_01] Promotions Page", "<pre>dhjbsabhdashjda</pre>");
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
    public void checkToolbarButtons(Method testMethod) throws Exception {
        Thread.sleep(4000);
        WebDriverWait wait = new WebDriverWait(driver, 15);

        imageFile = testMethod.getName();
        takeScreenshot(driver, "promotions");

        WebElement tableHeader = driver.findElement(By.cssSelector(promoTableHeader));
        BufferedImage img = ImageIO.read(screenshotFile);
        point = tableHeader.getLocation();
        width = tableHeader.getSize().getWidth();
        height = tableHeader.getSize().getHeight();
        bufferedImage = img.getSubimage(point.getX(), point.getY(), width, height);
        ImageIO.write(bufferedImage, "png", new File(filePath + "/" + "Screenshots/Actual/actualTableHeader.png"));

        actualImage = filePath + "/" + "Screenshots/Actual/actualTableHeader.png";
        expectedImage = filePath + "/" + "Screenshots/Expected/expectedTableHeader.png";

        GetImageCompare.GetCompare();


        WebElement removeBtn         = driver.findElement(By.cssSelector(removePageHeader));
        WebElement cloneBtn          = driver.findElement(By.cssSelector(clonePageHeader));
        WebElement copySegmentsBtn   = driver.findElement(By.cssSelector(copySegmentsPageHeader));
        WebElement removeSegmentsBtn = driver.findElement(By.cssSelector(removeSegmentsPageHeader));
        WebElement deselectBtn       = driver.findElement(By.cssSelector(deselectExpiresPageHeader));

        boolean removeButton    = WebDriverUtils.isClickable(removeBtn);
        boolean cloneButton     = WebDriverUtils.isClickable(cloneBtn);
        boolean copySgmButton   = WebDriverUtils.isClickable(copySegmentsBtn);
        boolean removeSgmButton = WebDriverUtils.isClickable(removeSegmentsBtn);
        boolean deselectButton  = WebDriverUtils.isClickable(deselectBtn);

        if (removeButton == true && cloneButton == true && copySgmButton == true && removeSgmButton == true && deselectButton == true) {
            test.pass("Toolbar buttons has correct states when there is no selection");
        }
        else if (removeButton == false || cloneButton == false || copySgmButton == false || removeSgmButton == false || deselectButton == false) {
            test.fail("Toolbar buttons has incorrect states when there is no selection", MediaEntityBuilder.createScreenCaptureFromPath(actualImage).build());
        }
    }
}