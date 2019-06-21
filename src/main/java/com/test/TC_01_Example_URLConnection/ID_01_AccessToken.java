package com.test.TC_01_Example_URLConnection;

import com.aventstack.extentreports.AnalysisStrategy;
import com.setup.BasicSetup;
import com.setup.ExtentManager;
import com.setup.URLConnection;
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
import static com.setup.URLConnection.*;


public class ID_01_AccessToken extends BasicSetup {

    protected static Logger LOG = LoggerFactory.getLogger(ID_01_AccessToken.class);

    public static String accessToken = "";
    public static String requestData = "";
    public static String requestURL = "";


    @BeforeClass()
    public void startTest() throws UnknownHostException {
        extent = ExtentManager.GetExtent();
        test = ExtentManager.extent.createTest("[SOAP] Access Token", "The purpose of this test is to verify that the user can get a valid access token.");
        test.assignAuthor("Pavel Popov");
        test.assignCategory("HttpURLConnection");
        extent.setAnalysisStrategy(AnalysisStrategy.TEST);
    }

    @Test
    public void getAccessToken(Method testMethod) throws Exception {

        requestURL = "https://api.flypaythis.com/v1/apps/bootstrap";

        JSONObject jsonPostData = new JSONObject();
        jsonPostData.put("deviceToken", "07304e56c452be73ad2b51a4647d0300");
        jsonPostData.put("platform", "Android");
        jsonPostData.put("platformVersion", "6.0");
        jsonPostData.put("appId", 1);
        jsonPostData.put("frameworkVersion", "1.0.0");
        jsonPostData.put("model", "LG Nexus 5X");
        jsonPostData.put("appVersion", "1.0.0");

        requestData = jsonPostData.toString(4);
        URLConnection.sendPost(requestURL, requestData);
        String method = httpConnection.getRequestMethod();

        test.info("<pre>"
                + "**************************************  REQUEST  **************************************"
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

        String fileName = testMethod.getName() + ".json";

        JSONObject objectResponse = new JSONObject(readInput(fileName));

        //String fileName = testMethod.getName() + ".json";
        //File filePath = new File(System.getProperty("user.dir")).getParentFile();
        //File file = new File(filePath + "/" + "report/JSON/" + fileName);
        //FileWriter fw = new FileWriter(file);
        //fw.write(objectResponse.toString(4));
        //fw.flush();
        //fw.close();

        accessToken = objectResponse.getString("accessToken");
        System.out.println("\n ******* TOKEN ******* \n" + accessToken);

        Assert.assertNotNull(accessToken);

        httpConnection.disconnect();
    }
}











