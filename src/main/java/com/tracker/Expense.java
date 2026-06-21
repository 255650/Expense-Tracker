package com.tracker;
import java.time.LocalDate;

public class Expense extends Transaction {
    public Expense(double amount,
                   String category,
                   LocalDate date,
                   String description) {
        super(-amount, category, date, description);
    }

    @Override
    public void setAmount(double amount) {
        if (amount < 0) throw new IllegalArgumentException("Amount cannot be negative");
        super.setAmount(-amount);
    }
}