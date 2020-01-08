package com.demo.test_suites;

import com.demo.config.BasicConfiguration;
import com.demo.test_properties.TestData;
import com.demo.test_scripts.api.AccessToken;
import com.demo.test_scripts.api.Authenticate;
import com.demo.test_scripts.api.RegisterUser;
import com.demo.test_scripts.api.UserInfo;
import com.demo.test_scripts.ui.ElementExist;
import com.demo.test_scripts.ui.GetAllLinks;
import com.demo.test_scripts.ui.RecordVideo;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class TC_01_Fly extends BasicConfiguration {


    @Test
    public void accessToken(Method method) throws Exception {
        TestData.fileName = method.getName() + ".json";
        AccessToken.getAccessToken(TestData.fileName);
    }

    @Test
    public void newUser(Method method) throws Exception {
        TestData.fileName = method.getName() + ".json";
        RegisterUser.getNewUser(TestData.fileName);
    }

    @Test
    public void login(Method method) throws Exception {
        TestData.fileName = method.getName() + ".json";
        Authenticate.getLogin(TestData.fileName);
    }

    @Test
    public void userInfo(Method method) throws Exception {
        TestData.fileName = method.getName() + ".json";
        UserInfo.getUserInfo(TestData.fileName);
    }

    @Test
    public void elementExist() throws Exception {
        ElementExist.checkElement();
    }

    @Test
    public void record() throws Exception {
        RecordVideo.recordVideo();
    }

    @Test
    public void hyperlinks() throws Exception {
        GetAllLinks.allLinks();
    }
}