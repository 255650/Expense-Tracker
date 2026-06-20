package com.tracker;

import java.util.ArrayList;
import java.util.List;

public class ExpenseTracker {
    private final TransactionHistory globalHistory;
    private final List<Budget> budgets;

    public ExpenseTracker() {
        this.globalHistory = new TransactionHistory();
        this.budgets = new ArrayList<>();
    }

    public void addBudget(Budget budget) {
        if (budget != null) {
            this.budgets.add(budget);
        }
    }

    public void processTransaction(Transaction transaction) {
        if (transaction == null) {
            return;
        }

        this.globalHistory.addTransaction(transaction);

        for (Budget budget : this.budgets) {
            if (budget.getCategory().equalsIgnoreCase(transaction.getCategory())) {
                budget.addTransaction(transaction);
            }
        }
    }

    public TransactionHistory getGlobalHistory() {
        return this.globalHistory;
    }

    public List<Budget> getBudgets() {
        return new ArrayList<>(this.budgets);
    }
}