package com.setup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Method;

import static com.setup.OkHttpClientUtils.file;

public class JSONUtils extends BasicSetup {


    public static Gson gson = new Gson();
    public static Gson gsonPretyPrint = new GsonBuilder().setPrettyPrinting().create();
    public static JsonParser jsonParser = new JsonParser();



    public static String convertJson(String jsonString) {
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(jsonString).getAsJsonObject();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String formattedJson = gson.toJson(jsonObject);

        return formattedJson;
    }


    public static void createJSONDebugFile(Method testMethod, Object jsonObject) throws Exception {
        String debugFileName = testMethod.getName() + "[debug].json";

        file = new File(filePath + "/report/JSON/" + debugFileName);
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(jsonObject.toString());
        fileWriter.flush();
        fileWriter.close();

        return;
    }
}
