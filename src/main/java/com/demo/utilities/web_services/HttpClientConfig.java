package com.demo.utilities.web_services;

import com.demo.config.BasicConfiguration;
import org.apache.http.HttpEntity;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.File;
import java.net.URI;


public class HttpClientConfig extends BasicConfiguration {

    public static  int          responseCode;
    public static  String       responseBody;
    public static  String       responseHeaders;
    public static  String       responseMsg;
    public static  String       requestMethod;
    public static  File         file;
    public static  URI          url;
    public static  HttpEntity   responseEntity;
    public static  String       responseStringEntity;
    public static  CloseableHttpResponse response;
    private static CloseableHttpClient   client;
    public static  CookieStore cookieStore = new BasicCookieStore();



    public static CloseableHttpClient closeableHttpClient() {
        RequestConfig config = RequestConfig.custom()
                .setSocketTimeout(300000)
                .setConnectTimeout(300000)
                .setConnectionRequestTimeout(300000)
                .setRedirectsEnabled(true)
                .build();

        return client = HttpClientBuilder.create()
                .setDefaultCookieStore(cookieStore)
                .setDefaultRequestConfig(config)
                .build();
    }
}