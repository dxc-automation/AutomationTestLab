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
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.lang.reflect.Method;

import static com.constants.API.external_login;
import static com.setup.ConsoleRunner.*;
import static com.setup.ExtentManager.extent;
import static com.setup.ExtentManager.test;
import static com.setup.HttpClientUtils.url;
import static com.setup.JSONUtils.gson;
import static com.setup.OkHttpClientUtils.*;
import static com.test.uat.TC_01_Bet.LogInRam.*;



public class ExternalLogin extends BasicSetup {

    public static String sessionToken;
    public static int    accountId;
    public static String accountCurrency;
    public static String accountLanguage;




    @BeforeClass
    public void startTest() throws Exception {
        extent = ExtentManager.GetExtent();
        test = extent.createTest(
                "[ID_02] External Login",
                "<pre>"
                        + "DESCRIPTION"
                        + "<br/>"
                        + "Verify that the login functionality is working correctly."
                        + "</pre>");
        test.assignAuthor("Pavel Popov");
        test.assignCategory("OkHttpClient");
        extent.setAnalysisStrategy(AnalysisStrategy.TEST);
    }

    @Test
    public void externalLogin(Method testMethod) throws Exception {

        String fileName = testMethod.getName() + ".json";

        RequestBody requestBody = new FormBody.Builder()
                .add("signature", signature)
                .add("externalToken", externalToken)
                .add("webId", webId)
                .add("application", "web-sportsbook")
                .add("locale", "en-gb")
                .add("site", String.valueOf(site))
                .add("lsrc", String.valueOf(lsrc))
                .build();


        url = new URIBuilder()
                .setScheme(scheme)
                .setHost(host)
                .setPath(external_login)
                .build();

        request = new Request.Builder()
                .url(url.toURL())
                .post(requestBody)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();


        okClientRequest(fileName, request);

        test.info("<pre>"
                + "[   REQUEST   HEADERS   ]"
                + "<br/>"
                + "<br/>"
                + "Method:   "    + requestMethod
                + "<br/>"
                + "Scheme:   "    + requestURLScheme.toUpperCase()
                + "<br/>"
                + "Host:     "    + requestURLHost
                + "<br/>"
                + "Path:     "    + requestURLPath
                + "<br/>"
                + "<br/>"
                + getRequestOkClientHeaders()
                + "<br/>"
                + "<br/>"
                + "[    REQUEST   BODY    ]"
                + "<br/>"
                + "<br/>"
                + requestBodyToString(requestBody)
                + "<br/>"
                + "</pre>");

        JsonParser jsonParser = new JsonParser();
        Object object         = jsonParser.parse(new FileReader(path + "/report/JSON/" + fileName));
        String jsonResponse   = gson.toJson(object);


        accountId       = JsonPath.read(jsonResponse, "$.Login.accountId");
        accountCurrency = JsonPath.read(jsonResponse, "$.Login.accountBalance.currency");
        sessionToken    = JsonPath.read(jsonResponse, "$.Login.sessionToken");
        accountLanguage = JsonPath.read(jsonResponse, "$.Login.preferredLanguage");


        Assert.assertNotNull(sessionToken);


        /*** Add key values that we take from the response. ***/
        test.pass("<pre>"
                + "[   ACCOUNT   DETAILS   ]"
                + "<br/>"
                + "<br/>"
                + "Account    = " + username
                + "<br/>"
                + "Account ID = " + accountId
                + "<br/>"
                + "Account Currency = " + accountCurrency
                + "<br/>"
                + "Account Language = " + accountLanguage
                + "<br/>"
                + "Session Token    = " + sessionToken
                + "<br/>"
                + "<br/>"
                + "</pre>");

    }
}
