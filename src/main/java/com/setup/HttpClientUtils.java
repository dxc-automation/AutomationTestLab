package com.setup;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.*;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.*;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static com.setup.BasicSetup.*;

public class HttpClientUtils {

    public static CloseableHttpClient client;
    public static URI url;

    public static String requestLine;
    public static String responseMsg;
    public static ProtocolVersion requestProtocol;
    public static String requestHost;
    public static String requestPath;
    public static String requestScheme;
    public static int httpResponseCode;
    public static JSONObject objectResponse;
    public static HttpResponse serverResponse;
    public static String requestMethod;
    public static String requestURL;
    public static List<Header> httpResponseHeaders;
    public static List<Header> httpRequestHeaders;

    private static HttpPost post;
    private static HttpGet get;


    public static File createJsonFile(String fileName, JSONObject objectResponse) throws Exception {
        File file = new File(filePath + "/" + "report/JSON/" + fileName);
        FileWriter fw = new FileWriter(file);
        fw.write(objectResponse.toString(4));
        fw.flush();
        fw.close();

        return file;
    }



    public static void getUrlElements() throws Exception {
        requestHost = url.getHost();
        requestPath = url.getRawPath();
        requestScheme = url.getScheme();

        return ;
    }

    public static HttpPost httpPost(String fileName, URI url, JSONObject jsonPostData) throws Exception {
        client = HttpClientBuilder.create().build();

        StringEntity parameters = new StringEntity(jsonPostData.toString());

        post = new HttpPost(url);
        post.setHeader("Content-Type", "application/json");
        post.setHeader("Accept", "*/*");
        post.setEntity(parameters);

        getUrlElements();
        getPostRequestHeaders();

        requestMethod = post.getMethod();
        requestProtocol = post.getProtocolVersion();

        serverResponse = client.execute(post);
        responseMsg = serverResponse.getStatusLine().getReasonPhrase();


        HttpEntity entity = serverResponse.getEntity();
        String responseEntity = EntityUtils.toString(entity, "UTF-8");

        requestLine = post.getRequestLine().toString();
        objectResponse = new JSONObject(responseEntity);

        getAllResponseHeaders();
        getHttpResponseCode();

        HttpClientUtils.createJsonFile(fileName, objectResponse);

        return post;
    }


    public static HttpGet httpGet(String fileName, URI url) throws Exception {
        client = HttpClientBuilder.create().build();

        get = new HttpGet(url);
        get.setHeader("Content-Type", "application/json");
        get.setHeader("Accept", "*/*");


        requestMethod = get.getMethod();
        requestURL = get.getURI().toURL().toString();

        getGetRequestHeaders();

        serverResponse = client.execute(get);

        httpResponseCode = serverResponse.getStatusLine().getStatusCode();
        responseMsg = serverResponse.getStatusLine().getReasonPhrase();

        HttpEntity entity = serverResponse.getEntity();
        String responseEntity = EntityUtils.toString(entity, "UTF-8");

        requestLine = get.getRequestLine().toString();

        objectResponse = new JSONObject(responseEntity);

        getAllResponseHeaders();
        getHttpResponseCode();

        HttpClientUtils.createJsonFile(fileName, objectResponse);

        return get;
    }

    public static int getHttpResponseCode() throws Exception {
        try {
            httpResponseCode = serverResponse.getStatusLine().getStatusCode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return httpResponseCode;
    }


    public static List<Header> getAllResponseHeaders() throws Exception {
        httpResponseHeaders = Arrays.asList(serverResponse.getAllHeaders());
        for (Header header : httpResponseHeaders) {
        }
        return httpResponseHeaders;
    }

    public static String getPostRequestHeaders() throws Exception {
        httpRequestHeaders = Arrays.asList(post.getAllHeaders());
        for (Header header : httpRequestHeaders) {
        }
        return httpRequestHeaders.toString().replace("[", "").replace("]", "");
    }

    public static String getGetRequestHeaders() throws Exception {
        httpRequestHeaders = Arrays.asList(get.getAllHeaders());
        for (Header header : httpRequestHeaders) {
        }
        return httpRequestHeaders.toString().replace("[", "").replace("]", "");
    }
}