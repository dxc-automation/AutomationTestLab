package com.setup;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static com.setup.BasicSetup.path;

public class HttpClientUtils {

    public static URI url;

    public static String requestLine;
    public static String responseMsg;
    public static ProtocolVersion requestProtocol;
    public static String requestHost;
    public static String requestPath;
    public static String requestScheme;
    public static int httpResponseCode;
    public static JSONObject jsonObjectResponse;
    public static JSONArray  jsonArrayResponse;
    public static HttpResponse serverResponse;
    public static String requestMethod;
    public static String requestURL;
    public static List<Header> httpResponseHeaders;
    public static List<Header> httpRequestHeaders;
    public static CookieStore cookieStore = new BasicCookieStore();

    private static HttpPost post;
    private static HttpGet get;
    private static CloseableHttpClient client = HttpClients.custom().setDefaultCookieStore(cookieStore).build();


    public static File createJsonFile(String fileName, JSONObject objectResponse) throws Exception {
        File file = new File(path +  "/report/JSON/" + fileName);
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

    public static HttpPost httpPost(String fileName, URI url, StringEntity entity) throws Exception {

        post = new HttpPost(url);
        post.setHeader("Content-Type", "application/json");
        post.setHeader("Accept", "*/*");
        post.setHeader("Connection", "keep-alive");
        post.setEntity(entity);

        getUrlElements();
        getPostRequestHeaders();

        requestMethod = post.getMethod();
        requestProtocol = post.getProtocolVersion();

        serverResponse = client.execute(post);
        responseMsg = serverResponse.getStatusLine().getReasonPhrase();
        
        HttpEntity serverResponseEntity = serverResponse.getEntity();
        String responseEntity = EntityUtils.toString(serverResponseEntity, "UTF-8");

        requestLine = post.getRequestLine().toString();
        jsonObjectResponse = new JSONObject(responseEntity);

        getAllResponseHeaders();
        getHttpResponseCode();

        HttpClientUtils.createJsonFile(fileName, jsonObjectResponse);

        return post;
    }


    public static HttpGet httpGet(String fileName, URI url) throws Exception {

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

        try {
            jsonObjectResponse = new JSONObject(responseEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }

        getAllResponseHeaders();
        getHttpResponseCode();

        HttpClientUtils.createJsonFile(fileName, jsonObjectResponse);

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


    public static String getAllResponseHeaders() throws Exception {
        httpResponseHeaders = Arrays.asList(serverResponse.getAllHeaders());
        for (Header header : httpResponseHeaders ) {
        }
        return httpResponseHeaders.toString();
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