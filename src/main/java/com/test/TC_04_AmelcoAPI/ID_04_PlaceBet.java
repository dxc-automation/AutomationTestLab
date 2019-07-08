package com.test.TC_04_AmelcoAPI;

import com.aventstack.extentreports.AnalysisStrategy;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.setup.BasicSetup;
import com.setup.ExtentManager;
import okhttp3.FormBody;
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
import static com.setup.HttpClientUtils.url;
import static com.setup.OkHttpClientUtils.*;
import static com.test.TC_04_AmelcoAPI.ID_01_LogIn.site;
import static com.test.TC_04_AmelcoAPI.ID_02_ExternalLogin.sessionToken;
import static com.test.TC_04_AmelcoAPI.ID_03_GetOpenBets.*;

public class ID_04_PlaceBet extends BasicSetup {

    @BeforeClass
    public void startTest() throws Exception {
        extent = ExtentManager.GetExtent();
        test = extent.createTest("[ID_04] Place a bet", "DESCRIPTION");
        test.assignAuthor("YOUR NAME");
        test.assignCategory("OkHttpClient");
        extent.setAnalysisStrategy(AnalysisStrategy.TEST);
    }

    @Test
    public void placeBet(Method testMethod) throws Exception {

        String fileName = testMethod.getName() + ".json";

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");


        String bets = "{\"PlaceBetsRequest\":{\"accountId\":" + accountId + ",\"bets\":{\"bet\":[{\"type\":\"" + type + "\",\"winType\":\"" + winType + "\",\"stake\":{\"amount\":\"" + amount + "\",\"currency\":\"" + currency + "\"},\"parts\":{\"betPart\":[{\"partNo\":" + partNo + ",\"selectionId\":" + selectionId + ",\"odds\":{\"decimal\":\"" + decimal + "\",\"fractional\":\"" + fractional + "\"}}]}}]},\"channelId\":6,\"reqId\":0,\"acceptPriceChange\":true}}";


        RequestBody requestBody = new FormBody.Builder()
                .add("bets", bets)
                .add("sessionToken", sessionToken)
                .build();

        url = new URIBuilder()
                .setScheme("https")
                .setHost("sports.uat.pyr")
                .setPath("/sportsbook/v1/api/placeBets")
                .build();

        request = new Request.Builder()
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
                + "Scheme:   "    + requestURLScheme.toUpperCase()
                + "<br />"
                + "Host:     "    + requestURLHost
                + "<br />"
                + "Path:     "    + requestURLPath
                + "<br />"
                + "<br />"
                + getRequestOkClientHeaders()
                + "<br />"
                + "<br />"
                + "[ REQUEST  BODY ]"
                + "<br />"
                + requestBodyToString(requestBody)
                + "<br />"
                + "</pre>");
    }
}
