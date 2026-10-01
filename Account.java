
package model;

public class Account {
    private final String accountNumber;
    private final String owner;
    private double balance;

    public Account(String accountNumber, String owner, double balance) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOwner() {
        return owner;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient funds.");
        }
        balance -= amount;
    }

    public String toFileString() {
        return accountNumber + "," + owner + "," + balance;
    }

    public static Account fromFileString(String line) {
        String[] parts = line.split(",");
        return new Account(parts[0], parts[1], Double.parseDouble(parts[2]));
    }
}