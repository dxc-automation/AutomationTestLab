package com.test.TC_02_Example_HttpClient.PayPal;

import com.aventstack.extentreports.AnalysisStrategy;
import com.setup.BasicSetup;
import com.setup.ExtentManager;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.setup.ExtentManager.extent;
import static com.setup.ExtentManager.test;
import static com.setup.HttpClientUtils.jsonObjectResponse;
import static com.setup.OkHttpClientUtils.*;

public class ID_02_Test extends BasicSetup {

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
    public void test(Method testMethod) throws Exception {

        String fileName = testMethod.getName() + ".json";

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");

        RequestBody requestBody = RequestBody.create(mediaType, "grant_type=client_credentials");

        Request request = new Request.Builder()
                .url("https://api.sandbox.paypal.com/v1/oauth2/token")
                .post(requestBody)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "*/*")
                .addHeader("Cache-Control", "no-cache")
                .addHeader("Host", "api.sandbox.paypal.com")
                .addHeader("Accept-Encoding", "gzip, deflate")
                .addHeader("Content-Length", "29")
                .addHeader("Connection", "keep-alive")
                .build();


        okClientRequest(fileName, request);


        test.info("<pre>"
                + " <<<=============  R E Q U E S T  =============>>>"
                + "<br />"
                + "<br />"
                + "Method: "    + requestMethod
                + "<br />"
                + "Protocol: "  + requestURLScheme
                + "<br />"
                + "Host: "      + requestURLHost
                + "<br />"
                + "Path: "      + requestURLPath
                + "<br />"
                + "<br />"
                + "<br />"
                + request.toString().replace(",", "\n")
                + "<br />"
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
