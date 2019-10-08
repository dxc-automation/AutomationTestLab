package com.test.uat.TC_01_Bet;

import com.aventstack.extentreports.AnalysisStrategy;
import com.jayway.jsonpath.JsonPath;
import com.setup.BasicSetup;
import com.setup.ExtentManager;
import okhttp3.Request;
import org.apache.http.client.utils.URIBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.lang.reflect.Method;

import static com.objects.API.RAM_SESSION_ID;
import static com.setup.ConsoleRunner.ramHost;
import static com.setup.ConsoleRunner.scheme;
import static com.setup.ExtentManager.extent;
import static com.setup.ExtentManager.test;
import static com.setup.HttpClientUtils.url;
import static com.setup.JSONUtils.gson;
import static com.setup.JSONUtils.jsonParser;
import static com.setup.OkHttpClientUtils.*;


public class SessionID extends BasicSetup {

    @BeforeClass
    public void startTest() throws Exception {
        extent = ExtentManager.GetExtent();
        extent = ExtentManager.GetExtent();
        test = extent.createTest(
                "[ID_06] Get Session ID",
                "<pre>"
                        + "DESCRIPTION"
                        + "<br/>"
                        + "Send POST request to get all bets with status OPEN. Then search for a Bet Slip ID in the response."
                        + "</pre>");
        test.assignAuthor("YOUR NAME");
        test.assignCategory("OkHttpClient");
        extent.setAnalysisStrategy(AnalysisStrategy.TEST);
    }

    @Test
    public void sessionId(Method testMethod) throws Exception {

        String fileName = testMethod.getName() + ".json";


        url = new URIBuilder()
                .setScheme(scheme)
                .setHost(ramHost)
                .setPath(RAM_SESSION_ID)
                .build();


        request = new Request.Builder()
                .url(url.toURL())
                .get()
                .build();

        okClientRequest(fileName, request);


        // Add request data to the report
        test.info("<pre>"
                + "[   REQUEST  HEADERS   ]"
                + "<br />"
                + "<br />"
                + "Method:   "    + requestMethod
                + "<br />"
                + "Protocol: "    + requestURLScheme.toUpperCase()
                + "<br />"
                + "Host:     "    + requestURLHost
                + "<br />"
                + "Path:     "    + requestURLPath
                + "<br />"
                + getRequestOkClientHeaders()
                + "<br/>"
                + "</pre>");

        Object object         = jsonParser.parse(new FileReader(path + "/" + "report/JSON/" + fileName));
        String jsonResponse   = gson.toJson(object);

        String signature = JsonPath.read(jsonResponse, "$.signature");
    }
}
