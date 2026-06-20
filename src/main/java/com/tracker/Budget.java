package com.tracker;

public class Budget {
    private final String category;
    private final double balance;
    private final TransactionHistory transactionHistory;

    public Budget(String category, double limit) {
        this.category = category;
        this.balance = limit;
        this.transactionHistory = new TransactionHistory();
    }

    public double getBalance() {
        return this.balance;
    }

    public void addTransaction(Transaction transaction) {
        if (transaction != null) {
            this.transactionHistory.addTransaction(transaction);
        }
    }

    public String getCategory() {
        return this.category;
    }

    public double getResources() {
        return this.balance;
    }
}