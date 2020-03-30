package com.demo.config;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.demo.config.BasicTestConfig.driver;
import static com.demo.properties.FilePaths.report_json_folder;
import static com.demo.properties.FilePaths.screenshots_failed_folder;
import static com.demo.properties.TestData.fileName;
import static com.demo.utilities.FileUtility.readJsonResponseFile;
import static com.demo.utilities.web_services.HttpClientConfig.*;

public class TestNGListener extends ReporterConfig implements ITestListener {


    @Override
    public void onStart(ITestContext arg0) { System.out.println("\n SUITE STARTED: [ " + arg0.getName().toUpperCase() + " ]"); }


    @Override
    public void onTestStart(ITestResult arg0) {
        System.out.println("\nTEST STARTED: [ "+arg0.getName().toUpperCase() + " ]");
    }


    @Override
    public void onTestSuccess(ITestResult arg0) {
    }


    @Override
    public void onTestFailure(ITestResult arg0) {
    }


    @Override
    public void onTestSkipped(ITestResult arg0) {
        System.out.println("\n TEST SKIPPED: [ " +arg0.getName().toUpperCase() + " ]");
    }


    @Override
    public void onFinish(ITestContext arg0) {
        System.out.println("\n TEST FINISH: [ " +arg0.getName().toUpperCase() + " ]");
    }


    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
        // TODO Auto-generated method stub

    }



}