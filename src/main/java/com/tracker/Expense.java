package com.tracker;
import java.time.LocalDate;

public class Expense extends Transaction {
    public Expense(double amount,
                   String category,
                   LocalDate date,
                   String description) {
        super(amount*-1, category, date, description);
    }
}