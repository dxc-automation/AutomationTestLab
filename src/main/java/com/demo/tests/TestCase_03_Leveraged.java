package com.demo.tests;

import com.demo.config.BasicTestConfig;
import com.demo.objects.products.ProductsBasic;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.demo.properties.TestData.*;
import static com.demo.scripts.api.account.ClientInfo.getClientInfo;
import static com.demo.scripts.api.account.Login.secureLogin;
import static com.demo.scripts.api.products.AllProducts.getAllProductsFromType;
import static com.demo.scripts.ui.UserLogin.*;
import static com.demo.scripts.ui.filters.LaveragesFilters.*;
import static com.demo.scripts.ui.products_page.BasicProductPage.*;
import static com.demo.scripts.ui.search.SearchForProductField.checkSearchForProduct;
import static com.demo.utilities.user_interface.GetElementText.*;


public class TestCase_03_Leveraged extends BasicTestConfig {

    private static ProductsBasic productsBasic = PageFactory.initElements(driver, ProductsBasic.class);


    @Test(description = "API", priority = 0)
    public void post_login(Method method) throws Exception {
        fileName = method.getName() + ".json";
        secureLogin(fileName);
    }


    @Test(description = "API", priority = 1)
    public void get_user_info(Method method) throws Exception {
        fileName = method.getName() + ".json";
        getClientInfo(fileName);
    }


    @Test(description = "API", priority = 2)
    public void get_product_list(Method method) throws Exception {
        fileName = method.getName() + ".json";
        getAllProductsFromType(fileName, "leverageds", false);
    }


    @Test(description = "WEB", priority = 3)
    public void web_login() throws Exception {
        secureLoginWeb();
    }


    @Test(description = "WEB", priority = 4)
    public void web_leveraged_filters() throws Exception {
        leveragesFilters();
    }


    @Test(description = "WEB", priority = 5)
    public void web_search_for_product() throws Exception {
        checkSearchForProduct();
    }


    @Test(description = "WEB", priority = 6)
    public void web_open_leveraged_product() throws Exception {
        openProduct();
    }

}
