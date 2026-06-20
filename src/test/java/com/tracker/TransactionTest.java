package com.tracker;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

    @Test
    void shouldCreateTransactionWithGivenFields() {
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

    /// Testowanie setterów
    @Test
    void shouldEditAllTransactionFields() {
        Transaction t = new Transaction(100, "Food", LocalDate.of(2024, 1, 1), "Lunch");

        t.setAmount(200);
        t.setCategory("Groceries");
        t.setDate(LocalDate.of(2024, 2, 1));
        t.setDescription("Big shopping");

        assertEquals(200, t.getAmount());
        assertEquals("Groceries", t.getCategory());
        assertEquals(LocalDate.of(2024, 2, 1), t.getDate());
        assertEquals("Big shopping", t.getDescription());
    }

    @Test
    void shouldNotAllowNegativeAmount() {
        Transaction t = new Transaction(100, "Food", LocalDate.now(), "Lunch");

        assertThrows(IllegalArgumentException.class, () -> t.setAmount(-1));
        assertEquals(100, t.getAmount()); // upewniamy się, że wartość nie została zmieniona
    }
    @Test
    void shouldNotAllowNullCategory() {
        Transaction t = new Transaction(100, "Food", LocalDate.now(), "Lunch");

        assertThrows(IllegalArgumentException.class, () -> t.setCategory(null));
        assertEquals("Food", t.getCategory());
    }
    @Test
    void shouldNotAllowNullDate() {
        Transaction t = new Transaction(100, "Food", LocalDate.now(), "Lunch");

        assertThrows(IllegalArgumentException.class, () -> t.setDate(null));
        assertNotNull(t.getDate());
    }
    @Test
    void shouldAllowEmptyOrNullDescription() {
        Transaction t = new Transaction(100, "Food", LocalDate.now(), "Lunch");

        t.setDescription("");
        assertEquals("", t.getDescription());

        t.setDescription(null);
        assertNull(t.getDescription());
    }
}