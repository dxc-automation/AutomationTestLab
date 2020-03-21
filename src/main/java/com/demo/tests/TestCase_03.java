package com.demo.tests;

import com.demo.config.BasicTestConfig;
import com.demo.utilities.user_interface.VideoRecord;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.demo.properties.Environments.*;
import static com.demo.properties.TestData.*;
import static com.demo.scripts.ui.UserLogin.secureLoginWeb;
import static com.demo.scripts.ui.search.SearchForProductField.*;

public class TestCase_03 extends BasicTestConfig {
    VideoRecord videoReord = new VideoRecord();


    @Test()
    public void test_laverage_page(Method method) throws Exception {
        videoReord.startRecording();
        fileName = method.getName() + ".json";

        secureLoginWeb();

        String productURL = "https://" + HOST + "/trader4/#/products?productType=" + leveraged();
        driver.get(productURL);

        checkSearchForProduct(productName);


    }
}
