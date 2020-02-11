package com.demo.tests;

import com.demo.config.BasicTestConfig;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.demo.properties.TestData.fileName;
import static com.demo.scripts.api.degiro.Login.secureLogin;
import static com.demo.scripts.api.degiro.ClientInfo.getClientInfo;
import static com.demo.scripts.ui.Degiro.*;

public class TestCase_01 extends BasicTestConfig {

    @Test(enabled = false)
    public void userLogin(Method method) throws Exception {
        fileName = method.getName() + ".json";
        secureLogin(fileName);
    }

    @Test(enabled = false)
    public void clientInfo(Method method) throws Exception {
        fileName = method.getName() + ".json";
        getClientInfo(fileName);
    }

    @Test
    public void loginWeb(Method method) throws Exception {
        fileName = method.getName() + ".json";
        secureLoginWeb();
    }
}
