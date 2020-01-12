package com.demo.test_scripts.ui;

import com.demo.config.BasicConfiguration;
import com.demo.ui_mapping.HomePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.demo.config.ReporterConfig.*;
import static com.demo.test_properties.UrlPaths.*;
import static com.demo.utilities.user_interface.ImageCompare.*;


public class CheckNewElement extends BasicConfiguration {

    private static final Logger LOG  = LogManager.getLogger(CheckNewElement.class);
    private static HomePage homePage = PageFactory.initElements(driver, HomePage.class);


    private static void report() throws Exception {
        String testName = "Check new element";
        String testDescription = "The purpose of this test is to verify that a element is ";
        String testCategory = "UI";

        startTestReport(testName, testDescription, testCategory);
    }


    public static void searchElement(String catalogElement) throws Exception {
        report();
        driver.get(AMAZON_BASE_URL);

        homePage.amazon_searchbox.sendKeys("iphone 11 xr");
        homePage.amazon_search_submit_btn.click();

        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("a")));

        WebElement element = driver.findElement(By.partialLinkText(catalogElement));
        String itemName = element.getText();
        test.info("Item catalog name is: " + "<i>" + itemName + "</i>");

        element.click();

        wait.until(webDriver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete"));

        String actualImage = "Item_Details_Actual";
        String expectImage = "Item_Details_Expect";
        takeScreenshot(driver, actualImage);

        imageCompare(actualImage, expectImage);

    }
}


