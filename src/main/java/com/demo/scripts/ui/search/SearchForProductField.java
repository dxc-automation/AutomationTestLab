package com.demo.scripts.ui.search;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.demo.config.BasicTestConfig;
import com.demo.objects.General;
import com.demo.objects.products.ProductsBasic;
import com.demo.utilities.user_interface.Basic;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.demo.config.ReporterConfig.startTestReport;
import static com.demo.config.ReporterConfig.test;
import static com.demo.properties.FilePaths.screenshots_actual_folder;
import static com.demo.properties.FilePaths.screenshots_failed_folder;
import static com.demo.properties.TestData.productName;


public class SearchForProductField extends Basic {

    private static General general           = PageFactory.initElements(driver, General.class);
    private static ProductsBasic productsBasic = PageFactory.initElements(driver, ProductsBasic.class);

    private static String product;
    private static String productSearch;
    private static String rowProduct;

    private static By table_row_1_product = By.xpath("//table/tbody/tr[1]/td/div/a");



    private static void report() throws Exception {
        String testName        = "<b>Search For A Product</b>";
        String testCategory    = "Frontend";
        String testDescription = "The purpose of this test is to verify that the user orders history is displayed properly."              +
                "<br><br><br>* * *  STEPS DESCRIPTION  * * *</b><br><br>"                                                       +
                                 "[1] Check that the login page can be opened and displayed with correct title.<br>"                      +
                                 "[2] Check the visualization of the login form element by image comparing based on RGB color model.<br>" +
                                 "[3] Check login with valid credentials.";

        startTestReport(testName, testDescription, testCategory);
    }



    public static void checkSearchForProductFromResponse() throws Exception {
        report();
        wait = new WebDriverWait(driver, 20);

        try {
            wait.until(ExpectedConditions.visibilityOf(general.search_for_a_product_field));
            int i = productName.indexOf(' ');
            String productPrefix = productName.substring(0, i);
            general.search_for_a_product_field.sendKeys(productPrefix);


            wait.until(ExpectedConditions.visibilityOf(productsBasic.table_row1_product));
            String rowProduct = productsBasic.table_row1_product.getText();

            try {
                if (rowProduct.contains(productName) == true) {
                    test.pass("<pre><b>[STEP 1]</b> Product search completed<br> Product <i><u>" + productName + "</i></u> is found");
                    takeScreenshot(driver, "Search_Results");
                    test.pass("<b>SEARCH RESULTS</b><br> First product table is: <u>" + productsBasic.table_row1_product.getText() + "</u>", MediaEntityBuilder.createScreenCaptureFromPath(screenshots_actual_folder + "Search_Results.png").build());
                } else {
                    test.fail("<pre><b> Product was not found in search results</b></pre>");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            test.fail("<pre>" + e + "<br><br> Product <u>" + productName + "</pre>");
            e.printStackTrace();
        }
    }



    public static String getItemText(By item) {
        //wait.until(presenceOfElementLocated(item));
        return driver.findElement(item).getText();
    }




    public static void checkSearchForProductFromTable() throws Exception {
        report();
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
        wait = new WebDriverWait(driver, 20);

        wait.until(ExpectedConditions.visibilityOfElementLocated(table_row_1_product));
        String ss = driver.findElement(table_row_1_product).getAttribute("title");
        System.out.println(ss);
        product = getItemText(table_row_1_product);
        String[] productPrefix = product.split(" ", 0);
        productSearch = productPrefix[0];
        general.search_for_a_product_field.sendKeys(productSearch);

        wait.until(ExpectedConditions.visibilityOfElementLocated(table_row_1_product));
        rowProduct = getItemText(table_row_1_product);

        Assert.assertTrue(rowProduct.contains(productSearch));
        takeScreenshot(driver, "Search_Results");
        test.pass("<pre><b>*** SEARCH RESULTS ***</b><br> First product table is: <u>" + rowProduct + "</u><br> Search for keyword: <u>" + productSearch + "</u><br><br><center>", MediaEntityBuilder.createScreenCaptureFromPath(screenshots_actual_folder + "Search_Results.png").build());
        }
    }
