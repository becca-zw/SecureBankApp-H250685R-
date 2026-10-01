package util;

import model.Account;
import model.Transaction;
import model.User;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    private static final String USERS_FILE = "data/users.txt";
    private static final String ACCOUNTS_FILE = "data/accounts.txt";
    private static final String TRANSACTIONS_FILE = "data/transactions.txt";

    public static List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        for (String line : readLines(USERS_FILE)) {
            if (!line.isBlank()) users.add(User.fromFileString(line));
        }
        return users;
    }

    public static void saveUsers(List<User> users) {
        List<String> lines = new ArrayList<>();
        for (User u : users) lines.add(u.toFileString());
        writeLines(USERS_FILE, lines);
    }

    public static List<Account> loadAccounts() {
        List<Account> accounts = new ArrayList<>();
        for (String line : readLines(ACCOUNTS_FILE)) {
            if (!line.isBlank()) accounts.add(Account.fromFileString(line));
        }
        return accounts;
    }

    public static void saveAccounts(List<Account> accounts) {
        List<String> lines = new ArrayList<>();
        for (Account a : accounts) lines.add(a.toFileString());
        writeLines(ACCOUNTS_FILE, lines);
    }

    public static void appendTransaction(Transaction t) {
        try {
            File file = new File(TRANSACTIONS_FILE);
            file.getParentFile().mkdirs();
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
                bw.write(t.toFileString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving transaction.");
        }
    }

    private static List<String> readLines(String path) {
        List<String> lines = new ArrayList<>();
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
                return lines;
            }
            lines = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            System.out.println("Error reading file: " + path);
        }
        return lines;
    }

    private static void writeLines(String path, List<String> lines) {
        try {
            File file = new File(path);
            file.getParentFile().mkdirs();
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                for (String line : lines) {
                    bw.write(line);
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error writing file: " + path);
        }
    }
}
