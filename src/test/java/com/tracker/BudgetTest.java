package com.tracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BudgetTest {

    private Budget budget;

    @BeforeEach
    void setUp() {
        budget = new Budget("Jedzenie", 1000.0);
    }

    @Test
    void shouldInitializeWithCorrectValues() {
        assertEquals("Jedzenie", budget.getCategory());
        assertEquals(1000.0, budget.getLimit());
    }
}