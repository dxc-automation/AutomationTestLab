package com.demo.utilities.web_services;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.JSONObject;

import java.io.*;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 *         This is utility class that contains all methods for
 *
 *         HTTP/HTTPS  connection,   request  and  httpResponse
 *
 *         config.
 *
 */

public class HttpURLConnectionConfig {

    public static HttpURLConnection httpConnection;
    public static String jsonResponse = "";


    /**
     * Makes an HTTP request using GET method to the specified URL.
     * @param requestURL the URL of the remote server
     * @return An HttpURLConnection object
     * @throws IOException thrown if any I/O error occurred
     */

    public static HttpURLConnection sendGet(String requestURL) throws IOException {
        URL url = new URL(requestURL);
        httpConnection = (HttpURLConnection) url.openConnection();
        httpConnection.setRequestMethod("GET");
        httpConnection.setRequestProperty("Content-Type", "application/json");
        httpConnection.setUseCaches(false);
        httpConnection.setDoInput(true);
        httpConnection.setDoOutput(true);

        return httpConnection;
    }


    /**
     * Makes an HTTP request using POST method to the specified URL.
     * @param requestURL the URL of the remote server
     * @param requestData A map containing POST data in form of key-value pairs
     * @return An HttpURLConnection object
     * @throws IOException thrown if any I/O error occurred
     */
    public static HttpURLConnection sendPost(String requestURL, String requestData) throws IOException {
        URL url = new URL(requestURL);
        httpConnection = (HttpURLConnection) url.openConnection();
        httpConnection.setRequestMethod("POST");
        httpConnection.setRequestProperty("Content-Type", "application/json");
        httpConnection.setUseCaches(false);
        httpConnection.setDoInput(true);
        httpConnection.setDoOutput(true);
        httpConnection.setInstanceFollowRedirects(true);
        httpConnection.connect();

        OutputStreamWriter writer = new OutputStreamWriter(httpConnection.getOutputStream());
        writer.write(requestData);
        writer.flush();

        return httpConnection;
    }


    /**
     * Returns an array of lines from the server's httpResponse. This method should
     * be used if the server returns multiple lines of String.
     * @return an array of Strings of the server's httpResponse
     * @throws IOException thrown if any I/O error occurred
     */

    public static JsonObject readRespone() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line = "";
        while ((line = reader.readLine()) != null) {
            response.append(line);

            jsonResponse = jsonResponse.concat(line);
            System.out.println("\n ****** RESPONSE ****** \n" + jsonResponse);
        }
        reader.close();
        return new JsonParser().parse(response.toString()).getAsJsonObject();
    }

    public static String readInput(String fileName) throws Exception {
      InputStream inputStream;
      int responseCode = httpConnection.getResponseCode();

      if (responseCode >= 299 && responseCode <= 499)
          inputStream = httpConnection.getErrorStream();
      else
          inputStream = httpConnection.getInputStream();

      BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
      StringBuilder response = new StringBuilder();
      String line;
      while (( line = reader.readLine()) != null) {
          response.append(line);

          JSONObject objectResponse = new JSONObject(inputStream);
          File filePath = new File(System.getProperty("user.dir")).getParentFile();
          File file = new File(filePath + "/" + "generateReport/JSON/" + fileName);
          FileWriter fw = new FileWriter(file);
          fw.write(response.toString());
          fw.flush();
          fw.close();
      }
        return response.toString();
    }

    public static File createResponseFile(Method testMethod) throws Exception {
        JSONObject objectResponse = new JSONObject();

        String fileName = testMethod.getName() + ".json";
        File filePath = new File(System.getProperty("user.dir")).getParentFile();
        File file = new File(filePath + "/" + "generateReport/JSON/" + fileName);
        FileWriter fw = new FileWriter(file);
        fw.write(objectResponse.toString(4));
        fw.flush();
        fw.close();

        return file;
    }
}

