package com.demo.tests;

import com.demo.config.BasicTestConfig;
import com.demo.utilities.user_interface.VideoRecord;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.demo.properties.TestData.*;
import static com.demo.scripts.api.account.Login.secureLogin;
import static com.demo.scripts.api.products.AllProducts.getAllProductsFromType;
import static com.demo.scripts.ui.UserBalance.*;
import static com.demo.scripts.api.account.ClientInfo.getClientInfo;
import static com.demo.scripts.api.orders.place.Order.*;
import static com.demo.scripts.api.orders.place.OrderConfirmation.*;
import static com.demo.scripts.api.orders.history.TransactionHistory.*;
import static com.demo.scripts.ui.UserLogin.secureLoginWeb;
import static com.demo.scripts.ui.search.SearchFromPlaceOrderSidePanel.*;
import static com.demo.scripts.ui.Transactions.checkUserTransactionsHistory;

public class TestCase_01 extends BasicTestConfig {
    VideoRecord videoReord = new VideoRecord();

    @Test(description = "WEB")
    public void web_login() throws Exception {
        videoReord.startRecording();
        secureLoginWeb();
        videoReord.stopRecording();
    }


    @Test(description = "WEB")
    public void web_user_amount() throws Exception {
        videoReord.startRecording();
        getUserAmount();
        videoReord.stopRecording();
    }


    @Test(description = "WEB")
    public void web_user_transactions() throws Exception {
        checkUserTransactionsHistory();
    }


    @Test(description = "WEB")
    public void web_search_product() throws Exception {
        placeOrderSidePanelSearchProduct("Telefonica");
    }


    @Test(description = "API")
    public void post_login(Method method) throws Exception {
        fileName = method.getName() + ".json";
        secureLogin(fileName);
    }



    @Test(description = "API")
    public void get_user_info(Method method) throws Exception {
        fileName = method.getName() + ".json";
        getClientInfo(fileName);
    }


    @Test(description = "API")
    public void get_product_list(Method method) throws Exception {
        fileName = method.getName() + ".json";
        getAllProductsFromType(fileName, "etfs", false);
        }



    @Test(description = "API")
    public void post_place_order(Method method) throws Exception {
        fileName = method.getName() + ".json";
        placeOrder(fileName, 1, 0.1, "buy");
    }


    @Test(description = "API")
    public void post_confirm_order(Method method) throws Exception {
        fileName = method.getName() + ".json";
        getOrderConfirmation(fileName, 1, 0.1, "buy");
    }


    @Test(description = "API")
    public void get_transaction_history(Method method) throws Exception {
        fileName = method.getName() + ".json";
        getActivityTransactionsHistory(fileName, "17/02/2020");
    }
}
