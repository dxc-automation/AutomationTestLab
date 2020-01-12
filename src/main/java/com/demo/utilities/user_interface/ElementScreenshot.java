package com.demo.utilities.user_interface;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.demo.config.BasicConfiguration;
import com.demo.test_properties.FilePaths;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static com.demo.config.ReporterConfig.test;


public class ElementScreenshot extends BasicConfiguration {

    private static String textFile;

    public static void elementScreenshot(WebElement element, String image) throws Exception {
        textFile  = element.toString();
        File file = element.getScreenshotAs(OutputType.FILE);
        File dest = new File(FilePaths.screenshots_actual_folder + image + ".png");
        FileUtils.copyFile(file, dest);

        try {
            Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(driver, element);
            ImageIO.write(screenshot.getImage(), "png", new File(FilePaths.screenshots_actual_folder + image + ".png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}