package com.tracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class ExpenseTrackerTest {

    private ExpenseTracker tracker;
    private Budget foodBudget;
    private Budget funBudget;

    @BeforeEach
    void setUp() {
        tracker = new ExpenseTracker();
        foodBudget = new Budget("Jedzenie", 1000.0);
        funBudget = new Budget("Rozrywka", 500.0);
        tracker.addBudget(foodBudget);
        tracker.addBudget(funBudget);
    }

    @Test
    void shouldInitializeWithEmptyHistoryAndAddedBudgets() {
        assertTrue(tracker.getGlobalHistory().getTransactions().isEmpty());
        assertEquals(2, tracker.getBudgets().size());
    }

    @Test
    void shouldProcessTransactionAndRouteToCorrectBudget() {
        Transaction grocery = new Transaction(150.0, "Jedzenie", LocalDate.now(), "Zakupy w markecie");

        tracker.processTransaction(grocery);
        assertEquals(1, tracker.getGlobalHistory().getTransactions().size());
        assertEquals(1150.0, foodBudget.getBalance());
        assertEquals(500.0, funBudget.getBalance());
    }

    @Test
    void shouldHandleNullTransactionSafely() {
        assertDoesNotThrow(() -> tracker.processTransaction(null));
        assertTrue(tracker.getGlobalHistory().getTransactions().isEmpty());
    }
}