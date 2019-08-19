package com.test.uat.TC_01_Bet;

import com.aventstack.extentreports.AnalysisStrategy;
import com.setup.BasicSetup;
import com.setup.ExtentManager;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.constants.API.wallet_bet_status;
import static com.setup.ConsoleRunner.scheme;
import static com.setup.ConsoleRunner.walletHost;
import static com.setup.ExtentManager.extent;
import static com.setup.ExtentManager.test;
import static com.setup.HttpClientUtils.*;
import static com.setup.JSONUtils.convertJson;
import static com.test.uat.TC_01_Bet.PlaceBet.betSlipId;


public class BetStatus extends BasicSetup {

    @BeforeClass
    public void startTest() throws Exception {
        extent = ExtentManager.GetExtent();
        extent = ExtentManager.GetExtent();
        test = extent.createTest(
                "[ID_06] Get Bet Status",
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
    public void betStatus(Method testMethod) throws Exception {

        String fileName = testMethod.getName() + ".json";


        url = new URIBuilder()
                .setScheme(scheme)
                .setHost(walletHost)
                .setPath(wallet_bet_status)
                .build();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("betId", betSlipId);

        String requestData = jsonObject.toString(4);
        String jsonBody = convertJson(requestData);

        StringEntity entity = new StringEntity(jsonObject.toString());

        httpPost(fileName, url, entity);


        test.info("<pre>"
                + "[   REQUEST   HEADERS   ]"
                + "<br/>"
                + "<br/>"
                + "Method:  " + requestMethod + "  " + requestProtocol
                + "<br/>"
                + "Scheme:  " + requestScheme.toUpperCase()
                + "<br/>"
                + "Host:    " + requestHost
                + "<br/>"
                + "Path:    " + requestPath
                + "<br/>"
                + "\n"
                + getPostRequestHeaders().replace(", ", "\n")
                + "<br/>"
                + "<br/>"
                + "<br/>"
                + "[    REQUEST   BODY    ]"
                + "<br/>"
                + "<br/>"
                + jsonBody
                + "<br/>"
                + "<br/>"
                + "</pre>");
    }
}
