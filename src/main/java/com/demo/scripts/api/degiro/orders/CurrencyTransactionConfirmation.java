package com.demo.scripts.api.degiro.orders;

import com.jayway.jsonpath.JsonPath;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.Assert;

import static com.demo.config.ReporterConfig.startTestReport;
import static com.demo.config.ReporterConfig.test;
import static com.demo.properties.Environments.INT_TEST_CURRENCY_EXC;
import static com.demo.properties.Environments.INT_TEST_HOST;
import static com.demo.properties.TestData.*;
import static com.demo.utilities.FileUtility.createLogFile;
import static com.demo.utilities.FileUtility.getFormattedJson;
import static com.demo.utilities.web_services.HttpClientConfig.*;
import static com.demo.utilities.web_services.HttpClientUtils.getClosableHttpClientResponseDetails;
import static com.demo.utilities.web_services.HttpClientUtils.post;

public class CurrencyTransactionConfirmation {


    private static String scheme;
    private static String host;
    private static String path;
    private static String status;


    static final Logger LOG = LogManager.getLogger(CurrencyTransactionConfirmation.class);

    private static void report() throws Exception {
        String testName        = "<b>[POST] Currency Transaction</b>";
        String testCategory    = "Frontend";
        String testDescription = "The purpose of this test is to verify that the login functionality is working as expected"              +
                "<br><br><b>*** STEPS DESCRIPTION ***</b><br><br>"                                                       +
                "[1] Check that the login page can be opened and displayed with correct title.<br>"                      +
                "[2] Check the visualization of the login form element by image comparing based on RGB color model.<br>" +
                "[3] Check login with valid credentials.";

        startTestReport(testName, testDescription, testCategory);
    }




    public static void currencyTransaction(String fileName, int amount, String orderType) throws Exception {
        report();

        scheme = "https";
        host   = INT_TEST_HOST;
        path   = INT_TEST_CURRENCY_EXC + confirmationId;

        url = new URIBuilder()
                .setScheme(scheme)
                .setHost(host)
                .setPath(path)
                .addParameter("intAccount", String.valueOf(intAccount))
                .addParameter("sessionId", sessionID)
                .build();



        JSONObject jsonPostData = new JSONObject();
        jsonPostData.put("size", amount);
        jsonPostData.put("buySell", orderType);
        jsonPostData.put("timeType", 1);
        jsonPostData.put("orderType", 1);
        jsonPostData.put("price",     productPrice);
        jsonPostData.put("productId", productId);

        StringEntity entity = new StringEntity(jsonPostData.toString());

        response = closeableHttpClient().execute(post(entity, url));
        getClosableHttpClientResponseDetails(response);


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
                + getFormattedJson(jsonPostData.toString())
                + "<br/>"
                + "<br/>"
                + "</pre>");

        createLogFile(fileName, getFormattedJson(responseBody));

        try {
            orderId = JsonPath.read(responseStringEntity, "$..orderId");
            test.pass("<pre>"
                    + "<br/>"
                    + "<center><b>* * * * * * * *    INFORMATION    * * * * * * * *</b></center>"
                    + "<br />"
                    + "<br />"
                    + "Your order ID is " + orderId
                    + "<br />"
                    + "<br/>"
                    + "</pre>");
        } catch (Exception e) {
            System.out.println("Get values from the response body has failed");
        }

        Assert.assertTrue(responseCode > 199 && responseCode < 300);
    }

}
