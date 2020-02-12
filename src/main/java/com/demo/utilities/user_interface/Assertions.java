package com.demo.utilities.user_interface;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import static com.demo.config.BasicTestConfig.wait;

public class Assertions {


    public static boolean elementExistsAssertion(WebElement element) {
        if (element.getSize() != null) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isLoaded() throws Exception {
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        });
        return false;
    }
}
