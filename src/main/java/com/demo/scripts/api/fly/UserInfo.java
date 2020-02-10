package com.demo.scripts.api.fly;

import org.apache.http.client.utils.URIBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.Assert;

import static com.demo.properties.Environments.FLY_HOST;
import static com.demo.properties.TestData.accessToken;
import static com.demo.properties.Environments.USER_INFO;
import static com.demo.config.ReporterConfig.startTestReport;
import static com.demo.config.ReporterConfig.test;
import static com.demo.utilities.web_services.HttpClientConfig.*;
import static com.demo.utilities.web_services.HttpClientUtils.*;
import static com.demo.utilities.FileUtility.createLogFile;
import static com.demo.utilities.FileUtility.getFormattedJson;

public class UserInfo {


    private static String scheme;
    private static String host;
    private static String path;

    static final Logger LOG = LogManager.getLogger(UserInfo.class);

    private static void report() throws Exception {
        String testName        = "Get User Info";
        String testDescription = "The purpose of this test is to verify that the correct user details are received.";
        String testCategory    = "API";

        startTestReport(testName, testDescription, testCategory);
    }




    public static void getUserInfo(String fileName) throws Exception {
        report();

        scheme = "https";
        host   = FLY_HOST;
        path   = USER_INFO;

        url = new URIBuilder()
                .setScheme(scheme)
                .setHost(host)
                .setPath(path)
                .addParameter("accessToken", accessToken)
                .build();


        JSONObject jsonPostData = new JSONObject();

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
                + "Path:     " + path   + "/"   + url.getQuery()
                + "<br/>"
                + "<br/>"
                + getFormattedJson(jsonPostData.toString())
                + "<br/>"
                + "<br/>"
                + "</pre>");

        createLogFile(fileName, getFormattedJson(responseBody));
        Assert.assertTrue(responseCode > 199 && responseCode < 300);
    }

}
