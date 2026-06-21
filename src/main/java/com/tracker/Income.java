package com.tracker;
import java.time.LocalDate;

public class Income extends Transaction {
    public Income(double amount,
                  String category,
                  LocalDate date,
                  String description) {
        super(amount, category, date, description);
    }
    @Override
    public void setAmount(double amount) {
        if (amount < 0) throw new IllegalArgumentException("Income cannot be negative");
        super.setAmount(amount);
    }

}