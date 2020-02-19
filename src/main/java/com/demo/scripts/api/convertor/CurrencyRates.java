package com.demo.scripts.api.convertor;

import org.apache.http.Header;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.cookie.Cookie;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.demo.config.ReporterConfig.startTestReport;
import static com.demo.config.ReporterConfig.test;
import static com.demo.properties.Environments.CURRENCY_CALC_HOST;
import static com.demo.properties.Environments.CURRENCY_CALC_PATH;
import static com.demo.utilities.FileUtility.createLogFile;
import static com.demo.utilities.FileUtility.getFormattedJson;
import static com.demo.utilities.web_services.HttpClientConfig.*;
import static com.demo.utilities.web_services.HttpClientUtils.get;
import static com.demo.utilities.web_services.HttpClientUtils.getClosableHttpClientResponseDetails;

public class CurrencyRates {


    private static String host;
    private static String path;
    private static String scheme;

    static final Logger LOG = LogManager.getLogger(CurrencyRates.class);

    private static void report() throws Exception {
        String testName = "Get SearchProductVWDServices Rates";
        String testDescription = "The purpose of this test is to verify that the correct user details are received.";
        String testCategory = "API";

        startTestReport(testName, testDescription, testCategory);
    }


    public static void getRates(String fileName, String convertTo, String convertFrom, String convertAmount) throws Exception {
        report();

        scheme = "https";
        host   = CURRENCY_CALC_HOST;
        path   = CURRENCY_CALC_PATH;

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        String currentDate = dateFormat.format(date);

        url = new URIBuilder()
                .setScheme(scheme)
                .setHost(host)
                .setPath(path)
                .setCustomQuery(currentDate)
                .addParameter("format", "json")
                .addParameter("to", convertTo)
                .addParameter("from", convertFrom)
                .addParameter("amaount", convertAmount)
                .build();
        
        response = closeableHttpClient().execute(get(url));
        getClosableHttpClientResponseDetails(response);


        test.info("<pre>"
                + "<br/>"
                + "<center><b>* * * * * * * *    R E Q U E S T    * * * * * * * *</b></center>"
                + "<br />"
                + "<br />"
                + "Method:   " + requestMethod
                + "<br />"
                + "Host:     " + scheme + "://" + host
                + "<br />"
                + "Path:     " + "/" + url.getQuery()
                + "<br/>"
                + "<br/>"
                + "</pre>");

        createLogFile(fileName, getFormattedJson(responseBody));
        Assert.assertTrue(responseCode > 199 && responseCode < 300);
    }
}
