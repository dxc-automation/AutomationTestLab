package com.demo.tests;

import com.demo.config.BasicTestConfig;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.demo.properties.TestData.fileName;
import static com.demo.scripts.api.degiro.Login.secureLogin;
import static com.demo.scripts.api.degiro.ClientInfo.getClientInfo;
import static com.demo.scripts.api.degiro.Stocks.getStocks;
import static com.demo.scripts.ui.degiro.PopularProducts.checkFormVisualization;

public class TestCase_01 extends BasicTestConfig {

    @Test
    public void userLogin(Method method) throws Exception {
        fileName = method.getName() + ".json";
        secureLogin(fileName);
    }

    @Test
    public void clientInfo(Method method) throws Exception {
        fileName = method.getName() + ".json";
        getClientInfo(fileName);
    }

    @Test
    public void productsPopular(Method method) throws Exception {
        fileName = method.getName() + ".json";
        checkFormVisualization();
    }

    @Test
    public void products(Method method) throws Exception {
        fileName = method.getName() + ".json";
        getStocks(fileName, 100, true, true);
    }
}
