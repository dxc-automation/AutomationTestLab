package com.demo.utilities;

import com.demo.config.BasicTestConfig;
import com.google.gson.*;
import okhttp3.MediaType;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.demo.properties.FilePaths.report_json_folder;
import static com.demo.properties.TestData.fileName;
import static com.demo.utilities.web_services.HttpClientConfig.*;


public class FileUtility extends BasicTestConfig {

    public static final JsonParser parser     = new JsonParser();
    public static final JSONParser jsonParser = new JSONParser();
    public static final Gson gson = new Gson();
    public static final MediaType MediaTypeJSON = MediaType.parse("application/json; charset=utf-8");

    private static String date;


    public static String getDate() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.now();
        date = dateTimeFormatter.format(localDate);
        return date;
    }


    public static Timestamp getTime(Long timestamp) throws Exception {
        Timestamp time = new Timestamp(timestamp);
        return time;
    }


    public static String getFileName(Method method) throws Exception {
        String fileName = method.getName() + ".json";
        return fileName;
    }


    public static String readJsonResponseFile() {
        try {
            Gson gson = new GsonBuilder().setLenient().setPrettyPrinting().create();
            JsonObject object = parser.parse(new FileReader(report_json_folder + fileName)).getAsJsonObject();
            String formattedJson = gson.toJson(object);
            return formattedJson;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String getFormattedJson(String responseBody) {
        try {
            Gson gson = new GsonBuilder().setLenient().setPrettyPrinting().create();
            JsonObject jsonObject    = parser.parse(responseBody).getAsJsonObject();
            String formattedJson = gson.toJson(jsonObject);
            return formattedJson;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static File createLogFile(String fileName, String responseBody) {
        try {
            File file = new File(report_json_folder + fileName);
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(getFormattedJson(responseBody));
            fileWriter.flush();
            fileWriter.close();

            return file;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static File createDebugFile(String fileName, String responseBody) {
        try {
            String response = responseBody
                    .replace("vwd.hchart.seriesRequestManager.sync_response(", "")
                    .replace(")", "");

            File file = new File(report_json_folder + fileName);
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(getFormattedJson(response));
            fileWriter.flush();
            fileWriter.close();
            return file;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}