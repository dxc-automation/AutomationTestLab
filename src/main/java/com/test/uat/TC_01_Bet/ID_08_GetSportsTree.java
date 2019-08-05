package com.test.uat.TC_01_Bet;

import com.aventstack.extentreports.AnalysisStrategy;
import com.jayway.jsonpath.JsonPath;
import com.setup.BasicSetup;
import com.setup.ExtentManager;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.apache.http.client.utils.URIBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

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


        Object object = parser.parse(new FileReader(filePath + "/" + "report/JSON/" + fileName));
        JSONObject jsonResponse = (JSONObject) object;


            Long competition_1 = JsonPath.read(jsonResponse, "popularCompetitions[0].numEvents");
            if (competition_1 > 0) {
                boolean displayed = JsonPath.read(jsonResponse, "$.popularCompetitions[0].event[0].displayed");
                boolean eventIsInplay = JsonPath.read(jsonResponse, "$.popularCompetitions[0].event[0].isInplay");
                Long eventId = JsonPath.read(jsonResponse, "$.popularCompetitions[0].event[0].id");
                Long competitionId = JsonPath.read(jsonResponse, "$.popularCompetitions[0].id");

                Long eventTime = JsonPath.read(jsonResponse, "$.popularCompetitions[0].event[0].eventTime");
                Timestamp time = new Timestamp(eventTime);

                String eventState = JsonPath.read(jsonResponse, "$.popularCompetitions[0].event[0].state");
                String competitionName = JsonPath.read(jsonResponse, "$.popularCompetitions[0].event[0].compNames.longName");
                String eventNames = JsonPath.read(jsonResponse, "$.popularCompetitions[0].event[0].names.longName");

                Long eventMarkets = JsonPath.read(jsonResponse, "$.popularCompetitions[0].event[0].numMarkets");
                if (eventMarkets > 0) {
                    JSONArray markets = JsonPath.read(jsonResponse, "$.popularCompetitions[0].event[0].markets");

                    List<Map<String, Object>> dataList = JsonPath.parse(markets).read("$[?('true' in @['displayed'])]");
                    JSONArray marketSelection = (JSONArray) dataList.get(0);
                    System.out.println(marketSelection);


                    if (eventMarkets > 0) {
                        JSONObject marketObject_1 = (JSONObject) markets.get(0);
                        boolean marketIsDisplayed_1 = (boolean) marketObject_1.getOrDefault("displayed", true);

                        if (marketIsDisplayed_1 == false) {
                            JSONObject marketObject_2 = (JSONObject) markets.get(3);
                            boolean marketIsDisplayed_2 = (boolean) marketObject_2.getOrDefault("displayed", true);
                            System.out.println(marketIsDisplayed_2);


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
                                    + "Event Name = " + eventNames
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
                    }
                }

                } else {
                    Long competition_2 = JsonPath.read(jsonResponse, "popularCompetitions[1].numEvents");
                    if (competition_1 == 0 && competition_2 > 0) {
                        boolean displayed = JsonPath.read(jsonResponse, "$.popularCompetitions[1].event[0].displayed");
                        boolean eventIsInplay = JsonPath.read(jsonResponse, "$.popularCompetitions[1].event[0].isInplay");
                        Long eventId = JsonPath.read(jsonResponse, "$.popularCompetitions[1].event[0].id");
                        Long competitionId = JsonPath.read(jsonResponse, "$.popularCompetitions[1].id");

                        Long eventTime = JsonPath.read(jsonResponse, "$.popularCompetitions[1].event[0].eventTime");
                        Timestamp time = new Timestamp(eventTime);

                        String eventState = JsonPath.read(jsonResponse, "$.popularCompetitions[1].event[0].state");
                        String competitionName = JsonPath.read(jsonResponse, "$.popularCompetitions[1].event[0].compNames.longName");
                        String eventNames = JsonPath.read(jsonResponse, "$.popularCompetitions[1].event[0].names.longName");

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
                                + "Event Name = " + eventNames
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
                }
            }
        }



