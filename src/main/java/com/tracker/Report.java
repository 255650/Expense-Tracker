package com.tracker;

public class Report
{
    public double totalIncome(TransactionHistory history)
    {
        return history.getTransactions().stream().filter(t -> t instanceof Income).mapToDouble(Transaction::getAmount).sum();
    }
}
