package com.demo.ui_mapping;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


import java.util.List;

public class Amazon {


    //  * * * *    A M A Z O N

    @FindBy(how = How.CSS, using = "input#twotabsearchtextbox")
    public WebElement amazon_searchbox;

    @FindBy(how = How.CSS, using = "input.nav-input")
    public WebElement amazon_search_submit_btn;

    @FindBy(how = How.CSS, using = "img#landingImage")
    public WebElement amazon_item_image;
}

