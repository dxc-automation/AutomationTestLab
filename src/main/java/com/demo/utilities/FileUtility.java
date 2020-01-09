package com.demo.utilities;

import com.demo.config.BasicConfiguration;
import com.demo.test_properties.FilePaths;
import com.google.gson.*;
import okhttp3.MediaType;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Method;
import java.sql.Timestamp;

import static com.demo.utilities.web_services.HttpClientConfig.*;


public class FileUtility extends BasicConfiguration {

    public static final JsonParser parser     = new JsonParser();
    public static final JSONParser jsonParser = new JSONParser();
    public static final Gson       gson       = new Gson();
    public static       String     debugFileName;
    public static final MediaType  MediaTypeJSON = MediaType.parse("application/json; charset=utf-8");



    public static Timestamp getTime(Long timestamp) throws Exception {
        Timestamp time = new Timestamp(timestamp);
        return time;
    }


    public static String getFileName(Method method) throws Exception {
        String fileName = method.getName() + ".json";
        return fileName;
    }


    public static String getFormattedJson(String responseBody) throws Exception {
        Gson gson             = new GsonBuilder().setLenient().setPrettyPrinting().create();
        Object jsonObject     = jsonParser.parse(responseBody);
        String formattedJson  = gson.toJson(jsonObject);

        return formattedJson;
    }



    public static File createLogFile(String fileName, String responseBody) throws Exception {
        File file = new File(FilePaths.report_json_folder + fileName);
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(getFormattedJson(responseBody));
        fileWriter.flush();
        fileWriter.close();

        return file;
    }



    public static void createJSONDebugFile(String fileName, String responseBody) throws Exception {
        debugFileName = fileName + "[debug].json";

        file = new File(FilePaths.report_json_folder + debugFileName);
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(responseBody);
        fileWriter.flush();
        fileWriter.close();

        return;
    }
}
