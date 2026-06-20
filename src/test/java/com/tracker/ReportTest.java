package com.tracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class ReportTest
{
    private TransactionHistory history;
    private Report report;
    private Budget budget;
    private final LocalDate d1 = LocalDate.of(2026, 6, 1);
    private final LocalDate d2 = LocalDate.of(2026, 6, 10);
    private final LocalDate d3 = LocalDate.of(2026, 6, 20);
    @BeforeEach
    void setUp()
    {
        history = new TransactionHistory();
        report = new Report();
        budget = new Budget("Food", 500);
        history.addTransaction(new Income(2000, "Salary", d1, "Pay"));
        history.addTransaction(new Expense(100, "Food", d1, "A"));
        history.addTransaction(new Expense(200, "Food", d2, "B"));
        history.addTransaction(new Expense(300, "Transport", d3, "C"));
    }
    @Test
    void shouldCalculateTotalIncome()
    {
        assertEquals(2000, report.totalIncome(history));
    }
    @Test
    void shouldCalculateTotalExpenses()
    {
        assertEquals(600, report.totalExpenses(history));
    }
    @Test
    void shouldCalculateBalance()
    {
        assertEquals(1400, report.balance(history));
    }
    @Test
    void shouldCalculateExpensesByCategory()
    {
        assertEquals(300, report.expensesByCategory(history, "Food"));
    }
}