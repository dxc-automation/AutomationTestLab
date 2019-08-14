package com.test.uat.TC_01_Bet;

import com.aventstack.extentreports.AnalysisStrategy;
import com.setup.BasicSetup;
import com.setup.ExtentManager;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.apache.http.client.utils.URIBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.constants.API.calculate_cashout;
import static com.setup.ConsoleRunner.host;
import static com.setup.ConsoleRunner.scheme;
import static com.setup.ExtentManager.extent;
import static com.setup.ExtentManager.test;
import static com.setup.HttpClientUtils.url;
import static com.setup.JSONUtils.convertJson;
import static com.setup.OkHttpClientUtils.*;
import static com.test.uat.TC_01_Bet.ID_02_ExternalLogin.*;
import static com.test.uat.TC_01_Bet.ID_03_GetFootballSportTree.selectionDecimal;
import static com.test.uat.TC_01_Bet.ID_03_GetFootballSportTree.selectionId;
import static com.test.uat.TC_01_Bet.ID_04_PlaceBet.*;


public class ID_06_CalculateCashout extends BasicSetup {

    @BeforeClass
    public void startTest() throws Exception {
        extent = ExtentManager.GetExtent();
        extent = ExtentManager.GetExtent();
        test = extent.createTest(
                "[ID_06] Get Open Bets",
                "<pre>"
                        + "DESCRIPTION"
                        + "<br/>"
                        + "Send POST request to get all bets with status OPEN. Then search for a Bet Slip ID in the response."
                        + "</pre>");
        test.assignAuthor("YOUR NAME");        test.assignAuthor("YOUR NAME");
        test.assignCategory("OkHttpClient");
        extent.setAnalysisStrategy(AnalysisStrategy.TEST);
    }

    @Test
    public void calcCashout(Method testMethod) throws Exception {

        String fileName = testMethod.getName() + ".json";

        System.out.println("\n \n \n BETSLIP \n" + betSlipId);

        String jsonString =
                "{\"CalculateCashoutRequest\":" +
                "{\"accountId\":"               + accountId + "," +
                "\"bets\":"                     +
                "{\"bet\":"                     +
                "[{\"type\":\""                 + betTypeRequest + "\"," +
                "\"winType\":\""                + betWinTypeRequest + "\"," +
                "\"stake\":"                    +
                "{\"amount\":"                  + amountRequest + "," +
                "\"currency\":\""               + accountCurrency + "\"}," +
                "\"parts\":"                    +
                "{\"betPart\":"                 +
                "[{\"partNo\":"                 + partNoRequest + "," +
                "\"selectionId\":"              + selectionId + "," +
                "\"odds\":"                     +
                "{\"decimal\":"                 + selectionDecimal + "," +
                "\"fractional\":null}}]},"      +
                "\"id\":\""                     + betSlipId + "\"}]}}}";

        RequestBody requestBody = new FormBody.Builder()
                .add("sessionToken", sessionToken)
                .add("bets", jsonString)
                .build();

        String jsonBody = convertJson(jsonString);

        url = new URIBuilder()
                .setScheme(scheme)
                .setHost(host)
                .setPath(calculate_cashout)
                .build();

        request = new Request.Builder()
                .url(url.toURL())
                .post(requestBody)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Sec-Fetch-Mode", "cors")
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
                + "[   REQUEST   BODY   ]"
                + "<br />"
                + "<br />"
                + jsonBody
                + "<br />"
                + "</pre>");
    }
}
