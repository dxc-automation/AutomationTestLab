package com.demo.scripts.ui.takeaway;

import com.demo.config.BasicTestConfig;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static com.demo.config.ReporterConfig.startTestReport;
import static com.demo.config.ReporterConfig.test;
import static com.demo.utilities.user_interface.Assertions.checkPageTitle;
import static com.demo.utilities.user_interface.ElementScreenshot.elementScreenshot;
import static com.demo.utilities.user_interface.ImageCompare.imageCompare;


public class SearchNearestRestaurant extends BasicTestConfig {

    private static PageObjects takeawayHomePage = PageFactory.initElements(driver, PageObjects.class);


    private static void report() throws Exception {
        String testName        = "<b>[WEB] Search For Nearest Restaurant</b>";
        String testCategory    = "Frontend";
        String testDescription = "The purpose of this test is to verify that the user orders history is displayed properly."              +
                                 "<br><br><br>*** STEPS DESCRIPTION ***</b><br><br>"                                                       +
                                 "[1] Check that the login page can be opened and displayed with correct title.<br>"                      +
                                 "[2] Check the visualization of the login form element by image comparing based on RGB color model.<br>" +
                                 "[3] Check login with valid credentials.";

        startTestReport(testName, testDescription, testCategory);
    }



    public static void searchNearestRestaurant() throws Exception {
        report();
        wait = new WebDriverWait(driver, 10);

        driver.get("https://www.thuisbezorgd.nl/en/order-takeaway-8888-alpha?search");

        wait.until(ExpectedConditions.visibilityOf(takeawayHomePage.user_localization_icon));
        takeawayHomePage.user_localization_icon.click();
        test.pass("<b>[STEP 1] GPS map was opened successfully.</b>");


        wait.until(ExpectedConditions.visibilityOf(takeawayHomePage.user_localization_round_icon));
        takeawayHomePage.user_localization_round_icon.click();
        test.pass("<b>[STEP 2] GPS localization was enabled.</b>");


        wait.until(ExpectedConditions.visibilityOf(takeawayHomePage.user_localization_gps_icon));
        takeawayHomePage.user_localization_gps_icon.click();
        test.pass("<b>[STEP 3] User GPS coordinates was detected.</b>");


        wait.until(ExpectedConditions.visibilityOf(takeawayHomePage.user_localization_near_rest));
        takeawayHomePage.user_localization_near_rest.click();
        test.pass("<b>[STEP 4] Nearest restaurant is selected.</b>");
    }
}
