package com.test.uat.TC_01_Bet;

import com.aventstack.extentreports.AnalysisStrategy;
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

import java.lang.reflect.Method;

import static com.setup.ConsoleRunner.host;
import static com.setup.ConsoleRunner.scheme;
import static com.setup.ExtentManager.extent;
import static com.setup.ExtentManager.test;
import static com.setup.HttpClientUtils.jsonObjectResponse;
import static com.setup.HttpClientUtils.url;
import static com.setup.OkHttpClientUtils.*;
import static com.test.uat.TC_01_Bet.ID_01_LogIn.*;
import static com.constants.API.*;



public class ID_02_ExternalLogin extends BasicSetup {

    public static String sessionToken;


    @BeforeClass
    public void startTest() throws Exception {
        extent = ExtentManager.GetExtent();
        test = extent.createTest("[ID_02] External log in", "DESCRIPTION");
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
                + "[ R E Q U E S T   H E A D E R S ]"
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
                + "[ R E Q U E S T   B O D Y ]"
                + "<br />"
                + "<br />"
                + requestBodyToString(requestBody).replaceAll("&", "\n").replaceAll("\"", "")
                + "<br />"
                + "</pre>");

        String response = jsonObjectResponse.toString();
        sessionToken = JsonPath.read(response, "$.Login.sessionToken");
        Assert.assertNotNull(sessionToken);

        /*** Add key values that we take from the response. ***/
        test.pass("<pre>"
                + "[ K E Y S ]"
                + "<br />"
                + "\n sessionToken = " + sessionToken
                + "<br />"
                + "<br />"
                + "</pre>");

    }
}
