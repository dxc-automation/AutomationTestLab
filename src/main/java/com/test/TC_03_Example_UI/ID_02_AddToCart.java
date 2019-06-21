package com.test.TC_03_Example_UI;

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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import static com.setup.ExtentManager.extent;
import static com.setup.ExtentManager.test;

public class ID_02_AddToCart extends BasicSetup {

    protected static Logger LOG = LoggerFactory.getLogger(ID_02_AddToCart.class);
    public static String requestData = "";
    public static String requestURL  = "";

    protected static String result;
    private String baseUrl = "https://www.amazon.com/";
    public String articleName;



    @BeforeClass
    public void startTest() throws Exception {
        extent = ExtentManager.GetExtent();
        test = extent.createTest("[UI] Add Item To Cart", "The purpose of this test is to verify that the user can add item to cart.");
        test.assignAuthor("Pavel Popov");
        test.assignCategory("");
        extent.setAnalysisStrategy(AnalysisStrategy.TEST);
    }

    @Parameters({"browser"})
    @Test
    public void openHomePage() throws Exception{
        driver.get(baseUrl);
        driver.manage().window().maximize();
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
        searchTextField.sendKeys("Harry Potter");
        searchTextField.submit();

        try {
            String actualSearchResult = searchTextField.getText();
            System.out.println("SEARCH: " + actualSearchResult);

            Assert.assertEquals(actualSearchResult, expectedSearchResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void openItem() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"search\"]/div[1]/div[2]/div[1]/span[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/h2[1]/a[1]/span[1]")));

        WebElement item = driver.findElement(By.xpath("//*[@id=\"search\"]/div[1]/div[2]/div[1]/span[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/h2[1]/a[1]/span[1]"));
        item.click();
    }
}