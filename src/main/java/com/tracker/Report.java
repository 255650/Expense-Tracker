package com.tracker;

public class Report
{
    public double totalIncome(TransactionHistory history)
    {
        return history.getTransactions().stream().filter(t -> t instanceof Income).mapToDouble(Transaction::getAmount).sum();
    }
    public double totalExpenses(TransactionHistory history)
    {
        return history.getTransactions().stream().filter(t -> t instanceof Expense).mapToDouble(Transaction::getAmount).sum();
    }
    public double balance(TransactionHistory history)
    {
        return totalIncome(history) - totalExpenses(history);
    }
    public double expensesByCategory(TransactionHistory history, String category)
    {
        return history.getTransactions().stream().filter(t -> t instanceof Expense).filter(t -> t.getCategory().equalsIgnoreCase(category)).mapToDouble(Transaction::getAmount).sum();
    }
    public boolean isBudgetExceeded(TransactionHistory history, Budget budget)
    {
        return totalExpenses(history) > budget.getLimit();
    }
}
