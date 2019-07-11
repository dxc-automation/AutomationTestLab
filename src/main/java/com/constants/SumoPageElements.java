package com.constants;

import com.setup.BasicSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.setup.BasicSetup.*;

public interface SumoPageElements  {


    //***   LOG IN  ***//

    WebElement usernameField = driver.findElement(By.name("name"));
    WebElement passwordField = driver.findElement(By.cssSelector(".form-group___3IVrV:nth-child(2) > .input___36Zmo"));
    WebElement loginBtn      = driver.findElement(By.cssSelector("#login"));

}