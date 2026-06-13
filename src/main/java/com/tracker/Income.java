package com.tracker;
import java.time.LocalDate;

public class Income extends Transaction {
    public Income(double amount,
                  String category,
                  LocalDate date,
                  String description) {
        super(amount, category, date, description);
    }
}