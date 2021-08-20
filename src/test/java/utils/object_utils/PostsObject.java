package utils.object_utils;

public class PostsObject {
    private int userId;
    private int id;
    private String body;
    private String title;

    public PostsObject(int userId, int id, String title, String body){
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }
    public int getUserId(){return userId;}
    public String getTitle(){return title;}
    public String getBody(){return body;}

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        PostsObject post = (PostsObject) obj;
        return userId == post.userId && id == post.id && body.equals(post.body) && title.equals(post.title);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + userId;
        result = prime * result + id;
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((body == null) ? 0 : body.hashCode());
        return result;
    }
}
