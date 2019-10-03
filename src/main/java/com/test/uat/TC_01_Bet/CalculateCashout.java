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

import static com.constants.API.calculate_cashout;
import static com.setup.ConsoleRunner.host;
import static com.setup.ConsoleRunner.scheme;
import static com.setup.ExtentManager.extent;
import static com.setup.ExtentManager.test;
import static com.setup.HttpClientUtils.url;
import static com.setup.JSONUtils.*;
import static com.setup.OkHttpClientUtils.*;
import static com.test.uat.TC_01_Bet.ExternalLogin.*;
import static com.test.uat.TC_01_Bet.FootballSportTree.selectionDecimal;
import static com.test.uat.TC_01_Bet.FootballSportTree.selectionId;
import static com.test.uat.TC_01_Bet.PlaceBet.*;


public class CalculateCashout extends BasicSetup {

    public static Double cashoutValue;

    @BeforeClass
    public void startTest() throws Exception {
        extent = ExtentManager.GetExtent();
        extent = ExtentManager.GetExtent();
        test = extent.createTest(
                "[ID_06] Calculate Cashout",
                "<pre>"
                        + "DESCRIPTION"
                        + "<br/>"
                        + "Send POST request to get all bets with status OPEN. Then search for a Bet Slip ID in the response."
                        + "</pre>");
        test.assignAuthor("YOUR NAME");
        test.assignAuthor("YOUR NAME");
        test.assignCategory("OkHttpClient");
        extent.setAnalysisStrategy(AnalysisStrategy.TEST);
    }

    @Test
    public void calcCashout(Method testMethod) throws Exception {

        String fileName = testMethod.getName() + ".json";

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

        Object object         = jsonParser.parse(new FileReader(path + "/report/JSON/" + fileName));
        String jsonResponse   = gson.toJson(object);

        String betStatus    = JsonPath.read(jsonResponse, "$.CalculateCashoutResponse.cashoutResult[0].status");
        String betId        = JsonPath.read(jsonResponse, "$.CalculateCashoutResponse.cashoutResult[0].betId");

        try {
            cashoutValue = JsonPath.read(jsonResponse, "$.CalculateCashoutResponse.cashoutResult[0].cashoutValue");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            int integerCashoutValue = JsonPath.read(jsonResponse, "$.CalculateCashoutResponse.cashoutResult[0].cashoutValue");
            String stringCashoutValue = Integer.toString(integerCashoutValue);
            cashoutValue = Double.valueOf(stringCashoutValue);
        } catch (Exception e) {
            e.printStackTrace();
        }

        test.pass("<pre>"
                + "[   CASHOUT DETAILS   ]"
                + "<br/>"
                + "<br/>"
                + "Bet ID = " + betId
                + "<br/>"
                + "Status = " + betStatus
                + "<br/>"
                + "Value  = " + cashoutValue
                + "<br/>"
                + "<br/>"
        );
    }
}
