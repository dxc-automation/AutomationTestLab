package com.test.uat.TC_01_Bet;

import com.aventstack.extentreports.AnalysisStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayway.jsonpath.JsonPath;
import com.setup.BasicSetup;
import com.setup.ExtentManager;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.apache.http.client.utils.URIBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.sql.Timestamp;

import static com.constants.API.place_bets;
import static com.setup.ConsoleRunner.host;
import static com.setup.ConsoleRunner.scheme;
import static com.setup.ExtentManager.extent;
import static com.setup.ExtentManager.test;
import static com.setup.HttpClientUtils.url;
import static com.setup.OkHttpClientUtils.*;
import static com.test.uat.TC_01_Bet.ID_01_LogIn.*;
import static com.test.uat.TC_01_Bet.ID_02_ExternalLogin.*;
import static com.test.uat.TC_01_Bet.ID_03_GetOpenBets.*;
import static com.test.uat.TC_01_Bet.ID_08_GetSportsTree.selectionId;


public class ID_04_PlaceBet extends BasicSetup {

    public static String betSlipId;


    @BeforeClass
    public void startTest() throws Exception {
        extent = ExtentManager.GetExtent();
        test = extent.createTest(
                "[ID_04] Place a Bet",
                "<pre>"
                        + "DESCRIPTION"
                        + "<br/>"
                        + "Get sport tree and place a bet."
                        + "</pre>");
        test.assignAuthor("YOUR NAME");
        test.assignCategory("OkHttpClient");
        extent.setAnalysisStrategy(AnalysisStrategy.TEST);
    }

    @Test
    public void placeBet(Method testMethod) throws Exception {

        String fileName = testMethod.getName() + ".json";

        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");

        String bets = "{\"PlaceBetsRequest\":{\"accountId\":"
                + accountId + ",\"bets\":{\"bet\":[{\"type\":\""
                + type + "\",\"winType\":\""
                + winType + "\",\"stake\":{\"amount\":\""
                + amount + "\",\"currency\":\""
                + accountCurrency + "\"},\"parts\":{\"betPart\":[{\"partNo\":"
                + partNo + ",\"selectionId\":"
                + selectionId + ",\"odds\":{\"decimal\":\""
                + decimal + "\",\"fractional\":\""
                + fractional + "\"}}]}}]},\"channelId\":6,\"reqId\":0,\"acceptPriceChange\":true}}";

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonBets = gson.toJson(bets);


        RequestBody requestBody =  new FormBody.Builder()
                .add("isSpinAndBet", "false")
                .add("sessionToken", sessionToken)
                .add("bets", bets)
                .add("locale", "en-gb")
                .add("siteId", String.valueOf(site))
                .build();

        url = new URIBuilder()
                .setScheme(scheme)
                .setHost(host)
                .setPath(place_bets)
                .build();

        request = new Request.Builder()
                .url(url.toURL())
                .post(requestBody)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .addHeader("Origin", "https://sports.uat.pyr")
                .addHeader("Accept", "application/json, text/javascript, */*; q=0.01")
                .addHeader("Cookie", "SBSG=" + signature)
                .addHeader("Cookie", "WBID=" + webId)
                .addHeader("Cookie", "SBTK=" + externalToken)
                .addHeader("Cookie", "SITE=" + site)
                .build();

        okClientRequest(fileName, request);

        test.info("<pre>"
                + "[   REQUEST   HEADERS   ]"
                + "<br/>"
                + "<br/>"
                + "Method:   "    + requestMethod
                + "<br/>"
                + "Scheme:   "    + requestURLScheme.toUpperCase()
                + "<br/>"
                + "Host:     "    + requestURLHost
                + "<br/>"
                + "Path:     "    + requestURLPath
                + "<br/>"
                + "<br/>"
                + getRequestOkClientHeaders()
                + "<br/>"
                + "<br/>"
                + "[    REQUEST   BODY    ]"
                + "<br/>"
                + "<br/>"
                + jsonBets
                + "<br/>"
                + "<br/>"
                + "</pre>");



        String jsonResponse = getJsonResponse(fileName);

        String betSlipId = JsonPath.read(jsonResponse, "$.PlaceBetsResponse.betSlipId");
        String betType   = JsonPath.read(jsonResponse, "$.PlaceBetsResponse.betPlacementResult[0].betType");
        String betStatus = JsonPath.read(jsonResponse, "$.PlaceBetsResponse.betPlacementResult[0].status");
        Double odd       = JsonPath.read(jsonResponse, "$.PlaceBetsResponse.betPlacementResult[0].betPartPlacementResult[0].odds.decimal");
        int    stake     = JsonPath.read(jsonResponse, "$.PlaceBetsResponse.betPlacementResult[0].totalStake");

        int eventId      = JsonPath.read(jsonResponse, "$.PlaceBetsResponse.betPlacementResult[0].betPartPlacementResult[0].eventId");
        Long eventTime   = JsonPath.read(jsonResponse, "$.PlaceBetsResponse.betPlacementResult[0].betPartPlacementResult[0].eventTime");
        boolean inplay   = JsonPath.read(jsonResponse, "$.PlaceBetsResponse.betPlacementResult[0].betPartPlacementResult[0].inplay");
        String eventName = JsonPath.read(jsonResponse, "x.PlaceBetsResponse.betPlacementResult[0].betPartPlacementResult[0].eventName");
        Timestamp time   = new Timestamp(eventTime);

        int selectionId        = JsonPath.read(jsonResponse, "$.PlaceBetsResponse.betPlacementResult[0].betPartPlacementResult[0].selectionId");
        String selectionName   = JsonPath.read(jsonResponse, "$.PlaceBetsResponse.betPlacementResult[0].betPartPlacementResult[0].selectionName");
        String competitionName = JsonPath.read(jsonResponse, "$.PlaceBetsResponse.betPlacementResult[0].betPartPlacementResult[0].competition");
        int marketId           = JsonPath.read(jsonResponse, "$.PlaceBetsResponse.betPlacementResult[0].betPartPlacementResult[0].marketId");
        String marketName      = JsonPath.read(jsonResponse, "$.PlaceBetsResponse.betPlacementResult[0].betPartPlacementResult[0].marketName");





        /*** Add key values that we take from the response. ***/
        test.pass("<pre>"
                + "[   BET DETAILS   ]"
                + "<br/>"
                + "Competition Name = " + competitionName
                + "<br/>"
                + "<br/>"
                + "Event Name = " + eventName
                + "<br/>"
                + "Event ID = " + eventId
                + "<br/>"
                + "Event Time = " + time
                + "<br/>"
                + "Event Inplay = " + inplay
                + "<br/>"
                + "<br/>"
                + "Selection Name = " + selectionName
                + "<br/>"
                + "Selection ID = " + selectionId
                + "<br/>"
                + "<br/>"
                + "Market Name = " + marketName
                + "<br/>"
                + "Market ID = " + marketId
                + "<br/>"
                + "Bet Status" + betStatus
                + "<br/>"
                + "Bet Type = " + betType
                + "<br/>"
                + "Odd = " + odd
                + "<br/>"
                + "Stake = " + stake
                + "<br/>"
                + "Betslip ID = " + betSlipId
                + "<br/>"
                + "<br/>"
                + "</pre>");
    }
}
