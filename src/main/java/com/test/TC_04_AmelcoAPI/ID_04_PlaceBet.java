package com.test.TC_04_AmelcoAPI;

import com.aventstack.extentreports.AnalysisStrategy;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.setup.BasicSetup;
import com.setup.ExtentManager;
import okhttp3.*;
import org.apache.http.client.utils.URIBuilder;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Method;

import static com.setup.ExtentManager.extent;
import static com.setup.ExtentManager.test;
import static com.setup.HttpClientUtils.objectResponse;
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

        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");

        String bets = "{\"PlaceBetsRequest\":{\"accountId\":" + accountId + ",\"bets\":{\"bet\":[{\"type\":\"" + type + "\",\"winType\":\"" + winType + "\",\"stake\":{\"amount\":\"" + amount + "\",\"currency\":\"" + currency + "\"},\"parts\":{\"betPart\":[{\"partNo\":" + partNo + ",\"selectionId\":" + selectionId + ",\"odds\":{\"decimal\":\"" + decimal + "\",\"fractional\":\"" + fractional + "\"}}]}}]},\"channelId\":6,\"reqId\":0,\"acceptPriceChange\":true}}";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonBets = gson.toJson(bets);
        System.out.println("\n jsonBets \n" + jsonBets);

        JsonParser jsonParser = new JsonParser();
        JsonObject objectFromString = jsonParser.parse(bets).getAsJsonObject();
        System.out.println("\n objectFromString \n" + objectFromString);

        RequestBody requestBody =  new FormBody.Builder()
                .add("isSpinAndBet", "false")
                .add("sessionToken", sessionToken)
                .add("bets", String.valueOf(objectFromString))
                .add("locale", "en-gb")
                .add("siteId", String.valueOf(site))
                .build();

        url = new URIBuilder()
                .setScheme("https")
                .setHost("sports.uat.pyr")
                .setPath("/sportsbook/v1/api/placeBets")
                .build();

        request = new Request.Builder()
                .url(url.toURL())
                .post(requestBody)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .addHeader("Origin", "https://sports.uat.pyr")
                .addHeader("Accept", "application/json, text/javascript, */*; q=0.01")
                .addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36")
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
                + "<br />"
                + requestBodyToString(requestBody)
                + "<br />"
                + "</pre>");
    }
}
