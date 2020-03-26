package com.demo.scripts.ui.products_page.leveraged;

import com.demo.config.BasicTestConfig;
import com.demo.objects.General;
import com.demo.objects.activity.ActivityBasic;
import com.demo.objects.products.LeveragedPage;
import com.demo.objects.products.ProductsBasic;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.demo.config.ReporterConfig.startTestReport;
import static com.demo.config.ReporterConfig.test;


public class OpenLeveragedProduct extends BasicTestConfig {

    private static General general             = PageFactory.initElements(driver, General.class);
    private static LeveragedPage leveragedPage = PageFactory.initElements(driver, LeveragedPage.class);
    private static ProductsBasic productsBasic = PageFactory.initElements(driver, ProductsBasic.class);


    private static void report() throws Exception {
        String testName        = "<b>Open leveraged product</b>";
        String testCategory    = "Frontend";
        String testDescription = "The purpose of this test is to verify that the user orders history is displayed properly."              +
                "<br><br><br>* * *  STEPS DESCRIPTION  * * *</b><br><br>"                                                       +
                                 "[1] Check that the login page can be opened and displayed with correct title.<br>"                      +
                                 "[2] Check the visualization of the login form element by image comparing based on RGB color model.<br>" +
                                 "[3] Check login with valid credentials.";

        startTestReport(testName, testDescription, testCategory);
    }



    public static void openLaveragesProduct() throws Exception {
        report();
        wait = new WebDriverWait(driver, 10);

        wait.until(ExpectedConditions.visibilityOf(productsBasic.table_row1_product));
        productsBasic.table_row1_product.click();
        test.pass("<b>[STEP 1]</b> Products page was opened successfully");


        wait.until(ExpectedConditions.visibilityOf(productsBasic.product_back_btn));
        productsBasic.product_back_btn.click();
        test.pass("<b>[STEP 2]</b> Laverages products page was opened successfully");

    }
}
