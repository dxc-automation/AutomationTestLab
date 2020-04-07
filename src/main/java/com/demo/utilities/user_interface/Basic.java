package com.demo.utilities.user_interface;

import com.demo.config.BasicTestConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Basic extends BasicTestConfig {


    public static boolean elementExistsAssertion(WebElement element) {
        if (element.getSize() != null) {
            return true;
        } else {
            return false;
        }
    }


    public void clickElement(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        driver.findElement((By) element).click();
    }
}
