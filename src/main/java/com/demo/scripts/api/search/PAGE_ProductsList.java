package com.demo.scripts.api.search;

import com.jayway.jsonpath.JsonPath;
import org.apache.http.client.utils.URIBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import static com.demo.config.ReporterConfig.startTestReport;
import static com.demo.config.ReporterConfig.test;
import static com.demo.properties.Environments.HOST;
import static com.demo.properties.Environments.setProductSearchPath;
import static com.demo.properties.TestData.*;
import static com.demo.utilities.FileUtility.createLogFile;
import static com.demo.utilities.FileUtility.getFormattedJson;
import static com.demo.utilities.web_services.HttpClientConfig.*;
import static com.demo.utilities.web_services.HttpClientUtils.get;
import static com.demo.utilities.web_services.HttpClientUtils.getClosableHttpClientResponseDetails;

public class PAGE_ProductsList {


    private static String scheme;
    private static String host;
    private static String path;


    static final Logger LOG = LogManager.getLogger(PAGE_ProductsList.class);

    private static void report() throws Exception {
        String testName        = "<b>[GET] SearchFromPlaceOrderSidePanel Stocks</b>";
        String testCategory    = "API";
        String testDescription = "The purpose of this test is to verify that the login functionality is working as expected"              +
                "<br><br><b>*****   D E S C R I P T I O N   *****</b><br><br>"                                                       +
                "[1] Check that the login page can be opened and displayed with correct title.<br>"                      +
                "[2] Check the visualization of the login form element by image comparing based on RGB color model.<br>" +
                "[3] Check login with valid credentials.";

        startTestReport(testName, testDescription, testCategory);
    }




    public static void takeProductsList(String fileName, int limit, boolean popularOnly) throws Exception {
        report();

        scheme = "https";
        host   = HOST;
        path   = setProductSearchPath();


        url = new URIBuilder()
                .setScheme(scheme)
                .setHost(host)
                .setPath(path)
                .setParameter("intAccount",       String.valueOf(intAccount))
                .setParameter("sessionId",        sessionID)
                .setParameter("popularOnly",      String.valueOf(popularOnly))
                .setParameter("limit",            String.valueOf(limit))
                .setParameter("offset",           "0")
                .setParameter("indexId",     "114005")
                .setParameter("stockCountryId","963")
                .setParameter("requireTotal",     "true")
                .setParameter("sortColumns",      "name")
                .setParameter("sortTypes",        "asc")
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



        Assert.assertTrue(responseCode > 199 && responseCode < 300);
    }

}
