package com.test.uat.TC_01_Bet;

import com.aventstack.extentreports.AnalysisStrategy;
import com.setup.BasicSetup;
import com.setup.ExtentManager;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.List;

import static com.setup.JSONUtils.*;
import static com.setup.ConsoleRunner.*;
import static com.setup.ExtentManager.extent;
import static com.setup.ExtentManager.test;
import static com.setup.HttpClientUtils.*;

public class ID_01_LogIn extends BasicSetup {

    public static String externalToken;
    public static String signature;
    public static String webId;
    public static int lsrc;
    public static int site;

    @BeforeClass
    public void startTest() throws Exception {
        extent = ExtentManager.GetExtent();
        test = extent.createTest(
                "[ID_01] Log in",
                "<pre>"
                        + "DESCRIPTION"
                        + "<br/>"
                        + "Verify that the login functionality is working correctly."
                        + "</pre>");
        test.assignAuthor("Pavel Popov");
        test.assignCategory("Wallet API");
        extent.setAnalysisStrategy(AnalysisStrategy.TEST);
    }

    @Test
    public void loginRam(Method testMethod) throws Exception {
        url = new URIBuilder()
                .setScheme("https")
                .setHost("ram.uat.pyr")
                .setPath("/ram/login")
                .build();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", username);
        jsonObject.put("password", password);
        jsonObject.put("signature", sign);
        jsonObject.put("devIx", devIx);

        String requestData = jsonObject.toString(4);
        String fileName = testMethod.getName() + ".json";
        String jsonBody = convertJson(requestData);

        StringEntity entity = new StringEntity(jsonObject.toString());

        httpPost(fileName, url, entity).addHeader("Origin", "https://sports.uat.pyr");

        test.info("<pre>"
                + "[   REQUEST   HEADERS   ]"
                + "<br/>"
                + "<br/>"
                + "Method:  " + requestMethod + "  " + requestProtocol
                + "<br/>"
                + "Scheme:  " + requestScheme.toUpperCase()
                + "<br/>"
                + "Host:    " + requestHost
                + "<br/>"
                + "Path:    " + requestPath
                + "<br/>"
                + "\n"
                + getPostRequestHeaders().replace(", ", "\n")
                + "<br/>"
                + "<br/>"
                + "<br/>"
                + "[    REQUEST   BODY    ]"
                + "<br/>"
                + "<br/>"
                + jsonBody
                + "<br/>"
                + "<br/>"
                + "</pre>");


        List<org.apache.http.cookie.Cookie> cookies = cookieStore.getCookies();
        for (Cookie cookie : cookies) {
                if (cookie.getName().equalsIgnoreCase("SBTK")) {
                    externalToken = cookie.getValue();
                }
                 else if (cookie.getName().equalsIgnoreCase("SBSG")) {
                    signature = cookie.getValue();
                }
                 else if (cookie.getName().equalsIgnoreCase("WBID")) {
                     webId = cookie.getValue();
                 }
                 else if (cookie.getName().equalsIgnoreCase("LSRC")) {
                     lsrc = Integer.parseInt(cookie.getValue());
                }
                 else if (cookie.getName().equalsIgnoreCase("SITE")) {
                     site = Integer.parseInt(cookie.getValue());
                }
        }

        /*** Add key values that we take from the response. ***/
        test.pass("<pre>"
                + "[   KEYS   ]"
                + "<br/>"
                + "<br/>"
                + "\n externalToken = " + externalToken
                + "\n signature     = " + signature
                + "\n webId         = " + webId
                + "\n lsrc          = " + lsrc
                + "\n site          = " + site
                + "<br/>"
                + "<br/>"
                + "</pre>");
    }
}
