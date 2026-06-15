package com.tracker;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
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
    public boolean removeTransaction(Transaction transaction)
    {
        return transactions.remove(transaction);
    }
    public List<Transaction> getHistory() {
        return transactions.stream().sorted(Comparator.comparing(Transaction::getDate, Comparator.reverseOrder())).toList();
    }
    public List<Transaction> getTransactions()
    {
        return new ArrayList<>(transactions);
    }
}