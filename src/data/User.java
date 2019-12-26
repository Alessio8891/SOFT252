package data;

public class User {
    public String authority;
    public String id;

    public static User user;

    public User(String authority, String id) {
        this.authority = authority;
        this.id = id;
    }
}