package com.setup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayway.restassured.mapper.ObjectMapper;
import okhttp3.*;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;

import static com.setup.HttpClientUtils.*;


public class OkHttpClientUtils extends BasicSetup {

    public static OkHttpClient okHttpClient;
    public static RequestBody requestBody;
    public static Response okServerResponse;
    public static String responseOkClientHeaders;
    public static String requestOkClientHeaders;
    public static int okHttpResponseCode;
    public static String responseProtocol;
    public static String requestURL;
    public static String requestURLHost;
    public static String requestURLPath;
    public static String requestURLScheme;
    public static String requestMethod;
    public static Request request;

    public static File createJsonFile(Response okServerResponse, String fileName) throws Exception {
        File file = new File(filePath + "/" + "report/JSON/" + fileName);
        FileWriter fw = new FileWriter(file);
        fw.write(objectResponse.toString());
        fw.flush();
        fw.close();

        return file;
    }

    public static OkHttpClient postRequest(String fileName, Request request) throws Exception {
        // Create client object
        okHttpClient = new OkHttpClient();

        // Send request and get the response body
        okServerResponse = okHttpClient.newCall(request).execute();

        requestMethod = request.method();
        requestURLPath = request.url().uri().getPath();
        requestURLHost = request.url().uri().getHost();
        requestURLScheme = request.url().uri().getScheme();

        // Get request headers
        getRequestOkClientHeaders();

        // Get response headers
        getResponseOkClientHeaders();

        // Get response code
        getOkHttpResponseCode(okServerResponse);

        // Parse the response to json
        String responseBody = okServerResponse.body().string();
        objectResponse = new JSONObject(responseBody);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        response = gson.toJson(responseBody);

        responseProtocol = okServerResponse.protocol().toString().toUpperCase();
        responseMsg = okServerResponse.message();

        createJsonFile(okServerResponse, fileName);

        return okHttpClient;
    }

    public static int getOkHttpResponseCode(Response okServerResponse) throws Exception {
        try {
            okHttpResponseCode = okServerResponse.code();
            } catch (Exception e) {
            e.printStackTrace();
        }
            return okHttpResponseCode;
    }


    public static String getResponseOkClientHeaders() throws Exception {
        try {
            responseOkClientHeaders = okServerResponse.headers().toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseOkClientHeaders;
    }

    public static String getRequestOkClientHeaders() throws Exception {
        try {
            requestOkClientHeaders = request.headers().toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return requestOkClientHeaders;
    }
}