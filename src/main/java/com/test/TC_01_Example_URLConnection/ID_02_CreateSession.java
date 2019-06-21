package com.test.TC_01_Example_URLConnection;

import com.aventstack.extentreports.AnalysisStrategy;
import com.setup.BasicSetup;
import com.setup.ExtentManager;
import com.setup.URLConnection;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.setup.ExtentManager.extent;
import static com.setup.ExtentManager.test;
import static org.testng.Assert.assertEquals;
import static com.setup.URLConnection.*;



public class ID_02_CreateSession extends BasicSetup {

    protected static Logger LOG = LoggerFactory.getLogger(ID_02_CreateSession.class);

    public static String requestData = "";
    public static String requestURL = "";

    @BeforeClass()
    public void startTest() throws Exception {
        extent = ExtentManager.GetExtent();
        test = ExtentManager.extent.createTest("[SOAP] Create Session", "The purpose of this test is to verify that the user can get a valid access token.");
        test.assignAuthor("Pavel Popov");
        test.assignCategory("HttpURLConnection");
        extent.setAnalysisStrategy(AnalysisStrategy.TEST);
    }

    @Test
    public void createSession(Method testMethod) throws Exception {

        String parameters = "?X-RapidAPI-Key=14b52ffb72mshf0b2234c3b6dfc9p12e9efjsnf0fb21d0b544&X-RapidAPI-Host=skyscanner-skyscanner-flight-search-v1.p.rapidapi.com";

        requestURL = "https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/pricing/v1.0" + parameters;

        JSONObject jsonPostData = new JSONObject();
        jsonPostData.put("inboundDate", "07304e56c452be73ad2b51a4647d0300");
        jsonPostData.put("cabinClass", "business");
        jsonPostData.put("children", 0);
        jsonPostData.put("infants", 0);
        jsonPostData.put("country", "US");
        jsonPostData.put("currency", "USD");
        jsonPostData.put("locale", "en-US");
        jsonPostData.put("originPlace", "SFO-sky");
        jsonPostData.put("destinationPlace", "LHR-sky");
        jsonPostData.put("outboundDate", "");
        jsonPostData.put("adults", 1);

        try {
            requestData = jsonPostData.toString(4);
            URLConnection.sendPost(requestURL, requestData);

            String method = httpConnection.getRequestMethod();
            test.info("<pre>"
                    + "***********************************  REQUEST  ***********************************"
                    + "<br />"
                    + "<br />"
                    + "URL : " + requestURL
                    + "<br />"
                    + "Method : " + method
                    + "<br />"
                    + "<br />"
                    + requestData.replace("    ", "&nbsp;&nbsp;")
                    + "<br />"
                    + "***************************************************************************************"
                    + "</pre>");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String fileName = testMethod.getName() + ".json";
        JSONObject objectResponse = new JSONObject(readInput(fileName));

        //String fileName = testMethod.getName() + ".json";
        //File filePath = new File(System.getProperty("user.dir")).getParentFile();
        //File file = new File(filePath + "/" + "report/JSON/" + fileName);
        //FileWriter fw = new FileWriter(file);
        //fw.write(objectResponse.toString(4));
        //fw.flush();
        //fw.close();

        int respCode = httpConnection.getResponseCode();
        assertEquals(respCode, 200);

        httpConnection.disconnect();
    }
}


