package com.demo.utilities.user_interface;

import com.demo.ui_mapping.HomePage;
import com.demo.config.BasicConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static com.demo.config.ReporterConfig.*;
import static com.demo.config.ReporterConfig.startTestReport;


public class GetAllPageLinks extends BasicConfiguration {


    private static final Logger LOG  = LogManager.getLogger(GetAllPageLinks.class);

    public static List<WebElement> hyperlinks;

    public static void allLinks() throws Exception {
        for (WebElement url : hyperlinks) {
            if ((url.getAttribute("href").trim().length() != 0) && (url.getText().trim().length() != 0)) {
                test.info(url.getAttribute("aria-label").trim() + "   ===>   " + url.getAttribute("href").trim());
            }
        }
    }
}

