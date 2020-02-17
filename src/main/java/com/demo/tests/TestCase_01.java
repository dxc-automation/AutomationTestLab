package com.demo.tests;

import com.demo.config.BasicTestConfig;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.demo.properties.TestData.*;
import static com.demo.scripts.api.degiro.account.Login.secureLogin;
import static com.demo.scripts.api.degiro.account.ClientInfo.getClientInfo;
import static com.demo.scripts.api.degiro.products.Stocks.getStocks;
import static com.demo.scripts.api.degiro.products.MarketPageProducts.getMarketsPageProdocts;
import static com.demo.scripts.api.degiro.products.CurrencyInfo.getCurrencyDetails;
import static com.demo.scripts.api.degiro.orders.CurrencyTransaction.*;
import static com.demo.scripts.ui.degiro.PopularProducts.checkFormVisualization;

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

    @Test(enabled = false)
    public void productsPopular(Method method) throws Exception {
        fileName = method.getName() + ".json";
        checkFormVisualization();
    }

    @Test(enabled = false)
    public void products(Method method) throws Exception {
        fileName = method.getName() + ".json";
        getStocks(fileName, 100, true, true);
    }

    @Test(enabled = false)
    public void marketsPageProducts(Method method) throws Exception {
        fileName = method.getName() + ".json";
        getMarketsPageProdocts(fileName);
    }

    @Test(enabled = true)
    public void currencyInfo(Method method) throws Exception {
        fileName = method.getName() + ".json";
        getCurrencyDetails(fileName, EUR_CAD);
    }

    @Test(enabled = false)
    public void convertCurrency(Method method) throws Exception {
        fileName = method.getName() + ".json";
        currencyTransaction(fileName, 1, "buy");
    }
}
