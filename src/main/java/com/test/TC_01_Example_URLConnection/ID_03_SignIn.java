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
import java.net.UnknownHostException;

import static com.setup.ExtentManager.extent;
import static com.setup.ExtentManager.test;
import static com.test.TC_01_Example_URLConnection.ID_01_AccessToken.accessToken;
import static com.setup.URLConnection.*;


public class ID_03_SignIn extends BasicSetup {

    protected static Logger LOG = LoggerFactory.getLogger(ID_03_SignIn.class);

    public static String requestData = "";
    public static String requestURL  = "";



    @BeforeClass()
    public void startTest() throws UnknownHostException {
        extent = ExtentManager.GetExtent();
        test = ExtentManager.extent.createTest("[SOAP] Sign In", "The purpose of this test is to verify that the user can get a valid access token.");
        test.assignAuthor("Pavel Popov");
        test.assignCategory("HttpURLConnection");
        extent.setAnalysisStrategy(AnalysisStrategy.TEST);
    }

    @Test
    public void signIn(Method testMethod) throws Exception {

        String parameters = "?accessToken=" + accessToken;
        requestURL = "https://api.flypaythis.com/v2/auth/login" + parameters;

        JSONObject jsonPostData = new JSONObject();
        jsonPostData.put("email", "user@email.com");
        jsonPostData.put("challenge", 1234);
        jsonPostData.put("credentialsType", "email");
        jsonPostData.put("challengeType", "pin");
        jsonPostData.put("countryCodeId", 221);
        jsonPostData.put("phoneNumber", 777799900);

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

        httpConnection.disconnect();
    }
}

