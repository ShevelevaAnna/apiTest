package utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Settings {
    private final static String settingsPath = "src/test/resources/settings.json";

    public static String readTestSettingsTag(String tag){
        String json = JsonUtils.readJSONParam("testSettings", settingsPath);
        JsonObject jsonObject = new Gson().fromJson(json, JsonObject.class);
        return jsonObject.get(tag).getAsString();
    }
}
