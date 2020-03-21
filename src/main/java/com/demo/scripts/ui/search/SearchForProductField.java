package com.demo.scripts.ui.search;

import com.demo.config.BasicTestConfig;
import com.demo.objects.General;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Method;

import static com.demo.config.ReporterConfig.startTestReport;
import static com.demo.properties.TestData.fileName;
import static com.demo.scripts.api.products.AllProducts.getAllProductsFromType;
import static com.demo.scripts.api.products.ProductList.*;
import static com.demo.properties.Environments.HOST;
import static com.demo.properties.Environments.WEB_MARKETS;
import static com.demo.utilities.user_interface.ElementScreenshot.elementScreenshot;
import static com.demo.utilities.user_interface.ImageCompare.imageCompare;


public class SearchForProductField extends BasicTestConfig {

    private static General general           = PageFactory.initElements(driver, General.class);


    private static void report() throws Exception {
        String testName        = "<b>[WEB] Search For A Product</b>";
        String testCategory    = "Frontend";
        String testDescription = "The purpose of this test is to verify that the user orders history is displayed properly."              +
                "<br><br><br>* * *  STEPS DESCRIPTION  * * *</b><br><br>"                                                       +
                                 "[1] Check that the login page can be opened and displayed with correct title.<br>"                      +
                                 "[2] Check the visualization of the login form element by image comparing based on RGB color model.<br>" +
                                 "[3] Check login with valid credentials.";

        startTestReport(testName, testDescription, testCategory);
    }



    public static void checkSearchForProduct(String product) throws Exception {
        report();
        wait = new WebDriverWait(driver, 10);

        getAllProductsFromType(fileName, product, false);

        wait.until(ExpectedConditions.visibilityOf(general.search_for_a_product_field));
        general.search_for_a_product_field.sendKeys(product);



    }
}
