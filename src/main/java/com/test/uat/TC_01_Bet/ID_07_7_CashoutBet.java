package com.test.uat.TC_01_Bet;

import com.aventstack.extentreports.AnalysisStrategy;
import com.setup.BasicSetup;
import com.setup.ExtentManager;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static com.constants.API.cashout;
import static com.setup.ConsoleRunner.host;
import static com.setup.ConsoleRunner.scheme;
import static com.setup.ExtentManager.extent;
import static com.setup.ExtentManager.test;
import static com.setup.HttpClientUtils.httpPost;
import static com.setup.HttpClientUtils.url;
import static com.setup.OkHttpClientUtils.*;
import static com.test.uat.TC_01_Bet.ID_02_ExternalLogin.accountId;
import static com.test.uat.TC_01_Bet.ID_02_ExternalLogin.sessionToken;
import static com.test.uat.TC_01_Bet.ID_03_GetFootballSportTree.selectionId;
import static com.test.uat.TC_01_Bet.ID_04_PlaceBet.betSlipId;
import static com.test.uat.TC_01_Bet.ID_06_CalculateCashout.cashoutValue;


public class ID_07_7_CashoutBet extends BasicSetup {

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

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("sessionToken", sessionToken));
        params.add(new BasicNameValuePair("betId", betSlipId));
        params.add(new BasicNameValuePair("cashOutStake", String.valueOf(cashoutValue)));
        params.add(new BasicNameValuePair("selectionId", String.valueOf(selectionId)));
        params.add(new BasicNameValuePair("siteId", "1"));
        params.add(new BasicNameValuePair("accountId", String.valueOf(accountId)));

        UrlEncodedFormEntity entity1 = new UrlEncodedFormEntity(params);
        StringEntity entity = new StringEntity(entity1.toString());


        //String jsonBody = convertJson(jsonString);

        url = new URIBuilder()
                .setScheme(scheme)
                .setHost(host)
                .setPath(cashout)
                .build();

        httpPost(fileName, url, entity);


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
                + params
              //  + requestBodyToString(requestBody).replaceAll("&", "\n").replaceAll("\"", "")
                + "<br />"
                + "</pre>");
    }
}
