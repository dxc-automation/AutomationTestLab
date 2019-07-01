package com.test.TC_04_AmelcoAPI;

import com.aventstack.extentreports.AnalysisStrategy;
import com.setup.BasicSetup;
import com.setup.ExtentManager;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static com.constants.Accounts.uat1_password;
import static com.constants.Accounts.uat1_username;
import static com.setup.ExtentManager.extent;
import static com.setup.ExtentManager.test;
import static com.setup.HttpClientUtils.*;

public class ID_02_SignIn extends BasicSetup {

    public static UrlEncodedFormEntity entity;

    @BeforeClass
    public void startTest() throws Exception {
        extent = ExtentManager.GetExtent();
        test = extent.createTest("[ID_02] TEST NAME", "DESCRIPTION");
        test.assignAuthor("YOUR NAME");
        test.assignCategory("HttpClient");
        extent.setAnalysisStrategy(AnalysisStrategy.TEST);
    }

    @Test
    public void signIn(Method testMethod) throws Exception {
        url = new URIBuilder()
                .setScheme("https")
                .setHost("sports.uat.pyr")
                .setPath("/sportsbook/v1/api/externalLogin")
                .build();

        JSONObject jsonPostData = new JSONObject();

        List<NameValuePair> loginParams = new ArrayList<NameValuePair>();
        loginParams.add(new BasicNameValuePair("username", uat1_username));
        loginParams.add(new BasicNameValuePair("password", uat1_password));

        entity = new UrlEncodedFormEntity(loginParams, Consts.UTF_8);

        String requestData = entity.toString();
        String fileName = testMethod.getName() + ".json";

        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(entity);

        //httpPost(fileName, url, jsonPostData).addHeader("Referer", "https://walletapi.uat.pyr/PsAmelcoApi/");

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
    }
}
