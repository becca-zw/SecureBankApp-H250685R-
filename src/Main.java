import model.Account;
import model.User;
import service.AccountService;
import service.AuthService;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final AuthService authService = new AuthService();
    private static final AccountService accountService = new AccountService();

    public static void main(String[] args) {
        System.out.println("=== Welcome to Secure Banking App ===");
        User loggedInUser = null;

        while (loggedInUser == null) {
            System.out.println("\n1. Register\n2. Login\n3. Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> handleRegister();
                case "2" -> loggedInUser = handleLogin();
                case "3" -> { System.out.println("Goodbye."); return; }
                default -> System.out.println("Invalid option.");
            }
        }

        Account account = accountService.findAccountByOwner(loggedInUser.getUsername());
        if (account == null) {
            account = accountService.createAccount(loggedInUser.getUsername());
            System.out.println("New account created: " + account.getAccountNumber());
        }

        mainMenu(account);
    }

    private static void handleRegister() {
        System.out.print("Choose a username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Choose a password: ");
        String password = scanner.nextLine().trim();
        if (authService.register(username, password)) {
            System.out.println("Registration successful. You can now log in.");
        }
    }

    private static User handleLogin() {
        System.out.print("Username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Password: ");
        String password = scanner.nextLine().trim();
        User user = authService.login(username, password);
        if (user != null) System.out.println("Login successful. Welcome, " + user.getUsername() + "!");
        return user;
    }

    private static void mainMenu(Account account) {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. View Balance\n2. Deposit\n3. Withdraw\n4. Logout");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> System.out.println("Balance: $" + account.getBalance());
                case "2" -> {
                    double amount = readAmount("Enter deposit amount: ");
                    if (amount >= 0) accountService.deposit(account, amount);
                }
                case "3" -> {
                    double amount = readAmount("Enter withdrawal amount: ");
                    if (amount >= 0) accountService.withdraw(account, amount);
                }
                case "4" -> { System.out.println("Logged out. Goodbye."); running = false; }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static double readAmount(String prompt) {
        System.out.print(prompt);
        try {
            return Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount entered.");
            return -1;
        }
    }
}
