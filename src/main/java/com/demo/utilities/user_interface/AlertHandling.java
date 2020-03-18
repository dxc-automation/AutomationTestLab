package com.demo.utilities.user_interface;

import com.aventstack.extentreports.Status;
import com.demo.config.BasicTestConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import static com.demo.config.ReporterConfig.*;

public class AlertHandling extends BasicTestConfig {


    public static void checkForAlert() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertMessage = driver.switchTo().alert().getText();
            test.warning("<b> Alert message <u>" + alertMessage + "</u> detected");
        } catch (Exception e) {
            e.getMessage();
        }
    }




    public static void acceptAlert() {
        // Accept alert
        try {
            Alert alert = driver.switchTo().alert();
            String alertMessage = driver.switchTo().alert().getText();
            alert.accept();
            test.pass("<b>" + alertMessage + " was accepted successfully</b>");
        } catch (Exception e) {
        }
    }
}