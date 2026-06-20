package com.tracker;

import java.time.LocalDate;

public class Report
{
    public double totalIncome(TransactionHistory history)
    {
        return history.getTransactions().stream().filter(t -> t instanceof Income).mapToDouble(Transaction::getAmount).sum();
    }
    public double totalExpenses(TransactionHistory history)
    {
        return history.getTransactions().stream().filter(t -> t instanceof Expense).mapToDouble(t -> Math.abs(t.getAmount())).sum();
    }
    public double balance(TransactionHistory history)
    {
        return totalIncome(history) - totalExpenses(history);
    }
    public double expensesByCategory(TransactionHistory history, String category)
    {
        return history.getTransactions().stream().filter(t -> t instanceof Expense).filter(t -> t.getCategory().equalsIgnoreCase(category)).mapToDouble(t -> Math.abs(t.getAmount())).sum();
    }
    public double maxExpense(TransactionHistory history)
    {
        return history.getTransactions().stream().filter(t -> t instanceof Expense).mapToDouble(t -> Math.abs(t.getAmount())).max().orElse(0.0);
    }
    public double minExpense(TransactionHistory history)
    {
        return history.getTransactions().stream().filter(t -> t instanceof Expense).mapToDouble(t -> Math.abs(t.getAmount())).min().orElse(0.0);
    }
    public double avgExpense(TransactionHistory history)
    {
        return history.getTransactions().stream().filter(t -> t instanceof Expense).mapToDouble(t -> Math.abs(t.getAmount())).average().orElse(0.0);
    }
    public double expensesInPeriod(TransactionHistory history, LocalDate start, LocalDate end)
    {
        return history.getTransactions().stream()
                .filter(t -> t instanceof Expense)
                .filter(t -> !t.getDate().isBefore(start))
                .filter(t -> !t.getDate().isAfter(end))
                .mapToDouble(t -> Math.abs(t.getAmount()))
                .sum();
    }
}
