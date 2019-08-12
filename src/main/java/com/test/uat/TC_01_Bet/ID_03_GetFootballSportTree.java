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

import java.io.File;
import java.io.FileReader;
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


public class ID_03_GetFootballSportTree extends BasicSetup {

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
                "[ID_04] Get Football Sport Tree",
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
    public void getSportsTree(Method testMethod) throws Exception {

        String fileName  = testMethod.getName() + ".json";
        String fileDebug = testMethod.getName() + "[debug].json";

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


        JsonParser jsonParser = new JsonParser();
        Object object         = jsonParser.parse(new FileReader(filePath + "/" + "report/JSON/" + fileName));
        String jsonResponse   = gson.toJson(object);

        Object event = JsonPath.read(jsonResponse, "$..[?(@.suspended == false && @.displayed == true)]");

        String debugFileName = testMethod.getName() + "[debug].json";
        file = new File(filePath + "/report/JSON/" + debugFileName);
        fileWriter = new FileWriter(file);
        fileWriter.write(event.toString());
        fileWriter.flush();
        fileWriter.close();

            if (event != null) {
                marketName = JsonPath.read(event, "$[0].name");
                marketType = JsonPath.read(event, "$[0].type");
                marketId   = JsonPath.read(event, "$[0].id");
                displayed  = JsonPath.read(event, "$[0].displayed");
                selectionId   = JsonPath.read(event, "$[0].selection[0].id");
                marketCashout = JsonPath.read(event, "$[0].attributes.attrib[1].value");
                selectionDecimal    = JsonPath.read(event, "$[0].selection[0].odds.dec");
                selectionFractional = JsonPath.read(event, "$[0].selection[0].odds.frac");

                test.pass("<pre>"
                        + "[   EVENT    DETAILS   ]"
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
}



