package com.demo.scripts.ui;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.demo.config.BasicTestConfig;
import com.demo.objects.General;
import com.demo.objects.MarketsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static com.demo.config.ReporterConfig.startTestReport;
import static com.demo.config.ReporterConfig.test;
import static com.demo.properties.FilePaths.screenshots_actual_folder;
import static com.demo.utilities.user_interface.ElementScreenshot.elementScreenshot;

public class PopularProducts extends BasicTestConfig {

    private static MarketsPage marketsPage = PageFactory.initElements(driver, MarketsPage.class);
    private static General general = PageFactory.initElements(driver, General.class);

    private static void report() throws Exception {
        String testName = "<b>Popular Products</b>";
        String testCategory = "Frontend";
        String testDescription = "The purpose of this test is to verify that the popular search element is displayed properly." +
                "<br><br><br>* * *  STEPS DESCRIPTION  * * *</b><br><br>"                                                       +
                "[1] Check that the login page can be opened and displayed with correct title.<br>" +
                "[2] Check the visualization of the login form element by image comparing based on RGB color model.<br>" +
                "[3] Check login with valid credentials.";

        startTestReport(testName, testDescription, testCategory);
    }


    public static void checkPopularProducts() throws Exception {
        report();

        wait.until(ExpectedConditions.visibilityOf(general.quick_search_input));
        general.side_navigation_market_btn.click();
        test.pass("<b>[STEP 1]</b> Markets page was opened successfully");

        Thread.sleep(500);
        String actualImage = "PopularProductsForm_Actual";
        elementScreenshot(marketsPage.markets_popular_products_form, actualImage);

        test.pass("<b>POPULAR PRODUCTS</b><br>", MediaEntityBuilder.createScreenCaptureFromPath(screenshots_actual_folder + actualImage + ".png").build());

        List<WebElement> popularProducts = marketsPage.markets_popular_products_form.findElements(By.tagName("li"));
        for (WebElement product : popularProducts) {
            test.info(product.getText().replace("B", "").replace("S", ""));
        }
    }
}