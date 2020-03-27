package com.demo.utilities.user_interface;

import com.demo.config.BasicTestConfig;
import com.demo.properties.FilePaths;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;


public class GetElementText extends BasicTestConfig {

    public static String getElementText(WebElement element) {
        String elementText = element.getText();
        return elementText;
    }
}