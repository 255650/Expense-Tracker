package com.tracker;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class IncomeTest {

    @Test
    void shouldCreateIncomeWithGivenFields() {
        Income income = new Income(
                5000,
                "Salary",
                LocalDate.of(2024, 1, 1),
                "January salary"
        );
        assertEquals(5000, income.getAmount());
        assertEquals("Salary", income.getCategory());
        assertEquals(LocalDate.of(2024, 1, 1), income.getDate());
        assertEquals("January salary", income.getDescription());
    }
}