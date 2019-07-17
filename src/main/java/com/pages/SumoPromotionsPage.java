package com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class SumoPromotionsPage {


    // *****    TOOLBAR     *****//

    @FindBy(how = How.CSS, using = "div.content-block___Kf-lC[data-qa='contentBlock-promotionConfigurations-toolbar']")
    public static WebElement toolbarPage;

    @FindBy(how = How.CSS, using = "div#content div > button[type='button']:nth-child(1)")
    public static WebElement toolbarBtn_Add;

    @FindBy(how = How.CSS, using = "div#content div > button[type='button']:nth-child(2)")
    public static WebElement toolbarBtn_Remove;

    @FindBy(how = How.CSS, using = "div#content div > button[type='button']:nth-child(3)")
    public static WebElement toolbarBtn_Clone;

    @FindBy(how = How.CSS, using = "div#content div > button[type='button']:nth-child(4)")
    public static WebElement toolbarBtn_CopySegments;

    @FindBy(how = How.CSS, using = "div#content div > button[type='button']:nth-child(5)")
    public static WebElement toolbatBtn_RemoveSegments;

    @FindBy(how = How.CSS, using = "div#content div > button[type='button']:nth-child(6)")
    public static WebElement toolbarBtn_DeselectSegments;







    // *****    TABLE   *****//

    @FindBy(how = How.CSS, using = "table.table___yjv84.variant-1___1z0lW")
    public static WebElement table;

    @FindBy(how = How.CSS, using = "tr.row___2FvyH[data-qa='tableRowWrapper-header']")
    public static WebElement header_Table;

    @FindBy(how = How.CSS, using = "label.checkbox___2HPJM")
    public static WebElement tableCheckBox_Row1;

    @FindBy(how = How.CSS, using = "div#content tr:nth-child(3) > td:nth-child(2) > div > label")
    public static WebElement tableCheckBox_Row2;
}
