package utils;

public enum PathUtils {
    PATH_POSTS("/posts"),
    PATH_USERS("/users");

    private final String path;

    PathUtils(String path) {
        this.path = path;
    }

    public String getPath() { return path; }

    public String getPathItem(String item) { return path+"/"+item; }
}
