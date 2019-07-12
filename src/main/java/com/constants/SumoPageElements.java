package com.constants;

import com.setup.BasicSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.setup.BasicSetup.*;

public interface SumoPageElements  {


    //*****   LOG IN  *****//

    String usernameField = ".form-group___3IVrV:nth-child(1) > .input___36Zmo";
    String passwordField = ".form-group___3IVrV:nth-child(2) > .input___36Zmo";
    String loginButton   = "#login";




    //*****   PROMOTIONS PAGE   *****//


    // PAGE HEADER
    String pageHeader = "div.content-block___Kf-lC[data-qa='contentBlock-promotionConfigurations-toolbar']";
    String addPageHeader     = "div#content div > button[type='button']:nth-child(1)";
    String removePageHeader  = "div#content div > button[type='button']:nth-child(2)";
    String clonePageHeader   = "div#content div > button[type='button']:nth-child(3)";
    String copySegmentsPageHeader    = "div#content div > button[type='button']:nth-child(4)";
    String removeSegmentsPageHeader  = "div#content div > button[type='button']:nth-child(5)";
    String deselectExpiresPageHeader = "div#content div > button[type='button']:nth-child(6)";




    // TABLE
    String promoTable = "table.table___yjv84.variant-1___1z0lW";
    String promoTableHeader = "tr.row___2FvyH[data-qa='tableRowWrapper-header']";

    String promoTableCellWrapper = "td[data-qa='tableCellWrapper']";


}