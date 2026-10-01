package service;

import model.Account;
import model.Transaction;
import util.FileManager;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class AccountService {

    private final List<Account> accounts;

    public AccountService() {
        this.accounts = FileManager.loadAccounts();
    }

    public Account createAccount(String owner) {
        String accountNumber = "ACC-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        Account account = new Account(accountNumber, owner, 0.0);
        accounts.add(account);
        FileManager.saveAccounts(accounts);
        return account;
    }

    public Account findAccountByOwner(String owner) {
        for (Account a : accounts) {
            if (a.getOwner().equals(owner)) return a;
        }
        return null;
    }

    public boolean deposit(Account account, double amount) {
        try {
            account.deposit(amount);
            FileManager.saveAccounts(accounts);
            FileManager.appendTransaction(new Transaction(account.getAccountNumber(), "DEPOSIT", amount, LocalDateTime.now()));
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Deposit failed: " + e.getMessage());
            return false;
        }
    }

    public boolean withdraw(Account account, double amount) {
        try {
            account.withdraw(amount);
            FileManager.saveAccounts(accounts);
            FileManager.appendTransaction(new Transaction(account.getAccountNumber(), "WITHDRAW", amount, LocalDateTime.now()));
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Withdrawal failed: " + e.getMessage());
            return false;
        }
    }
}
