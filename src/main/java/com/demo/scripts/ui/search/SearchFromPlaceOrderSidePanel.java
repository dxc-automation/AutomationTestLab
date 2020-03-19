package com.demo.scripts.ui.search;

import com.demo.config.BasicTestConfig;
import com.demo.objects.General;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.demo.config.ReporterConfig.startTestReport;
import static com.demo.config.ReporterConfig.test;
import static com.demo.properties.Environments.HOST;
import static com.demo.utilities.user_interface.ImageCompare.imageCompare;
import static com.demo.properties.Environments.WEB_MARKETS;
import static com.demo.utilities.user_interface.ElementScreenshot.elementScreenshot;


public class SearchFromPlaceOrderSidePanel extends BasicTestConfig {

    private static General general           = PageFactory.initElements(driver, General.class);


    private static void report() throws Exception {
        String testName        = "<b>[WEB] SearchFromPlaceOrderSidePanel Product</b>";
        String testCategory    = "Frontend";
        String testDescription = "The purpose of this test is to verify that the user orders history is displayed properly."              +
                                 "<br><br><br>*** STEPS DESCRIPTION ***</b><br><br>"                                                       +
                                 "[1] Check that the login page can be opened and displayed with correct title.<br>"                      +
                                 "[2] Check the visualization of the login form element by image comparing based on RGB color model.<br>" +
                                 "[3] Check login with valid credentials.";

        startTestReport(testName, testDescription, testCategory);
    }



    public static void placeOrderSidePanelSearchProduct(String searchProduct) throws Exception {
        report();
        wait = new WebDriverWait(driver, 10);

        String url = "https://" + HOST + WEB_MARKETS;
        driver.get(url);

        wait.until(ExpectedConditions.visibilityOf(general.palace_order_btn));
        general.palace_order_btn.click();

        wait.until(ExpectedConditions.visibilityOf(general.place_order_side_panel));
        elementScreenshot(general.place_order_side_panel, "Place_Order_Side_Panel_Actual");
        imageCompare("Place_Order_Side_Panel_Actual", "Place_Order_Side_Panel_Expected");
        general.place_order_search_field.sendKeys(searchProduct);

        wait.until(ExpectedConditions.visibilityOf(general.place_order_search_result_shares));
        elementScreenshot(general.place_order_side_panel, "Place_Order_Side_Panel_Search_Actual");
        imageCompare("Place_Order_Side_Panel_Search_Actual", "Place_Order_Side_Panel_Search_Expected");

        wait.until(ExpectedConditions.visibilityOf(general.place_order_search_result1));
        String searchResult = general.place_order_search_result1.getText();

        boolean checkResults = searchResult.contains(searchProduct);
        if (checkResults != true) {
            test.pass("<pre>Found search result from place order side panel that contains <b><u>" + searchProduct + "</b></u>");
        } else  {
            test.fail("<pre><b> No search result from place order side panel that contains <u>" + searchProduct + "</b></u>");
        }

        general.place_order_close_btn.click();

    }
}
