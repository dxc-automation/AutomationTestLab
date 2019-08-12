package com.setup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static com.setup.HttpClientUtils.*;


public class OkHttpClientUtils extends BasicSetup {

    public static String responseOkClientHeaders;
    public static String requestOkClientHeaders;
    public static int    okHttpResponseCode;
    public static String responseProtocol;
    public static String requestURLHost;
    public static String requestURLPath;
    public static String requestURLScheme;
    public static String requestMethod;
    public static String query;
    public static Request request;

    public static OkHttpClient okHttpClient;
    public static Response okServerResponse;
    public static File file;
    public static Gson gson = new Gson();
    public static Gson gsonPretyPrint = new GsonBuilder().setPrettyPrinting().create();
    public static JsonParser jsonParser = new JsonParser();



    public static OkHttpClient okClientRequest(String fileName, Request request) throws Exception {
        file = new File(filePath + "/" + "report/JSON/" + fileName);

        okHttpClient = new OkHttpClient.Builder()
                .readTimeout(190, TimeUnit.SECONDS)
                .connectTimeout(300, TimeUnit.SECONDS)
                .callTimeout(190, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .followRedirects(true)
                .build();

        okServerResponse = okHttpClient.newCall(request).execute();

        query = request.url().query();
        requestMethod = request.method();
        requestURLPath = request.url().uri().getPath();
        requestURLHost = request.url().uri().getHost();
        requestURLScheme = request.url().uri().getScheme();

        // Get request headers
        //getRequestOkClientHeaders();

        // Get response headers
        getResponseOkClientHeaders();

        // Get response code
        getOkHttpResponseCode(okServerResponse);

        // Parse the response to json
        String responseBody = okServerResponse.body().string();


        try {
            jsonObjectResponse = new JSONObject(responseBody);
        } catch (Exception e) {
            System.out.println("*** No JSON Object found in response ***");
        }

        try {
            jsonArrayResponse = new JSONArray(responseBody);
        } catch (Exception e) {
            System.out.println("*** No JSON Array found in response ***");
        }


        response = gsonPretyPrint.toJson(responseBody);

        responseProtocol = okServerResponse.protocol().toString().toUpperCase();
        responseMsg = okServerResponse.message();

        createJsonFile(fileName);

        return okHttpClient;
    }



    public static File createJsonFile(String fileName ) throws Exception {
        file = new File(filePath + "/" + "report/JSON/" + fileName);

        if (jsonObjectResponse != null) {
            FileWriter fw = new FileWriter(file);
            fw.write(jsonObjectResponse.toString());
            fw.flush();
            fw.close();


        } else if (jsonArrayResponse != null) {
            FileWriter fw = new FileWriter(file);
            fw.write(jsonArrayResponse.toString());
            fw.flush();
            fw.close();

        }
        return file;
    }




    public static String requestBodyToString(RequestBody requestBody) throws IOException, IOException {
        Buffer buffer = new Buffer();
        requestBody.writeTo(buffer);
        String object = buffer.readUtf8();

        String jsonRequest = String.valueOf(gsonPretyPrint.toJsonTree(object));

        return jsonRequest;
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

    public static JSONObject checkDisplayed(JSONArray markets, JSONObject[] jsonObjects) {
        for ( JSONObject object : jsonObjects) {
            if (object.getBoolean("displayed") == true);
            return object;
        }
        return null;
    }
}