package com.demo.ui_mapping;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class HomePage {


    //  * * * *    L O G I N   F O R M

    @FindBy(how = How.CSS, using = "img#hplogo")
    public WebElement google_logo;

    @FindBy(how = How.CSS, using = "input[name=q]")
    public WebElement google_search_input;

    @FindBy(how = How.CSS, using = "form#tsf div.iblpc")
    public WebElement google_search_btn;

    @FindBy(how = How.TAG_NAME, using = "a")
    public List<WebElement> hyperlinks;
}

