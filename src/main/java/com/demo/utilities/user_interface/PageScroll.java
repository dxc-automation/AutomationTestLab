package com.demo.utilities.user_interface;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import static com.demo.utilities.user_interface.WebDriverUtils.*;
public class PageScroll {

    public static final Logger LOG = LogManager.getLogger(PageScroll.class);


    public static void pageScrollUp(int coordinateY) throws Exception {
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("scroll(" + coordinateY + ", 0)");
    }


    public static void pageScrollDown(int coordinateX) throws Exception {
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("scroll(0," + coordinateX + ")");
    }
}
