package com.demo.scripts.ui.search;

import com.demo.config.BasicTestConfig;
import com.demo.objects.General;
import com.demo.objects.products.ProductsBasic;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.demo.config.ReporterConfig.startTestReport;
import static com.demo.config.ReporterConfig.test;
import static com.demo.properties.TestData.productName;


public class SearchForProductField extends BasicTestConfig {

    private static General general           = PageFactory.initElements(driver, General.class);
    private static ProductsBasic productsBasic = PageFactory.initElements(driver, ProductsBasic.class);


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



    public static void checkSearchForProduct() throws Exception {
        report();
        wait = new WebDriverWait(driver, 20);

        wait.until(ExpectedConditions.visibilityOf(general.search_for_a_product_field));
        int i = productName.indexOf(' ');
        String productPrefix = productName.substring(0, i);
        general.search_for_a_product_field.sendKeys(productPrefix);


        wait.until(ExpectedConditions.visibilityOf(productsBasic.page_table));
        String rowProduct = productsBasic.table_row1_product.getText();

        try {
            if (rowProduct.contains(productName) != true) {
                test.pass("<pre><b>[STEP 1]</b> Product search completed<br>" +
                          "Product <i><u>" + productName + "</i></u> is found");
            } else {
                test.fail("<pre><b> Product was not found in search results</b></pre>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
