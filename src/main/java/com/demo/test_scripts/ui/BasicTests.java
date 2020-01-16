package com.demo.test_scripts.ui;

import com.demo.config.BasicConfiguration;
import com.demo.ui_mapping.Amazon;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static com.demo.config.ReporterConfig.*;
import static com.demo.test_properties.UrlPaths.*;
import static com.demo.utilities.user_interface.ImageCompare.*;


public class BasicTests extends BasicConfiguration {

    private static final Logger LOG  = LogManager.getLogger(BasicTests.class);
    private static Amazon homePage   = PageFactory.initElements(driver, Amazon.class);

    private static WebElement element;

    public static void openHomePage(String homePageTitle) throws Exception {
        driver.get(AMAZON_BASE_URL);

        String actualPageTitle = driver.getTitle();
        Assert.assertEquals(homePageTitle, actualPageTitle);
    }


    public static void searchElement(String iteamSearchName, String expectedItemCatalogName) throws Exception {
        homePage.amazon_searchbox.sendKeys(iteamSearchName);
        homePage.amazon_search_submit_btn.click();

        element = driver.findElement(By.partialLinkText(expectedItemCatalogName));
        String actualItemCatalogName = element.getText();
        Assert.assertEquals(actualItemCatalogName, expectedItemCatalogName);
    }


    public static void checkItemDescription() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(webDriver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete"));

        element.click();

        String actualImage = "Item_Details_Actual";
        String expectImage = "Item_Details_Expect";
        takeScreenshot(driver, actualImage);

        imageCompare(actualImage, expectImage);
    }
}


