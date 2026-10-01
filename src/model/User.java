package model;

public class User {
    private final String username;
    private String hashedPassword;

    public User(String username, String hashedPassword) {
        this.username = username;
        this.hashedPassword = hashedPassword;
    }

    public String getUsername() {
        return username;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public String toFileString() {
        return username + "," + hashedPassword;
    }

    public static User fromFileString(String line) {
        String[] parts = line.split(",", 2);
        return new User(parts[0], parts[1]);
    }
}
