package com.demo.utilities.user_interface;

import com.demo.config.BasicTestConfig;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.demo.config.ReporterConfig.test;

public class WriteText extends BasicTestConfig {


    public static void typeTextWithDelay(WebElement element, String toBeTyped) {
        char[] charArr = toBeTyped.toCharArray();
        for (int i = 0; i <= charArr.length - 1; i++) {
            String productName = String.valueOf(charArr[i]);
            element.sendKeys(productName);
            try {
                Thread.sleep(200);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}