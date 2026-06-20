package com.tracker;

import java.time.LocalDate;

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
    public boolean editTransaction(Transaction transaction, double newAmount, String newCategory, LocalDate newDate, String newDescription) {
        if (!transactionHistory.getTransactions().contains(transaction)) return false;
        double oldAmount = transaction.getAmount();

        transaction.setAmount(newAmount);
        transaction.setCategory(newCategory);
        transaction.setDate(newDate);
        transaction.setDescription(newDescription);

        double updatedAmount = transaction.getAmount();
        this.balance -= oldAmount;
        this.balance += updatedAmount;
        return true;
    }

    public String getCategory() {
        return this.category;
    }

    public double getResources() {
        return this.balance;
    }

    public TransactionHistory getTransactionHistory(){return transactionHistory;}
}