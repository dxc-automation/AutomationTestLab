package com.demo.objects.activity;

import com.demo.config.BasicTestConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class SharesPage extends BasicTestConfig {

    public static final Logger LOG = LogManager.getLogger(SharesPage.class);
    public SharesPage(WebDriver driver) {
        this.driver = driver;
    }

    final WebDriver driver;



    // Filters
    @FindBy(how = How.CSS, using = "input[maxlength=1000]")
    public WebElement filter_country;

    @FindBy(how = How.CSS, using = "input[maxlength=0]")
    public WebElement filter_indicates;




    @FindBy(how = How.CSS, using = "table._3QuQRAgUeF2pqrF7IRP6nC")
    public WebElement products_table;

    @FindBy(how = How.XPATH, using = "//tr[1]/td[1]/div/a[@pathname='/trader4/']")
    public WebElement product_row_1;


}
