package com.tracker;

import java.time.LocalDate;

public class Transaction {

    private final double amount;
    private final String category;
    private final LocalDate date;
    private final String description;

    public Transaction(double amount, String category, LocalDate date, String description) {
        if (amount < 0) throw new IllegalArgumentException("Amount cannot be negative");
        if (category == null) throw new IllegalArgumentException("Category cannot be null");
        if (date == null) throw new IllegalArgumentException("Date cannot be null");

        this.amount = amount;
        this.category = category;
        this.date = date;
        this.description = description;
    }

    public double getAmount() { return amount; }
    public String getCategory() { return category; }
    public LocalDate getDate() { return date; }
    public String getDescription() { return description; }
}
