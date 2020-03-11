package com.demo.scripts.ui.takeaway;

import com.demo.config.BasicTestConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class PageObjects extends BasicTestConfig {

    public static final Logger LOG = LogManager.getLogger(PageObjects.class);
    public PageObjects(WebDriver driver) {
        this.driver = driver;
    }
    final WebDriver driver;


    //  Place Order Side Panel
    @FindBy(how = How.CSS, using = "input[id='imysearchstring']")
    public WebElement search_field;

    @FindBy(how = How.CSS, using = "div#iautoCompleteDropDownContent")
    public WebElement search_results_form;

    @FindBy(how = How.CSS, using = "a[data-reference=postcode]")
    public WebElement search_suggestion;

    @FindBy(how = How.CSS, using = "[data-href='/en/order-takeaway-8888-alpha?search'] span")
    public WebElement search_result_1;




    @FindBy(how = How.CSS, using = ".utility-middle-bar .btn.btn-map .btn-icon")
    public WebElement user_localization_icon;

    @FindBy(how = How.XPATH, using = "//div[@class='clustered-pins']//div[contains(text(),'3')]")
    public WebElement user_localization_round_icon;

    @FindBy(how = How.XPATH, using = "//div[3]//div[4]//img[1]")
    public WebElement user_localization_gps_icon;

    @FindBy(how = How.XPATH, using = "//div[@class='restaurant-infopanel delivery-card']//div[@class='logowrapper']")
    public WebElement user_localization_near_rest;
}






