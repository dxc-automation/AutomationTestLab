package com.demo.scripts.ui.takeaway;

import com.demo.config.BasicTestConfig;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import static com.demo.config.ReporterConfig.*;
import static com.demo.utilities.user_interface.ImageCompare.imageCompare;
import static com.demo.utilities.user_interface.Assertions.*;

public class OpenHomePage extends BasicTestConfig {

    private static PageObjects takeawayHomePage = PageFactory.initElements(driver, PageObjects.class);

    private static void report() throws Exception {
        String testName        = "<b>[WEB] Open Home Page</b>";
        String testCategory    = "Frontend";
        String testDescription = "The purpose of this test is to verify that the user orders history is displayed properly."              +
                                 "<br><br><br>*** STEPS DESCRIPTION ***</b><br><br>"                                                      +
                                 "[1] Check that the login page can be opened and displayed with correct title.<br>"                     +
                                 "[2] Check the visualization of the login form element by image comparing based on RGB color model.<br>" +
                                 "[3] Check login with valid credentials.";

        startTestReport(testName, testDescription, testCategory);
    }


    public static void openHomePage() throws Exception {
            report();
            wait = new WebDriverWait(driver, 10);

            String url = "https://www.thuisbezorgd.nl/en/";
            driver.get(url);

            wait.until(ExpectedConditions.visibilityOf(takeawayHomePage.search_field));
            test.pass("<b>[STEP 1] URL <u>" + url + "</u> was opened successfully.</b>");

            checkPageTitle("Thuisbezorgd");
            test.pass("<b>[STEP 2] The title of the web page is correct.</b>");

            takeScreenshot(driver, "Takeaway_HomePage_Actual");
            imageCompare("Takeaway_HomePage_Actual", "Takeaway_HomePage_Expected");
    }
}
