package com.demo.objects;

import com.demo.config.BasicTestConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage extends BasicTestConfig {

    public static final Logger LOG = LogManager.getLogger(LoginPage.class);

    final WebDriver driver;

    @FindBy(how = How.CSS, using = "input#username")
    public WebElement login_user_input;

    @FindBy(how = How.CSS, using = "input#password")
    public WebElement login_pass_input;

    @FindBy(how = How.CSS, using = "button[type=submit][name=loginButtonUniversal]")
    public WebElement login_submit_btn;

    @FindBy(how = How.CSS, using = "form[id=loginForm][name=loginForm]")
    public WebElement login_form;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
}
