package com.demo.scripts.api.degiro.products;

import org.apache.http.client.utils.URIBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import static com.demo.config.ReporterConfig.startTestReport;
import static com.demo.config.ReporterConfig.test;
import static com.demo.properties.Environments.*;
import static com.demo.properties.TestData.intAccount;
import static com.demo.properties.TestData.sessionID;
import static com.demo.utilities.FileUtility.createLogFile;
import static com.demo.utilities.FileUtility.getFormattedJson;
import static com.demo.utilities.web_services.HttpClientConfig.*;
import static com.demo.utilities.web_services.HttpClientUtils.get;
import static com.demo.utilities.web_services.HttpClientUtils.getClosableHttpClientResponseDetails;

public class Trackers {


    private static String scheme;
    private static String host;
    private static String path;
    private static String status;


    static final Logger LOG = LogManager.getLogger(Trackers.class);

    private static void report() throws Exception {
        String testName        = "<b>[GET] Exchange Traded Funds List </b>";
        String testCategory    = "Frontend";
        String testDescription = "The purpose of this test is to verify that the login functionality is working as expected"              +
                "<br><br><b>*** STEPS DESCRIPTION ***</b><br><br>"                                                       +
                "[1] Check that the login page can be opened and displayed with correct title.<br>"                      +
                "[2] Check the visualization of the login form element by image comparing based on RGB color model.<br>" +
                "[3] Check login with valid credentials.";

        startTestReport(testName, testDescription, testCategory);
    }




    public static void getEtfs(String fileName, int limit, boolean onlyPopular, boolean requireTotal) throws Exception {
        report();

        scheme = "https";
        host   = INTERNAL_HOST;
        path   = INTERNAL_ETF;

        url = new URIBuilder()
                .setScheme(scheme)
                .setHost(host)
                .setPath(path)
                .addParameter("intAccount", String.valueOf(intAccount))
                .addParameter("sessionId", sessionID)
                .addParameter("offset", "0")
                .addParameter("limit", String.valueOf(limit))
                .addParameter("popularOnly", String.valueOf(onlyPopular))
                .addParameter("requireTotal", String.valueOf(requireTotal))
                .addParameter("sortColumns", "name")
                .addParameter("sortTypes", "asc")
                .build();


        response = closeableHttpClient().execute(get(url));
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
                + "<br/>"
                + "</pre>");

        createLogFile(fileName, getFormattedJson(responseBody));

        Assert.assertTrue(responseCode > 199 && responseCode < 300);
    }

}
