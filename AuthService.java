package service;

import model.User;
import util.FileManager;
import util.PasswordUtil;

import java.util.List;

public class AuthService {

    private final List<User> users;

    public AuthService() {
        this.users = FileManager.loadUsers();
    }

    public boolean register(String username, String password) {
        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            System.out.println("Username and password cannot be empty.");
            return false;
        }
        if (findUser(username) != null) {
            System.out.println("Username already exists.");
            return false;
        }
        String hashed = PasswordUtil.hashPassword(password);
        users.add(new User(username, hashed));
        FileManager.saveUsers(users);
        return true;
    }

    public User login(String username, String password) {
        User user = findUser(username);
        if (user == null || !PasswordUtil.verifyPassword(password, user.getHashedPassword())) {
            System.out.println("Invalid username or password.");
            return null;
        }
        return user;
    }

    private User findUser(String username) {
        for (User u : users) {
            if (u.getUsername().equals(username)) return u;
        }
        return null;
    }
}
