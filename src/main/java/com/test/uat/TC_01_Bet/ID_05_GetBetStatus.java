package com.test.uat.TC_01_Bet;

import com.aventstack.extentreports.AnalysisStrategy;
import com.setup.BasicSetup;
import com.setup.ExtentManager;
import org.apache.http.client.utils.URIBuilder;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.setup.ExtentManager.extent;
import static com.setup.ExtentManager.test;
import static com.setup.HttpClientUtils.*;
import static com.test.uat.TC_01_Bet.ID_04_PlaceBet.betSlipId;
import static com.setup.ConsoleRunner.*;
import static com.constants.API.*;




public class ID_05_GetBetStatus extends BasicSetup {

    @BeforeClass
    public void startTest() throws Exception {
        extent = ExtentManager.GetExtent();
        test = extent.createTest("[ID_05] Get Bet Status", "DESCRIPTION");
        test.assignAuthor("YOUR NAME");
        test.assignCategory("HttpClient");
        extent.setAnalysisStrategy(AnalysisStrategy.TEST);
    }

    @Test
    public void getBetStatus(Method testMethod) throws Exception {
        url = new URIBuilder()
                .setScheme(scheme)
                .setHost("walletapi.uat.pyr")
                .setPath(get_bet_status)
                .build();


        JSONObject jsonPostData = new JSONObject();
        jsonPostData.put("betId", betSlipId);

        String requestData = jsonPostData.toString(4);
        String fileName = testMethod.getName() + ".json";

        httpPost(fileName, url, jsonPostData).addHeader("Origin", "https://walletapi.uat.pyr");

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
                + "<br />"
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
    }
}
