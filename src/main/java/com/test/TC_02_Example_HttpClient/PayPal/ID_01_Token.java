package com.test.TC_02_Example_HttpClient.PayPal;

import com.aventstack.extentreports.AnalysisStrategy;
import com.setup.BasicSetup;
import com.setup.ExtentManager;
import okhttp3.*;

import org.apache.http.client.utils.URIBuilder;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.Method;


import static com.setup.ExtentManager.extent;
import static com.setup.ExtentManager.test;
import static com.setup.HttpClientUtils.*;
import static com.setup.OkHttpClientUtils.*;
import static com.setup.OkHttpClientUtils.requestMethod;

public class ID_01_Token extends BasicSetup {

    public static String paypalAccessToken = "";

    @BeforeClass
    public void startTest() throws Exception {
        extent = ExtentManager.GetExtent();
        test = extent.createTest("[ID_01] Get Access Token", "DESCRIPTION");
        test.assignAuthor("Pavel Popov");
        test.assignCategory("POST");
        extent.setAnalysisStrategy(AnalysisStrategy.TEST);
    }

    @Test
    public void getToken(Method testMethod) throws Exception {

        String fileName = testMethod.getName() + ".json";

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");

        RequestBody requestBody = RequestBody.create(mediaType, "grant_type=client_credentials");

        url = new URIBuilder()
                .setScheme("https")
                .setHost("api.sandbox.paypal.com")
                .setPath("/v1/oauth2/token")
                .build();


        request = new Request.Builder()
                .url(url.toURL())
                .post(requestBody)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Basic QWY4SU0wMFNPZFVCUmhwMzY3ODBwUEZZUEdHWExoZEdoZHptLS1MQVVrQThibXE1STVyajgwcVI5Q1dlT2cwa1hQZjRTOS1CQl9qUTgxYlU6RUstYThOdVB1ZzcyRmt5VEhwTTNjc2RIQUtqYUhYQ2ZKOG5MTlQ3RTM2bkxGVmlPd0MwVWRycHcxeFdCZGhNbVowMjJfV04xeUlqSVlyRkc=")
                .addHeader("Accept", "*/*")
                .addHeader("Cache-Control", "no-cache")
                .addHeader("Host", "api.sandbox.paypal.com")
                .addHeader("Accept-Encoding", "gzip, deflate")
                .addHeader("Content-Length", "29")
                .addHeader("Connection", "keep-alive")
                .build();

        postRequest(fileName, request);


        test.info("<pre>"
                + "[ REQUEST  HEADERS ]"
                + "<br />"
                + "<br />"
                + "Method:   "    + requestMethod
                + "<br />"
                + "Protocol: "    + requestURLScheme.toUpperCase()
                + "<br />"
                + "Host:     "    + requestURLHost
                + "<br />"
                + "Path:     "    + requestURLPath
                + "<br />"
                + "<br />"
                + requestOkClientHeaders
                + "<br />"
                + "<br />"
                + "[ REQUEST  BODY ]"
                + "<br />"
                + "<br />"
                + "</pre>");


        paypalAccessToken = jsonObjectResponse.getString("access_token");
        System.out.println("\n ===TOKEN=== \n" + paypalAccessToken);


        test.info("<pre>"
                + "[ KEYS ]"
                + "<br />"
                + "accessToken = " + paypalAccessToken
                + "<br />"
                + "<br />"
                + "</pre>");

        Assert.assertNotNull(paypalAccessToken);
    }
}
