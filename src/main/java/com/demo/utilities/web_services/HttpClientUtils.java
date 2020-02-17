package com.demo.utilities.web_services;

import com.demo.config.BasicTestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileReader;
import java.net.URI;
import java.util.Arrays;

import static com.demo.utilities.FileUtility.*;
import static com.demo.utilities.web_services.HttpClientConfig.*;
import static com.demo.properties.FilePaths.report_json_folder;
import static com.demo.properties.TestData.fileName;


public class HttpClientUtils extends BasicTestConfig {

    public static HttpGet get;


    public static HttpGet get(URI url) {
        String address = url.toString();
        get = new HttpGet(address);
        get.setHeader("Content-Type", "application/json");
        get.setHeader("Connection", "keep-alive");

        requestMethod = get.getMethod();
        return get;
    }




    public static HttpPost post(StringEntity entity, URI url) {
        String address = url.toString();
        HttpPost post = new HttpPost(address);
        post.setHeader("Content-Type", "application/json");
        post.setHeader("Accept", "*/*");
        post.setHeader("Connection", "keep-alive");
        post.setEntity(entity);

        requestMethod = post.getMethod();
        return post;
    }


    public static void getClosableHttpClientResponseDetails(CloseableHttpResponse response) throws Exception {
        try {
            responseEntity = response.getEntity();
            responseStringEntity = EntityUtils.toString(responseEntity, "UTF-8");
            responseBody = getFormattedJson(responseStringEntity);
            responseCode = response.getStatusLine().getStatusCode();
            responseMsg = response.getStatusLine().getReasonPhrase();
            responseHeaders = Arrays.asList(response.getAllHeaders())
                    .toString()
                    .replace(", ", "\n")
                    .replace("[", "")
                    .replace("]", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}