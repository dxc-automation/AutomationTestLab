package com.test.examples.TC_02_Example_HttpClient.Fly;

import com.aventstack.extentreports.AnalysisStrategy;
import com.setup.BasicSetup;
import com.setup.ExtentManager;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.net.UnknownHostException;

import static com.setup.ExtentManager.extent;
import static com.setup.ExtentManager.test;
import static com.setup.HttpClientUtils.*;
import static com.test.examples.TC_02_Example_HttpClient.Fly.ID_01_AccessToken.accessToken;

public class ID_02_RegisterUser extends BasicSetup {

    protected static Logger LOG = LoggerFactory.getLogger(ID_02_RegisterUser.class);

    /*** Set test name, test description, author and category. ***/
    @BeforeClass()
    public void startTest() throws UnknownHostException {
        extent = ExtentManager.GetExtent();
        test = ExtentManager.extent.createTest("[ID_02] Register New User", "The purpose of this test is to verify that the user can create a new account.");
        test.assignAuthor("Pavel Popov");
        test.assignCategory("POST");
        extent.setAnalysisStrategy(AnalysisStrategy.TEST);
    }

    @Test
    public void registerUser(Method testMethod) throws Exception {

        /*** Set URL address components. ***/
        url = new URIBuilder()
                .setScheme("https")
                .setHost("api.flypaythis.com")
                .setPath("/v1/user")
                .addParameter("accessToken", accessToken)
                .build();

        /*** Create JSON object for request body. ***/
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", "user@email.com");
        jsonObject.put("pinCode", 1234);
        jsonObject.put("credentialsType", "email");
        jsonObject.put("challengeType", "pin");
        jsonObject.put("countryCodeId", 221);
        jsonObject.put("phoneNumber", 777799900);

        String requestData = jsonObject.toString(4);
        String fileName = testMethod.getName() + ".json";

        StringEntity entity = new StringEntity(jsonObject.toString());


        /*** Send request by using method 'httpPost' from HttpClientUtils.class ***/
        httpPost(fileName, url, entity);


        /*** Add request properties to the report. ***/
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

        Assert.assertEquals(responseMsg, "OK");
    }
}

