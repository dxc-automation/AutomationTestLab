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

import java.io.FileReader;
import java.lang.reflect.Method;

import static com.setup.ConsoleRunner.host;
import static com.setup.ConsoleRunner.scheme;
import static com.setup.ExtentManager.extent;
import static com.setup.ExtentManager.test;
import static com.setup.HttpClientUtils.url;
import static com.setup.OkHttpClientUtils.*;
import static com.test.uat.TC_01_Bet.ID_01_LogIn.*;
import static com.test.uat.TC_01_Bet.ID_02_ExternalLogin.*;
import static com.test.uat.TC_01_Bet.ID_03_GetOpenBets.*;
import static com.constants.API.*;




public class ID_04_PlaceBet extends BasicSetup {

    public static String betSlipId;


    @BeforeClass
    public void startTest() throws Exception {
        extent = ExtentManager.GetExtent();
        test = extent.createTest("[ID_04] Place a bet", "DESCRIPTION");
        test.assignAuthor("YOUR NAME");
        test.assignCategory("OkHttpClient");
        extent.setAnalysisStrategy(AnalysisStrategy.TEST);
    }

    @Test
    public void placeBet(Method testMethod) throws Exception {

        String fileName = testMethod.getName() + ".json";

        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");

        String bets = "{\"PlaceBetsRequest\":{\"accountId\":" + accountId + ",\"bets\":{\"bet\":[{\"type\":\"" + type + "\",\"winType\":\"" + winType + "\",\"stake\":{\"amount\":\"" + amount + "\",\"currency\":\"" + accountCurrency + "\"},\"parts\":{\"betPart\":[{\"partNo\":" + partNo + ",\"selectionId\":" + selectionId + ",\"odds\":{\"decimal\":\"" + decimal + "\",\"fractional\":\"" + fractional + "\"}}]}}]},\"channelId\":6,\"reqId\":0,\"acceptPriceChange\":true}}";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonBets = gson.toJson(bets);
        System.out.println("\n jsonBets \n" + jsonBets);


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
                + "[   R E Q U E S T   H E A D E R S   ]"
                + "<br />"
                + "<br />"
                + "Method:   "    + requestMethod
                + "<br />"
                + "Scheme:   "    + requestURLScheme.toUpperCase()
                + "<br />"
                + "Host:     "    + requestURLHost
                + "<br />"
                + "Path:     "    + requestURLPath
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

        Object object = parser.parse(new FileReader(filePath + "/" + "report/JSON/" + fileName));
        betSlipId = JsonPath.read(object, "$.PlaceBetsResponse.betSlipId");

        /*** Add key values that we take from the response. ***/
        test.pass("<pre>"
                + "[   K E Y S   ]"
                + "<br />"
                + "\n betSlipId = " + betSlipId
                + "<br />"
                + "<br />"
                + "</pre>");
    }
}
