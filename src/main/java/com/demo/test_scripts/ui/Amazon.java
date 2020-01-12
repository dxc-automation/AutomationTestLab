package com.demo.test_scripts.ui;

import com.demo.config.BasicConfiguration;
import com.demo.ui_mapping.HomePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.demo.config.ReporterConfig.startTestReport;
import static com.demo.config.ReporterConfig.test;


public class Amazon extends BasicConfiguration {


    private static final Logger LOG  = LogManager.getLogger(Amazon.class);

    private static HomePage homePage = PageFactory.initElements(driver, HomePage.class);


    private static void report() throws Exception {
        String testName = "All hyperlinks";
        String testDescription = "The purpose of this test_suites is to verify that the user can log in RAM successfully.";
        String testCategory = "UI";

        startTestReport(testName, testDescription, testCategory);
    }

    public static void allLinks() throws Exception {
        report();
        driver.get("https://www.google.com/");

        homePage.google_search_input.sendKeys("Automation Test");
        homePage.google_search_btn.click();

        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("a")));

        for (WebElement url : homePage.hyperlinks) {
            if ((url.getAttribute("href").trim().length() != 0) && (url.getText().trim().length() != 0)) {
                test.info(url.getAttribute("aria-label").trim() + "   ===>   " + url.getAttribute("href").trim());
            }
        }
    }
}

