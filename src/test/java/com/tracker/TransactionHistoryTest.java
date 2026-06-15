package com.tracker;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class TransactionHistoryTest
{
    @Test
    void shouldAddTransaction()
    {
        TransactionHistory history = new TransactionHistory();
        Transaction transaction = new Transaction(
                100.0,
                "Food",
                LocalDate.now(),
                "Lunch"
        );
        history.addTransaction(transaction);
        assertEquals(1, history.getTransactions().size());
        assertTrue(history.getTransactions().contains(transaction));
    }
    @Test
    void shouldThrowExceptionWhenAddingNullTransaction()
    {
        TransactionHistory history = new TransactionHistory();
        assertThrows(IllegalArgumentException.class, () -> history.addTransaction(null));
    }
}