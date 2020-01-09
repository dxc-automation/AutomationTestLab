package com.demo.utilities.user_interface;

import org.openqa.selenium.WebElement;

public class Assertions {


    public static boolean elementExistsAssertion(WebElement element) {
        if (element.getSize() != null) {
            return true;
        } else {
            return false;
        }
    }
}
