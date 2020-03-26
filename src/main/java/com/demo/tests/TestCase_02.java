package com.demo.tests;

import com.demo.config.BasicTestConfig;
import com.demo.utilities.user_interface.VideoRecord;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.demo.scripts.api.search.PAGE_ProductsList.*;
import static com.demo.properties.TestData.fileName;
import static com.demo.scripts.api.account.Login.secureLogin;
import static com.demo.scripts.ui.UserLogin.secureLoginWeb;
import static com.demo.scripts.ui.filters.SharesFilters.*;

public class TestCase_02 extends BasicTestConfig {
    VideoRecord videoReord = new VideoRecord();


    @Test(description = "WEB")
    public void web_login() throws Exception {
        videoReord.startRecording();
        secureLoginWeb();
        videoReord.stopRecording();
    }

    @Test(description = "API")
    public void loginUser(Method method) throws Exception {
        fileName = method.getName() + ".json";
        secureLogin(fileName);
    }

    @Test(description = "API")
    public void share_products_list(Method method) throws Exception {
        fileName = method.getName() + ".json";
        takeProductsList(fileName, 100, false);
    }
}
