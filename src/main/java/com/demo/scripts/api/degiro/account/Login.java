package com.demo.scripts.api.degiro.account;

import com.jayway.jsonpath.JsonPath;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.Assert;

import static com.demo.config.ReporterConfig.startTestReport;
import static com.demo.config.ReporterConfig.test;
import static com.demo.properties.Environments.*;
import static com.demo.properties.TestData.*;
import static com.demo.utilities.FileUtility.createLogFile;
import static com.demo.utilities.FileUtility.getFormattedJson;
import static com.demo.utilities.web_services.HttpClientConfig.*;
import static com.demo.utilities.web_services.HttpClientUtils.getClosableHttpClientResponseDetails;
import static com.demo.utilities.web_services.HttpClientUtils.post;

public class Login {


    private static String scheme;
    private static String host;
    private static String path;
    private static String status;


    static final Logger LOG = LogManager.getLogger(Login.class);

    private static void report() throws Exception {
        String testName        = "<b>[POST] Login</b>";
        String testCategory    = "Frontend";
        String testDescription = "The purpose of this test is to verify that the login functionality is working as expected"              +
                "<br><br><b>*** STEPS DESCRIPTION ***</b><br><br>"                                                       +
                "[1] Check that the login page can be opened and displayed with correct title.<br>"                      +
                "[2] Check the visualization of the login form element by image comparing based on RGB color model.<br>" +
                "[3] Check login with valid credentials.";

        startTestReport(testName, testDescription, testCategory);
    }




    public static void secureLogin(String fileName) throws Exception {
        report();

        scheme = "https";
        host   = INTERNAL_HOST;
        path   = INTERNAL_LOGIN;

        url = new URIBuilder()
                .setScheme(scheme)
                .setHost(host)
                .setPath(path)
                .build();



        JSONObject jsonPostData = new JSONObject();
        jsonPostData.put("username", INTERNAL_USER);
        jsonPostData.put("password", INTERNAL_PASS);
        jsonPostData.put("isPassCodeReset", false);
        jsonPostData.put("isRedirectToMobile", false);
        jsonPostData.put("queryParams", null);


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
            sessionID = JsonPath.read(responseStringEntity, "$.sessionId");
            status    = JsonPath.read(responseStringEntity, "$.statusText");
            test.pass("<pre>"
                    + "<br/>"
                    + "<center><b>* * * * * * * *    INFORMATION    * * * * * * * *</b></center>"
                    + "<br />"
                    + "<br />"
                    + "Session ID:   " + sessionID
                    + "<br />"
                    + "<br/>"
                    + "</pre>");
        } catch (Exception e) {
            System.out.println("Get values from the response body has failed");
        }

        Assert.assertTrue(responseCode > 199 && responseCode < 300);
        Assert.assertEquals(status, "success");
    }

}
