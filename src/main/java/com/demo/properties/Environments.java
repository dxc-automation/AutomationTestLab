package com.demo.properties;

import static com.demo.properties.TestData.*;



public class Environments {

    //***** HOSTS
    public final static String INT_TEST_HOST    = "internal.degiro.eu";
    public final static String WEB_TEST_HOST    = "test-webtrader.internal.degiro.eu";
    public final static String TEST_HOST        = "trader.degiro.nl";
    public final static String VWD_SERVICE_HOST = "charting.vwdservices.com";


    //***** LOGIN
    public final static String INT_TEST_LOGIN  = "/login/secure/login";
    public final static String INT_TEST_CONFIG = "/login/secure/config";


    //***** PRODUCTS
    public final static String INT_TEST_STOCKS = "/dgproductsearch/secure/v5/stocks";
    public final static String INT_TEST_FUNDS  = "/dgproductsearch/secure/v5/funds";
    public final static String INT_TEST_BONDS  = "/dgproductsearch/secure/v5/bonds";
    public final static String INT_TEST_ETF    = "/dgproductsearch/secure/v5/etfs";

    public final static String INT_TEST_MARKETS_PAGE   = "/paservice/secure/settings/web";

    public final static String INT_TEST_CASH_HISTORY   = "/reporting/secure/v6/accountoverview";
    public final static String INT_TEST_ORDER_HISTORY  = "/reporting/secure/v4/order-history";

    public final static String INT_ORDER               = "/dgtrading/secure/v5/checkOrder;jsessionid=";
    public final static String INT_ORDER_CONFIRMATION  = "/dgtrading/secure/v5/order/";
    public final static String INT_TEST_ACCOUNT_INFO   = "/dgtrading/secure/v5/account/info/";
    public final static String INT_TEST_CLIENT_INFO    = "/paservice/secure/client";
    public final static String INT_TEST_REAL_QUOTES    = "/paservice/secure/settings/vwdModules";
    public final static String INT_TEST_CURRENCY       = "/hchart/v1/deGiro/data.js";

    public final static String WEB_LOGIN               = "/login/ie#/login";
    public final static String WEB_MARKETS             = "/trader4/#/markets";






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


    //  * * * *    H O S T
    public final static String CURRENCY_CALC_HOST   = "currency-converter5.p.rapidapi.com";
    public final static String CURRENCY_CALC_PATH   = "/currency/historical/";
}


