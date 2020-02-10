package com.demo.scripts.api.fly;

import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.Assert;

import static com.demo.config.ReporterConfig.startTestReport;
import static com.demo.config.ReporterConfig.test;
import static com.demo.properties.TestData.accessToken;
import static com.demo.properties.Environments.FLY_HOST;
import static com.demo.properties.Environments.NEW_TOKEN;
import static com.demo.utilities.FileUtility.createLogFile;
import static com.demo.utilities.FileUtility.getFormattedJson;
import static com.demo.utilities.web_services.HttpClientConfig.*;
import static com.demo.utilities.web_services.HttpClientUtils.getClosableHttpClientResponseDetails;
import static com.demo.utilities.web_services.HttpClientUtils.post;

public class NewToken {


    private static String scheme;
    private static String host;
    private static String path;

    static final Logger LOG = LogManager.getLogger(NewToken.class);

    private static void report() throws Exception {
        String testName        = "Create New Access Token";
        String testDescription = "The purpose of this test is to verify that the username can be changed successfully.";
        String testCategory    = "API";

        startTestReport(testName, testDescription, testCategory);
    }




    public static void addUserInfo(String fileName) throws Exception {
        report();

        scheme = "https";
        host   = FLY_HOST;
        path   = NEW_TOKEN;

        url = new URIBuilder()
                .setScheme(scheme)
                .setHost(host)
                .setPath(path)
                .addParameter("accessToken", accessToken)
                .build();


        JSONObject jsonPostData = new JSONObject();
        jsonPostData.put("channelId", "1d1094bb-6340-49ca-8bee-c6746425cc48");
        jsonPostData.put("deviceId", "10:25:ec:5f:c5:55");
        jsonPostData.put("model", "HUAWEI Nexus 6P");
        jsonPostData.put("platform", "Android");
        jsonPostData.put("platformVersion", "7.2.1");
        jsonPostData.put("frameworkVersion", "7.3.2");
        jsonPostData.put("appVersion", "5.6.2");

        StringEntity entity = new StringEntity(jsonPostData.toJSONString());

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
