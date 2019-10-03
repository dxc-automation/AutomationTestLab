package com.setup;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *                              This class contains methods for HTML report generation.
 *          List:
 *   [1]    ExtentReports       Print information about machine.
 *   [2]    ExtentHtmlReporter  HTML file design configuration.
 *   [3]    ExtentTest          Create a new test object.
 */

public class ExtentManager {

    public static ExtentReports extent;
    public static ExtentTest test;
    private static ExtentHtmlReporter htmlReporter;

    private static String htmlPath = "/report/TestReport.html";
    private static String confPath = "/src/main/resources/extent-config.xml";
    private static String osName = System.getProperty("os.name");
    private static String osVersion = System.getProperty("os.version");
    private static String osArch = System.getProperty("os.arch");

    public static ExtentReports GetExtent() throws UnknownHostException {
        if (extent != null)
            return extent;
        extent = new ExtentReports();
        extent.attachReporter(getHtmlReporter());
        extent.attachReporter(htmlReporter);

        InetAddress localHost = InetAddress.getLocalHost();
        String hostname = localHost.getHostName();

        extent.setSystemInfo("Local Host", localHost.getHostAddress());
        extent.setSystemInfo("Host Name", hostname);
        extent.setSystemInfo("OS", osName);
        extent.setSystemInfo("OS Version", osVersion);
        extent.setSystemInfo("OS Arch", osArch);
        return extent;
    }

    private static ExtentHtmlReporter getHtmlReporter() {
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + htmlPath);
        htmlReporter.loadXMLConfig(System.getProperty("user.dir") + confPath);
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setEncoding("UTF-8");
        return htmlReporter;
    }

    public static ExtentTest createTest(String name, String description) {
        test = extent.createTest(name, description);
        return test;
    }
}
