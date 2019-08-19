package com.test.temp;

import com.aventstack.extentreports.AnalysisStrategy;
import com.setup.BasicSetup;
import com.setup.ExtentManager;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.apache.http.client.utils.URIBuilder;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.setup.ConsoleRunner.scheme;
import static com.setup.ExtentManager.extent;
import static com.setup.ExtentManager.test;
import static com.setup.HttpClientUtils.url;
import static com.setup.OkHttpClientUtils.*;
import static com.test.uat.TC_01_Bet.LogInRam.site;
import static com.test.uat.TC_01_Bet.ExternalLogin.sessionToken;

public class OkHttpClient_POST extends BasicSetup {

    @BeforeClass
    public void startTest() throws Exception {
        extent = ExtentManager.GetExtent();
        test = extent.createTest(
                "[ID_**] TEMP",
                "<pre>"
                        + "DESCRIPTION"
                        + "<br/>"
                        + "Test description....."
                        + "</pre>");
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

        RequestBody requestBody = new FormBody.Builder()
                .add("sessionToken", sessionToken)
                .add("excludeChildBets", "true")
                .add("locale", "en-gb")
                .add("siteId", String.valueOf(site))
                .add("channelId", "6")
                .build();

        url = new URIBuilder()
                .setScheme(scheme)
                .setHost("")
                .setPath("")
                .build();

        request = new Request.Builder()
                .url(url.toURL())
                .post(requestBody)
                .build();

        okClientRequest(fileName, request);

        test.info("<pre>"
                + "[   REQUEST  HEADERS   ]"
                + "<br />"
                + "<br />"
                + "Method:   "    + requestMethod
                + "<br />"
                + "Scheme:   "    + requestURLScheme.toUpperCase()
                + "<br />"
                + "Host:     "    + requestURLHost
                + "<br />"
                + "Path:     "    + requestURLPath + "/" + query
                + "<br />"
                + "<br />"
                + getRequestOkClientHeaders()
                + "<br />"
                + "<br />"
                + "[   REQUEST   BODY   ]"
                + "<br />"
                + "<br />"
                + requestBodyToString(requestBody).replaceAll("&", "\n").replaceAll("\"", "")
                + "<br />"
                + "</pre>");
    }
}
