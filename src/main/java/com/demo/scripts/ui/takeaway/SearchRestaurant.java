package com.demo.scripts.ui.takeaway;

import com.demo.config.BasicTestConfig;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static com.demo.config.ReporterConfig.*;
import static com.demo.utilities.user_interface.ElementScreenshot.elementScreenshot;
import static com.demo.utilities.user_interface.ImageCompare.imageCompare;
import static com.demo.utilities.user_interface.Assertions.*;



public class SearchRestaurant extends BasicTestConfig {

    private static PageObjects takeawayHomePage = PageFactory.initElements(driver, PageObjects.class);


    private static void report() throws Exception {
        String testName        = "<b>[WEB] Search For Restaurant</b>";
        String testCategory    = "Frontend";
        String testDescription = "The purpose of this test is to verify that the user orders history is displayed properly."              +
                                 "<br><br><br>*** STEPS DESCRIPTION ***</b><br><br>"                                                       +
                                 "[1] Check that the login page can be opened and displayed with correct title.<br>"                      +
                                 "[2] Check the visualization of the login form element by image comparing based on RGB color model.<br>" +
                                 "[3] Check login with valid credentials.";

        startTestReport(testName, testDescription, testCategory);
    }



    public static void searchRestaurant(String searchValue) throws Exception {
        report();
        wait = new WebDriverWait(driver, 10);

        wait.until(ExpectedConditions.visibilityOf(takeawayHomePage.search_field));
        takeawayHomePage.search_field.sendKeys(searchValue);
        wait.until(ExpectedConditions.visibilityOf(takeawayHomePage.search_suggestion));

        String suggestionText   = takeawayHomePage.search_suggestion.getText();
        boolean checkSuggestion = suggestionText.equals(searchValue);
        Assert.assertTrue(checkSuggestion);
        test.pass("<b>[STEP 1] Correct search suggestion appear.</b>");

        takeawayHomePage.search_suggestion.click();
        wait.until(ExpectedConditions.visibilityOf(takeawayHomePage.search_results_form));
        test.pass("<b>[STEP 2] Search results web form appear.</b>");

        wait.until(ExpectedConditions.visibilityOf(takeawayHomePage.search_result_1));
        elementScreenshot(takeawayHomePage.search_results_form, "Search_Results_Actual");
        imageCompare("Search_Results_Actual", "Search_Results_Expected");
        takeawayHomePage.search_result_1.click();
        checkPageTitle("Order takeaway food in  8888");
        test.pass("<b>[STEP 3] Restaurants search page was opened successfully.");
    }
}
