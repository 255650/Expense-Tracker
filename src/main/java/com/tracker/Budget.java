package com.tracker;

public class Budget {
    private final String category;
    private double balance;
    private final TransactionHistory transactionHistory;

    public Budget(String category, double balance) {
        this.category = category;
        this.balance = balance;
        this.transactionHistory = new TransactionHistory();
    }

    public double getBalance() {
        return this.balance;
    }

    public void addTransaction(Transaction transaction) {
        if (transaction != null) {
            this.balance += transaction.getAmount();
            this.transactionHistory.addTransaction(transaction);
        }
    }public void removeTransaction(Transaction transaction) {
        if (transaction == null) return;
        this.balance -= transaction.getAmount();
        this.transactionHistory.removeTransaction(transaction);
    }

    public String getCategory() {
        return this.category;
    }

    public double getResources() {
        return this.balance;
    }

    public TransactionHistory getTransactionHistory(){return transactionHistory;}
}