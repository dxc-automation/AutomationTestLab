package com.demo.test_suites.frontend;

import com.demo.config.BasicConfiguration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;

import static com.demo.config.ReporterConfig.startTestReport;
import static com.demo.config.ReporterConfig.test;
import static com.demo.test_properties.TestData.fileName;
import static com.demo.test_scripts.ui.CarTest.*;

public class TC_02_CarCheck extends BasicConfiguration {


    @BeforeClass
    private void report() throws Exception {
        String testName        = "Check Search Functionality";
        String testDescription = "The purpose of this test is to verify that a new item is added and displayed properly.";
        String testCategory    = "UI";

        startTestReport(testName, testDescription, testCategory);
    }


    @Test
    public void homepage(Method method) throws Exception {
        fileName = method.getName() + ".json";
        openHomePage();
        test.pass("[STEP 1] Home page was opened successfully.");
    }


    @Test
    public void search(Method method) throws IOException {
        fileName = method.getName() + ".json";
        String car_reg = "OV12UYY";
        carSearch(car_reg);
        test.pass("[STEP 2] Search for a car has been completed successfully.");
    }
}
