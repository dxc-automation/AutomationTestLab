package com.test.uat.TC_01_Bet;

import com.aventstack.extentreports.AnalysisStrategy;
import com.setup.BasicSetup;
import com.setup.ExtentManager;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.apache.http.client.utils.URIBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.objects.API.CASHOUT_BET;
import static com.setup.ConsoleRunner.host;
import static com.setup.ConsoleRunner.scheme;
import static com.setup.ExtentManager.extent;
import static com.setup.ExtentManager.test;
import static com.setup.HttpClientUtils.url;
import static com.setup.OkHttpClientUtils.*;
import static com.test.uat.TC_01_Bet.LogInRam.externalToken;
import static com.test.uat.TC_01_Bet.ExternalLogin.accountId;
import static com.test.uat.TC_01_Bet.FootballSportTree.selectionId;
import static com.test.uat.TC_01_Bet.PlaceBet.betSlipId;
import static com.test.uat.TC_01_Bet.CalculateCashout.cashoutValue;


public class CashoutBet extends BasicSetup {

    @BeforeClass
    public void startTest() throws Exception {
        extent = ExtentManager.GetExtent();
        extent = ExtentManager.GetExtent();
        test = extent.createTest(
                "[ID_07] Cashout Bet",
                "<pre>"
                        + "DESCRIPTION"
                        + "<br/>"
                        + "Send POST request to get all bets with status OPEN. Then search for a Bet Slip ID in the response."
                        + "</pre>");
        test.assignAuthor("YOUR NAME");
        test.assignCategory("OkHttpClient");
        extent.setAnalysisStrategy(AnalysisStrategy.TEST);
    }

    @Test
    public void cashoutBet(Method testMethod) throws Exception {

        String fileName = testMethod.getName() + ".json";

        RequestBody requestBody = new FormBody.Builder()
                .add("sessionToken", externalToken)
                .add("betId", betSlipId)
                .add("cashOutStake", String.valueOf(cashoutValue))
                .add("selectionId", String.valueOf(selectionId))
                .add("siteId", "1")
                .add("accountId", String.valueOf(accountId))
                .build();

        //String jsonBody = convertJson(jsonString);

        url = new URIBuilder()
                .setScheme(scheme)
                .setHost(host)
                .setPath(CASHOUT_BET)
                .build();

        request = new Request.Builder()
                .url(url.toURL())
                .post(requestBody)
                .addHeader("sec-fetch-mode", "cors")
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .addHeader("cache-control", "no-cache")
                .addHeader("accept", "application/json, text/javascript, */*; q=0.01")
                .addHeader("x-requested-with", "XMLHttpRequest")
                .build();

        okClientRequest(fileName, request);

        test.info("<pre>"
                + "[   REQUEST   HEADERS   ]"
                + "<br/>"
                + "<br/>"
                + "Method:   " + requestMethod
                + "<br/>"
                + "Scheme:   " + requestURLScheme.toUpperCase()
                + "<br />"
                + "Host:     " + requestURLHost
                + "<br/>"
                + "Path:     " + requestURLPath
                + "<br/>"
                + "<br/>"
                + getRequestOkClientHeaders()
                + "<br/>"
                + "<br/>"
                + "[   REQUEST   BODY   ]"
                + "<br />"
                + "<br />"
                + requestBodyToString(requestBody).replaceAll("&", "\n").replaceAll("\"", "")
                + "<br />"
                + "</pre>");
    }
}
