package com.demo.objects.Degiro;

import com.demo.config.BasicTestConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class General extends BasicTestConfig {

    public static final Logger LOG = LogManager.getLogger(General.class);

    final WebDriver driver;

    @FindBy(how = How.CSS, using = "._36CbGFJLG6WzVjeXIpURvd[data-id=marketsMenuItem]")
    public WebElement sidenavigation_market_btn;

    @FindBy(how = How.CSS, using = "input[name=quickSearchInput][type=text]")
    public WebElement quick_search_input;


    public General(WebDriver driver) {
        this.driver = driver;
    }
}
