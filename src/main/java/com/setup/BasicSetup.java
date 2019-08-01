package com.setup;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.OutputType;
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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.setup.ConsoleRunner.xmlFile;
import static com.setup.ExtentManager.extent;
import static com.setup.ExtentManager.test;
import static com.setup.HttpClientUtils.*;
import static com.setup.OkHttpClientUtils.okHttpResponseCode;
import static com.setup.OkHttpClientUtils.responseOkClientHeaders;


/**
 *                          This class contains all methods for taking screenshots,
 *                          browser initialization and report generation.
 *          List:
 *   [1]    takeScreenshot  Capture screenshot and save the file with PNG extension.
 *                          Example:            takeScreenshot(driver, "FileName");
 *   [2]    setup           Launch web browser. Value must be setted in testng.xml
 *   [3]    report          Describes the result of a test and print result values.
 *   [4]    finishReport    Writes test information from the started reporters to
 *                          their output view.
 *   [5]    tearDown        Stop web driver and close the browser.
 */


public class BasicSetup {

    public static WebDriver driver;
    public static String pathFail;
    public static String pathPass;
    public static String methodName;
    public static String response;
    public static JSONParser parser;
    private static String headers;
    public static int responseCode;
    public static File screenshotFile;
    public static File filePath = new File(System.getProperty("user.dir")).getParentFile();

    static final Logger LOG = LogManager.getLogger(BasicSetup.class);


        public void takeScreenshot (WebDriver driver, String name){
            screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(screenshotFile, new File(filePath + "/" + "Screenshots/Actual/" + name + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @BeforeSuite
        public void cleanTestData() throws Exception{

            FileUtils.cleanDirectory(new File(filePath + "/report/JSON"));
            FileUtils.cleanDirectory(new File(filePath + "/Screenshots/Failed"));
        }



        //  Launch web browser
        @Parameters({"browser"})
        @BeforeSuite
        public void setup(String browser) throws Exception {
            String pathChrome = filePath + "/" + "src/main/resources/drivers/chromedriver";
            String pathFirefox = filePath + "/" + "src/main/resources/drivers/geckodriver.exe";
            String pathSafari = filePath + "/" + "src/main/resources/drivers/safaridriver";

            DesiredCapabilities capability = new DesiredCapabilities();

            if (browser.equalsIgnoreCase("chrome")) {
                System.setProperty("webdriver.chrome.driver", pathChrome);
                ChromeOptions options = new ChromeOptions();
                options.addArguments("test-type");
                options.addArguments("start-maximized");
                driver = new ChromeDriver(options);
                LOG.info("| Chrome browser launched successfully |");

            } else if (browser.equalsIgnoreCase("firefox")) {
                System.setProperty("webdriver.gecko.driver", pathFirefox);
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
            }
        }

    /**
     * Collect the results from every test.
     */
    @AfterMethod(alwaysRun = true)
        public void report(ITestResult result) throws Exception {

            pathFail = filePath + "/" + "Screenshots/Failed/";
            pathPass = filePath + "/" + "Screenshots/Passed/";

            String method = result.getMethod().getMethodName();
            String fileName = method + ".json";
            Path file = Paths.get(filePath + "/" + "report/JSON/" + fileName);

            methodName = String.format("%s  [%s]", result.getMethod().getRealClass().getSimpleName(), result.getMethod().getMethodName());

            switch (result.getStatus()) {

                    case ITestResult.SUCCESS:
                        LOG.info("| PASSED | " + methodName);

                        // Check for file that will be created if input stream in not null
                        if (Files.exists(file)) {

                            // Get the response headers
                            if (httpResponseHeaders != null) {
                                try {
                                    headers = httpResponseHeaders.toString().replaceAll("; ", ";\n").replaceAll("Path=/;", "").replaceAll("Secure,", "");
                                    } catch (Exception e) {
                                    e.printStackTrace();
                                    }
                                } else {
                                headers = responseOkClientHeaders.replaceAll("Domain=.uat.pyr;", "");
                            }


                            // Get the response code
                            if (httpResponseCode >= 10) {
                                try {
                                    responseCode = httpResponseCode;
                                    } catch (Exception e) {
                                    e.printStackTrace();
                                    }
                                } else {
                                    responseCode = okHttpResponseCode;
                            }


                            // Parse the data from json file
                            parser = new JSONParser();
                            Object object = parser.parse(new FileReader(filePath + "/" + "report/JSON/" + fileName));
                            Gson gson = new GsonBuilder().setPrettyPrinting().create();
                            response = gson.toJson(object);


                            //  Print into HTML report file
                            test.pass("<pre>"
                                        + "[ RESPONSE ]"
                                        + "<br />"
                                        + "<br />"
                                        + "Response Code  : " + responseCode
                                        + "<br />"
                                        + "Message : " + responseMsg
                                        + "<br />"
                                        + "<br />"
                                        + headers.replace("[", "").replace("]", "")
                                        + "<br />"
                                        + "<br />"
                                        + "<br />"
                                        + response
                                        + "<br />"
                                        + "<br />"
                                        + "</pre>");
                        } else {
                            test.pass(methodName);
                        }
                        break;


                    case ITestResult.FAILURE:
                        Throwable throwable = result.getThrowable();
                        LOG.error("| FAILED | " + methodName);


                        // Check for file that will be created if input stream in not null
                        if (Files.exists(file) && throwable != null) {


                            // Get the response headers
                                try {
                                    headers = httpResponseHeaders.toString().replace("; ", ";\n").replaceAll("Path=/;", "").replaceAll("Secure,", "");
                                    } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                try {
                                    headers = responseOkClientHeaders;
                            } catch (Exception e) {
                                    e.printStackTrace();
                                }


                            // Get the response code
                            if (httpResponseCode >= 10) {
                                try {
                                    responseCode = httpResponseCode;
                                    } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else {
                                responseCode = okHttpResponseCode;
                            }


                            // Parse the data from json file
                            parser = new JSONParser();
                            Object object = parser.parse(new FileReader(filePath + "/" + "report/JSON/" + fileName));
                            Gson gson = new GsonBuilder().setPrettyPrinting().create();
                            response = gson.toJson(object);


                            // Print into HTML report file
                            test.fail("<pre>"
                                        + "[ RESPONSE ]"
                                        + "<br />"
                                        + "<br />"
                                        + "Response Code  : " + responseCode
                                        + "<br />"
                                        + "Error Message : " + responseMsg
                                        + "<br />"
                                        + "<br />"
                                        + headers.replace("[", "").replace("]", "")
                                        + "<br />"
                                        + "<br />"
                                        + "<br />"
                                        + "***** E X C E P T I O N ***** "
                                        + "<br />"
                                        + throwable
                                        + "<br />"
                                        + "<br />"
                                        + "<br />"
                                        + response
                                        + "<br />"
                                        + "<br />"
                                        + "</pre>");
                        } else {
                            File fileFail;
                            fileFail = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                            FileUtils.copyFile(fileFail, new File(pathFail + methodName + ".png"));

                            test.fail(methodName);
                            test.fail(throwable);
                            //test.log(Status.ERROR, "EXCEPTION" + "<br />" + result.getThrowable());
                            test.fail("FAILED ON SCREEN", MediaEntityBuilder.createScreenCaptureFromPath(pathFail + methodName + ".png").build());
                        }
                            break;

                    case ITestResult.SKIP:
                        test.skip(methodName);
                        break;

                    default:
                        String msg = "Unexpected test status: " + result.getStatus();
                        test.error(msg);
                        System.out.println("ERROR:" + msg);
                        throw new RuntimeException(msg);
                }
            }


        @AfterSuite
        public void clearXml() throws Exception {
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
        @AfterSuite(dependsOnMethods = "clearXml")
        public void tearDown () {
            driver.quit();
            extent.flush();
            }
        }

