package com.demo.objects.products;

import com.demo.config.BasicTestConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LeveragedPage extends BasicTestConfig {

    public static final Logger LOG = LogManager.getLogger(LeveragedPage.class);
    public LeveragedPage(WebDriver driver) {
        this.driver = driver;
    }

    final WebDriver driver;



}
