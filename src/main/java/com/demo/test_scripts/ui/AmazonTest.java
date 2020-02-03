package com.demo.test_scripts.ui;

import com.demo.config.BasicTestConfig;
import com.demo.ui_mapping.Amazon;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static com.demo.test_properties.UrlPaths.*;
import static com.demo.utilities.user_interface.ImageCompare.*;

public class AmazonTest extends BasicTestConfig {

    private static final Logger LOG  = LogManager.getLogger(AmazonTest.class);
    private static Amazon homePage   = PageFactory.initElements(driver, Amazon.class);

    private static WebElement itemName;
    private static WebElement addedToCart;
    private static String     elementText;
    private static WebDriverWait wait = new WebDriverWait(driver, 30);


    // Reusable method for open website home page
    public static void openHomePage(String homePageTitle) {
        driver.get(AMAZON_BASE_URL);
        String actualPageTitle = driver.getTitle();
        Assert.assertEquals(homePageTitle, actualPageTitle);
    }


    // Reusable method that will search for a item
    public static void searchElement(String iteamSearchName, String expectedItemCatalogName) {
        homePage.amazon_searchbox.sendKeys(iteamSearchName);
        homePage.amazon_search_submit_btn.click();

        itemName = driver.findElement(By.partialLinkText(expectedItemCatalogName));
        String actualItemCatalogName = itemName.getText();
        Assert.assertEquals(actualItemCatalogName, expectedItemCatalogName);
    }


    // Reusable method that will take a screenshot and compare it with image from data base
    public static void checkItemDescription() throws Exception {
        wait.until(webDriver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete"));

        itemName.click();

        String actualImage = "Item_Details_Actual";
        String expectImage = "Item_Details_Expect";
        takeScreenshot(driver, actualImage);

        imageCompare(actualImage, expectImage);
    }



    public static String addToCart() {
        homePage.amazon_add_to_cart_btn.click();
        addedToCart = driver.findElement(By.cssSelector("h1#a-size-medium.a-text-bold"));
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(String.valueOf(homePage.amazon_side_panel))));
            homePage.amazon_side_panel_cart_btn.click();

            elementText = addedToCart.getText();
            return elementText;
        } catch (Exception e) {
            e.printStackTrace();
        }
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h1#a-size-medium.a-text-bold")));
        elementText = addedToCart.getText();
        return elementText;
    }
}


