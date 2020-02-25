package com.demo.scripts.ui.degiro;

import com.demo.config.BasicTestConfig;
import static com.demo.config.ReporterConfig.*;
import com.demo.objects.Degiro.General;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;

import static com.demo.config.ReporterConfig.startTestReport;
import static com.demo.scripts.ui.degiro.UserLogin.secureLoginWeb;

public class UserBalance extends BasicTestConfig {

    private static General general = PageFactory.initElements(driver, General.class);


    private static void report() throws Exception {
        String testName        = "<b>[WEB] User Balance</b>";
        String testCategory    = "Frontend";
        String testDescription = "The purpose of this test is to verify that the user balance is displayed."              +
                                 "<br><br><b>*** STEPS DESCRIPTION ***</b><br><br>"                                                       +
                                 "[1] Check that the login page can be opened and displayed with correct title.<br>"                      +
                                 "[2] Check the visualization of the login form element by image comparing based on RGB color model.<br>" +
                                 "[3] Check login with valid credentials.";

        startTestReport(testName, testDescription, testCategory);
    }



    public static void getUserAmount() throws Exception {
        report();
        wait = new WebDriverWait(driver, 10);

        wait.until(ExpectedConditions.visibilityOf(general.quick_search_input));
        general.side_navigation_activity_btn.click();

        wait.until(ExpectedConditions.visibilityOf(general.account_content_available_to_spend));
        String amountToSpend = general.account_content_available_to_spend.getText();
        String total         = general.account_content_total.getText();
        test.info("Account available to trade = " + amountToSpend.substring(1));
        test.info("Account total              = " + total.substring(1));
    }
}
