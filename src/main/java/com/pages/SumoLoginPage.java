package com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class SumoLoginPage {

    @FindBy(how = How.CSS, using = ".form-group___3IVrV:nth-child(1) > .input___36Zmo")
    public static WebElement input_UserName;

    @FindBy(how = How.CSS, using = ".form-group___3IVrV:nth-child(2) > .input___36Zmo")
    public static WebElement input_Password;

    @FindBy(how = How.CSS, using = "#login")
    public static WebElement btn_Login;
}

