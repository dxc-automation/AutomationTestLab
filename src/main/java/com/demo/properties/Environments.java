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
    private static String TRANSACTIONS_HISTORY;



    //***** HOSTS
    public static       String HOST;
    public final static String VWD_SERVICE_HOST = "charting.vwdservices.com";


    //***** PAGES
    public final static String TRANSACTIONS_PAGE       = "/trader4/#/transactions";
    public final static String ORDERS_OUTSTANDING_PAGE = "/trader4/#/orders/open";
    public final static String ORDERS_HISTORY_PAGE     = "/trader4/#/orders/history";
    public final static String ACCOUNT_OVERVIEW_PAGE   = "/trader4/#/account-overview";


    //***** LOGIN
    public final static String INT_LOGIN  = "/login/secure/login";
    public final static String CONFIG     = "/login/secure/config";


    //***** PRODUCTS
    public final static String INT_TEST_MARKETS_PAGE   = "/paservice/secure/settings/web";
    public final static String INT_TEST_CASH_HISTORY   = "/reporting/secure/v6/accountoverview";
    public final static String INT_TEST_ORDER_HISTORY  = "/reporting/secure/v4/order-history";
    public final static String INT_TEST_REAL_QUOTES    = "/paservice/secure/settings/vwdModules";
    public final static String INT_TEST_CURRENCY       = "/hchart/v1/deGiro/data.js";
    public final static String TEST_SEARCH_PRODUCTS    = "/productsnew/secure/v5/";

    public final static String WEB_LOGIN               = "/login/ie#/login";
    public final static String WEB_MARKETS             = "/trader4/#/markets";




    public  static String setTransactionsHistoryPath() {
        if (env.equalsIgnoreCase("internal")) {
            TRANSACTIONS_HISTORY = "/reporting/secure/v4/transactions";
        } else if (env.equalsIgnoreCase("web-trader")) {
            TRANSACTIONS_HISTORY = "/DGReportingWeb/secure/v4/transactions";
        } else if (env.equalsIgnoreCase("production")) {
            TRANSACTIONS_HISTORY = "/reporting/secure/v4/transactions";
        }
        return TRANSACTIONS_HISTORY;
    }



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
            PRODUCT_SEARCH = "/productsnew/secure/v5/";
        } else if (env.equalsIgnoreCase("production")) {
            PRODUCT_SEARCH = "/dgproductsearch/secure/v5/products/lookup";
        }
        return PRODUCT_SEARCH;
    }
}


