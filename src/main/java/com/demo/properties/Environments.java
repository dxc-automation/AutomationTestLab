package com.demo.properties;

import com.demo.config.BasicTestConfig;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import static com.demo.properties.TestData.*;



public class Environments extends BasicTestConfig {
    private static String PRODUCT_SEARCH;
    private static String CLIENT_INFO;
    private static String ORDER;
    private static String ORDER_CONFIRMATION;



    //***** HOSTS
    public static       String HOST;
    public final static String VWD_SERVICE_HOST = "charting.vwdservices.com";


    //***** LOGIN
    public final static String INT_LOGIN  = "/login/secure/login";
    public final static String CONFIG = "/login/secure/config";


    //***** PRODUCTS
    public final static String SEARCH_STOCKS = "/productsnew/secure/v5/stocks";
    public final static String SEARCH_FUNDS  = "/dgproductsearch/secure/v5/funds";
    public final static String SEARCH_BONDS  = "/dgproductsearch/secure/v5/bonds";
    public final static String SEARCH_ETF    = "/dgproductsearch/secure/v5/etfs";

    public final static String INT_TEST_MARKETS_PAGE   = "/paservice/secure/settings/web";

    public final static String INT_TEST_CASH_HISTORY   = "/reporting/secure/v6/accountoverview";
    public final static String INT_TEST_ORDER_HISTORY  = "/reporting/secure/v4/order-history";
    public final static String INT_TEST_REAL_QUOTES    = "/paservice/secure/settings/vwdModules";
    public final static String INT_TEST_CURRENCY       = "/hchart/v1/deGiro/data.js";

    public final static String WEB_LOGIN               = "/login/ie#/login";
    public final static String WEB_MARKETS             = "/trader4/#/markets";



    public  static String setPlaceOrderPath() {
        if (env.equalsIgnoreCase("internal")) {
            ORDER = "/dgtrading/secure/v5/checkOrder;jsessionid=";
        } else if (env.equalsIgnoreCase("web-trader")) {
            ORDER = "/DGTrading/secure/v5/checkOrder;jsessionid=";
        } else if (env.equalsIgnoreCase("production")) {
            ORDER = "/dgtrading/secure/v5/checkOrder;jsessionid=";
        }
        return ORDER;
    }


    public  static String setOrderConfirmationPath() {
        if (env.equalsIgnoreCase("internal")) {
            ORDER_CONFIRMATION = "/dgtrading/secure/v5/order/";
        } else if (env.equalsIgnoreCase("web-trader")) {
            ORDER_CONFIRMATION = "/DGTrading/secure/v5/order/";
        } else if (env.equalsIgnoreCase("production")) {
            ORDER_CONFIRMATION = "/dgtrading/secure/v5/order/";
        }
        return ORDER_CONFIRMATION;
    }


    public  static String setClientInfoPath() {
        if (env.equalsIgnoreCase("internal")) {
            CLIENT_INFO = "/paservice/secure/client";
        } else if (env.equalsIgnoreCase("web-trader")) {
            CLIENT_INFO = "/dgpawebservice/secure/client";
        } else if (env.equalsIgnoreCase("production")) {
            CLIENT_INFO = "/paservice/secure/client";
        }
        return CLIENT_INFO;
    }

    public  static String setProductSearchPath() {
        if (env.equalsIgnoreCase("internal")) {
            PRODUCT_SEARCH = "/dgproductsearch/secure/v5/products/lookup";
        } else if (env.equalsIgnoreCase("web-trader")) {
            PRODUCT_SEARCH = "/productsnew/secure/v5/products/lookup";
        } else if (env.equalsIgnoreCase("production")) {
            PRODUCT_SEARCH = "/dgproductsearch/secure/v5/products/lookup";
        }
        return PRODUCT_SEARCH;
    }




    //  * * * *    A M A Z O N
    public static String AMAZON_BASE_URL;


    //  * * * *    N E W S
    public static String NEWS_BASIC_URL;


    //  * * * *    F L Y T
    public static String FLY_HOST;
    public final static String BOOTSTRAP     = "/v1/apps/bootstrap";
    public final static String ADD_USER      = "/v1/user";
    public final static String LOGIN         = "/v2/auth/login";
    public final static String USER_INFO     = "/v1/user/me";
    public final static String ADD_USER_INFO = "/v1/user/me/profile-field";
    public final static String NEW_TOKEN     = "/users/v1/token";

}


