package com.demo.config;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.demo.properties.TestData;
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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import static com.demo.config.ConsoleRunner.xmlFile;
import static com.demo.config.ReporterConfig.*;
import static com.demo.properties.FilePaths.*;
import static com.demo.properties.Environments.*;
import static com.demo.properties.TestData.*;
import static com.demo.utilities.FileUtility.*;

import static com.demo.utilities.web_services.HttpClientConfig.*;
import static org.apache.commons.io.FileUtils.cleanDirectory;


/**
 *                          This class contains all methods for taking screenshots,
 *                          browser initialization and generateReport generation.
 *          List:
 *   [1]    takeScreenshot  Capture screenshot and save the file with PNG extension.
 *                          Example:            takeScreenshot(driver, "FileName");
 *   [2]    browserConfig           Launch web browser. Value must be setted in testng.xml
 *   [3]    generateReport          Describes the result of a scripts and print result values.
 *   [4]    finishReport    Writes scripts information from the started reporters to
 *                          their output view.
 *   [5]    tearDown        Stop web driver and close the browser.
 */


public class BasicTestConfig {

    public static WebDriver     driver;
    public static WebDriverWait wait;
    public static File          screenshotFile;


    static final Logger LOG = LogManager.getLogger(BasicTestConfig.class);

    /**
     * Used for screenshot generating
     * @param driver, name
     * @throws Exception
     */
        public static void takeScreenshot (WebDriver driver, String name){
            screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(screenshotFile, new File(screenshots_actual_folder + name + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    /**
     * Delete all reporting files from previous tests
     */
        @BeforeSuite
        public void cleanReportData() {
            File reportJsonDir    = new File(report_json_folder);
            File reportFailedDir  = new File(screenshots_failed_folder);
            File reportActual     = new File(screenshots_actual_folder);
            File reportBuffer     = new File(screenshots_actual_folder);
            File reportVideos     = new File(video_files);

            try {
                if (!reportJsonDir.exists() && reportFailedDir.exists()) {
                    reportJsonDir.mkdir();
                    reportFailedDir.mkdir();
                } else {
                    cleanDirectory(new File(report_json_folder));
                    cleanDirectory(new File(screenshots_failed_folder));
                } if (!reportActual.exists() && reportBuffer.exists()) {
                    reportFailedDir.mkdir();
                    reportActual.mkdir();
                } else {
                    cleanDirectory(new File(screenshots_failed_folder));
                    cleanDirectory(new File(screenshots_failed_folder));
                } if (!reportVideos.exists()) {
                    reportVideos.mkdir();
                } else {
                    cleanDirectory(new File(video_files));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    /**
     * Used for get test data from /resources/config.properties file
     * @throws Exception
     */
    @BeforeSuite
    public static void setEnvironmentConfiguration() throws Exception {
        InputStream inputStream = new FileInputStream(config_properties_file);
        Properties properties   = new Properties();
        properties.load(inputStream);

        AMAZON_BASE_URL = properties.getProperty("amazon_url");
        FLY_HOST        = properties.getProperty("fly_host");
        NEWS_BASIC_URL  = properties.getProperty("news_url");
        inputStream.close();
    }




    @Parameters({"environment"})
    @BeforeTest
    public void setEnvironment(String environment) {
        TestData.env = environment;
        if (TestData.env.equalsIgnoreCase("internal")) {
            HOST = "internal.degiro.eu";
            USER = "dgtraderie";
            PASS = "Test_web2020";
        } else if (TestData.env.equalsIgnoreCase("web-trader")) {
            HOST = "test-webtrader.internal.degiro.eu";
            USER = "web2879nl";
            PASS = "Test600";
        } else if (TestData.env.equalsIgnoreCase("production")) {
            HOST = "trader.degiro.nl";
            USER = "dgtraderie";
            PASS = "Test_web2020";
        }
    }


    /**
     * Used for browser configuration
     * @param browser
     * @throws Exception
     */
        @Parameters({"browser"})
        @BeforeTest
        public void browserConfig(String browser) throws Exception {
            DesiredCapabilities capability = new DesiredCapabilities();

            if (browser.equalsIgnoreCase("chrome")) {
                System.setProperty("webdriver.chrome.driver", chrome_driver_file);
                ChromeOptions options = new ChromeOptions();
                options.addArguments("scripts-type");
                options.addArguments("start-maximized");
                options.addArguments("--incognito");
                driver = new ChromeDriver(options);
                LOG.info("| Chrome browser launched successfully |");

            } else if (browser.equalsIgnoreCase("firefox")) {
                System.setProperty("webdriver.gecko.driver", firefox_driver_file);
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
                System.setProperty("webdriver.chrome.driver", chrome_driver_file);
                ChromeOptions options = new ChromeOptions();
                options.addArguments("scripts-type");
                driver = new ChromeDriver(options);

                Point position = new Point(-2000, 0);
                driver.manage().window().setPosition(position);
                LOG.info("| Chrome browser minimized successfully |");
            }
        }

    /**
     * Collect the results from every test
     */
    @AfterMethod(alwaysRun = true)
        public void generateReport(ITestResult result) throws Exception {
            String method     = result.getMethod().getMethodName();
            String fileName   = method + ".json";
            Path file         = Paths.get(report_json_folder + fileName);
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
                        + "Response Code    : " + responseCode
                        + "<br/>"
                        + "Response Message : " + responseMsg
                        + "<br/>"
                        + "<br/>"
                        + responseHeaders
                        + "<br/>"
                        + "<br/>"
                        + "<br/>"
                        + readJsonResponseFile()
                        + "<br/>"
                        + "<br/>"
                        + "</pre>");
            } else if (Files.exists(file) == false){
                System.out.println("TEST PASSED");
            }
            break;

            case ITestResult.FAILURE:
                    Throwable throwable = result.getThrowable();
                    LOG.error("| FAILED | " + methodName);

                    File jsonReportFile = new File(report_json_folder + fileName);

                    if(jsonReportFile.exists()) {

                        // Print into HTML generateReport file
                        test.fail("<pre>"
                                + "<br/>"
                                + "<center><b>* * * * * * * *    R E S P O N S E    * * * * * * * *</b></center>"
                                + "<br />"
                                + "<br />"
                                + "Response Code  : " + responseCode
                                + "<br />"
                                + "Error Message  : " + responseMsg
                                + "<br />"
                                + "<br />"
                                + responseHeaders
                                + "<br />"
                                + "<br />"
                                + "<br />"
                                + "<center><b>********  E X C E P T I O N  ********</b></center>"
                                + "<br />"
                                + throwable
                                + "<br />"
                                + "<br />"
                                + readJsonResponseFile()
                                + "<br />"
                                + "</pre>");

                    } else {
                        File fileFail;
                        fileFail = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                        FileUtils.copyFile(fileFail, new File(screenshots_failed_folder + methodName + ".png"));

                        test.fail("<b>FAILED ON SCREEN</b>", MediaEntityBuilder.createScreenCaptureFromPath(screenshots_failed_folder + methodName + ".png").build());
                        test.fail(throwable);
                    }
        }
    }


    /**
     * Delete all previous executed XML files
     */
        @AfterSuite
        public void clearXmlFiles() {
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
        @AfterTest
        public void tearDown () {
            driver.close();
            }

        @AfterSuite(alwaysRun = true)
        public void flushReportData() {
            extent.flush();
            driver.quit();
        }
}

