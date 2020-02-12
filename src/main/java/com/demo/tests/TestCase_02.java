package com.demo.tests;

import com.demo.config.BasicTestConfig;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.demo.config.ReporterConfig.startTestReport;
import static com.demo.config.ReporterConfig.test;
import static com.demo.properties.TestData.fileName;
import static com.demo.scripts.ui.amazon.Amazon.*;


public class TestCase_02 extends BasicTestConfig {

    private static String homePageTitle;
    private static String itemSearchName;
    private static String expectedItemCatalogName;


    @BeforeClass
    private void report() throws Exception {
        String testName        = "Check New Item";
        String testDescription = "The purpose of this test is to verify that a new item is added and displayed properly."    +
                "For verification are used general item properties name and expected catalog name from the search results. " +
                "Image compering for the item image that is displayed on details page.";
        String testCategory    = "UI";

        startTestReport(testName, testDescription, testCategory);
    }



    @Test
    public void loadHomePage(Method method) throws Exception {
        fileName      = method.getName() + ".json";
        homePageTitle = "AmazonHomePage.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more";

        load(homePageTitle);
        test.pass("<b>[STEP 1]</b> <br> Home page has been opened successfully.");
    }



    @Test
    public void searchItem(Method method) throws Exception {
        fileName                = method.getName() + ".json";
        itemSearchName          = "iPhone 11 XR";
        expectedItemCatalogName = "Apple iPhone XR, 64GB, Red - Fully Unlocked (Renewed)";

        searchElement(itemSearchName, expectedItemCatalogName);
        test.pass("<b>[STEP 2]</b> <br> Search item completed successfully.");
    }


    @Test
    public void openItemDescription(Method method) throws Exception {
        fileName = method.getName() + ".json";
        checkItemDescription();
        test.pass("<b>[STEP 3]</b> <br> Item details page was opened successfully.");
    }


    @Test
    public void addItemToCart(Method method) {
        fileName = method.getName() + ".json";
        addToCart();
        test.pass("<b>[STEP 4]</b> <br> Item was added to cart successfully.");
    }
}