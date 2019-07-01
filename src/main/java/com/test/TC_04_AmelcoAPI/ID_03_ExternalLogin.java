package com.test.TC_04_AmelcoAPI;

import com.aventstack.extentreports.AnalysisStrategy;
import com.setup.BasicSetup;
import com.setup.ExtentManager;
import com.setup.OkHttpClientUtils;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.apache.http.client.utils.URIBuilder;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.constants.Accounts.*;
import static com.setup.ExtentManager.extent;
import static com.setup.ExtentManager.test;
import static com.setup.HttpClientUtils.url;
import static com.setup.OkHttpClientUtils.*;


public class ID_03_ExternalLogin extends BasicSetup {

    @BeforeClass
    public void startTest() throws Exception {
        extent = ExtentManager.GetExtent();
        test = extent.createTest("[ID_03] External log in", "DESCRIPTION");
        test.assignAuthor("Pavel Popov");
        test.assignCategory("OkHttpClient");
        extent.setAnalysisStrategy(AnalysisStrategy.TEST);
    }

    @Test
    public void externalLogin(Method testMethod) throws Exception {

        String fileName = testMethod.getName() + ".json";

        JSONObject jsonPostData = new JSONObject();

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");

        RequestBody requestBody = new FormBody.Builder()
                .add("username", uat1_username)
                .add("password", uat1_password)
                .add("devIx", uat1_devIx)
                .build();

        url = new URIBuilder()
                .setScheme("https")
                .setHost("sports.uat.pyr")
                .setPath("/sportsbook/v1/api/externalLogin")
                .build();

        request = new Request.Builder()
                .url(url.toURL())
                .post(requestBody)
                .build();

        String postBody = OkHttpClientUtils.request.body().toString();
        System.out.println(postBody);

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
                + requestBodyToString(requestBody)
                + "<br />"
                + "</pre>");
    }
}
