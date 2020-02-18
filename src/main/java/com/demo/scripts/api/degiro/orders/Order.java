package com.demo.scripts.api.degiro.orders;

import com.demo.properties.TestData;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.Assert;

import java.io.FileReader;

import static com.demo.config.ReporterConfig.startTestReport;
import static com.demo.config.ReporterConfig.test;
import static com.demo.properties.Environments.*;
import static com.demo.properties.FilePaths.report_json_folder;
import static com.demo.properties.TestData.*;
import static com.demo.utilities.FileUtility.*;
import static com.demo.utilities.web_services.HttpClientConfig.*;
import static com.demo.utilities.web_services.HttpClientUtils.getClosableHttpClientResponseDetails;
import static com.demo.utilities.web_services.HttpClientUtils.post;

public class Order {


    private static String scheme;
    private static String host;
    private static String path;


    static final Logger LOG = LogManager.getLogger(Order.class);

    private static void report() throws Exception {
        String testName        = "<b>[POST] Place Order</b>";
        String testCategory    = "API";
        String testDescription = "The purpose of this test is to verify that the login functionality is working as expected"              +
                "<br><br><b>*****   D E S C R I P T I O N   *****</b><br><br>"                                                       +
                "[1] Check that the login page can be opened and displayed with correct title.<br>"                      +
                "[2] Check the visualization of the login form element by image comparing based on RGB color model.<br>" +
                "[3] Check login with valid credentials.";

        startTestReport(testName, testDescription, testCategory);
    }




    public static void placeOrder(String fileName, int amount, int price, String buySell) throws Exception {
        report();

        scheme = "https";
        host   = INT_TEST_HOST;
        path   = INT_ORDER + sessionID;

        url = new URIBuilder()
                .setScheme(scheme)
                .setHost(host)
                .setPath(path)
                .setParameter("intAccount", String.valueOf(intAccount))
                .setParameter("sessionId", sessionID)
                .build();


        String productID = String.valueOf(TestData.productId);

        JSONObject jsonPostData = new JSONObject();
        jsonPostData.put("size",      amount);
        jsonPostData.put("buySell",   buySell.toUpperCase());
        jsonPostData.put("timeType",  1);
        jsonPostData.put("orderType", 0);
        jsonPostData.put("price",     price);
        jsonPostData.put("productId", productID);

        StringEntity entity = new StringEntity(jsonPostData.toJSONString());

        response = closeableHttpClient().execute(post(entity, url));
        getClosableHttpClientResponseDetails(response);
        createLogFile(fileName, getFormattedJson(responseStringEntity));


        test.info("<pre>"
                + "<br/>"
                + "<center><b>* * * * * * * *    R E Q U E S T    * * * * * * * *</b></center>"
                + "<br />"
                + "<br />"
                + "Method:   " + requestMethod
                + "<br />"
                + "Host:     " + scheme + "://" + host
                + "<br />"
                + "Path:     " + path  + url.getQuery()
                + "<br/>"
                + "<br/>"
                + getFormattedJson(jsonPostData.toString())
                + "<br/>"
                + "<br/>"
                + "</pre>");

        try {
            Object object         = jsonParser.parse(new FileReader(report_json_folder + fileName));
            JSONObject jsonObject = (JSONObject) object;
            JSONArray confArray   = JsonPath.read(jsonObject, "$..confirmationId");
            confirmationId = confArray.get(0).toString();

            test.pass("<pre>"
                    + "<br/>"
                    + "<center><b>* * * * * * * *    I N F O R M A T I O N    * * * * * * * *</b></center>"
                    + "<br />"
                    + "<br />"
                    + "Your confirmation ID is " + confirmationId
                    + "<br />"
                    + "<br/>"
                    + "</pre>");
        } catch (Exception e) {
            System.out.println("Get values from the response body has failed");
        }

        Assert.assertTrue(responseCode > 199 && responseCode < 300);
    }

}
