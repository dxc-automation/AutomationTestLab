package com.test.uat.TC_01_Bet;

import com.aventstack.extentreports.AnalysisStrategy;
import com.jayway.jsonpath.JsonPath;
import com.setup.BasicSetup;
import com.setup.ExtentManager;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.apache.http.client.utils.URIBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.lang.reflect.Method;
import java.sql.Timestamp;

import static com.constants.API.place_bets;
import static com.setup.ConsoleRunner.host;
import static com.setup.ConsoleRunner.scheme;
import static com.setup.ExtentManager.extent;
import static com.setup.ExtentManager.test;
import static com.setup.HttpClientUtils.url;
import static com.setup.OkHttpClientUtils.*;
import static com.setup.JSONUtils.*;
import static com.test.uat.TC_01_Bet.ID_01_LogIn.*;
import static com.test.uat.TC_01_Bet.ID_02_ExternalLogin.*;
import static com.test.uat.TC_01_Bet.ID_03_GetFootballSportTree.*;


public class ID_04_PlaceBet extends BasicSetup {

    protected static  int integerDecimalOdd;
    protected static String stringIntDecimalOdd;
    protected static Double selectionDecimalOdd;

    public static String betSlipId;


    @BeforeClass
    public void startTest() throws Exception {
        extent = ExtentManager.GetExtent();
        test = extent.createTest(
                "[ID_05] Place a Bet",
                "<pre>"
                        + "DESCRIPTION"
                        + "<br/>"
                        + "Place a single bet."
                        + "</pre>");
        test.assignAuthor("YOUR NAME");
        test.assignCategory("OkHttpClient");
        extent.setAnalysisStrategy(AnalysisStrategy.TEST);
    }

    @Test
    public void placeBet(Method testMethod) throws Exception {

        String fileName = testMethod.getName() + ".json";

        int amount  = 2;
        int partNo  = 1;
        String type = "SINGLE";
        String winType = "WIN";

        String bets = "{\"PlaceBetsRequest\":{\"accountId\":"
                + accountId + ",\"bets\":{\"bet\":[{\"type\":\""
                + type + "\",\"winType\":\""
                + winType + "\",\"stake\":{\"amount\":\""
                + amount + "\",\"currency\":\""
                + accountCurrency + "\"},\"parts\":{\"betPart\":[{\"partNo\":"
                + partNo + ",\"selectionId\":"
                + selectionId + ",\"odds\":{\"decimal\":\""
                + selectionDecimal + "\",\"fractional\":\""
                + selectionFractional + "\"}}]}}]},\"channelId\":6,\"reqId\":0,\"acceptPriceChange\":true}}";

        RequestBody requestBody =  new FormBody.Builder()
                .add("isSpinAndBet", "false")
                .add("sessionToken", sessionToken)
                .add("bets", bets)
                .add("locale", "en-gb")
                .add("siteId", String.valueOf(site))
                .build();

        String jsonBody = convertJson(bets);


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
                + jsonBody
                + "<br/>"
                + "<br/>"
                + "</pre>");



        Object object         = jsonParser.parse(new FileReader(filePath + "/" + "report/JSON/" + fileName));
        String jsonResponse   = gsonPretyPrint.toJson(object);

        String betType   = JsonPath.read(jsonResponse, "$.PlaceBetsResponse.betPlacementResult[0].betType");
        String betStatus = JsonPath.read(jsonResponse, "$.PlaceBetsResponse.betPlacementResult[0].status");
        int betStake = JsonPath.read(jsonResponse, "$.PlaceBetsResponse.betPlacementResult[0].totalStake");
        int  eventId = JsonPath.read(jsonResponse, "$.PlaceBetsResponse.betPlacementResult[0].betPartPlacementResult[0].eventId");
        String eventName = JsonPath.read(jsonResponse, "$.PlaceBetsResponse.betPlacementResult[0].betPartPlacementResult[0].eventName");
        int  selectionId = JsonPath.read(jsonResponse, "$.PlaceBetsResponse.betPlacementResult[0].betPartPlacementResult[0].selectionId");
        betSlipId = JsonPath.read(jsonResponse, "$.PlaceBetsResponse.betSlipId");
        boolean inplay   = JsonPath.read(jsonResponse, "$.PlaceBetsResponse.betPlacementResult[0].betPartPlacementResult[0].inplay");
        String selectionName = JsonPath.read(jsonResponse, "$.PlaceBetsResponse.betPlacementResult[0].betPartPlacementResult[0].eventName");
        String competitionName = JsonPath.read(jsonResponse, "$.PlaceBetsResponse.betPlacementResult[0].betPartPlacementResult[0].competition");

        try {
            integerDecimalOdd = JsonPath.read(jsonResponse, "$.PlaceBetsResponse.betPlacementResult[0].betPartPlacementResult[0].odds.decimal");
            stringIntDecimalOdd = Integer.toString(integerDecimalOdd);
            selectionDecimalOdd = Double.valueOf(stringIntDecimalOdd);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            selectionDecimalOdd = JsonPath.read(jsonResponse, "$.PlaceBetsResponse.betPlacementResult[0].betPartPlacementResult[0].odds.decimal");
        } catch (Exception e) {
            e.printStackTrace();
        }
     
            Long time = JsonPath.read(jsonResponse, "$.PlaceBetsResponse.betPlacementResult[0].betPartPlacementResult[0].eventTime");
            Timestamp eventTime = new Timestamp(time);


            /*** Add key values that we take from the response. ***/
            test.pass("<pre>"
                    + "[   BET DETAILS   ]"
                    + "<br/>"
                    + "<br/>"
                    + "Competition Name = " + competitionName
                    + "<br/>"
                    + "Event Name       = " + eventName
                    + "<br/>"
                    + "Event ID         = " + eventId
                    + "<br/>"
                    + "Event Time       = " + eventTime
                    + "<br/>"
                    + "<br/>"
                    + "Event Inplay     = " + inplay
                    + "<br/>"
                    + "Selection Name   = " + selectionName
                    + "<br/>"
                    + "Selection ID     = " + selectionId
                    + "<br/>"
                    + "<br/>"
                    + "Market Name      = " + marketName
                    + "<br/>"
                    + "Market ID        = " + marketId
                    + "<br/>"
                    + "<br/>"
                    + "Bet Type     = " + betType
                    + "<br/>"
                    + "Bet Status   = " + betStatus
                    + "<br/>"
                    + "Odd          = " + selectionDecimalOdd
                    + "<br/>"
                    + "Bet Stake    = " + betStake
                    + "<br/>"
                    + "Betslip ID   = " + betSlipId
                    + "<br/>"
                    + "<br/>"
                    + "</pre>");
    }
}
