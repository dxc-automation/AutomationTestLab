package com.demo.scripts.ui.search;

import com.demo.config.BasicTestConfig;
import com.demo.objects.General;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.demo.config.ReporterConfig.startTestReport;
import static com.demo.config.ReporterConfig.test;
import static com.demo.properties.Environments.HOST;
import static com.demo.properties.Environments.PRODUCTS_PAGE;
import static com.demo.properties.Environments.WEB_MARKETS;
import static com.demo.utilities.user_interface.ElementScreenshot.elementScreenshot;
import static com.demo.utilities.user_interface.ImageCompare.imageCompare;


public class SearchForProduct extends BasicTestConfig {

    private static General general           = PageFactory.initElements(driver, General.class);


    private static void report() throws Exception {
        String testName        = "<b>[WEB] Search For A Product</b>";
        String testCategory    = "Frontend";
        String testDescription = "The purpose of this test is to verify that the user orders history is displayed properly."              +
                                 "<br><br><br>*** STEPS DESCRIPTION ***</b><br><br>"                                                       +
                                 "[1] Check that the login page can be opened and displayed with correct title.<br>"                      +
                                 "[2] Check the visualization of the login form element by image comparing based on RGB color model.<br>" +
                                 "[3] Check login with valid credentials.";

        startTestReport(testName, testDescription, testCategory);
    }



    public static void checkSearchForProduct(int searchProduct) throws Exception {
        report();
        wait = new WebDriverWait(driver, 10);

        String url = "https://" + HOST + PRODUCTS_PAGE + searchProduct;
        driver.get(url);



    }
}
