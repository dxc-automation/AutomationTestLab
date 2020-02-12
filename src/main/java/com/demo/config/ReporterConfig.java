package com.demo.config;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import static com.demo.properties.FilePaths.*;

import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 *                              This class contains methods for HTML generateReport generation.
 *          List:
 *   [1]    ExtentReports       Print information about machine.
 *   [2]    ExtentHtmlReporter  HTML file design configuration.
 *   [3]    ExtentTest          Create a new scripts object.
 */

public class ReporterConfig {

    public static ExtentTest    test;
    public static ExtentReports extent;
    private static ExtentHtmlReporter htmlReporter;

    private static String osName    = System.getProperty("os.name");
    private static String osVersion = System.getProperty("os.version");
    private static String osArch    = System.getProperty("os.arch");

    public static ExtentReports GetExtent() throws UnknownHostException {
        if (extent != null)
            return extent;
        extent = new ExtentReports();
        extent.attachReporter(getHtmlReporter());
        extent.attachReporter(htmlReporter);

        InetAddress localHost = InetAddress.getLocalHost();
        String hostname = localHost.getHostName();

        extent.setSystemInfo("Local Host", localHost.getHostAddress());
        extent.setSystemInfo("Host Name",  hostname);
        extent.setSystemInfo("OS",         osName);
        extent.setSystemInfo("OS Version", osVersion);
        extent.setSystemInfo("OS Arch",    osArch);
        return extent;
    }

    private static ExtentHtmlReporter getHtmlReporter() {
        htmlReporter = new ExtentHtmlReporter(report_html_file);
        htmlReporter.loadXMLConfig(report_config_xml_file);
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setEncoding("UTF-8");
        return htmlReporter;
    }


    public static void startTestReport(String testName, String testDescription, String testCategory) throws Exception {
        extent = GetExtent();
        test   = extent.createTest(
                "<b>" + testName + "</b>",
                "<pre>"
                        + "<center><b>* * * * * * * *    I N F O R M A T I O N    * * * * * * * *</b></center>"
                        + "<p align=justify>"
                        + testDescription
                        + "</p>"
                        + "</pre>");

        test.assignCategory(testCategory);
        extent.setAnalysisStrategy(AnalysisStrategy.TEST);
    }
}
