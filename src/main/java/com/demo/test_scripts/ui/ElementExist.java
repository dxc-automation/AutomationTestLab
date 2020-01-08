package com.demo.test_scripts.ui;

import com.demo.ui_mapping.HomePage;
import com.demo.config.BasicConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.PageFactory;

import static com.demo.config.ReporterConfig.*;
import static com.demo.config.ReporterConfig.startTestReport;


public class ElementExist extends BasicConfiguration {


    private static final Logger LOG = LogManager.getLogger(ElementExist.class);
    private static HomePage homePage  = PageFactory.initElements(driver, HomePage.class);


    private static void report() throws Exception {
        String testName        = "Check element";
        String testDescription = "The purpose of this test_suites is to verify that the user can log in RAM successfully.";
        String testCategory    = "UI";

        startTestReport(testName, testDescription, testCategory);
    }

    public static void checkElement() throws Exception {
        report();

        driver.get("https://www.google.com/");

        if (homePage.google_logo.getSize() != null) {
            test.pass("Element exists");
        }
    }
}
