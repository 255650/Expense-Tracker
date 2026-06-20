package com.tracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class BudgetTest {

    private Budget budget;

    @BeforeEach
    void setUp() {
        budget = new Budget("Jedzenie", 1000.0);
    }

    @Test
    void shouldInitializeWithCorrectValues() {
        assertEquals("Jedzenie", budget.getCategory(), "Kategoria powinna być ustawiona na 'Jedzenie'");
        assertEquals(1000.0, budget.getBalance(), "Początkowy balans powinien wynosić 1000.0");
        assertEquals(1000.0, budget.getResources(), "Metoda getResources() powinna zwracać aktualny balans");
    }

    @Test
    void shouldIncreaseBalanceWhenPositiveTransactionIsAdded() {
        Transaction income = new Transaction(250.0, "Jedzenie", LocalDate.now(), "Zwrot za zakupy");
        budget.addTransaction(income);
        assertEquals(1250.0, budget.getBalance(), "Balans powinien wzrosnąć po dodaniu dodatniej transakcji");
    }

    @Test
    void shouldDecreaseBalanceWhenNegativeTransactionIsAdded() {
        Transaction transaction = new Transaction(150.0, "Jedzenie", LocalDate.now(), "Zakupy");
        budget.addTransaction(transaction);
        assertEquals(1150.0, budget.getBalance(), "Balans powinien zmienić się zgodnie z logiką dodawania kwoty");
    }

    @Test
    void shouldAccumulateMultipleTransactions() {
        Transaction first = new Transaction(100.0, "Jedzenie", LocalDate.now(), "Zakupy 1");
        Transaction second = new Transaction(50.0, "Jedzenie", LocalDate.now(), "Zakupy 2");

        budget.addTransaction(first);
        budget.addTransaction(second);

        assertEquals(1150.0, budget.getBalance(), "Balans powinien poprawnie sumować wiele transakcji z rzędu");
    }

    @Test
    void shouldNotAlterBalanceWhenTransactionIsNull() {
        assertDoesNotThrow(() -> budget.addTransaction(null));
        assertEquals(1000.0, budget.getBalance(), "Balans nie powinien się zmienić, jeśli transakcja to null");
    }
}