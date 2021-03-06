package com.demo.scripts.ui;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.demo.config.BasicTestConfig;
import com.demo.objects.activity.ActivityBasic;
import com.demo.objects.General;
import com.demo.objects.activity.TransactionsPage;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.demo.config.ReporterConfig.startTestReport;
import static com.demo.config.ReporterConfig.test;
import static com.demo.properties.Environments.*;
import static com.demo.properties.FilePaths.screenshots_actual_folder;
import static com.demo.utilities.user_interface.ElementScreenshot.elementScreenshot;


public class Transactions extends BasicTestConfig {

    private static TransactionsPage transactionsPage = PageFactory.initElements(driver, TransactionsPage.class);
    private static General general           = PageFactory.initElements(driver, General.class);



    private static void report() throws Exception {
        String testName = "<b>Transactions History</b>";
        String testCategory = "Frontend";
        String testDescription = "The purpose of this test is to verify that the user orders history is displayed properly." +
                "<br><br><br>* * *  STEPS DESCRIPTION  * * *</b><br><br>" +
                "[1] Check that the login page can be opened and displayed with correct title.<br>" +
                "[2] Check the visualization of the login form element by image comparing based on RGB color model.<br>" +
                "[3] Check login with valid credentials.";

        startTestReport(testName, testDescription, testCategory);
    }



    public static void checkUserTransactionsHistory() throws Exception {
        report();
        wait = new WebDriverWait(driver, 10);

        driver.get("https://" + HOST + TRANSACTIONS_PAGE);

        wait.until(ExpectedConditions.visibilityOf(transactionsPage.history_table));
        elementScreenshot(transactionsPage.history_table, "Transaction_Table");
        test.pass("<b>TRANSACTION TABLE</b><br>", MediaEntityBuilder.createScreenCaptureFromPath(screenshots_actual_folder + "Transaction_Table" + ".png").build());

        transactionsPage.history_table_details_row1_info_btn.click();
        wait.until(ExpectedConditions.visibilityOf(general.side_information_panel));
        elementScreenshot(general.side_information_panel, "Transaction_Details");

        String transactionId = transactionsPage.info_panel_details_id.getText();

        try {
            String date = transactionsPage.transaction_details_date.getText();
            String action = transactionsPage.transaction_details_action.getText();
            String qty = transactionsPage.transaction_details_qty.getText();
            String price = transactionsPage.transaction_details_price.getText();
            String value = transactionsPage.transaction_details_value.getText();
            String fee = transactionsPage.transaction_details_fee.getText();
            String total = transactionsPage.transaction_details_total.getText();
            String localValue = transactionsPage.transaction_details_local_value.getText();
            String exchangeRate = transactionsPage.transaction_details_exchange_rate.getText();


        test.pass("<pre>"
                + "<br>"
                + "<center><b>* * * * * * * *    TRANSACTION  DETAILS    * * * * * * * *</b></center>"
                + "<br>"
                + "<br>"
                + "Date: "           + date
                + "<br>"
                + "Transaction ID: " + transactionId
                + "<br>"
                + "Action: "         + action
                + "<br>"
                + "Qty: "            + qty
                + "<br>"
                + "Price: "          + price
                + "<br>"
                + "Local Value: "    + localValue
                + "<br>"
                + "Value: "          + value
                + "<br>"
                + "Exchange Rate: "  + exchangeRate
                + "<br>"
                + "Fee: "            + fee
                + "<br>"
                + "Total: "          + total
                + "<br>"
                + "<br>"
                + "</pre>");

        } catch (Exception e) {
            e.printStackTrace();
            test.warning(e);
        }
    }
}
