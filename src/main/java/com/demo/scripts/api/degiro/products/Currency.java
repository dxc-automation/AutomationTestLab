package com.demo.scripts.api.degiro.products;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;

import static com.demo.config.ReporterConfig.startTestReport;
import static com.demo.config.ReporterConfig.test;
import static com.demo.properties.Environments.*;
import static com.demo.properties.TestData.intAccount;
import static com.demo.properties.TestData.sessionID;
import static com.demo.utilities.FileUtility.*;
import static com.demo.utilities.web_services.HttpClientConfig.*;
import static com.demo.utilities.web_services.HttpClientUtils.get;
import static com.demo.utilities.web_services.HttpClientUtils.getClosableHttpClientResponseDetails;

public class Currency {


    private static String scheme;
    private static String host;
    private static String path;
    private static String status;


    static final Logger LOG = LogManager.getLogger(Currency.class);

    private static void report() throws Exception {
        String testName        = "<b>[GET] Currency Details</b>";
        String testCategory    = "Frontend";
        String testDescription = "The purpose of this test is to verify that the login functionality is working as expected"              +
                "<br><br><b>*** STEPS DESCRIPTION ***</b><br><br>"                                                       +
                "[1] Check that the login page can be opened and displayed with correct title.<br>"                      +
                "[2] Check the visualization of the login form element by image comparing based on RGB color model.<br>" +
                "[3] Check login with valid credentials.";

        startTestReport(testName, testDescription, testCategory);
    }




    public static void getCurrencyDetails(String fileName, String currency) throws Exception {
        report();

        scheme = "https";
        host   = VWD_SERVICE_HOST;
        path   = INT_TEST_CURRENCY;

        url = new URIBuilder()
                .setScheme(scheme)
                .setHost(host)
                .setPath(path)
                .addParameter("requestid", "0")
                .addParameter("resolution", "PT1M")
                .addParameter("culture", "en-US")
                .addParameter("period", "P1D")
                .addParameter("series", "issueid:" + currency)
                .addParameter("series", "price:issueid:" + currency)
                .addParameter("format", "json")
                .addParameter("callback", "vwd.hchart.seriesRequestManager.sync_response")
                .addParameter("userToken", "463421")
                .addParameter("tz", "Europe/Dublin")
                .build();



        response = closeableHttpClient().execute(get(url));
        getClosableHttpClientResponseDetails(response);
        createDebugFile(fileName, responseStringEntity);


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

        Assert.assertTrue(responseCode > 199 && responseCode < 300);
    }

}
