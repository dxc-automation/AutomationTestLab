package com.demo.objects;

import com.demo.config.BasicTestConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class MarketsPage extends BasicTestConfig {

    public static final Logger LOG = LogManager.getLogger(MarketsPage.class);

    final WebDriver driver;


    // Popular search form
    @FindBy(how = How.CSS, using = "div[data-name=marketPopularProducts]")
    public WebElement markets_popular_products_form;

    // Popular search form row 1
    @FindBy(how = How.CSS, using = "li._2aj70jg6s-8B-QyO54F7fH _2rS9kEdfAZN9j0cIXMi-G3")
    public WebElement markets_popular_products_form_row1;

    // Popular search form row 2
    @FindBy(how = How.CSS, using = "span[data-id=331761]")
    public WebElement markets_popular_products_form_row2;

    // Popular search form row 3
    @FindBy(how = How.CSS, using = "span[data-id=331829]")
    public WebElement markets_popular_products_form_row3;

    // Popular search form row 4
    @FindBy(how = How.CSS, using = "span[data-id=92171]")
    public WebElement markets_popular_products_form_row4;

    // Popular search form row 5
    @FindBy(how = How.CSS, using = "span[data-id=8267043]")
    public WebElement markets_popular_products_form_row5;

    // Popular search form row 6
    @FindBy(how = How.CSS, using = "span[data-id=64879]")
    public WebElement markets_popular_products_form_row6;

    // Popular search form row 7
    @FindBy(how = How.CSS, using = "span[data-id=331720]")
    public WebElement markets_popular_products_form_row7;

    // Popular search form row 8
    @FindBy(how = How.CSS, using = "span[data-id=5029637]")
    public WebElement markets_popular_products_form_row8;






    public MarketsPage(WebDriver driver) {
        this.driver = driver;
    }
}
