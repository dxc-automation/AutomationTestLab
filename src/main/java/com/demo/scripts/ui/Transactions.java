package com.demo.scripts.ui;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.demo.config.BasicTestConfig;
import com.demo.objects.ActivityPage;
import com.demo.objects.General;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.demo.config.ReporterConfig.startTestReport;
import static com.demo.config.ReporterConfig.test;
import static com.demo.properties.Environments.*;
import static com.demo.properties.FilePaths.screenshots_actual_folder;
import static com.demo.utilities.user_interface.ElementScreenshot.elementScreenshot;
import static com.demo.utilities.user_interface.HandleTable.handleWebTable;


public class Transactions extends BasicTestConfig {

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
        wait = new WebDriverWait(driver, 10);

        String url = "https://" + HOST + TRANSACTIONS_PAGE;
        driver.get(url);

        wait.until(ExpectedConditions.visibilityOf(activityPage.transactions_history_table));

        WebElement table_first_row = driver.findElement(By.cssSelector("span[data-field='price']"));
        wait.until(ExpectedConditions.visibilityOf(table_first_row));
        table_first_row.click();

        wait.until(ExpectedConditions.visibilityOf(activityPage.transactions_details_side_panel));
        elementScreenshot(activityPage.transactions_details_side_panel, "Transaction_Details");
        test.pass("<b>TRANSACTION DETAILS</b><br>", MediaEntityBuilder.createScreenCaptureFromPath(screenshots_actual_folder + "Transaction_Details" + ".png").build());

        String date   = activityPage.transaction_details_date.getText();
        String action = activityPage.transaction_details_action.getText();
        String qty    = activityPage.transaction_details_qty.getText();
        String price  = activityPage.transaction_details_price.getText();
        String value  = activityPage.transaction_details_value.getText();
        String fee    = activityPage.transaction_details_fee.getText();
        String total  = activityPage.transaction_details_total.getText();
        String localValue    = activityPage.transaction_details_local_value.getText();
        String exchangeRate  = activityPage.transaction_details_exchange_rate.getText();
        String transactionId = activityPage.transactions_details_id.getText();


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
    }
}
