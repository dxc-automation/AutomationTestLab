package com.demo.objects.products;

import com.demo.config.BasicTestConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ProductsBasic extends BasicTestConfig {

    public static final Logger LOG = LogManager.getLogger(ProductsBasic.class);
    public ProductsBasic(WebDriver driver) {
        this.driver = driver;
    }
    final WebDriver driver;


    //  *****   Tab Menu Panel

    @FindBy(how = How.CSS, using = "div[role='tabpanel'] > a:nth-of-type(4)")
    public WebElement header_menu_laverages_btn;





    //  *****   Orders
    @FindBy(how = How.CSS, using = "button[data-name=backButton]")
    public WebElement product_back_btn;
}
