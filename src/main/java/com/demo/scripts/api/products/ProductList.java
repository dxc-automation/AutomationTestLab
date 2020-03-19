package com.demo.scripts.api.products;

import com.jayway.jsonpath.JsonPath;
import org.apache.http.client.utils.URIBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import static com.demo.config.ReporterConfig.startTestReport;
import static com.demo.config.ReporterConfig.test;
import static com.demo.properties.Environments.*;
import static com.demo.properties.TestData.*;
import static com.demo.utilities.FileUtility.*;
import static com.demo.utilities.web_services.HttpClientConfig.*;
import static com.demo.utilities.web_services.HttpClientUtils.get;
import static com.demo.utilities.web_services.HttpClientUtils.getClosableHttpClientResponseDetails;

public class ProductList {


    private static String scheme;
    private static String host;
    private static String path;
    private static String status;


    static final Logger LOG = LogManager.getLogger(ProductList.class);

    private static void report() throws Exception {
        String testName        = "<b>[GET] Product List</b>";
        String testCategory    = "API";
        String testDescription = "The purpose of this test is to verify that the login functionality is working as expected"              +
                "<br><br><b>*****   D E S C R I P T I O N   *****</b><br><br>"                                                       +
                "[1] Check that the login page can be opened and displayed with correct title.<br>"                      +
                "[2] Check the visualization of the login form element by image comparing based on RGB color model.<br>" +
                "[3] Check login with valid credentials.";

        startTestReport(testName, testDescription, testCategory);
    }




    public static void getProductList(String fileName, int productTypeId, int offset, int limit, String searchText) throws Exception {
        report();

        scheme = "https";
        host   = HOST;
        path   = setProductSearchPath();


        url = new URIBuilder()
                .setScheme(scheme)
                .setHost(host)
                .setPath(path)
                .setParameter("intAccount",    String.valueOf(intAccount))
                .setParameter("sessionId",     sessionID)
                .setParameter("productTypeId", String.valueOf(productTypeId))
                .setParameter("offset",        String.valueOf(offset))
                .setParameter("limit",         String.valueOf(limit))
                .setParameter("searchText",    searchText)
                .build();


        response = closeableHttpClient().execute(get(url));
        getClosableHttpClientResponseDetails(response);
        createLogFile(fileName, getFormattedJson(responseBody));


        test.info("<pre>"
                + "<br/>"
                + "<center><b>* * * * * * * *    R E Q U E S T    * * * * * * * *</b></center>"
                + "<br />"
                + "<br />"
                + "Method:   " + requestMethod
                + "<br />"
                + "Host:     " + scheme + "://" + host
                + "<br />"
                + "Path:     " + path + "/" + url.getQuery()
                + "<br/>"
                + "<br/>"
                + "<br/>"
                + "</pre>");


        try {
            productName     = JsonPath.read(responseStringEntity, "$.search[1].name");
            productId       = JsonPath.read(responseStringEntity, "$.search[1].id");
            productType     = JsonPath.read(responseStringEntity, "$.search[1].productType");
            productCurrency = JsonPath.read(responseStringEntity, "$.search[1].currency");
            productIsin     = JsonPath.read(responseStringEntity, "$.search[1].isin");
            productCategory = JsonPath.read(responseStringEntity, "$.search[1].category");

            test.info("<pre>"
                    + "<br>"
                    + "<center><b>* * * * * * * *    PRODUCT  INFORMATION    * * * * * * * *</b></center>"
                    + "<br>"
                    + "ID:       " + productId
                    + "<br>"
                    + "Name:     " + productName
                    + "<br>"
                    + "ISIN:     " + productIsin
                    + "<br>"
                    + "Type:     " + productType
                    + "<br>"
                    + "Category: " + productCategory
                    + "<br>"
                    + "Currency: " + productCurrency
                    + "<br>"
                    + "<br>"
                    + "</pre>");
        } catch (Exception e) {
            String ex = e.getLocalizedMessage();
            test.warning(ex);
            e.printStackTrace();
        }

        Assert.assertTrue(responseCode > 199 && responseCode < 300);
    }

}
