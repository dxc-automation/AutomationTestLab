package com.demo.tests.backend.fly;

import com.demo.config.BasicTestConfig;
import org.testng.annotations.Test;
import java.lang.reflect.Method;
import static com.demo.properties.TestData.*;
import static com.demo.scripts.api.fly.AccessToken.getAccessToken;
import static com.demo.scripts.api.fly.RegisterUser.getNewUser;
import static com.demo.scripts.api.fly.Authenticate.getLogin;
import static com.demo.scripts.api.fly.UserInfo.getUserInfo;
import static com.demo.scripts.api.fly.NewToken.addUserInfo;


public class TC_01_Login extends BasicTestConfig {


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