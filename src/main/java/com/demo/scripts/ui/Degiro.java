package com.demo.scripts.ui;

import com.demo.config.BasicTestConfig;
import com.demo.objects.Degiro.LoginPage;
import com.demo.objects.Degiro.LoginPage.*;
import com.jayway.jsonpath.JsonPath;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static com.demo.config.ReporterConfig.startTestReport;
import static com.demo.config.ReporterConfig.test;
import static com.demo.properties.Environments.*;
import static com.demo.properties.TestData.*;
import static com.demo.utilities.user_interface.ElementScreenshot.*;

public class Degiro extends BasicTestConfig {



    static final Logger LOG = LogManager.getLogger(Degiro.class);

    private static void report() throws Exception {
        String testName = "Login WEB";
        String testDescription = "The purpose of this test is to verify that the authentication functionality" +
                "is working as expected. POST method is used to simulate user login from mobile device " +
                "and verify that the server will return response with JSON body. Body should containing " +
                "valid accessToken, hash session ID (for current user activity log) and all available IDs.";
        String testCategory = "API";

        startTestReport(testName, testDescription, testCategory);
    }


    public static void secureLoginWeb() throws Exception {
        report();
        wait = new WebDriverWait(driver, 10);

        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);

        String url = "https://" + INTERNAL_HOST + WEB_LOGIN;
        driver.get(url);
        test.pass("<b>[STEP 1]</b> Login page was opened successfully");

        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(String.valueOf(loginPage.login_form))));
            String formImg = "LoginForm";
            elementScreenshot(loginPage.login_form, formImg);
        } catch (Exception e) {
            e.printStackTrace();
        }

        loginPage.login_user_input.sendKeys(INTERNAL_USER);
        loginPage.login_pass_input.sendKeys(INTERNAL_PASS);
        loginPage.login_submit_btn.click();
        test.pass("<b>[STEP 2]</b> Account login successfully");

    }
}