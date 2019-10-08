package com.test.uat.TC_01_Bet;

import com.aventstack.extentreports.AnalysisStrategy;
import com.google.gson.JsonParser;
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
import java.io.FileWriter;
import java.lang.reflect.Method;
import java.sql.Timestamp;

import static com.objects.API.GET_SPORT_TREE;
import static com.setup.ConsoleRunner.host;
import static com.setup.ConsoleRunner.scheme;
import static com.setup.ExtentManager.extent;
import static com.setup.ExtentManager.test;
import static com.setup.HttpClientUtils.url;
import static com.setup.JSONUtils.createJSONDebugFile;
import static com.setup.JSONUtils.gson;
import static com.setup.OkHttpClientUtils.*;
import static com.test.uat.TC_01_Bet.LogInRam.site;
import static com.test.uat.TC_01_Bet.ExternalLogin.sessionToken;


public class FootballSportTree extends BasicSetup {

    protected FileWriter fileWriter;

    // ***  EVENT   *** //
    public static boolean displayed;
    public static Timestamp time;

    // ***  MARKET  *** //
    public static String marketName;
    public static String marketType;
    public static int    marketId;
    public static String marketCashout;
    public static String selectionDecimal;
    public static String selectionFractional;
    public static int    selectionId;




    @BeforeClass
    public void startTest() throws Exception {
        extent = ExtentManager.GetExtent();
        test = extent.createTest(
                "[ID_03] Get Football Sport Tree",
                "<pre>"
                        + "DESCRIPTION"
                        + "<br/>"
                        + "Get the full football sports tree and search for event that is displayed and has markets that are not suspended."
                        + "</pre>");
        test.assignAuthor("YOUR NAME");
        test.assignCategory("OkHttpClient");
        extent.setAnalysisStrategy(AnalysisStrategy.TEST);
    }

    @Test
    public void getSportsTree(Method testMethod) throws Exception {

        String fileName  = testMethod.getName() + ".json";

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
                .setPath(GET_SPORT_TREE)
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


        JsonParser jsonParser = new JsonParser();
        Object object         = jsonParser.parse(new FileReader(path + "/report/JSON/" + fileName));
        String jsonResponse   = gson.toJson(object);

        Object jsonArray = JsonPath.read(jsonResponse, "$..[?(@.suspended == false && @.displayed == true)]");

        createJSONDebugFile(testMethod, jsonArray);

                marketName = JsonPath.read(jsonArray, "$[0].name");
                marketType = JsonPath.read(jsonArray, "$[0].type");
                marketId   = JsonPath.read(jsonArray, "$[0].id");
                displayed  = JsonPath.read(jsonArray, "$[0].displayed");
                selectionId   = JsonPath.read(jsonArray, "$[0].selection[0].id");
                selectionDecimal    = JsonPath.read(jsonArray, "$[0].selection[0].odds.dec");
                selectionFractional = JsonPath.read(jsonArray, "$[0].selection[0].odds.frac");

                test.pass("<pre>"
                        + "[   EVENT    DETAILS   ]"
                        + "<br/>"
                        + "<br/>"
                        + "Market Name  = " + marketName
                        + "<br/>"
                        + "Market Type  = " + marketType
                        + "<br/>"
                        + "Market ID    = " + marketId
                        + "<br/>"
                        + "Selection ID = " + selectionId
                        + "<br/>"
                        + "Cashout      = " + marketCashout
                        + "<br/>"
                        + "Decimal      = " + selectionDecimal
                        + "<br/>"
                        + "Fractional   = " + selectionFractional
                        + "</pre>");
        }
}



