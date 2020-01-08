package com.demo.config;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.demo.test_properties.FilePaths;
import com.demo.test_properties.TestData;
import com.demo.utilities.web_services.HttpClientConfig;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import static com.demo.config.ConsoleRunner.xmlFile;
import static com.demo.config.ReporterConfig.extent;
import static com.demo.config.ReporterConfig.test;


/**
 *                          This class contains all methods for taking screenshots,
 *                          browser initialization and generateReport generation.
 *          List:
 *   [1]    takeScreenshot  Capture screenshot and save the file with PNG extension.
 *                          Example:            takeScreenshot(driver, "FileName");
 *   [2]    browserConfig           Launch web browser. Value must be setted in testng.xml
 *   [3]    generateReport          Describes the result of a test_scripts and print result values.
 *   [4]    finishReport    Writes test_scripts information from the started reporters to
 *                          their output view.
 *   [5]    tearDown        Stop web driver and close the browser.
 */


public class BasicConfiguration {

    public static WebDriver driver;
    public static File      screenshotFile;


    static final Logger LOG = LogManager.getLogger(BasicConfiguration.class);


        public static void takeScreenshot (WebDriver driver, String name){
            screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(screenshotFile, new File(FilePaths.screenshots_actual_folder + name + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        @BeforeSuite
        public void cleanReportData() throws Exception{
            try {
            FileUtils.cleanDirectory(new File(FilePaths.report_json_folder));
            FileUtils.cleanDirectory(new File(FilePaths.screenshots_failed_folder));
            FileUtils.cleanDirectory(new File(FilePaths.screenshots_actual_folder));
            FileUtils.cleanDirectory(new File(FilePaths.screenshots_buffer_folder));
        } catch (Exception e) {
                e.printStackTrace();
            }
        }



    @Parameters({"environment"})
    @BeforeSuite
    public static void setEnvironmentConfiguration(String environment) throws Exception {
        InputStream inputStream = new FileInputStream(FilePaths.config_properties_file);
        Properties properties   = new Properties();
        properties.load(inputStream);


        // Get value of the property
        if (environment.equals("uat")) {
            TestData.RAM_HOST     = properties.getProperty("uat_ram_host");
            TestData.WALLET_HOST  = properties.getProperty("uat_wallet_host");
            TestData.TRADING_HOST = properties.getProperty("uat_trading_host");
            TestData.username     = properties.getProperty("uat_username");
            TestData.password     = properties.getProperty("uat_password");
            TestData.devIx        = properties.getProperty("devIx");

        } else if (environment.equals("qacore")) {
            TestData.RAM_HOST     = properties.getProperty("qacore_ram_host");
            TestData.WALLET_HOST  = properties.getProperty("qacore_wallet_host");
            TestData.TRADING_HOST = properties.getProperty("qacore_trading_host");
            TestData.username     = properties.getProperty("qacore_username");
            TestData.password     = properties.getProperty("qacore_password");
            TestData.devIx        = properties.getProperty("devIx");

        } else if (environment.equals("fly")) {
            TestData.FLY_HOST = properties.getProperty("fly_host");
        }
        inputStream.close();
    }


    /**
     * Used for
     * @param browser
     * @throws Exception
     */
        @Parameters({"browser"})
        @BeforeSuite
        public void browserConfig(String browser) throws Exception {
            DesiredCapabilities capability = new DesiredCapabilities();

            if (browser.equalsIgnoreCase("chrome")) {
                System.setProperty("webdriver.chrome.driver", FilePaths.chrome_driver_file);
                ChromeOptions options = new ChromeOptions();
                options.addArguments("test_scripts-type");
                options.addArguments("start-maximized");
                driver = new ChromeDriver(options);
                LOG.info("| Chrome browser launched successfully |");

            } else if (browser.equalsIgnoreCase("firefox")) {
                System.setProperty("webdriver.gecko.driver", FilePaths.firefox_driver_file);
                FirefoxProfile profile = new FirefoxProfile();
                profile.setAcceptUntrustedCertificates(true);
                profile.setAssumeUntrustedCertificateIssuer(true);

                FirefoxOptions options = new FirefoxOptions();
                options.setLogLevel(FirefoxDriverLogLevel.TRACE);
                driver = new FirefoxDriver();
                LOG.info("| Firefox browser launched successfully |");

            } else if (browser.equalsIgnoreCase("safari")) {
                capability.setCapability("browserstack.safari.driver", "3.141.59");
                capability.setCapability("browserstack.safari.enablePopups", false);
                capability.setCapability("browserstack.debug", true);
                capability.setCapability("browserstack.console", "debug");
                capability.setCapability("browserstack.networkLogs", true);

                SafariOptions sOptions = new SafariOptions();
                sOptions.setUseTechnologyPreview(true);
                SafariOptions.fromCapabilities(capability);
                capability.setCapability(SafariOptions.CAPABILITY, sOptions);
                driver = new SafariDriver();
                LOG.info("| Safari browser launched successfully |");

            } else if (browser.equalsIgnoreCase("none")) {
                System.setProperty("webdriver.chrome.driver", FilePaths.chrome_driver_file);
                ChromeOptions options = new ChromeOptions();
                options.addArguments("test_scripts-type");
                driver = new ChromeDriver(options);

                Point position = new Point(-2000, 0);
                driver.manage().window().setPosition(position);
                LOG.info("| Chrome browser minimized successfully |");
            }
        }

    /**
     * Collect the results from every test_scripts.
     */
    @AfterMethod(alwaysRun = true)
        public void generateReport(ITestResult result) throws Exception {
            String method     = result.getMethod().getMethodName();
            String fileName   = method + ".json";
            Path file         = Paths.get(FilePaths.report_json_folder + fileName);
            String methodName = String.format("%s", result.getMethod().getRealClass().getSimpleName());

        switch (result.getStatus()) {

            case ITestResult.SUCCESS:

            if (Files.exists(file)) {
                //  Print into HTML generateReport file
                test.pass("<pre>"
                        + "<br/>"
                        + "<center><b>* * * * * * * *    R E S P O N S E    * * * * * * * *</b></center>"
                        + "<br/>"
                        + "<br/>"
                        + "Response Code    : " + HttpClientConfig.responseCode
                        + "<br/>"
                        + "Response Message : " + HttpClientConfig.responseMsg
                        + "<br/>"
                        + "<br/>"
                        + HttpClientConfig.responseHeaders
                        + "<br/>"
                        + "<br/>"
                        + "<br/>"
                        + HttpClientConfig.responseBody
                        + "<br/>"
                        + "<br/>"
                        + "</pre>");
            } else {
                test.pass(MarkupHelper.createLabel("| PASSED | " + methodName, ExtentColor.GREEN));

            }
            break;

            case ITestResult.FAILURE:
                    Throwable throwable = result.getThrowable();
                    LOG.error("| FAILED | " + methodName);

                    File jsonReportFile = new File(FilePaths.report_json_folder + fileName);

                    if(jsonReportFile.exists()) {

                        // Print into HTML generateReport file
                        test.fail("<pre>"
                                + "<br/>"
                                + "<center><b>* * * * * * * *    R E S P O N S E    * * * * * * * *</b></center>"
                                + "<br />"
                                + "<br />"
                                + "Response Code  : " + HttpClientConfig.responseCode
                                + "<br />"
                                + "Error Message  : " + HttpClientConfig.responseMsg
                                + "<br />"
                                + "<br />"
                                + HttpClientConfig.responseHeaders
                                + "<br />"
                                + "<br />"
                                + "<br />"
                                + "<center><b>********  E X C E P T I O N  ********</b></center>"
                                + "<br />"
                                + throwable
                                + "<br />"
                                + "<br />"
                                + HttpClientConfig.responseBody
                                + "<br />"
                                + "</pre>");

                    } else {

                        File fileFail;
                        fileFail = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                        FileUtils.copyFile(fileFail, new File(FilePaths.screenshots_failed_folder + methodName + ".png"));

                        test.fail(throwable);
                        //test_scripts.log(Status.ERROR, "EXCEPTION" + "<br />" + result.getThrowable());
                        test.fail("FAILED ON SCREEN", MediaEntityBuilder.createScreenCaptureFromPath(FilePaths.screenshots_failed_folder + methodName + ".png").build());
                    }
        }
    }


        @AfterSuite
        public void clearXmlFiles() throws Exception {
            try {
                if (xmlFile.exists()) {
                    xmlFile.delete();
                } else {
                    System.out.println("\n testng.xml file does not exist");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        //  Stop web driver
        @AfterSuite(dependsOnMethods = "clearXmlFiles")
        public void tearDown () {
            driver.quit();
            }

        @AfterTest
        public void flushReportData() {
            extent.flush();

        }
}

