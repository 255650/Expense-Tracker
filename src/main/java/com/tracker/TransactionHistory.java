package com.tracker;
import java.util.ArrayList;
import java.util.List;

public class TransactionHistory
{
    private final List<Transaction> transactions = new ArrayList<>();
    public void addTransaction(Transaction transaction)
    {
        if (transaction == null)
        {
            throw new IllegalArgumentException("Transaction cannot be null");
        }
        transactions.add(transaction);
    }
    public List<Transaction> getTransactions()
    {
        return new ArrayList<>(transactions);
    }
}