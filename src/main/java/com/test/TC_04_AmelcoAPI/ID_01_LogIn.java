package com.test.TC_04_AmelcoAPI;

import com.aventstack.extentreports.AnalysisStrategy;
import com.setup.BasicSetup;
import com.setup.ExtentManager;
import org.apache.http.client.utils.URIBuilder;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.constants.Accounts.*;
import static com.setup.ExtentManager.extent;
import static com.setup.ExtentManager.test;
import static com.setup.HttpClientUtils.*;

public class ID_01_LogIn extends BasicSetup {

    @BeforeClass
    public void startTest() throws Exception {
        extent = ExtentManager.GetExtent();
        test = extent.createTest("[ID_01] Send log in ram module request ", "");
        test.assignAuthor("Pavel Popov");
        test.assignCategory("POST");
        extent.setAnalysisStrategy(AnalysisStrategy.TEST);
    }

    @Test
    public void loginRam(Method testMethod) throws Exception {
        url = new URIBuilder()
                .setScheme("https")
                .setHost("ram.uat.pyr")
                .setPath("/ram/login")
                .build();

        JSONObject jsonPostData = new JSONObject();
        jsonPostData.put("username", uat1_username);
        jsonPostData.put("password", uat1_password);
        jsonPostData.put("signature", uat1_signature);
        jsonPostData.put("devIx", uat1_devIx);

        String requestData = jsonPostData.toString(4);
        String fileName = testMethod.getName() + ".json";

        httpPost(fileName, url, jsonPostData).addHeader("Referer", "https://sports.uat.pyr/");

        test.info("<pre>"
                + "[ REQUEST  HEADERS ]"
                + "<br />"
                + "<br />"
                + "Method:  "  + requestMethod   +   "  "   + requestProtocol
                + "<br />"
                + "Scheme:  "    + requestScheme.toUpperCase()
                + "<br />"
                + "Host:    "      + requestHost
                + "<br />"
                + "Path:    "      + requestPath
                + "<br />"
                + "\n"
                + getPostRequestHeaders().replace(", ", "\n")
                + "<br />"
                + "<br />"
                + "<br />"
                + "[ REQUEST  BODY ]"
                + "<br />"
                + "<br />"
                + requestData.replace("    ", "&nbsp;&nbsp;")
                + "<br />"
                + "<br />"
                + "</pre>");


        String token = httpResponseHeaders.get(8).getValue();
        System.out.println("\n TOKEN \n" + token);
    }
}
