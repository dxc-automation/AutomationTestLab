package com.demo.scripts.ui.degiro;

import com.demo.config.BasicTestConfig;
import com.demo.objects.LoginPage;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static com.demo.config.ReporterConfig.startTestReport;
import static com.demo.config.ReporterConfig.test;
import static com.demo.properties.Environments.*;
import static com.demo.properties.TestData.*;
import static com.demo.utilities.user_interface.ElementScreenshot.*;
import static com.demo.utilities.user_interface.ImageCompare.imageCompare;


public class UserLogin extends BasicTestConfig {

    private static LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);


    private static void report() throws Exception {
        String testName        = "<b>[WEB] Account Login</b>";
        String testCategory    = "Frontend";
        String testDescription = "The purpose of this test is to verify that the login functionality is working as expected."              +
                                 "<br><br><br>*** STEPS DESCRIPTION ***</b><br><br>"                                                       +
                                 "[1] Check that the login page can be opened and displayed with correct title.<br>"                      +
                                 "[2] Check the visualization of the login form element by image comparing based on RGB color model.<br>" +
                                 "[3] Check login with valid credentials.";

        startTestReport(testName, testDescription, testCategory);
    }



    public static void secureLoginWeb() throws Exception {
        report();
        wait = new WebDriverWait(driver, 10);
        String url = "https://" + HOST + WEB_LOGIN;
        driver.get(url);

        String pageTitle = driver.getTitle();
        boolean title = pageTitle.contains("DEGIRO");
        Assert.assertTrue(title);
        test.pass("<pre><b>[STEP 1]</b> User Login page was opened successfully</pre>");

        Thread.sleep(300);
        elementScreenshot(loginPage.login_form, "LoginForm_Actual");

        imageCompare("LoginForm_Actual", "LoginForm_Expected");

        loginPage.login_user_input.sendKeys(USER);
        loginPage.login_pass_input.sendKeys(PASS);
        loginPage.login_submit_btn.click();
        test.pass("<pre><b>[STEP 2]</b> Account login successfully");
        test.info(  "<pre>URL: <i>"      + url +  "</i><br>" +
                    "Username: <i>" + USER + "</i><br>" +
                    "Password: <i>" + PASS + "</i>");
    }
}
