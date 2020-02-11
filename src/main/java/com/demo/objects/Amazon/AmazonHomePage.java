package com.demo.objects.Amazon;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class AmazonHomePage {


    //  * * * *    A M A Z O N

    @FindBy(how = How.CSS, using = "input#twotabsearchtextbox")
    public WebElement amazon_searchbox;

    @FindBy(how = How.CSS, using = "input.nav-input")
    public WebElement amazon_search_submit_btn;

    @FindBy(how = How.CSS, using = "input#add-to-cart-button")
    public WebElement amazon_add_to_cart_btn;



    //  * * * *    S I D E   P A N E L

    @FindBy(how = How.CSS, using = "div#attach-accessory-pane.a-section")
    public WebElement amazon_side_panel;

    @FindBy(how = How.CSS, using = "form#attach-view-cart-button-form")
    public WebElement amazon_side_panel_cart_btn;
}

