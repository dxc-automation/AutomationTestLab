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

import static com.constants.API.get_open_bets;
import static com.setup.ConsoleRunner.host;
import static com.setup.ConsoleRunner.scheme;
import static com.setup.ExtentManager.extent;
import static com.setup.ExtentManager.test;
import static com.setup.HttpClientUtils.url;
import static com.setup.OkHttpClientUtils.*;
import static com.setup.JSONUtils.*;
import static com.test.uat.TC_01_Bet.LogInRam.site;
import static com.test.uat.TC_01_Bet.ExternalLogin.sessionToken;
import static com.test.uat.TC_01_Bet.PlaceBet.betSlipId;



public class OpenBets extends BasicSetup {



    @BeforeClass
    public void startTest() throws Exception {
        extent = ExtentManager.GetExtent();
        test = extent.createTest(
                "[ID_05] Get Open Bets",
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
    public void openBets(Method testMethod) throws Exception {

        String fileName = testMethod.getName() + ".json";

        RequestBody requestBody = new FormBody.Builder()
                .add("sessionToken", sessionToken)
                .add("excludeChildBets", "true")
                .add("locale", "en-gb")
                .add("siteId", String.valueOf(site))
                .add("channelId", "6")
                .build();

        url = new URIBuilder()
                .setScheme(scheme)
                .setHost(host)
                .setPath(get_open_bets)
                .build();

        request = new Request.Builder()
                .url(url.toURL())
                .post(requestBody)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Origin", "https://sports.uat.pyr")
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
                + "[    REQUEST   BODY    ]"
                + "<br/>"
                + "<br/>"
                + requestBodyToString(requestBody).replaceAll("&", "\n").replaceAll("\"", "")
                + "<br/>"
                + "<br/>"
                + "</pre>");

        Object object         = jsonParser.parse(new FileReader(filePath + "/" + "report/JSON/" + fileName));
        String jsonResponse   = gson.toJson(object);

        String bet = "$..[?(@.betSlipId == " + betSlipId + ")]";
        Object jsonArray = JsonPath.read(jsonResponse, bet);

        createJSONDebugFile(testMethod, jsonArray);

            String eventName = JsonPath.read(jsonArray, "$[0].parts.betPart[0].event.name");
            int    eventId   = JsonPath.read(jsonArray, "$[0].parts.betPart[0].event.id");
            String betStatus = JsonPath.read(jsonArray, "$[0].betStatus");
            String betType   = JsonPath.read(jsonArray, "$[0].type");
            String selectionName   = JsonPath.read(jsonArray, "$[0].parts.betPart[0].selection.name");
            int selectionId        = JsonPath.read(jsonArray, "$[0].parts.betPart[0].selection.id");
            Double potentialPayout = JsonPath.read(jsonArray, "$[0].potentialPayout");
            int stakeAmount        = JsonPath.read(jsonArray, "$[0].stake.amount");
            String stakeCurrency   = JsonPath.read(jsonArray, "$[0].stake.currency");

            test.pass("<pre>"
                    + "[   SEARCH RESULT   ]"
                    + "<br/>"
                    + "<br/>"
                    + "Event Name        = " + eventName
                    + "<br/>"
                    + "Event ID          = " + eventId
                    + "<br/>"
                    + "Selection Name    = " + selectionName
                    + "<br/>"
                    + "Selection ID      = " + selectionId
                    + "<br/>"
                    + "Stake             = " + stakeAmount + "  " + stakeCurrency
                    + "<br/>"
                    + "Bet Type          = " +  betType
                    + "<br/>"
                    + "Bet Status        = " + betStatus
                    + "<br/>"
                    + "Potential Payout  = " + potentialPayout
                    + "<br/>"
                    + "BetSlip ID        = " + betSlipId
                    + "<br/>"
                    + "<br/>"
                    + "</pre>");
        }
    }



