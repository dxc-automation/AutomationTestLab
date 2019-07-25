package com.test.uat.TC_01_Bet;

import com.aventstack.extentreports.AnalysisStrategy;
import com.setup.BasicSetup;
import com.setup.ExtentManager;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.apache.http.client.utils.URIBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.setup.ConsoleRunner.host;
import static com.setup.ConsoleRunner.scheme;
import static com.setup.ExtentManager.extent;
import static com.setup.ExtentManager.test;
import static com.setup.HttpClientUtils.url;
import static com.setup.OkHttpClientUtils.*;
import static com.test.uat.TC_01_Bet.ID_01_LogIn.*;
import static com.test.uat.TC_01_Bet.ID_02_ExternalLogin.sessionToken;
import static com.test.uat.TC_01_Bet.ID_03_GetOpenBets.accountId;
import static com.test.uat.TC_01_Bet.ID_03_GetOpenBets.selectionId;
import static com.test.uat.TC_01_Bet.ID_04_PlaceBet.betSlipId;



public class ID_07_Cashout extends BasicSetup {



    @BeforeClass
    public void startTest() throws Exception {
        extent = ExtentManager.GetExtent();
        test = extent.createTest("[ID_06] Cashout bet", "DESCRIPTION");
        test.assignAuthor("YOUR NAME");
        test.assignCategory("OkHttpClient");
        extent.setAnalysisStrategy(AnalysisStrategy.TEST);
    }

    @Test
    public void betCashout(Method testMethod) throws Exception {

        String fileName = testMethod.getName() + ".json";

        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");

        RequestBody requestBody =  new FormBody.Builder()
                .add("accountId", String.valueOf(accountId))
                .add("sessionToken", sessionToken)
                .add("betId", betSlipId)
                .add("selectionId", String.valueOf(selectionId))
                .add("siteId", String.valueOf(site))
                .add("cashOutStake", "")
                .build();

        url = new URIBuilder()
                .setScheme(scheme)
                .setHost(host)
                .setPath("/sportsbook/v1/api/cashoutBet")
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

        Object object = parser.parse(requestBody.toString());
        System.out.println(object);

        test.info("<pre>"
                + "[ REQUEST  HEADERS ]"
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
                + "[ REQUEST  BODY ]"
                + "<br />"
                + "<br />"
                + requestBodyToString(requestBody)
                + "<br />"
                + "</pre>");
    }
}
