package com.demo.test_scripts.api;

import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.Assert;

import static com.demo.config.ReporterConfig.startTestReport;
import static com.demo.config.ReporterConfig.test;
import static com.demo.test_properties.TestData.accessToken;
import static com.demo.test_properties.UrlPaths.ADD_USER;
import static com.demo.test_properties.UrlPaths.ADD_USER_INFO;
import static com.demo.test_properties.UrlPaths.FLY_HOST;
import static com.demo.utilities.FileUtility.createLogFile;
import static com.demo.utilities.FileUtility.getFormattedJson;
import static com.demo.utilities.web_services.HttpClientConfig.*;
import static com.demo.utilities.web_services.HttpClientUtils.getClosableHttpClientResponseDetails;
import static com.demo.utilities.web_services.HttpClientUtils.post;

public class AddUserInfo {


    private static String scheme;
    private static String host;
    private static String path;

    static final Logger LOG = LogManager.getLogger(AddUserInfo.class);

    private static void report() throws Exception {
        String testName        = "Add account info";
        String testDescription = "The purpose of this test is to verify that the username can be changed successfully.";
        String testCategory    = "API";

        startTestReport(testName, testDescription, testCategory);
    }




    public static void addUserInfo(String fileName) throws Exception {
        report();

        scheme = "https";
        host   = FLY_HOST;
        path   = ADD_USER_INFO;

        url = new URIBuilder()
                .setScheme(scheme)
                .setHost(host)
                .setPath(path)
                .addParameter("accessToken", accessToken)
                .build();


        JSONObject jsonPostData = new JSONObject();
        jsonPostData.put("profileFields", "name");
        jsonPostData.put("value", "Automation_Test");

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
