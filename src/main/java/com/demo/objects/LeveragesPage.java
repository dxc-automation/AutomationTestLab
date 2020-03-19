package com.demo.objects;

import com.demo.config.BasicTestConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LeveragesPage extends BasicTestConfig {

    public static final Logger LOG = LogManager.getLogger(LeveragesPage.class);
    public LeveragesPage(WebDriver driver) {
        this.driver = driver;
    }

    final WebDriver driver;



    // Filters
    @FindBy(how = How.CSS, using = "input[maxlength=1000][autocomplete=new-selectControl-1584564675114]")
    public WebElement filter_long_short;

    @FindBy(how = How.CSS, using = "input[maxlength=0][autocomplete=new-selectControl-1584564164489]")
    public WebElement filter_indicates;

}
