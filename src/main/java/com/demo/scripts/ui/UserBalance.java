package com.demo.scripts.ui;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.demo.config.BasicTestConfig;
import static com.demo.config.ReporterConfig.*;
import com.demo.objects.General;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.demo.config.ReporterConfig.startTestReport;
import static com.demo.properties.Environments.HOST;
import static com.demo.properties.Environments.WEB_LOGIN;
import static com.demo.properties.TestData.*;
import static com.demo.properties.FilePaths.screenshots_actual_folder;
import static com.demo.utilities.user_interface.ElementScreenshot.elementScreenshot;

public class UserBalance extends BasicTestConfig {

    private static General general = PageFactory.initElements(driver, General.class);


    private static void report() throws Exception {
        String testName        = "<b>User Balance</b>";
        String testCategory    = "Frontend";
        String testDescription = "The purpose of this test is to verify that the user balance is displayed."  +
                                 "<br><br><br>*** STEPS DESCRIPTION ***</b><br><br>"                                                       +
                                 "[1] Wait until account available to trade balance is displayed.<br>"        +
                                 "[2] Check that the account summary for is displayed.<br>"                   +
                                 "[3] Get displayed available and total user balance.<br>"                    +
                                 "[4] Take a screenshot of the account summary web form.";

        startTestReport(testName, testDescription, testCategory);
    }



    public static void getUserAmount() throws Exception {
        report();
        wait = new WebDriverWait(driver, 10);

        String url = "https://" + HOST + WEB_LOGIN;
        driver.get(url);

        wait.until(ExpectedConditions.visibilityOf(general.account_content_available_to_spend));
        accountAvailableBalance = general.account_content_available_to_spend.getText();
        String total            = general.account_content_total.getText();
        test.info("<pre> Account available balance for trading is " + accountAvailableBalance.substring(1));
        test.info("<pre>Account total balance is " + total.substring(1));

        elementScreenshot(general.account_content_form, "Account_Header_Form");
        test.pass("<pre><b>ACCOUNT SUMMARY</b><br>", MediaEntityBuilder.createScreenCaptureFromPath(screenshots_actual_folder + "Account_Header_Form.png").build());

    }
}
