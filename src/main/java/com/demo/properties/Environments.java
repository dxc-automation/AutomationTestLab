package com.demo.properties;

import com.demo.config.BasicTestConfig;

import static com.demo.utilities.FileUtility.getDate;
import static com.demo.properties.TestData.*;



public class Environments extends BasicTestConfig {
    private static String PRODUCT_LIST;
    private static String CLIENT_INFO;
    private static String ORDER;
    private static String ORDER_CONFIRMATION;
    private static String TRANSACTIONS_HISTORY;



    //***** HOSTS
    public static       String HOST;
    public final static String VWD_SERVICE_HOST = "charting.vwdservices.com";


    //***** PAGES
    public final static String TRANSACTIONS_PAGE       = "https://" + HOST + "/trader4/#/transactions?fromDate=2020-02-04&toDate=" + getDate() + "&groupTransactionsByOrder=false";
    public final static String ORDERS_OUTSTANDING_PAGE = "/trader4/#/orders/open";
    public final static String ORDERS_HISTORY_PAGE     = "/trader4/#/orders/history";
    public final static String ACCOUNT_OVERVIEW_PAGE   = "/trader4/#/account-overview";
    public final static String SHARES_PAGE             = "https://" + HOST + "/trader4/#/search?productType=1&stockListType=index&stockList=114005&country=963&sortColumns=name&sortTypes=asc";
    private static String LEVERAGES_PAGE;

    public static String getLeveragesPage(int exchange, int shortLong, int issuer, int underlying) {
        LEVERAGES_PAGE = "/trader4/#/products?productType=14&exchange="
                + exchange + "&shortLong="
                + shortLong + "&issuer="
                + issuer + "&underlying="
                + underlying + "&popularOnly=false&sortColumns=name&sortTypes=asc";

        return LEVERAGES_PAGE;
    }


    //***** LOGIN
    public final static String INT_LOGIN  = "/login/secure/login";
    public final static String CONFIG     = "/login/secure/config";


    //***** PRODUCTS
    public final static String INT_TEST_MARKETS_PAGE   = "/paservice/secure/settings/web";
    public final static String INT_TEST_CASH_HISTORY   = "/reporting/secure/v6/accountoverview";
    public final static String INT_TEST_ORDER_HISTORY  = "/reporting/secure/v4/order-history";
    public final static String INT_TEST_REAL_QUOTES    = "/paservice/secure/settings/vwdModules";
    public final static String INT_TEST_CURRENCY       = "/hchart/v1/deGiro/data.js";

    public final static String WEB_LOGIN               = "/login/ie#/login";
    public final static String WEB_MARKETS             = "/trader4/#/markets";




    public  static String setTransactionsHistoryPath() {
        if (env.equalsIgnoreCase("internal")) {
            TRANSACTIONS_HISTORY = "/reporting/secure/v4/transactions";
        } else if (env.equalsIgnoreCase("webtrader")) {
            TRANSACTIONS_HISTORY = "/DGReportingWeb/secure/v4/transactions";
        } else if (env.equalsIgnoreCase("weekly")) {
            TRANSACTIONS_HISTORY = "/reporting/secure/v4/transactions";
        }
        return TRANSACTIONS_HISTORY;
    }



    public  static String setPlaceOrderPath() {
        if (env.equalsIgnoreCase("internal")) {
            ORDER = "/dgtrading/secure/v5/checkOrder;jsessionid=";
        } else if (env.equalsIgnoreCase("webtrader")) {
            ORDER = "/DGTrading/secure/v5/checkOrder;jsessionid=";
        } else if (env.equalsIgnoreCase("weekly")) {
            ORDER = "/dgtrading/secure/v5/checkOrder;jsessionid=";
        }
        return ORDER;
    }


    public  static String setOrderConfirmationPath() {
        if (env.equalsIgnoreCase("internal")) {
            ORDER_CONFIRMATION = "/dgtrading/secure/v5/order/";
        } else if (env.equalsIgnoreCase("webtrader")) {
            ORDER_CONFIRMATION = "/DGTrading/secure/v5/order/";
        } else if (env.equalsIgnoreCase("weekly")) {
            ORDER_CONFIRMATION = "/dgtrading/secure/v5/order/";
        }
        return ORDER_CONFIRMATION;
    }


    public  static String setClientInfoPath() {
        if (env.equalsIgnoreCase("internal")) {
            CLIENT_INFO = "/paservice/secure/client";
        } else if (env.equalsIgnoreCase("webtrader")) {
            CLIENT_INFO = "/dgpawebservice/secure/client";
        } else if (env.equalsIgnoreCase("weekly")) {
            CLIENT_INFO = "/paservice/secure/client";
        }
        return CLIENT_INFO;
    }


    public  static String setProductSearchPath() {
        if (env.equalsIgnoreCase("internal")) {
            PRODUCT_LIST = "/dgproductsearch/secure/v5/";
        } else if (env.equalsIgnoreCase("webtrader")) {
            PRODUCT_LIST = "/productsnew/secure/v5/";
        } else if (env.equalsIgnoreCase("weekly")) {
            PRODUCT_LIST = "/dgproductsearch/secure/v5/";
        }
        return PRODUCT_LIST;
    }


    public static String getProductWebPage(int product) {
        String productURL = "https://" + HOST + "/trader4/#/products?productType=" + product;
        return  productURL;
    }
}


