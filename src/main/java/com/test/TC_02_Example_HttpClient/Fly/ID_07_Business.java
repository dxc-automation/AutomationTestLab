package com.test.TC_02_Example_HttpClient.Fly;

import com.aventstack.extentreports.AnalysisStrategy;
import com.jayway.jsonpath.JsonPath;
import com.setup.BasicSetup;
import com.setup.ExtentManager;
import org.apache.http.client.utils.URIBuilder;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.lang.reflect.Method;
import java.net.URI;

import static com.setup.ExtentManager.extent;
import static com.setup.ExtentManager.test;
import static com.setup.HttpClientUtils.*;
import static com.test.TC_02_Example_HttpClient.Fly.ID_01_AccessToken.accessToken;


public class ID_07_Business extends BasicSetup {

    public static String locationID;

    @BeforeClass
    public void startTest() throws Exception {
        extent = ExtentManager.GetExtent();
        test = extent.createTest("[ID_07] List Of Business", "Get a list of business associated to the accessToken or SSO tokens provided with the request.");
        test.assignAuthor("Pavel Popov");
        test.assignCategory("GET");
        extent.setAnalysisStrategy(AnalysisStrategy.TEST);
    }

    @Test
    public void getBusiness(Method testMethod) throws Exception {

        // Create url address
        url = new URIBuilder()
                .setScheme("https")
                .setHost("api.flypaythis.com")
                .setPath("/v4/business")
                .addParameter("accessToken", accessToken)
                .build();

        JSONObject jsonGetData = new JSONObject();

        String requestData = jsonGetData.toString(4);
        String fileName = testMethod.getName() + ".json";

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
                + requestData.replace("    ", "&nbsp;&nbsp;")
                + "<br />"
                + "<br />"
                + "</pre>");

        Object object = parser.parse(new FileReader(filePath + "/" + "report/JSON/" + fileName));
        locationID = JsonPath.read(object, "$.businesses.141.id");

        test.info("<pre>"
                + "[ KEYS ]"
                + "<br />"
                + "\n businessId = " + locationID
                + "<br />"
                + "<br />"
                + "</pre>");

        Assert.assertEquals(responseMsg, "OK");
    }
}
