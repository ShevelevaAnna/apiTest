package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.springframework.http.HttpStatus;
import utils.object_utils.PostsObject;
import utils.object_utils.ResponseObject;

public class APIUtils {
    public static ResponseObject postRequest(String url, PostsObject post){
        String param = "title="+post.getTitle()+"&body="+post.getBody()+"&userId="+post.getUserId();
        StringBuilder messages = new StringBuilder();
        HttpURLConnection connection = null;
        BufferedReader in = null;
        PrintWriter out = null;
        int code = 0;
        try{
            URL postUrl = new URL(url);
            connection = (HttpURLConnection) postUrl.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.connect();

            out = new PrintWriter(connection.getOutputStream());
            out.print(param);
            out.flush();

            code = connection.getResponseCode();

            in = (connection.getResponseCode() == HttpStatus.CREATED.value() ?
                    new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8)) :
                    new BufferedReader(new InputStreamReader(connection.getErrorStream())));
                String line;
            while ((line = in.readLine()) != null) {
                messages.append(line);
            }

        } catch (IOException e) { e.printStackTrace(); }
        finally {
            try {
                if (in != null) { in.close(); }
                if (connection != null) { connection.disconnect(); }
                if (out != null) { out.close(); }
            } catch (IOException ex) { ex.printStackTrace(); }
        }
        return new ResponseObject(code, messages.toString());
    }

    public static ResponseObject getRequest(String url){
        StringBuilder messages = new StringBuilder();
        HttpURLConnection connection = null;
        BufferedReader in = null;
        int code = 0;
        try {
            URL getUrl = new URL(url);
            connection = (HttpURLConnection) getUrl.openConnection();
            connection.setRequestProperty("Content-Type", "application / json");
            connection.setRequestMethod("GET");
            connection.connect();

            code = connection.getResponseCode();

            in = (connection.getResponseCode() == HttpStatus.OK.value() ?
                    new BufferedReader(new InputStreamReader(connection.getInputStream())) :
                    new BufferedReader(new InputStreamReader(connection.getErrorStream())));
                String line;
            while ((line = in.readLine()) != null) {
                messages.append(line);
            }
        } catch (IOException e) { e.printStackTrace(); }
        finally {
        try {
            if (in != null) { in.close(); }
            if (connection != null) { connection.disconnect(); }
        } catch (IOException ex) { ex.printStackTrace(); }
    }
        return new ResponseObject(code, messages.toString());
    }
}
