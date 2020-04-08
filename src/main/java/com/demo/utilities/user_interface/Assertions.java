package com.demo.utilities.user_interface;

import com.demo.config.BasicTestConfig;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Assertions extends BasicTestConfig {


    public static boolean elementExistsAssertion(WebElement element) {
        if (element.getSize() != null) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isLoaded() {
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        });
        return false;
    }

    public static boolean checkPageTitle(String expectedPageTitle) {
        String pageTitle   = driver.getTitle();
        boolean checkTitle = pageTitle.contains(expectedPageTitle);
        return checkTitle;
    }
}
