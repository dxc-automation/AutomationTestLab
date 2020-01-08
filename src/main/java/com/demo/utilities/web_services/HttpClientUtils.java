package com.demo.utilities.web_services;

import com.demo.config.BasicConfiguration;
import com.demo.utilities.FileUtility;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.util.Arrays;


public class HttpClientUtils extends BasicConfiguration {


    public static HttpGet get(URI url) throws Exception {
        HttpGet get = new HttpGet(url);
        get.setHeader("Content-Type", "application/json");
        get.setHeader("Accept", "*/*");
        get.setHeader("Connection", "keep-alive");

        HttpClientConfig.requestMethod = get.getMethod();
        return get;
    }




    public static HttpPost post(StringEntity entity, URI url) throws Exception {
        HttpPost post = new HttpPost(url);
        post.setHeader("Content-Type", "application/json");
        post.setHeader("Accept", "*/*");
        post.setHeader("Connection", "keep-alive");
        post.setEntity(entity);

        HttpClientConfig.requestMethod = post.getMethod();
        return post;
    }


    public static void getClosableHttpClientResponseDetails(CloseableHttpResponse response) throws Exception {
        HttpClientConfig.responseEntity       = response.getEntity();
        HttpClientConfig.responseStringEntity = EntityUtils.toString(HttpClientConfig.responseEntity, "UTF-8");
        HttpClientConfig.responseBody         = FileUtility.getFormattedJson(HttpClientConfig.responseStringEntity);
        HttpClientConfig.responseCode         = response.getStatusLine().getStatusCode();
        HttpClientConfig.responseMsg          = response.getStatusLine().getReasonPhrase();
        HttpClientConfig.responseHeaders      = Arrays.asList(response.getAllHeaders())
                            .toString()
                            .replace(", ", "\n")
                            .replace("[", "")
                            .replace("]", "");
    }




    public static JSONObject checkDisplayed(JSONArray markets, JSONObject[] jsonObjects) {
        for ( JSONObject object : jsonObjects) {
            if (object.getBoolean("displayed") == true);
            return object;
        }
        return null;
    }
}