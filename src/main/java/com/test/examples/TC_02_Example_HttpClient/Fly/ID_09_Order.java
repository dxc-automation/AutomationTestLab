package com.test.examples.TC_02_Example_HttpClient.Fly;

import com.aventstack.extentreports.AnalysisStrategy;
import com.setup.BasicSetup;
import com.setup.ExtentManager;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.setup.ExtentManager.extent;
import static com.setup.ExtentManager.test;
import static com.setup.HttpClientUtils.*;
import static com.test.examples.TC_02_Example_HttpClient.Fly.ID_01_AccessToken.accessToken;
import static com.test.examples.TC_02_Example_HttpClient.Fly.ID_07_Business.locationID;
import static com.test.examples.TC_02_Example_HttpClient.Fly.ID_08_Menu.itemID;

public class ID_09_Order extends BasicSetup {

    @BeforeClass
    public void startTest() throws Exception {
        extent = ExtentManager.GetExtent();
        test = extent.createTest("[ID_09] Create An Order", "DESCRIPTION");
        test.assignAuthor("Pavel Popov");
        test.assignCategory("POST");
        extent.setAnalysisStrategy(AnalysisStrategy.TEST);
    }

    @Test
    public void setOrder(Method testMethod) throws Exception {
        url = new URIBuilder()
                .setScheme("https")
                .setHost("api.flypaythis.com")
                .setPath("/ordering/v2/order")
                .addParameter("accessToken", accessToken)
                .build();

        int id = Integer.parseInt(locationID);

        JSONObject uuid = new JSONObject();
        uuid.put("uuid", itemID);

        JSONArray items = new JSONArray();
        items.put(uuid);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("locationId", id);
        //jsonPostData.put("items", items);
        jsonObject.append("items", uuid);

        String requestData = jsonObject.toString(4);
        String fileName = testMethod.getName() + ".json";

        StringEntity entity = new StringEntity(jsonObject.toString());

        httpPost(fileName, url, entity).setHeader("X-Flypay-Api-Key", "sltpeikpvo4i2rzqufo5f");

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
