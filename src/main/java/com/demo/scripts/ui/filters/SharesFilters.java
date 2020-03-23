package com.demo.scripts.ui.filters;

import com.demo.config.BasicTestConfig;
import com.demo.objects.activity.SharesPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.demo.config.ReporterConfig.startTestReport;
import static com.demo.properties.Environments.HOST;
import static com.demo.properties.Environments.SHARES_PAGE;
import static com.demo.properties.Environments.WEB_LOGIN;


public class SharesFilters extends BasicTestConfig {

    private static SharesPage sharesPage = PageFactory.initElements(driver, SharesPage.class);

    private static void report() throws Exception {
        String testName        = "<b>[WEB] Shares</b>";
        String testCategory    = "Frontend";
        String testDescription = "The purpose of this test is to verify that the user orders history is displayed properly."              +
                "<br><br><br>*** STEPS DESCRIPTION ***</b><br><br>"                                                       +
                "[1] Check that the login page can be opened and displayed with correct title.<br>"                      +
                "[2] Check the visualization of the login form element by image comparing based on RGB color model.<br>" +
                "[3] Check login with valid credentials.";

        startTestReport(testName, testDescription, testCategory);
    }


    public static void selectByIndex(int index) throws Exception {
        report();

        driver.get("https://" + HOST + SHARES_PAGE);
        wait.until(ExpectedConditions.visibilityOf(sharesPage.filter_country));

        Select dropdownElement = new Select(sharesPage.filter_country);
        dropdownElement.selectByIndex(index);
        }



    public static void selectByValue(String url, String value, WebElement element) {
        Select dropdownElement = new Select(element);
        dropdownElement.selectByVisibleText(value);
    }



    }

