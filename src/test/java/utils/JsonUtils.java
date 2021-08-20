package utils;

import com.google.gson.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class JsonUtils {
    public static String getValueTagItem(String json, String nameTag, int item){
        JsonArray jsonArray = new Gson().fromJson(json, JsonArray.class);
        JsonObject jsonObject = jsonArray.get(item).getAsJsonObject();
        return jsonObject.get(nameTag).getAsString();
    }

    public static JsonObject getElement(String json, int item){
        JsonArray jsonArray = new Gson().fromJson(json, JsonArray.class);
        return jsonArray.get(item).getAsJsonObject();
    }

    public static <T> T toObject(String json, Class <T> classObj){
        Gson gson = new Gson();
        return gson.fromJson(json, classObj);
    }

    public static int size(String json){
        JsonArray jsonArray = new Gson().fromJson(json, JsonArray.class);
        return jsonArray.size();
    }

    public static void parseToJson(String str){
        JsonParser parser = new JsonParser();
        parser.parse(str);
    }

    public static boolean TryParseToJson(String json){
        try{
            JsonUtils.parseToJson(json);
            return true;
        }
        catch (JsonSyntaxException err){ return false;}
    }

    public static boolean CheckSortAscOnTag(String json, String tag){
        for(int i = 0; i < size(json) - 1; i++){
            if(Integer.parseInt(getValueTagItem(json,tag, i)) >=
                    Integer.parseInt(getValueTagItem(json,tag, i+1))) {
                return false;
            }
        }
        return true;
    }

    public static <T> T readJSONObject( String param, String path, Class <T> classObj){
       String json = readJSONParam(param, path);
        return toObject(json, classObj);
    }

    public static String readJSONParam( String param, String path){
        JSONParser parser = new JSONParser();
        JSONObject json = new JSONObject();
        try {
            JSONObject a = (JSONObject) parser.parse(new FileReader(path));
            json = (JSONObject) a.get(param);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return json.toJSONString();
    }
    public static String readJSON( String path){
        JSONParser parser = new JSONParser();
        JSONObject json = new JSONObject();
        try {
            json = (JSONObject) parser.parse(new FileReader(path));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return json.toJSONString();
    }

    public static <T> void writeJSON(String path, T classObj){
        try (Writer writer = new FileWriter(path)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String jsonString = gson.toJson(classObj);
            writer.write(jsonString);
        } catch (IOException e) { e.printStackTrace(); }
    }
}
