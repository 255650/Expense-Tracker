package com.tracker;

import java.time.LocalDate;

public class Transaction {

    private double amount;
    private String category;
    private LocalDate date;
    private String description;

    public Transaction(double amount, String category, LocalDate date, String description) {
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

    public void setAmount(double amount) {
        if (amount < 0) throw new IllegalArgumentException("Amount cannot be negative");
        this.amount = amount;
    }
    public void setCategory(String category) {
        if (category == null) throw new IllegalArgumentException("Category cannot be null");
        this.category = category;
    }
    public void setDate(LocalDate date) {
        if (date == null) throw new IllegalArgumentException("Date cannot be null");
        this.date = date;
    }
    public void setDescription(String description) {this.description = description;}
}
