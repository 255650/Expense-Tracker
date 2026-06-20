package com.tracker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class TransactionHistoryTest
{
    private TransactionHistory history;
    private final LocalDate d1 = LocalDate.of(2026, 6, 1);
    private final LocalDate d2 = LocalDate.of(2026, 6, 10);
    private final LocalDate d3 = LocalDate.of(2026, 6, 20);
    @BeforeEach
    void setUp()
    {
        history = new TransactionHistory();
        history.addTransaction(new Expense(100, "Food", d1, "A"));
        history.addTransaction(new Expense(200, "Food", d2, "B"));
        history.addTransaction(new Expense(300, "Transport", d3, "C"));
        history.addTransaction(new Income(1000, "Salary", d2, "D"));
    }
    @Test
    void shouldAddTransaction()
    {
        Transaction transaction = new Expense(50, "Food", d1, "Test");
        history.addTransaction(transaction);
        assertEquals(5, history.getTransactions().size());
    }
    @Test
    void shouldRemoveTransaction()
    {
        Transaction t = history.getTransactions().getFirst();
        boolean removed = history.removeTransaction(t);
        assertTrue(removed);
        assertEquals(3, history.getTransactions().size());
    }
    @Test
    void shouldReturnFalseWhenRemovingNonExistingTransaction()
    {
        Transaction fake = new Expense(999, "X", d1, "Fake");
        assertFalse(history.removeTransaction(fake));
    }
    @Test
    void shouldThrowExceptionWhenAddingNull()
    {
        assertThrows(IllegalArgumentException.class, () -> history.addTransaction(null));
    }
    @Test
    void shouldReturnCopyOfList()
    {
        List<Transaction> copy = history.getTransactions();
        copy.clear();
        assertEquals(4, history.getTransactions().size());
    }
    @Test
    void shouldReturnAllTransactions()
    {
        List<Transaction> result = history.getTransactions(null, null, null);
        assertEquals(4, result.size());
    }
    @Test
    void shouldFilterByCategoryOnly()
    {
        List<Transaction> result = history.getTransactions("Food", null, null);
        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(t -> t.getCategory().equalsIgnoreCase("Food")));
    }
    @Test
    void shouldFilterByDateRangeOnly()
    {
        List<Transaction> result = history.getTransactions(null, LocalDate.of(2026, 6, 5), LocalDate.of(2026, 6, 30));
        assertEquals(3, result.size());
    }
    @Test
    void shouldFilterByCategoryAndDateRange()
    {
        List<Transaction> result = history.getTransactions("Food", LocalDate.of(2026, 6, 5), LocalDate.of(2026, 6, 30));
        assertEquals(1, result.size());
    }
    @Test
    void shouldFilterByStartDateOnly()
    {
        List<Transaction> result = history.getTransactions(null, LocalDate.of(2026, 6, 10), null);
        assertTrue(result.stream().noneMatch(t -> t.getDate().isBefore(LocalDate.of(2026, 6, 10))));
    }
    @Test
    void shouldFilterByEndDateOnly()
    {
        List<Transaction> result = history.getTransactions(null, null, LocalDate.of(2026, 6, 10));
        assertTrue(result.stream().noneMatch(t -> t.getDate().isAfter(LocalDate.of(2026, 6, 10))));
    }
    @Test
    void shouldFilterByCategoryAndStartDate()
    {
        List<Transaction> result = history.getTransactions("Food", LocalDate.of(2026, 6, 10), null);
        assertTrue(result.stream().allMatch(t -> t.getCategory().equalsIgnoreCase("Food") && !t.getDate().isBefore(LocalDate.of(2026, 6, 10))));
    }
    @Test
    void shouldFilterByCategoryAndEndDate()
    {
        List<Transaction> result = history.getTransactions("Food", null, LocalDate.of(2026, 6, 10));
        assertTrue(result.stream().allMatch(t -> t.getCategory().equalsIgnoreCase("Food") && !t.getDate().isAfter(LocalDate.of(2026, 6, 10))));
    }
    @Test
    void shouldReturnEmptyListWhenNoMatches()
    {
        List<Transaction> result = history.getTransactions("Electronics", null, null);
        assertTrue(result.isEmpty());
    }
}