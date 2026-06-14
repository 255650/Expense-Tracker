package com.tracker;

public class Budget {
    private final String category;
    private final double limit;
    private double currentExpenses = 0.0;

    public Budget(String category, double limit) {
        this.category = category;
        this.limit = limit;
    }

    public void addTransaction(Transaction transaction) {
        if (transaction.getCategory().equalsIgnoreCase(this.category)) {
            this.currentExpenses += transaction.getAmount();
        }
    }

    public String getCategory() {
        return this.category;
    }

    public double getLimit() {
        return this.limit;
    }
}