package com.test.temp;

import com.aventstack.extentreports.AnalysisStrategy;
import com.setup.BasicSetup;
import com.setup.ExtentManager;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.apache.http.client.utils.URIBuilder;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.setup.ExtentManager.extent;
import static com.setup.ExtentManager.test;
import static com.setup.HttpClientUtils.*;
import static com.setup.OkHttpClientUtils.*;
import static com.setup.OkHttpClientUtils.requestMethod;
import static com.setup.OkHttpClientUtils.requestURLPath;

public class OkHttpClient_POST extends BasicSetup {

    @BeforeClass
    public void startTest() throws Exception {
        extent = ExtentManager.GetExtent();
        test = extent.createTest("[REST/SOAP] TEST NAME", "DESCRIPTION");
        test.assignAuthor("YOUR NAME");
        test.assignCategory("OkHttpClient");
        extent.setAnalysisStrategy(AnalysisStrategy.TEST);
    }

    @Test
    public void get(Method testMethod) throws Exception {

        String fileName = testMethod.getName() + ".json";

        JSONObject jsonPostData = new JSONObject();
        jsonPostData.put("key", "value");
        jsonPostData.put("key", "value");

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");

        RequestBody requestBody = RequestBody.create(mediaType, "grant_type=client_credentials");

        url = new URIBuilder()
                .setScheme("http/https")
                .setHost("api.flypaythis.com")
                .setPath("/v2/auth/login")
                .build();

        Request request = new Request.Builder()
                .url(url.toURL())
                .post(requestBody)
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
    }
}
