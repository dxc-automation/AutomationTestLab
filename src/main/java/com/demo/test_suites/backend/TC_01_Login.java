package com.demo.test_suites.backend;

import com.demo.config.BasicTestConfig;
import org.testng.annotations.Test;
import java.lang.reflect.Method;
import static com.demo.test_properties.TestData.*;
import static com.demo.test_scripts.api.AccessToken.getAccessToken;
import static com.demo.test_scripts.api.RegisterUser.getNewUser;
import static com.demo.test_scripts.api.Authenticate.getLogin;
import static com.demo.test_scripts.api.UserInfo.getUserInfo;
import static com.demo.test_scripts.api.NewToken.addUserInfo;


public class TC_01_Login extends BasicTestConfig {

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
    public void addAccountInfo(Method method) throws Exception {
        fileName = method.getName() + ".json";
        addUserInfo(fileName);
    }


    @Test
    public void userInfo(Method method) throws Exception {
        fileName = method.getName() + ".json";
        getUserInfo(fileName);
    }
}