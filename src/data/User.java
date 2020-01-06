package data;

public class User {
    private char authority;
    private int id;

    public static User user;

    public User(char authority, int id) {
        this.authority = authority;
        this.id = id;
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        User.user = user;
    }

    public char getAuthority() {
        return authority;
    }

    public int getId() {
        return id;
    }
}