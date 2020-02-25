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
    public General(WebDriver driver) {
        this.driver = driver;
    }
    final WebDriver driver;



    //  Side Navigation Menu
    @FindBy(how = How.CSS, using = "._36CbGFJLG6WzVjeXIpURvd[data-id=marketsMenuItem]")
    public WebElement side_navigation_market_btn;

    @FindBy(how = How.CSS, using = "._36CbGFJLG6WzVjeXIpURvd[data-id=activityMenuItem]")
    public WebElement side_navigation_activity_btn;



    //  Account Summary
    @FindBy(how = How.CSS, using = "span[data-id=totalPortfolio]")
    public WebElement account_content_available_to_spend;

    @FindBy(how = How.CSS, using = "span[data-id=totalPortfolio][data-field=total]")
    public WebElement account_content_total;



    @FindBy(how = How.CSS, using = "input[name=quickSearchInput][type=text]")
    public WebElement quick_search_input;
}
