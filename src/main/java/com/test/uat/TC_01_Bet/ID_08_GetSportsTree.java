package com.test.uat.TC_01_Bet;

import com.aventstack.extentreports.AnalysisStrategy;
import com.google.gson.Gson;
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


public class ID_08_GetSportsTree extends BasicSetup {

    private int popularCompetition;
    private int eventMarketsNumber;
    private int eventId;
    private int competitionId;
    private int eventsNumber;
    private Long eventTime;
    private String eventState;
    private String eventName;
    private String competitionName;
    private boolean displayed;
    private boolean eventIsInplay;
    private Timestamp time;


    @BeforeClass
    public void startTest() throws Exception {
        extent = ExtentManager.GetExtent();
        test = extent.createTest("[ID_08] Get Sports Tree", "DESCRIPTION");
        test.assignAuthor("YOUR NAME");
        test.assignCategory("OkHttpClient");
        extent.setAnalysisStrategy(AnalysisStrategy.TEST);
    }

    @Test
    public void getSportsTree(Method testMethod) throws Exception {

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
                + "[ R E Q U E S T   H E A D E R S ]"
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
                + "[ R E Q U E S T   B O D Y ]"
                + "<br />"
                + "<br />"
                + requestBodyToString(requestBody).replaceAll("&", "\n").replaceAll("\"", "")
                + "<br />"
                + "<br />"
                + "</pre>");

        Gson gson = new Gson();
        JsonParser jsonParser = new JsonParser();
        Object object = jsonParser.parse(new FileReader(filePath + "/" + "report/JSON/" + fileName));
        String jsonResponse = gson.toJson(object);

        try {
            eventsNumber = JsonPath.read(jsonResponse, "$.popularCompetitions[0].numEvents");
            eventMarketsNumber = JsonPath.read(jsonResponse, "$.popularCompetitions[0].event[0].numMarkets");

            if (eventsNumber > 0 && eventMarketsNumber > 0) {

                Object marketsJsonArray = JsonPath.read(jsonResponse, "$.popularCompetitions[0].event[0].markets");

                displayed = JsonPath.read(jsonResponse, "$.popularCompetitions[0].event[0].displayed");
                eventIsInplay = JsonPath.read(jsonResponse, "$.popularCompetitions[0].event[0].isInplay");
                eventId = JsonPath.read(jsonResponse, "$.popularCompetitions[0].event[0].id");
                competitionId = JsonPath.read(jsonResponse, "$.popularCompetitions[0].id");

                eventTime = JsonPath.read(jsonResponse, "$.popularCompetitions[0].event[0].eventTime");
                time = new Timestamp(eventTime);

                eventState = JsonPath.read(jsonResponse, "$.popularCompetitions[0].event[0].state");
                competitionName = JsonPath.read(jsonResponse, "$.popularCompetitions[0].event[0].compNames.longName");
                eventName = JsonPath.read(jsonResponse, "$.popularCompetitions[0].event[0].names.longName");

                test.pass("<pre>"
                        + "[ E V E N T    D E T A I L S ]"
                        + "<br />"
                        + "<br />"
                        + "Competition ID = " + competitionId
                        + "<br />"
                        + "Competition Name = " + competitionName
                        + "<br />"
                        + "<br />"
                        + "Event ID = " + eventId
                        + "<br />"
                        + "Event Name = " + eventName
                        + "<br />"
                        + "Event Time = " + time
                        + "<br />"
                        + "<br />"
                        + "IsInplay = " + eventIsInplay
                        + "<br />"
                        + "IsDisplayed = " + displayed
                        + "<br />"
                        + "State = " + eventState
                        + "<br />"
                        + "</pre>");


            } else {
                try {
                eventsNumber = JsonPath.read(jsonResponse, "$.popularCompetitions[1].numEvents");
                eventMarketsNumber = JsonPath.read(jsonResponse, "$.popularCompetitions[1].event[0].numMarkets");

                if (eventsNumber > 0 && eventMarketsNumber > 0) {

                    Object marketsJsonArray = JsonPath.read(jsonResponse, "$.popularCompetitions[1].event[0].markets");

                    displayed = JsonPath.read(jsonResponse, "$.popularCompetitions[1].event[0].displayed");
                    eventIsInplay = JsonPath.read(jsonResponse, "$.popularCompetitions[1].event[0].isInplay");
                    eventId = JsonPath.read(jsonResponse, "$.popularCompetitions[1].event[0].id");
                    competitionId = JsonPath.read(jsonResponse, "$.popularCompetitions[1].id");

                    eventTime = JsonPath.read(jsonResponse, "$.popularCompetitions[1].event[0].eventTime");
                    time = new Timestamp(eventTime);

                    eventState = JsonPath.read(jsonResponse, "$.popularCompetitions[1].event[0].state");
                    competitionName = JsonPath.read(jsonResponse, "$.popularCompetitions[1].event[0].compNames.longName");
                    eventName = JsonPath.read(jsonResponse, "$.popularCompetitions[1].event[0].names.longName");

                    test.pass("<pre>"
                            + "[ E V E N T    D E T A I L S ]"
                            + "<br />"
                            + "<br />"
                            + "Competition ID = " + competitionId
                            + "<br />"
                            + "Competition Name = " + competitionName
                            + "<br />"
                            + "<br />"
                            + "Event ID = " + eventId
                            + "<br />"
                            + "Event Name = " + eventName
                            + "<br />"
                            + "Event Time = " + time
                            + "<br />"
                            + "<br />"
                            + "IsInplay = " + eventIsInplay
                            + "<br />"
                            + "IsDisplayed = " + displayed
                            + "<br />"
                            + "State = " + eventState
                            + "<br />"
                            + "</pre>");
                        }
                    } finally {

                }
                }
            } catch (Exception e) {
              e.printStackTrace();
        }
    }
}




