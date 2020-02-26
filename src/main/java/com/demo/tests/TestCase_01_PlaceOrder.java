package com.demo.tests;

import com.demo.config.BasicTestConfig;
import com.demo.utilities.user_interface.VideoRecord;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.demo.properties.TestData.*;
import static com.demo.scripts.api.degiro.account.Login.secureLogin;
import static com.demo.scripts.ui.degiro.UserBalance.*;
import static com.demo.scripts.api.degiro.account.ClientInfo.getClientInfo;
import static com.demo.scripts.api.degiro.products.TEST_SearchProduct.searchStocks;
import static com.demo.scripts.api.degiro.products.INT_SearchProduct.searchProduct;
import static com.demo.scripts.api.degiro.orders.place.Order.*;
import static com.demo.scripts.api.degiro.orders.place.OrderConfirmation.*;
import static com.demo.scripts.api.degiro.orders.history.OrdersHistory.*;
import static com.demo.scripts.ui.degiro.MarketPopularProducts.checkPopularProducts;
import static com.demo.scripts.ui.degiro.ActivityOrdersHistory.checkUserOrdersHistory;
import static com.demo.scripts.ui.degiro.UserLogin.secureLoginWeb;
import static com.demo.scripts.ui.degiro.ActivityTransactionsHistory.checkUserTransactionsHistory;

public class TestCase_01_PlaceOrder extends BasicTestConfig {
    VideoRecord videoReord = new VideoRecord();


    @Test(enabled = true)
    public void web_test() throws Exception {
        videoReord.startRecording();
        secureLoginWeb();
        getUserAmount();
        checkUserOrdersHistory();
        checkPopularProducts();
        checkUserTransactionsHistory();
        videoReord.stopRecording();
    }


    @Test(enabled = true)
    public void api_user_login(Method method) throws Exception {
        fileName = method.getName() + ".json";
        secureLogin(fileName);
    }


    @Test(enabled = true)
    public void api_get_client_info(Method method) throws Exception {
        fileName = method.getName() + ".json";
        getClientInfo(fileName);
    }


    @Test(enabled = true)
    public void api_search_stocks(Method method) throws Exception {
        fileName = method.getName() + ".json";
        if (env.equalsIgnoreCase("web-trader")) {
            searchStocks(fileName, "etfs", 100, false);

        } else if (env.equalsIgnoreCase("internal")) {
            searchProduct(fileName, TRACKERS, 0, 300, "");
        }
    }


    @Test(enabled = true)
    public void api_place_order(Method method) throws Exception {
        fileName = method.getName() + ".json";
        placeOrder(fileName, 1, 0.1, "buy");
    }


    @Test(enabled = true)
    public void api_order_confirmation(Method method) throws Exception {
        fileName = method.getName() + ".json";
        getOrderConfirmation(fileName, 1, 0.1, "buy");
    }


    @Test(enabled = true)
    public void api_get_orders_history(Method method) throws Exception {
        fileName = method.getName() + ".json";
        getOrdersHistory(fileName, "17/02/2020", "25/02/2020");
    }
}
