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

import java.lang.reflect.Method;

import static com.constants.API.get_open_bets;
import static com.setup.ConsoleRunner.host;
import static com.setup.ConsoleRunner.scheme;
import static com.setup.ExtentManager.extent;
import static com.setup.ExtentManager.test;
import static com.setup.HttpClientUtils.url;
import static com.setup.OkHttpClientUtils.*;
import static com.test.uat.TC_01_Bet.ID_01_LogIn.site;
import static com.test.uat.TC_01_Bet.ID_02_ExternalLogin.sessionToken;


public class ID_03_GetOpenBets extends BasicSetup {

    protected static int       selectionId;
    protected static String    type;
    protected static String    winType;
    protected static int       amount;
    protected static int       partNo;
    protected static Double    decimal;
    protected static String    fractional;



    @BeforeClass
    public void startTest() throws Exception {
        extent = ExtentManager.GetExtent();
        test = extent.createTest("[ID_03] Get open bets", "DESCRIPTION");
        test.assignAuthor("YOUR NAME");
        test.assignCategory("OkHttpClient");
        extent.setAnalysisStrategy(AnalysisStrategy.TEST);
    }

    @Test
    public void getOpenBets(Method testMethod) throws Exception {

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
                + "[   R E Q U E S T   H E A D E R S   ]"
                + "<br />"
                + "<br />"
                + "Method:   " + requestMethod
                + "<br />"
                + "Scheme:   " + requestURLScheme.toUpperCase()
                + "<br />"
                + "Host:     " + requestURLHost
                + "<br />"
                + "Path:     " + requestURLPath
                + "<br />"
                + "<br />"
                + getRequestOkClientHeaders()
                + "<br />"
                + "<br />"
                + "[   R E Q U E S T   B O D Y   ]"
                + "<br />"
                + "<br />"
                + requestBodyToString(requestBody).replaceAll("&", "\n").replaceAll("\"", "")
                + "<br />"
                + "<br />"
                + "</pre>");

        String jsonResponse = getJsonResponse(fileName);

        selectionId = JsonPath.read(jsonResponse, "$.Bets.bet[0].parts.betPart[0].selectionId");
        winType     = JsonPath.read(jsonResponse, "$.Bets.bet[0].parts.betPart[0].winType");
        type        = JsonPath.read(jsonResponse, "$.Bets.bet[0].type");
        amount      = JsonPath.read(jsonResponse, "$.Bets.bet[0].stake.amount");
        partNo      = JsonPath.read(jsonResponse, "$.Bets.bet[0].parts.betPart[0].partNo");
        fractional  = JsonPath.read(jsonResponse, "$.Bets.bet[0].parts.betPart[0].odds.fractional");
        decimal     = JsonPath.read(jsonResponse, "$.Bets.bet[0].parts.betPart[0].odds.decimal");

        /*** Add key values that we take from the response. ***/
        test.pass("<pre>"
                + "[   K E Y S   ]"
                + "<br />"
                + "\n selectionId = "   + selectionId
                + "\n winType = "       + winType
                + "\n type = "          + type
                + "\n amount = "        + amount
                + "\n partNo = "        + partNo
                + "\n fractional = "    + fractional
                + "\n decimal = "       + decimal
                + "<br />"
                + "<br />"
                + "</pre>");
    }
}

