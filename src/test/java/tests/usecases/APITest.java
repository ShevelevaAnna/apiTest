package tests.usecases;

import utils.APIUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.*;
import utils.object_utils.PostsObject;
import utils.object_utils.ResponseObject;
import utils.object_utils.UsersObject;

public class APITest{
    @Test
    private void test(){
        ResponseObject result = APIUtils.getRequest(Settings.readTestSettingsTag("url")+ PathUtils.PATH_POSTS.getPath());
        Assert.assertEquals(result.getResponseCode(),200, "Статус код не 200");
        Assert.assertTrue(JsonUtils.TryParseToJson(result.getResponseBody()), "Не JSON формат");
        Assert.assertTrue(JsonUtils.CheckSortAscOnTag(result.getResponseBody(), "id"),"id не по возрастанию");

        result = APIUtils.getRequest(Settings.readTestSettingsTag("url")+ PathUtils.PATH_POSTS.getPathItem("99"));
        Assert.assertEquals(result.getResponseCode(),200, "Статус код не 200");
        Assert.assertEquals(JsonUtils.toObject(result.getResponseBody(), PostsObject.class),
                JsonUtils.readJSONObject("postsId99",Settings.readTestSettingsTag("pathTestDataGet"), PostsObject.class),
                "Полученные данные не соответствуют запросу");

        result = APIUtils.getRequest(Settings.readTestSettingsTag("url")+ PathUtils.PATH_POSTS.getPathItem("150"));
        Assert.assertEquals(result.getResponseCode(),404, "Статус код не 404");
        Assert.assertEquals(result.getResponseBody(),"{}", "Тело ответа не пустое");

        PostsObject post = RandomParameters.getRandomPost(1, 101);
        JsonUtils.writeJSON(Settings.readTestSettingsTag("pathTestDataPost"), post);
        result = APIUtils.postRequest(Settings.readTestSettingsTag("url")+PathUtils.PATH_POSTS.getPath(), post);
        Assert.assertEquals(result.getResponseCode(),201, "Статус код не 201");
        System.out.println(JsonUtils.toObject(JsonUtils.readJSON(Settings.readTestSettingsTag("pathTestDataPost")),PostsObject.class));
        Assert.assertEquals(JsonUtils.toObject(result.getResponseBody(), PostsObject.class), post,
                "Полученные данные не соответствуют запросу");

        result = APIUtils.getRequest(Settings.readTestSettingsTag("url")+ PathUtils.PATH_USERS.getPath());
        Assert.assertEquals(result.getResponseCode(),200, "Статус код не 200");
        Assert.assertTrue(JsonUtils.TryParseToJson(result.getResponseBody()), "Не JSON формат");
        UsersObject user = JsonUtils.toObject(JsonUtils.getElement(result.getResponseBody(), 4).toString(), UsersObject.class);
        Assert.assertEquals(JsonUtils.readJSONObject("userId5",Settings.readTestSettingsTag("pathTestDataGet"), UsersObject.class),
                user, "Данные user 5 не совпадают");

        result = APIUtils.getRequest(Settings.readTestSettingsTag("url")+ PathUtils.PATH_USERS.getPathItem("5"));
        Assert.assertEquals(result.getResponseCode(),200, "Статус код не 200");
        Assert.assertEquals(JsonUtils.toObject(result.getResponseBody(), UsersObject.class), user, "Данные user 5 не совпадают");
    }
}
