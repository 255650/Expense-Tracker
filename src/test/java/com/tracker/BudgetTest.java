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

    /// Testy edycji transakcji
    @Test
    void shouldEditTransactionInsideBudget() {
        Budget food = new Budget("Food", 0);
        Transaction t = new Expense(100, "Food", LocalDate.of(2024, 1, 1), "Lunch");

        food.addTransaction(t);

        boolean result = food.editTransaction(
                t,
                150,
                "Food",
                LocalDate.of(2024, 2, 1),
                "Dinner"
        );

        assertTrue(result);
        assertEquals(-150, t.getAmount());
        assertEquals("Food", t.getCategory());
        assertEquals(LocalDate.of(2024, 2, 1), t.getDate());
        assertEquals("Dinner", t.getDescription());
    }
    @Test
    void shouldUpdateBudgetBalanceAfterEditingTransaction() {
        Budget food = new Budget("Food", 0);
        Transaction t = new Expense(100, "Food", LocalDate.now(), "Lunch");

        food.addTransaction(t);

        food.editTransaction(
                t,
                200,
                "Food",
                LocalDate.now(),
                "Dinner"
        );

        assertEquals(-200, food.getBalance());
    }
    @Test
    void shouldReturnFalseWhenEditingTransactionNotInBudget() {
        Budget food = new Budget("Food", 0);
        Transaction t = new Expense(50, "Food", LocalDate.now(), "Snack");

        boolean result = food.editTransaction(
                t,
                100,
                "Food",
                LocalDate.now(),
                "Dinner"
        );

        assertFalse(result);
    }
    @Test
    void shouldUpdateBalanceCorrectlyForIncomeEdit() {
        Budget salary = new Budget("Salary", 0);
        Transaction t = new Income(1000, "Salary", LocalDate.now(), "January");

        salary.addTransaction(t); // balance = 1000

        salary.editTransaction(
                t,
                1500,
                "Salary",
                LocalDate.now(),
                "Updated"
        );

        assertEquals(1500, salary.getBalance());
    }
    @Test
    void shouldUpdateBalanceCorrectlyForExpenseEdit() {
        Budget food = new Budget("Food", 0);
        Transaction t = new Expense(80, "Food", LocalDate.now(), "Groceries");

        food.addTransaction(t); // balance = -80

        food.editTransaction(
                t,
                120,
                "Food",
                LocalDate.now(),
                "More groceries"
        );

        assertEquals(-120, food.getBalance());
    }
    @Test
    void shouldEditDescriptionWithoutChangingBalance() {
        Budget food = new Budget("Food", 0);
        Transaction t = new Expense(50, "Food", LocalDate.now(), "Old desc");

        food.addTransaction(t); // balance = -50

        food.editTransaction(
                t,
                50,
                "Food",
                LocalDate.now(),
                "New desc"
        );

        assertEquals(-50, food.getBalance());
        assertEquals("New desc", t.getDescription());
    }

}