package com.demo.test_suites;

import com.demo.config.BasicConfiguration;
import org.testng.annotations.Test;
import java.lang.reflect.Method;
import static com.demo.test_properties.TestData.*;
import static com.demo.test_scripts.api.AccessToken.getAccessToken;
import static com.demo.test_scripts.api.RegisterUser.getNewUser;
import static com.demo.test_scripts.api.Authenticate.getLogin;
import static com.demo.test_scripts.api.UserInfo.getUserInfo;
import static com.demo.test_scripts.ui.CheckNewElement.*;


public class TC_01_Epam extends BasicConfiguration {

    public static String catalogElement;


    @Test
    public void accessToken(Method method) throws Exception {
        fileName = method.getName() + ".json";
        getAccessToken(fileName);
    }

    @Test
    public void newUser(Method method) throws Exception {
        fileName = method.getName() + ".json";
        getNewUser(fileName);
    }

    @Test
    public void login(Method method) throws Exception {
        fileName = method.getName() + ".json";
        getLogin(fileName);
    }

    @Test
    public void userInfo(Method method) throws Exception {
        fileName = method.getName() + ".json";
        getUserInfo(fileName);
    }

    @Test
    public void searchItem(Method method) throws Exception {
        fileName = method.getName() + ".json";
        catalogElement = "Apple iPhone XR";
        searchElement(catalogElement);
    }
}