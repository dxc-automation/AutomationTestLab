package com.test.examples.TC_02_Example_HttpClient.Fly;

import com.aventstack.extentreports.AnalysisStrategy;
import com.setup.BasicSetup;
import com.setup.ExtentManager;
import org.apache.http.client.utils.URIBuilder;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.setup.ExtentManager.extent;
import static com.setup.ExtentManager.test;
import static com.setup.HttpClientUtils.*;
import static com.test.examples.TC_02_Example_HttpClient.Fly.ID_01_AccessToken.accessToken;

public class ID_04_SignIn extends BasicSetup {

    @BeforeClass
    public void startTest() throws Exception {
        extent = ExtentManager.GetExtent();
        test = extent.createTest("[ID_04] Sign In", "Returns the user object. The purpose of this test is to verify that the user can use access token for sign in.");
        test.assignAuthor("Pavel Popov");
        test.assignCategory("GET");
        extent.setAnalysisStrategy(AnalysisStrategy.TEST);
    }

    @Test
    public void signIn(Method testMethod) throws Exception {

        /*** Set URL address components. ***/
        url = new URIBuilder()
                .setScheme("https")
                .setHost("api.flypaythis.com")
                .setPath("/v1/user/me")
                .addParameter("accessToken", accessToken)
                .build();

        String fileName = testMethod.getName() + ".json";

        /*** Send request by using method 'httpGet' from HttpClientUtils.class ***/
        httpGet(fileName, url);

        // Add request data to the report
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
                + "<br />"
                + "</pre>");

        Assert.assertEquals(responseMsg, "OK");
    }
}