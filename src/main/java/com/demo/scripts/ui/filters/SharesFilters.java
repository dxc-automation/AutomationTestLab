package com.demo.scripts.ui.filters;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.demo.config.BasicTestConfig;
import com.demo.objects.activity.SharesPage;
import com.demo.objects.products.ProductsBasic;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Method;

import static com.demo.config.ReporterConfig.startTestReport;
import static com.demo.config.ReporterConfig.test;
import static com.demo.objects.products.ProductsBasic.*;
import static com.demo.properties.FilePaths.screenshots_actual_folder;
import static com.demo.properties.FilePaths.screenshots_failed_folder;
import static com.demo.scripts.ui.products_page.shares.OpenSharesPage.*;

public class SharesFilters extends BasicTestConfig {

    private static ProductsBasic productsBasic = PageFactory.initElements(driver, ProductsBasic.class);

    private static Method method;

    private static void report() throws Exception {
        String testName        = "<b>Check shares filters</b>";
        String testCategory    = "Frontend";
        String testDescription = "The purpose of this test is to verify that the user orders history is displayed properly."              +
                "<br><br><br>*** STEPS DESCRIPTION ***</b><br><br>"                                                       +
                "[1] Check that the login page can be opened and displayed with correct title.<br>"                      +
                "[2] Check the visualization of the login form element by image comparing based on RGB color model.<br>" +
                "[3] Check login with valid credentials.";

        startTestReport(testName, testDescription, testCategory);
    }


    public static void shraresFilters() throws Exception {
        openSharesPage();
        report();

        wait = new WebDriverWait(driver, 50);

        wait.until(ExpectedConditions.visibilityOf(productsBasic.filter_1));
        productsBasic.filter_1.click();

        wait.until(ExpectedConditions.visibilityOf(productsBasic.filter_option_2));
        filter_text_1 = productsBasic.filter_option_2.getText();
        productsBasic.filter_option_2.click();
        test.pass("<b>[STEP 1]</b> Country filter was settled to show only <u><i>" + filter_text_1 + "</i></u>");


        wait.until(ExpectedConditions.visibilityOf(productsBasic.filter_2));
        productsBasic.filter_2.click();
        wait.until(ExpectedConditions.visibilityOf(productsBasic.filter_option_2));
        filter_text_2 = productsBasic.filter_option_2.getText();
        productsBasic.filter_option_2.click();
        test.pass("<b>[STEP 2]</b> Indicates filter was settled to show only <u><i>" + filter_text_2 + "</i></u>");

        wait.until(ExpectedConditions.visibilityOf(productsBasic.page_table));
        test.pass("<b>[STEP 3]</b> Shares products table was displayed successfully");

        try {
            wait.until(ExpectedConditions.visibilityOf(productsBasic.page_table));
            test.pass("<b>[STEP 3]</b> Shares products table was displayed successfully <br>" + productsBasic.table_row1_product.getText());
        } catch (Exception e) {
            e.printStackTrace();
            String methodName = method.getName();
            test.fail("<pre><b>FAILED ON SCREEN</b><br>", MediaEntityBuilder.createScreenCaptureFromPath(screenshots_failed_folder + methodName + ".png", "<br>" + e.getLocalizedMessage()).build());
        }
    }
}

