package com.demo.properties;


public class Environments {

    //***** HOSTS
    public final static String INTERNAL_HOST = "internal.degiro.eu";
    public final static String WEB_TEST_HOST = "test-webtrader.internal.degiro.eu";
    public final static String TEST_HOST     = "https://trader.degiro.nl/login/uk";


    //***** LOGIN
    public final static String INTERNAL_LOGIN                = "/login/secure/login";
    public final static String INTERNAL_CONFIG               = "/login/secure/config";


    //***** PRODUCTS
    public final static String INTERNAL_STOCKS               = "/dgproductsearch/secure/v5/stocks";
    public final static String INTERNAL_FUNDS                = "/dgproductsearch/secure/v5/funds";
    public final static String INTERNAL_BONDS                = "/dgproductsearch/secure/v5/bonds";
    public final static String INTERNAL_ETF                  = "/dgproductsearch/secure/v5/etfs";

    public final static String INTERNAL_CASH_HISTORY         = "/reporting/secure/v6/accountoverview";
    public final static String INTERNAL_ORDER_HISTORY        = "/reporting/secure/v4/order-history";

    public final static String INTERNAL_CURRENCY_EXC         = "/dgtrading/secure/v5/checkOrder";
    public final static String INTERNAL_CURRENCY_EXC_CONFIRM = "/dgtrading/secure/v5/order/";
    public final static String INTERNAL_ACCOUNT_INFO         = "/dgtrading/secure/v5/account/info/";
    public final static String INTERNAL_CLIENT_INFO          = "/paservice/secure/client";
    public final static String INTERNAL_REAL_QUOTES          = "/paservice/secure/settings/vwdModules";

    public final static String WEB_LOGIN              = "/login/ie#/login";
    public final static String WEB_MARKETS            = "/trader4/#/markets";






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


