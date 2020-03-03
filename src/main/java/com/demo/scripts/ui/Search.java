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
import static com.demo.properties.Environments.HOST;
import static com.demo.utilities.user_interface.ImageCompare.imageCompare;
import static com.demo.properties.Environments.WEB_MARKETS;
import static com.demo.properties.FilePaths.screenshots_actual_folder;
import static com.demo.utilities.user_interface.ElementScreenshot.elementScreenshot;


public class Search extends BasicTestConfig {

    private static General general           = PageFactory.initElements(driver, General.class);
    private static ActivityPage activityPage = PageFactory.initElements(driver, ActivityPage.class);


    private static void report() throws Exception {
        String testName        = "<b>[WEB] Search Product</b>";
        String testCategory    = "Frontend";
        String testDescription = "The purpose of this test is to verify that the user orders history is displayed properly."              +
                                 "<br><br><b>*** STEPS DESCRIPTION ***</b><br><br>"                                                       +
                                 "[1] Check that the login page can be opened and displayed with correct title.<br>"                      +
                                 "[2] Check the visualization of the login form element by image comparing based on RGB color model.<br>" +
                                 "[3] Check login with valid credentials.";

        startTestReport(testName, testDescription, testCategory);
    }



    public static void checkSearchProduct(String searchProduct) throws Exception {
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
    }
}
