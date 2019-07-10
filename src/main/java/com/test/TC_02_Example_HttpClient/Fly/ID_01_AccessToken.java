package com.test.TC_02_Example_HttpClient.Fly;

import com.aventstack.extentreports.AnalysisStrategy;
import com.setup.BasicSetup;
import com.setup.ExtentManager;
import org.apache.http.client.utils.URIBuilder;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.setup.ExtentManager.extent;
import static com.setup.ExtentManager.test;
import static com.setup.HttpClientUtils.*;
import static com.setup.HttpClientUtils.requestMethod;

public class ID_01_AccessToken extends BasicSetup {

    public static String accessToken = "";
    public static int businessId;


     /*** Set test name, test description, author and category. ***/
    @BeforeClass
    public void startTest() throws Exception {
        extent = ExtentManager.GetExtent();
        test = extent.createTest("[ID_01] Access Token", "Bootstrap into the Flyt API. The purpose of this test is to verify that the user can receive a unique access token.");
        test.assignAuthor("Pavel Popov");
        test.assignCategory("POST");
        extent.setAnalysisStrategy(AnalysisStrategy.TEST);
    }

    @Test
    public void getAccessToken(Method testMethod) throws Exception {

        /*** Set URL address components. ***/
        url = new URIBuilder()
                .setScheme("https")
                .setHost("api.flypaythis.com")
                .setPath("/v1/apps/bootstrap")
                .build();

        /*** Create JSON object for request body. ***/
        JSONObject jsonPostData = new JSONObject();
        jsonPostData.put("deviceToken", "07304e56c452be73ad2b51a4647d0300");
        jsonPostData.put("platform", "Android");
        jsonPostData.put("platformVersion", "6.0");
        jsonPostData.put("appId", 1);
        jsonPostData.put("frameworkVersion", "1.0.0");
        jsonPostData.put("model", "LG Nexus 5X");
        jsonPostData.put("appVersion", "1.0.0");

        String requestData = jsonPostData.toString(4);
        String fileName = testMethod.getName() + ".json";

        /*** Send request by using method 'httpPost' from HttpClientUtils.class ***/
        httpPost(fileName, url, jsonPostData);

        /*** Add request properties to the report. ***/
        test.info("<pre>"
                + "[ REQUEST  HEADERS ]"
                + "<br />"
                + "<br />"
                + "Method:  "  + requestMethod   +   "  "   + requestProtocol
                + "<br />"
                + "Scheme:  "    + requestScheme.toUpperCase()
                + "<br />"
                + "Host:    "      + requestHost
                + "<br />"
                + "Path:    "      + requestPath
                + "<br />"
                + "\n"
                + getPostRequestHeaders().replace(", ", "\n")
                + "<br />"
                + "<br />"
                + "<br />"
                + "[ REQUEST  BODY ]"
                + "<br />"
                + "<br />"
                + requestData.replace("    ", "&nbsp;&nbsp;")
                + "<br />"
                + "<br />"
                + "</pre>");


        /*** Get keys from the response. ***/
        accessToken = jsonObjectResponse.getString("accessToken");
        businessId = jsonObjectResponse.getJSONArray("businessIds").getInt(2);


        /*** Add key values that we take from the response. ***/
        test.info("<pre>"
                + "[ KEYS ]"
                + "<br />"
                + "\n accessToken = " + accessToken
                + "\n businessId = " + businessId
                + "<br />"
                + "<br />"
                + "</pre>");

        Assert.assertNotNull(accessToken);
    }
}
