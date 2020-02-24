package com.demo.tests;

import com.demo.config.BasicTestConfig;
import com.demo.utilities.user_interface.VideoRecord;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.demo.properties.TestData.*;
import static com.demo.scripts.api.degiro.account.Login.secureLogin;
import static com.demo.scripts.ui.degiro.UserBalance.*;
import static com.demo.scripts.api.degiro.account.ClientInfo.getClientInfo;
import static com.demo.scripts.api.degiro.products.MarketPageProducts.getMarketsPageProdocts;
import static com.demo.scripts.api.degiro.products.SearchProductVWDServices.getProductDetail;
import static com.demo.scripts.api.degiro.orders.Order.*;
import static com.demo.scripts.api.degiro.orders.OrderConfirmation.*;
import static com.demo.scripts.api.degiro.orders.OrdersHistory.*;
import static com.demo.scripts.ui.degiro.PopularProducts.checkPopularProducts;
import static com.demo.scripts.ui.degiro.ActivityOrdersHistory.checkUserOrdersHistory;
import static com.demo.scripts.ui.degiro.UserLogin.secureLoginWeb;

public class TestCase_01 extends BasicTestConfig {
    VideoRecord videoReord = new VideoRecord();


    @Test(enabled = true)
    public void web_user_login() throws Exception {
        videoReord.startRecording();
        secureLoginWeb();
        getUserAmount();
        checkUserOrdersHistory();
        checkPopularProducts();
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
    public void api_get_markets_page_products(Method method) throws Exception {
        fileName = method.getName() + ".json";
        getMarketsPageProdocts(fileName);
    }


    @Test(enabled = true)
    public void api_get_product_details(Method method) throws Exception {
        fileName = method.getName() + ".json";
        getProductDetail(fileName, EUR_CAD);
    }


    @Test(enabled = false)
    public void api_place_order(Method method) throws Exception {
        fileName = method.getName() + ".json";
        placeOrder(fileName, 1, 2, "buy");
    }


    @Test(enabled = false)
    public void api_order_confirmation(Method method) throws Exception {
        fileName = method.getName() + ".json";
        getOrderConfirmation(fileName, 1, 2, "buy");
    }


    @Test(enabled = true)
    public void api_get_orders_history(Method method) throws Exception {
        fileName = method.getName() + ".json";
        getOrdersHistory(fileName, "17/02/2020", "18/02/2020");
    }
}
