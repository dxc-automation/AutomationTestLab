package com.demo.objects.Degiro;

import com.demo.config.BasicTestConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ActivityOrdersPage extends BasicTestConfig {

    public static final Logger LOG = LogManager.getLogger(ActivityOrdersPage.class);
    public ActivityOrdersPage(WebDriver driver) {
        this.driver = driver;
    }
    final WebDriver driver;



    @FindBy(how = How.CSS, using = "button[data-id='/orders/history']")
    public WebElement orders_hitory_btn;

    @FindBy(how = How.CSS, using = "table._3QuQRAgUeF2pqrF7IRP6nC")
    public WebElement orders_history_table;
}
