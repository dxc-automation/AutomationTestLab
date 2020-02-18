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
import static com.demo.scripts.api.degiro.orders.Order.*;
import static com.demo.scripts.api.degiro.orders.OrderConfirmation.*;
import static com.demo.scripts.api.degiro.orders.OrdersHistory.*;
import static com.demo.scripts.ui.degiro.PopularProducts.checkFormVisualization;

public class TestCase_01 extends BasicTestConfig {

    @Test(enabled = true)
    public void userLogin(Method method) throws Exception {
        fileName = method.getName() + ".json";
        secureLogin(fileName);
    }

    @Test(enabled = true)
    public void clientInfo(Method method) throws Exception {
        fileName = method.getName() + ".json";
        getClientInfo(fileName);
    }

    @Test(enabled = true)
    public void productsPopular(Method method) throws Exception {
        fileName = method.getName() + ".json";
        checkFormVisualization();
    }

    @Test(enabled = true)
    public void products(Method method) throws Exception {
        fileName = method.getName() + ".json";
        getStocks(fileName, 100, true, true);
    }

    @Test(enabled = true)
    public void marketsPageProducts(Method method) throws Exception {
        fileName = method.getName() + ".json";
        getMarketsPageProdocts(fileName);
    }

    @Test(enabled = true)
    public void currencyInfo(Method method) throws Exception {
        fileName = method.getName() + ".json";
        getCurrencyDetails(fileName, EUR_CAD);
    }

    @Test(enabled = true)
    public void order(Method method) throws Exception {
        fileName = method.getName() + ".json";
        placeOrder(fileName, 1, 2, "buy");
    }

    @Test(enabled = true)
    public void orderConfirmation(Method method) throws Exception {
        fileName = method.getName() + ".json";
        getOrderConfirmation(fileName, 1, 2, "buy");
    }

    @Test(enabled = true)
    public void ordersHistory(Method method) throws Exception {
        fileName = method.getName() + ".json";
        getOrdersHistory(fileName, "17/02/2020", "18/02/2020");
    }
}
