package com.demo.objects.activity;

import com.demo.config.BasicTestConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class TransactionsPage extends BasicTestConfig {

    public static final Logger LOG = LogManager.getLogger(TransactionsPage.class);

    public TransactionsPage(WebDriver driver) {
        this.driver = driver;
    }

    final WebDriver driver;


    //  *****   Transactions Table
    @FindBy(how = How.CSS, using = "div._3DN34TFiXFyffoeKL5wfQV._1tsAEIA90lF_qaaoHO4HCv")
    public WebElement history_table;

    @FindBy(how = How.XPATH, using = "//tr[1]/td[12]/span/i[@role='img']")
    public WebElement history_table_details_row1_info_btn;


    //  *****   Transactions Side Panel
    @FindBy(how = How.XPATH, using = "//*[@id='appWorkspace']/div[2]/section/div[1]/div/div[3]/div[2]")
    public WebElement info_panel_details_id;

    @FindBy(how = How.CSS, using = "time[data-field=date]")
    public WebElement transaction_details_date;

    @FindBy(how = How.CSS, using = "span[data-name=buySellBadge]")
    public WebElement transaction_details_action;

    @FindBy(how = How.XPATH, using = "//*[@id='appWorkspace']/div[2]/section/div[1]/div/div[5]/div[2]")
    public WebElement transaction_details_qty;

    @FindBy(how = How.CSS, using = "span[data-field=price]")
    public WebElement transaction_details_price;

    @FindBy(how = How.CSS, using = "span[data-field=total]")
    public WebElement transaction_details_local_value;

    @FindBy(how = How.CSS, using = "span[data-field=totalInBaseCurrency]")
    public WebElement transaction_details_value;

    @FindBy(how = How.CSS, using = "span[data-field=fxRate]")
    public WebElement transaction_details_exchange_rate;

    @FindBy(how = How.CSS, using = "span[data-field=feeInBaseCurrency]")
    public WebElement transaction_details_fee;

    @FindBy(how = How.CSS, using = "span[data-field=totalPlusFeeInBaseCurrency]")
    public WebElement transaction_details_total;
}
