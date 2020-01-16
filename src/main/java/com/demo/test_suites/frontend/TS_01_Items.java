package com.demo.test_suites.frontend;

import com.demo.config.BasicConfiguration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.demo.config.ReporterConfig.startTestReport;
import static com.demo.config.ReporterConfig.test;
import static com.demo.test_properties.TestData.fileName;
import static com.demo.test_scripts.ui.BasicTests.checkItemDescription;
import static com.demo.test_scripts.ui.BasicTests.openHomePage;
import static com.demo.test_scripts.ui.BasicTests.searchElement;


public class TS_01_Items extends BasicConfiguration {

    private static String homePageTitle;
    private static String itemSearchName;
    private static String expectedItemCatalogName;


    @BeforeClass
    private void report() throws Exception {
        String testName        = "Check new element";
        String testDescription = "The purpose of this test is to verify that a element is added and displayed properly.";
        String testCategory    = "UI";

        startTestReport(testName, testDescription, testCategory);
    }



    @Test
    public void loadHomePage(Method method) throws Exception {
        fileName      = method.getName() + ".json";
        homePageTitle = "Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more";

        openHomePage(homePageTitle);
        test.pass("<b>[STEP 1]</b> <br> Home page has been opened successfully.");
    }



    @Test
    public void searchItem(Method method) throws Exception {
        fileName        = method.getName() + ".json";
        itemSearchName  = "iPhone 11 XR";
        expectedItemCatalogName = "Apple iPhone XR, 64GB, Red - Fully Unlocked (Renewed)";

        searchElement(itemSearchName, expectedItemCatalogName);
        test.pass("<b>[STEP 2]</b> <br> Search item completed uccessfully.");
    }


    @Test
    public void openItemDescription(Method method) throws Exception {
        fileName = method.getName() + ".json";
        checkItemDescription();
    }
}