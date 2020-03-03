package com.demo.tests;

import com.demo.config.BasicTestConfig;
import com.demo.scripts.ui.Search;
import com.demo.utilities.user_interface.VideoRecord;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.demo.properties.TestData.*;
import static com.demo.scripts.api.account.Login.secureLogin;
import static com.demo.scripts.ui.UserBalance.*;
import static com.demo.scripts.api.account.ClientInfo.getClientInfo;
import static com.demo.scripts.api.products.TEST_SearchProduct.searchStocks;
import static com.demo.scripts.api.products.INT_SearchProduct.searchProduct;
import static com.demo.scripts.api.orders.place.Order.*;
import static com.demo.scripts.api.orders.place.OrderConfirmation.*;
import static com.demo.scripts.api.orders.history.TransactionHistory.*;
import static com.demo.scripts.ui.UserLogin.secureLoginWeb;
import static com.demo.scripts.ui.Search.*;
import static com.demo.scripts.ui.Transactions.checkUserTransactionsHistory;

public class TestCase_01 extends BasicTestConfig {
    VideoRecord videoReord = new VideoRecord();

    @Test(description = "WEB")
    public void web_test() throws Exception {
        //  start screen recorder
        videoReord.startRecording();

        secureLoginWeb();
        getUserAmount();
        checkUserTransactionsHistory();
        checkSearchProduct("Telefonica");

        //  stop screen recorder
        videoReord.stopRecording();
    }


    @Test(description = "API")
    public void loginUser(Method method) throws Exception {
        fileName = method.getName() + ".json";
        secureLogin(fileName);
    }



    @Test(description = "API")
    public void checkUserInfo(Method method) throws Exception {
        fileName = method.getName() + ".json";
        getClientInfo(fileName);
    }


    @Test(description = "API")
    public void searchForProduct(Method method) throws Exception {
        fileName = method.getName() + ".json";

        if (env.equalsIgnoreCase("web-trader")) {
            searchStocks(fileName, "etfs", 100, false);

        } else if (env.equalsIgnoreCase("internal")) {
            searchProduct(fileName, TRACKERS, 0, 300, "");
        }
    }


    @Test(description = "API")
    public void putOrder(Method method) throws Exception {
        fileName = method.getName() + ".json";
        placeOrder(fileName, 1, 0.1, "buy");
    }


    @Test(description = "API")
    public void confirmOrder(Method method) throws Exception {
        fileName = method.getName() + ".json";
        getOrderConfirmation(fileName, 1, 0.1, "buy");
    }


    @Test(description = "API")
    public void checkTransactionActivities(Method method) throws Exception {
        fileName = method.getName() + ".json";
        getActivityTransactionsHistory(fileName, "17/02/2020");
    }
}
