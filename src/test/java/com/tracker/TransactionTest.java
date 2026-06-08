package com.tracker;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

    @Test
    void shouldCreateExpenseWithGivenFields() {
        Transaction e = new Transaction(
                100.0,
                "Food",
                LocalDate.of(2024, 1, 1),
                "Lunch"
        );

        assertEquals(100.0, e.getAmount());
        assertEquals("Food", e.getCategory());
        assertEquals(LocalDate.of(2024, 1, 1), e.getDate());
        assertEquals("Lunch", e.getDescription());
    }

    /// Testowanie poprawności argumnetów
    @Test
    void shouldThrowExceptionWhenAmountIsNegative() {
        assertThrows(IllegalArgumentException.class, () ->
                new Transaction(-10, "Food", LocalDate.now(), "Invalid")
        );
    }

    @Test
    void shouldThrowExceptionWhenCategoryIsNull() {
        assertThrows(IllegalArgumentException.class, () ->
                new Transaction(20, null, LocalDate.now(), "No category")
        );
    }

    @Test
    void shouldThrowExceptionWhenDateIsNull() {
        assertThrows(IllegalArgumentException.class, () ->
                new Transaction(20, "Food", null, "No date")
        );
    }
}