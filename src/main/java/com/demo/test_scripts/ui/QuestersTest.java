package com.demo.test_scripts.ui;

import com.demo.config.BasicTestConfig;
import com.demo.ui_mapping.News;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;

import static com.demo.test_properties.UrlPaths.*;
import static com.demo.utilities.user_interface.ImageCompare.imageCompare;


public class QuestersTest extends BasicTestConfig {

    private static News page   = PageFactory.initElements(driver, News.class);


    public static void openHomePage() {
        driver.get(NEWS_BASIC_URL);
        String pageCurrentUrl = driver.getCurrentUrl();
        Assert.assertEquals(pageCurrentUrl, "https://covercheck.vwfsinsuranceportal.co.uk/");
    }


    public static void carSearch(String carRegistrationNumber) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, 20);

        page.car_reg_input.sendKeys(carRegistrationNumber);
        page.submit_btn.click();

        String actualImage = "Car_Search_Actual";
        String expectImage = "Car_Search_Expect";
        takeScreenshot(driver, actualImage);

        imageCompare(actualImage, expectImage);
    }


}
