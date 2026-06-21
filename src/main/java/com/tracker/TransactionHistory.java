package com.tracker;
import java.time.LocalDate;
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
    public List<Transaction> getTransactions(String category, LocalDate startDate, LocalDate endDate)
    {
        return transactions.stream().filter(t -> category == null ||
                        t.getCategory().equalsIgnoreCase(category)).filter(t -> startDate == null ||
                        !t.getDate().isBefore(startDate)).filter(t -> endDate == null ||
                        !t.getDate().isAfter(endDate))
                .toList();
    }
}