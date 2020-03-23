package com.demo.objects.products;

import com.demo.config.BasicTestConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LeveragedPage extends BasicTestConfig {

    public static final Logger LOG = LogManager.getLogger(LeveragedPage.class);
    public LeveragedPage(WebDriver driver) {
        this.driver = driver;
    }

    final WebDriver driver;



    // Filters
    @FindBy(how = How.CLASS_NAME, using = "_2434So3H9oWJ_OYbyC48su _2ZMfjlvne7XcqTClLK5Lkd _1IND80Y6PsQ6BuVcfXBSkB")
    public WebElement filter_long_short;

    @FindBy(how = How.CSS, using = "input[maxlength=0][autocomplete=new-selectControl-1584564164489]")
    public WebElement filter_indicates;






    @FindBy(how = How.CSS, using = "table._3QuQRAgUeF2pqrF7IRP6nC")
    public WebElement page_table;

    @FindBy(how = How.CSS, using = "span[data-name=productName]")
    public WebElement table_row1_product;

}
