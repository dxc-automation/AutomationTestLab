package com.demo.objects;

import com.demo.config.BasicTestConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ActivityPage extends BasicTestConfig {

    public static final Logger LOG = LogManager.getLogger(ActivityPage.class);
    public ActivityPage(WebDriver driver) {
        this.driver = driver;
    }
    final WebDriver driver;


    //  *****   Tab Menu Panel
    @FindBy(how = How.CSS, using = "div[role=tabpanel]")
    public WebElement activity_tab_menu;

    @FindBy(how = How.CSS,using = "a[href=#/orders/open]")
    public WebElement activity_tab_menu_orders_btn;

    @FindBy(how = How.LINK_TEXT, using = "Transactions")
    public WebElement activity_tab_menu_transactions_btn;



    //  *****   Transactions
    @FindBy(how = How.CSS, using = "div[aria-live=polite]")
    public WebElement transactions_history_table;

    @FindBy(how = How.CSS, using = "section[data-name=sideInformationPanel]")
    public WebElement transactions_details_side_panel;

    @FindBy(how = How.XPATH, using = "//*[@id='appWorkspace']/div[2]/section/div[1]/div/div[3]/div[2]")
    public WebElement transactions_details_id;

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







    //  *****   Orders
    @FindBy(how = How.CSS, using = "button[data-id='/orders/history']")
    public WebElement orders_hitory_btn;

    @FindBy(how = How.CSS, using = "table._3QuQRAgUeF2pqrF7IRP6nC")
    public WebElement orders_history_table;

    @FindBy(how = How.CSS, using = "span[data-id=96826][data-name=productName]")
    public WebElement orders_history_table_row1_product;

    @FindBy(how = How.CSS, using = "time[data-id=ecbadb6e-90d6-4db0-8dc2-62d6ae1a1fbd][data-field=createdAt]")
    public WebElement orders_history_table_row1_time;

    @FindBy(how = How.CSS, using = "span[data-name=exchangeAbbr]")
    public WebElement orders_history_table_row1_excahnge;
}
