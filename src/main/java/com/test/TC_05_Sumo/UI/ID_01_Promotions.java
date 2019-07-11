package com.test.TC_05_Sumo.UI;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.setup.BasicSetup;
import com.setup.ExtentManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import static com.setup.ExtentManager.extent;
import static com.setup.ExtentManager.test;
import static com.constants.SumoPageElements.*;

public class ID_01_Promotions extends BasicSetup {

    // Start report session
    @BeforeClass
    public void startTest() throws Exception {
        extent = ExtentManager.GetExtent();
        test = extent.createTest("[TC_01] Promotions Page", "DESCRIPTION");
        test.assignAuthor("SUMO");
        test.assignCategory("Front-End");
        extent.setAnalysisStrategy(AnalysisStrategy.TEST);
    }

    // Test example that compare actual screenshot and image from data base
    @Test
    public void openSumoHomePage() throws Exception {
        driver.get("https://sumo-qa.pokerstarsdev.com/matrix/promotions");
        driver.manage().window().maximize();
        String url = driver.getCurrentUrl();

        usernameField.sendKeys("test1");
        passwordField.sendKeys("test1");
        loginBtn.click();
    }
}