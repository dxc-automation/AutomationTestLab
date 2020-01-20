package com.demo.ui_mapping;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class News {


    //  * * * *   N E W S

    @FindBy(how = How.ID_OR_NAME, using = "vehicleReg")
    public WebElement car_reg_input;

    @FindBy(how = How.CSS, using = "div#page-container button[type='button']")
    public WebElement submit_btn;

    @FindBy(how = How.CSS, using = "div#page-container > div:nth-child(4)")
    public WebElement result_txt;
}