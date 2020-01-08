package com.demo.test_scripts.api;

import com.jayway.jsonpath.JsonPath;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.Assert;

import static com.demo.test_properties.TestData.*;


import static com.demo.test_properties.UrlPaths.BOOTSTRAP;
import static com.demo.config.ReporterConfig.*;
import static demo.config.HttpClientConfig.closeableHttpClient;
import static demo.config.HttpClientConfig.*;
import static com.demo.utilities.FileUtility.createLogFile;
import static com.demo.utilities.FileUtility.getFormattedJson;

public class AccessToken {


    private static String scheme;
    private static String host;
    private static String path;
    private static String parameterName;
    private static String parameterValue;

    static final Logger LOG = LogManager.getLogger(AccessToken.class);

    private static void report() throws Exception {
        String testName        = "Get access token";
        String testDescription = "The purpose of this test_suites is to verify that the user can log in RAM successfully.";
        String testCategory    = "RAM";

        startTestReport(testName, testDescription, testCategory);
    }




    public static void getAccessToken(String fileName) throws Exception {
        report();

        scheme = "https";
        host   = FLY_HOST;
        path   = BOOTSTRAP;

        url = new URIBuilder()
                .setScheme(scheme)
                .setHost(host)
                .setPath(path)
                .build();



        JSONObject jsonPostData = new JSONObject();
        jsonPostData.put("deviceToken", "07304e56c452be73ad2b51a4647d0300");
        jsonPostData.put("platform", "Android");
        jsonPostData.put("platformVersion", "6.0");
        jsonPostData.put("appId", 1);
        jsonPostData.put("frameworkVersion", "1.0.0");
        jsonPostData.put("model", "LG Nexus 5X");
        jsonPostData.put("appVersion", "1.0.0");

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
                + "Path:     " + path
                + "<br/>"
                + "<br/>"
                + getFormattedJson(jsonPostData.toString())
                + "<br/>"
                + "<br/>"
                + "</pre>");

        createLogFile(fileName, getFormattedJson(responseBody));
        Assert.assertTrue(responseCode > 199 && responseCode < 300);

        try {
            accessToken = JsonPath.read(responseStringEntity, "$.accessToken");
        } catch (Exception e) {
            e.printStackTrace();
            test.warning(e.getMessage());
        }
    }

}
