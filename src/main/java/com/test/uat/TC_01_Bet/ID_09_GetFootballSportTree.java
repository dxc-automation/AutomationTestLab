package com.test.uat.TC_01_Bet;

import com.aventstack.extentreports.AnalysisStrategy;
import com.google.gson.JsonArray;
import com.jayway.jsonpath.JsonPath;
import com.setup.BasicSetup;
import com.setup.ExtentManager;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.apache.http.client.utils.URIBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileWriter;
import java.lang.reflect.Method;
import java.sql.Timestamp;

import static com.constants.API.get_sports_tree;
import static com.setup.ConsoleRunner.host;
import static com.setup.ConsoleRunner.scheme;
import static com.setup.ExtentManager.extent;
import static com.setup.ExtentManager.test;
import static com.setup.HttpClientUtils.url;
import static com.setup.OkHttpClientUtils.*;
import static com.test.uat.TC_01_Bet.ID_01_LogIn.site;
import static com.test.uat.TC_01_Bet.ID_02_ExternalLogin.sessionToken;


public class ID_09_GetFootballSportTree extends BasicSetup {

    protected FileWriter fileWriter;

    // ***  COMPETITION   ***//
    public static String competitionName;

    // ***  EVENT   *** //
    public static int    eventMarketsNumber;
    public static int    eventId;
    public static int    competitionId;
    public static Long   eventTime;
    public static String eventState;
    public static String eventName;
    public static boolean displayed;
    public static boolean eventIsInplay;
    public static Timestamp time;

    // ***  MARKET  *** //
    public static String marketName;
    public static String marketType;
    public static int    marketId;
    public static String marketCashout;
    public static String selectionDecimal;
    public static String selectionFractional;
    public static int    selectionId;

    protected static Object allMarkets;
    protected static Object displayedMarketsObj;
    protected static String displayedMarkets;
    public static JsonArray eventsNumber;




    @BeforeClass
    public void startTest() throws Exception {
        extent = ExtentManager.GetExtent();
        test = extent.createTest(
                "[ID_09] Get Football Sport Tree",
                "<pre>"
                        + "DESCRIPTION"
                        + "<br/>"
                        + "Verify that the login functionality is working correctly."
                        + "</pre>");
        test.assignAuthor("YOUR NAME");
        test.assignCategory("OkHttpClient");
        extent.setAnalysisStrategy(AnalysisStrategy.TEST);
    }

    @Test
    public void getFootballTree(Method testMethod) throws Exception {

        String fileName = testMethod.getName() + ".json";

        RequestBody requestBody = new FormBody.Builder()
                .add("sessionToken", sessionToken)
                .add("sport", "SOCCER")
                .add("includeOutrights", "true")
                .add("includeEvents", "true")
                .add("locale", "en-gb")
                .add("siteId", String.valueOf(site))
                .add("channelId", "6")
                .build();

        url = new URIBuilder()
                .setScheme(scheme)
                .setHost(host)
                .setPath(get_sports_tree)
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
                + "<br/>"
                + "Host:     " + requestURLHost
                + "<br/>"
                + "Path:     " + requestURLPath
                + "<br/>"
                + "<br/>"
                + getRequestOkClientHeaders()
                + "<br />"
                + "<br />"
                + "[    REQUEST   BODY    ]"
                + "<br/>"
                + "<br/>"
                + requestBodyToString(requestBody).replaceAll("&", "\n").replaceAll("\"", "")
                + "<br/>"
                + "<br/>"
                + "</pre>");


        String jsonResponse = getJsonResponse(fileName);
        eventsNumber        = JsonPath.read(jsonResponse, "$.popularCompetitions.[?(@.numEvents > 0)]");
        eventMarketsNumber  = JsonPath.read(jsonResponse, "$.popularCompetitions.[?(@.numMarkets > 0)]");
        eventState          = JsonPath.read(jsonResponse, "$.popularCompetitions.[?(@.suspended == false)]");

        System.out.println(eventsNumber);



        test.pass("<pre>"
                        + "[   EVENT    DETAILS   ]"
                        + "<br/>"
                        + "  used for place bets  "
                        + "<br/>"
                        + "<br/>"
                        + "Competition ID = "   + competitionId
                        + "<br/>"
                        + "Competition Name = " + competitionName
                        + "<br/>"
                        + "<br/>"
                        + "Event ID = "         + eventId
                        + "<br/>"
                        + "Event Name = "       + eventName
                        + "<br/>"
                        + "Event Time = "       + time
                        + "<br/>"
                        + "Event State = "      + eventState
                        + "<br/>"
                        + "Event IsInplay = "   + eventIsInplay
                        + "<br/>"
                        + "Event IsDisplayed = "+ displayed
                        + "<br/>"
                        + "<br/>"
                        + "Selection ID = "     + selectionId
                        + "<br/>"
                        + "Selection Decimal = "    + selectionDecimal
                        + "<br/>"
                        + "Selection Fractional = " + selectionFractional
                        + "</pre>");
    }
}



