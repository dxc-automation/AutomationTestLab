package com.demo.scripts.api.degiro.products;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.apache.http.client.utils.URIBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.Assert;

import java.io.FileReader;

import static com.demo.config.ReporterConfig.startTestReport;
import static com.demo.config.ReporterConfig.test;
import static com.demo.properties.Environments.*;
import static com.demo.properties.TestData.*;
import static com.demo.properties.FilePaths.*;
import static com.demo.utilities.FileUtility.*;
import static com.demo.utilities.web_services.HttpClientConfig.*;
import static com.demo.utilities.web_services.HttpClientUtils.get;
import static com.demo.utilities.web_services.HttpClientUtils.getClosableHttpClientResponseDetails;

public class SearchProductVWDServices {


    private static String scheme;
    private static String host;
    private static String path;


    static final Logger LOG = LogManager.getLogger(SearchProductVWDServices.class);

    private static void report() throws Exception {
        String testName        = "<b>[GET] Search Product VWD Services</b>";
        String testCategory    = "API";
        String testDescription = "The purpose of this test is to verify that the login functionality is working as expected"              +
                "<br><br><b>*****   D E S C R I P T I O N   *****</b><br><br>"                                                       +
                "[1] Check that the login page can be opened and displayed with correct title.<br>"                      +
                "[2] Check the visualization of the login form element by image comparing based on RGB color model.<br>" +
                "[3] Check login with valid credentials.";

        startTestReport(testName, testDescription, testCategory);
    }




    public static void getProductDetail(String fileName, String issueid) throws Exception {
        report();

        scheme = "https";
        host   = VWD_SERVICE_HOST;
        path   = INT_TEST_CURRENCY;

        url = new URIBuilder()
                .setScheme(scheme)
                .setHost(host)
                .setPath(path)
                .addParameter("requestid",  "0")
                .addParameter("resolution", "PT1M")
                .addParameter("culture",    "en-US")
                .addParameter("period",     "P1D")
                .addParameter("series",     "issueid:"       + issueid)
                .addParameter("series",     "price:issueid:" + issueid)
                .addParameter("format",     "json")
                .addParameter("callback",   "vwd.hchart.seriesRequestManager.sync_response")
                .addParameter("userToken",  "463421")
                .addParameter("tz",         "Europe/Dublin")
                .build();



        response = closeableHttpClient().execute(get(url));
        getClosableHttpClientResponseDetails(response);
        createDebugFile(fileName, responseStringEntity);


        test.info("<pre>"
                + "<br/>"
                + "<center><b>* * * * * * * *    R E Q U E S T    * * * * * * * *</b></center>"
                + "<br />"
                + "<br />"
                + "Method:   " + requestMethod
                + "<br />"
                + "Host:     " + scheme + "://" + host
                + "<br />"
                + "Path:     " + path + "/" + url.getQuery()
                + "<br/>"
                + "<br/>"
                + "<br/>"
                + "</pre>");


        try {
            Object object                    = jsonParser.parse(new FileReader(report_json_folder + fileName));
            JSONObject jsonObject            = (JSONObject) object;
            JSONArray currencyArray          = JsonPath.read(jsonObject.toString(), "$..name");
            JSONArray currencyLastPriceArray = JsonPath.read(jsonObject.toString(), "$..lastPrice");

            currencyConvertName = currencyArray.get(0).toString();
            currencyLastPrice   = currencyLastPriceArray.get(0).toString();

            test.info("<pre>"
                + "<br>"
                + "<center><b>* * * * * * * *    I N F O R M A T I O N    * * * * * * * *</b></center>"
                + "<br>"
                + "Currency: " + currencyConvertName
                + "<br>"
                + "Price:    " + currencyLastPrice
                + "<br>"
                + "<br>"
                + "</pre>");
        } catch (Exception e) {
            String ex = e.getLocalizedMessage();
            test.warning(ex);
            e.printStackTrace();
        }

        Assert.assertTrue(responseCode > 199 && responseCode < 300);
    }

}
