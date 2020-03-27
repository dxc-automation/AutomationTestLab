package com.demo.tests;

import com.demo.config.BasicTestConfig;
import com.demo.objects.products.ProductsBasic;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.demo.properties.TestData.fileName;
import static com.demo.scripts.api.account.ClientInfo.getClientInfo;
import static com.demo.scripts.api.account.Login.secureLogin;
import static com.demo.scripts.api.products.AllProducts.getAllProductsFromType;
import static com.demo.scripts.ui.UserLogin.secureLoginWeb;
import static com.demo.scripts.ui.filters.EtfsFilters.*;
import static com.demo.scripts.ui.products_page.BasicProductPage.openProduct;

public class TestCase_06_Etfs extends BasicTestConfig {

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
        getAllProductsFromType(fileName, "bonds", false);
    }


    @Test(description = "WEB", priority = 3)
    public void web_login() throws Exception {
        secureLoginWeb();
    }


    @Test(description = "WEB", priority = 4)
    public void web_etfs_filters() throws Exception {
        etfsFilters();
    }


    @Test(description = "WEB", priority = 5)
    public void web_open_etfs_product() throws Exception {
        openProduct();
    }

}
