package com.test.examples.TC_02_Example_HttpClient.Fly;

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

import static com.setup.ExtentManager.extent;
import static com.setup.ExtentManager.test;
import static com.setup.HttpClientUtils.*;
import static com.test.examples.TC_02_Example_HttpClient.Fly.ID_01_AccessToken.accessToken;
import static com.test.examples.TC_02_Example_HttpClient.Fly.ID_07_Business.locationID;

public class ID_08_Menu extends BasicSetup {

    public static String itemName;
    public static String itemID;

    @BeforeClass
    public void startTest() throws Exception {
        extent = ExtentManager.GetExtent();
        test = extent.createTest("[ID_08] Menu For Ordering", "DESCRIPTION");
        test.assignAuthor("Pavel Popov");
        test.assignCategory("GET");
        extent.setAnalysisStrategy(AnalysisStrategy.TEST);
    }

    @Test
    public void getMenu(Method testMethod) throws Exception {

        int orderType = 2;

        // Create url address
        url = new URIBuilder()
                .setScheme("https")
                .setHost("api.flypaythis.com")
                .setPath("/v2/location/" + locationID + "/menu/" + orderType)
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
        itemName = JsonPath.read(object, "$.menus[0].categories[0].items[0].portions[0].name");
        Assert.assertEquals(itemName, "Classic Beef");

        itemID = JsonPath.read(object, "$.menus[3].categories[17].items[0].portions[0].uuid");
        Assert.assertNotNull(itemID);

        test.info("<pre>"
                + "[ KEYS ]"
                + "<br />"
                + "Item Name = " + itemName
                + "<br />"
                + "Item ID = " + itemID
                + "<br />"
                + "<br />"
                + "</pre>");
    }
}
