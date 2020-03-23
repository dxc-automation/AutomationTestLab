package com.demo.tests;

import com.demo.config.BasicTestConfig;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.demo.properties.TestData.*;
import static com.demo.scripts.api.account.ClientInfo.getClientInfo;
import static com.demo.scripts.api.account.Login.secureLogin;
import static com.demo.scripts.api.products.AllProducts.getAllProductsFromType;
import static com.demo.scripts.ui.UserLogin.*;
import static com.demo.scripts.ui.products_page.leveraged.OpenLaveragesPage.*;
import static com.demo.scripts.ui.filters.LaveragesFilters.*;
import static com.demo.scripts.ui.products_page.leveraged.OpenLeverageProduct.*;

public class TestCase_03_Leveraged extends BasicTestConfig {

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
        getAllProductsFromType(fileName, "leverageds", false);
    }


    @Test(description = "WEB")
    public void web_user_login() throws Exception {
        secureLoginWeb();
        openLaveragesPage();
        leveragesFilters();
        openLaveragesProduct();
    }
}
