package com.test.temp;

import com.aventstack.extentreports.AnalysisStrategy;
import com.setup.BasicSetup;
import com.setup.ExtentManager;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.apache.http.client.utils.URIBuilder;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.setup.ExtentManager.extent;
import static com.setup.ExtentManager.test;
import static com.setup.HttpClientUtils.*;
import static com.setup.OkHttpClientUtils.*;
import static com.setup.OkHttpClientUtils.requestMethod;
import static com.setup.OkHttpClientUtils.requestURLPath;
import static com.test.TC_02_Example_HttpClient.Fly.ID_01_AccessToken.accessToken;

public class OkHttpClient_GET extends BasicSetup {

    @BeforeClass
    public void startTest() throws Exception {
        extent = ExtentManager.GetExtent();
        test = extent.createTest("[REST/SOAP] TEST NAME", "DESCRIPTION");
        test.assignAuthor("AUTHOR NAME");
        test.assignCategory("OkHttpClient");
        extent.setAnalysisStrategy(AnalysisStrategy.TEST);
    }

    @Test
    public void get(Method testMethod) throws Exception {

        String fileName = testMethod.getName() + ".json";

        // Create url address
        url = new URIBuilder()
                .setScheme("https")
                .setHost("api.flypaythis.com")
                .setPath("/v1/user/me")
                .addParameter("accessToken", accessToken)
                .build();

        Request request = new Request.Builder()
                .url(url.toURL())
                .get()
                .build();

        postRequest(fileName, request);


        // Add request data to the report
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

        Assert.assertEquals(responseMsg, "OK");
    }
}
