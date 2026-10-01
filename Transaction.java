package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private final String accountNumber;
    private final String type;
    private final double amount;
    private final LocalDateTime timestamp;

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Transaction(String accountNumber, String type, double amount, LocalDateTime timestamp) {
        this.accountNumber = accountNumber;
        this.type = type;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public String toFileString() {
        return accountNumber + "," + type + "," + amount + "," + timestamp.format(FORMATTER);
    }

    public static Transaction fromFileString(String line) {
        String[] parts = line.split(",");
        LocalDateTime ts = LocalDateTime.parse(parts[3], FORMATTER);
        return new Transaction(parts[0], parts[1], Double.parseDouble(parts[2]), ts);
    }
}

