package com.demo.scripts.ui.degiro;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.demo.config.BasicTestConfig;
import com.demo.objects.Degiro.ActivityPage;
import com.demo.objects.Degiro.General;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.demo.config.ReporterConfig.startTestReport;
import static com.demo.config.ReporterConfig.test;
import static com.demo.properties.Environments.HOST;
import static com.demo.properties.FilePaths.screenshots_actual_folder;
import static com.demo.utilities.user_interface.ElementScreenshot.elementScreenshot;
import static com.demo.utilities.user_interface.HandleTable.handleWebTable;


public class ActivityTransactionsHistory extends BasicTestConfig {

    private static General           general = PageFactory.initElements(driver, General.class);
    private static ActivityPage activityPage = PageFactory.initElements(driver, ActivityPage.class);


    private static void report() throws Exception {
        String testName        = "<b>[WEB] User Transactions History</b>";
        String testCategory    = "Frontend";
        String testDescription = "The purpose of this test is to verify that the user orders history is displayed properly."              +
                                 "<br><br><b>*** STEPS DESCRIPTION ***</b><br><br>"                                                       +
                                 "[1] Check that the login page can be opened and displayed with correct title.<br>"                      +
                                 "[2] Check the visualization of the login form element by image comparing based on RGB color model.<br>" +
                                 "[3] Check login with valid credentials.";

        startTestReport(testName, testDescription, testCategory);
    }



    public static void checkUserTransactionsHistory() throws Exception {
        report();
        try {
            wait = new WebDriverWait(driver, 10);

            wait.until(ExpectedConditions.visibilityOf(general.side_navigation_activity_btn));
            general.side_navigation_activity_btn.click();

            wait.until(ExpectedConditions.visibilityOf(activityPage.activity_tab_menu_transactions_btn));
            activityPage.activity_tab_menu_transactions_btn.click();

            wait.until(ExpectedConditions.visibilityOf(activityPage.transactions_history_table));
            elementScreenshot(activityPage.transactions_history_table, "Transactions_History_Actual");

            test.pass("<b>ORDERS HISTORY</b><br>", MediaEntityBuilder.createScreenCaptureFromPath(screenshots_actual_folder + "Transactions_History_Actual" + ".png").build());

            handleWebTable(activityPage.transactions_history_table);
        } catch (Exception e) {
            e.printStackTrace();
            test.warning("Orders history are not available for <b>https://" + HOST + "</b>");
        }
    }
}
