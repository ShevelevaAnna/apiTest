package utils.object_utils;

public class ResponseObject {
    private int responseCode;
    private String responseBody;
    public ResponseObject(int responseCode, String responseBody){
        this.responseBody = responseBody;
        this.responseCode = responseCode;
    }
    public int getResponseCode(){return  responseCode;}
    public String getResponseBody(){return responseBody;}
}
