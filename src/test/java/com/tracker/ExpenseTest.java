package com.tracker;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class ExpenseTest {

    @Test
    void shouldCreateExpenseWithGivenFields() {
        Expense expense = new Expense(
                100.0,
                "Food",
                LocalDate.of(2024, 1, 1),
                "Lunch"
        );

        assertEquals(-100.0, expense.getAmount());
        assertEquals("Food", expense.getCategory());
        assertEquals(LocalDate.of(2024, 1, 1), expense.getDate());
        assertEquals("Lunch", expense.getDescription());
    }
}