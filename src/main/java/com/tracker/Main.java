package com.tracker;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ExpenseTracker tracker = new ExpenseTracker();
        Budget currentBudget = null;
        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("""
                    \n=== MENU BUDŻETÓW ===
                    1. Dodaj budżet
                    2. Usuń budżet
                    3. Wybierz budżet
                    0. Wyjście
                    """);

            int option = readInt(scanner, "Wybierz opcję: ");

            switch (option) {
                case 1 -> addBudget(scanner, tracker);
                case 2 -> removeBudget(scanner, tracker);
                case 3 -> {
                    currentBudget = selectBudget(scanner, tracker);
                    if (currentBudget != null) {
                        budgetMenu(scanner, currentBudget);
                    }
                }
                case 0 -> {
                    System.out.println("Do widzenia!");
                    return;
                }
                default -> System.out.println("Nieprawidłowa opcja.");
            }
        }
    }

    // ============================
    // MENU BUDŻETU
    // ============================

    private static void budgetMenu(Scanner scanner, Budget budget) {

        while (true) {
            System.out.println("\n=== BUDŻET: " + budget.getCategory() +
                    " | saldo: " + budget.getBalance() + " ===");

            System.out.println("""
                    1. Dodaj wydatek
                    2. Usuń wydatek
                    3. Edytuj wydatek
                    4. Historia wydatków
                    5. Historia filtrowana
                    0. Powrót
                    """);

            int option = readInt(scanner, "Wybierz opcję: ");

            switch (option) {
                case 1 -> addExpense(scanner, budget);
                case 2 -> removeExpense(scanner, budget);
                case 3 -> editExpense(scanner, budget);
                case 4 -> showHistory(budget.getTransactionHistory());
                case 5 -> showFilteredHistory(scanner, budget.getTransactionHistory());
                case 0 -> { return; }
                default -> System.out.println("Nieprawidłowa opcja.");
            }
        }
    }

    // ============================
    // OBSŁUGA BUDŻETÓW
    // ============================

    private static void addBudget(Scanner scanner, ExpenseTracker tracker) {
        System.out.print("Nazwa budżetu: ");
        String name = scanner.nextLine();

        System.out.print("Początkowy balans: ");
        double balance = readDouble(scanner);

        tracker.addBudget(new Budget(name, balance));
        System.out.println("Dodano budżet.");
    }

    private static void removeBudget(Scanner scanner, ExpenseTracker tracker) {
        List<Budget> budgets = tracker.getBudgets();

        if (budgets.isEmpty()) {
            System.out.println("Brak budżetów.");
            return;
        }

        for (int i = 0; i < budgets.size(); i++) {
            Budget b = budgets.get(i);
            System.out.printf("%d. %s | saldo: %.2f%n", i + 1, b.getCategory(), b.getBalance());
        }

        int index = readInt(scanner, "Numer budżetu do usunięcia: ") - 1;

        if (index >= 0 && index < budgets.size()) {
            tracker.removeBudget(budgets.get(index));
            System.out.println("Usunięto.");
        } else {
            System.out.println("Nieprawidłowy numer.");
        }
    }

    private static Budget selectBudget(Scanner scanner, ExpenseTracker tracker) {
        List<Budget> budgets = tracker.getBudgets();

        if (budgets.isEmpty()) {
            System.out.println("Brak budżetów.");
            return null;
        }

        for (int i = 0; i < budgets.size(); i++) {
            Budget b = budgets.get(i);
            System.out.printf("%d. %s | saldo: %.2f%n", i + 1, b.getCategory(), b.getBalance());
        }

        int index = readInt(scanner, "Wybierz budżet: ") - 1;

        if (index >= 0 && index < budgets.size()) {
            return budgets.get(index);
        }

        System.out.println("Nieprawidłowy numer.");
        return null;
    }

    // ============================
    // OPERACJE NA TRANSAKCJACH
    // ============================

    private static void addExpense(Scanner scanner, Budget budget) {
        double amount = readDouble(scanner);

        System.out.print("Kategoria: ");
        String category = scanner.nextLine();

        System.out.print("Opis: ");
        String description = scanner.nextLine();

        budget.addTransaction(
                new Expense(
                        amount,
                        category,
                        LocalDate.now(),
                        description
                )
        );
        System.out.println("Wydatek dodany.");
    }

    private static void removeExpense(Scanner scanner, Budget budget) {

        TransactionHistory history = budget.getTransactionHistory();
        showHistory(history);

        int index = readInt(scanner, "Numer wydatku do usunięcia: ") - 1;

        List<Transaction> transactions = history.getHistory();

        if (index >= 0 && index < transactions.size()) {
            Transaction t = transactions.get(index);
            budget.removeTransaction(t);
            System.out.println("Usunięto.");
        } else {
            System.out.println("Nieprawidłowy numer.");
        }
    }

    private static void editExpense(Scanner scanner, Budget budget) {

        TransactionHistory history = budget.getTransactionHistory();
        List<Transaction> transactions = history.getHistory();

        if (transactions.isEmpty()) {
            System.out.println("Brak transakcji do edycji.");
            return;
        }

        showHistory(history);

        int index = readInt(scanner, "Numer transakcji do edycji: ") - 1;

        if (index < 0 || index >= transactions.size()) {
            System.out.println("Nieprawidłowy numer.");
            return;
        }

        Transaction t = transactions.get(index);

        System.out.println("Pozostaw puste, aby nie zmieniać.");

        // amount
        System.out.print("Nowa kwota (" + Math.abs(t.getAmount()) + "): ");
        String amountInput = scanner.nextLine();
        double newAmount = amountInput.isBlank()
                ? Math.abs(t.getAmount())
                : Double.parseDouble(amountInput);

        // category
        System.out.print("Nowa kategoria (" + t.getCategory() + "): ");
        String newCategory = scanner.nextLine();
        if (newCategory.isBlank()) newCategory = t.getCategory();

        // description
        System.out.print("Nowy opis (" + t.getDescription() + "): ");
        String newDescription = scanner.nextLine();
        if (newDescription.isBlank()) newDescription = t.getDescription();

        // date
        System.out.print("Nowa data (" + t.getDate() + "): ");
        String dateInput = scanner.nextLine();
        LocalDate newDate = dateInput.isBlank()
                ? t.getDate()
                : LocalDate.parse(dateInput);

        boolean ok = budget.editTransaction(t, newAmount, newCategory, newDate, newDescription);

        if (ok) System.out.println("Zaktualizowano.");
        else System.out.println("Błąd edycji.");
    }

    private static void showHistory(TransactionHistory history) {

        List<Transaction> transactions = history.getHistory();

        if (transactions.isEmpty()) {
            System.out.println("Brak transakcji.");
            return;
        }

        for (int i = 0; i < transactions.size(); i++) {
            Transaction t = transactions.get(i);

            System.out.printf(
                    "%d. %.2f | %s | %s | %s%n",
                    i + 1,
                    Math.abs(t.getAmount()),
                    t.getCategory(),
                    t.getDate(),
                    t.getDescription()
            );
        }
    }

    private static void showFilteredHistory(Scanner scanner, TransactionHistory history) {

        System.out.print("Kategoria (ENTER = brak): ");
        String category = scanner.nextLine();
        if (category.isBlank()) category = null;

        LocalDate from = readDate(scanner, "Data od (yyyy-MM-dd, ENTER = brak): ");
        LocalDate to = readDate(scanner, "Data do (yyyy-MM-dd, ENTER = brak): ");

        List<Transaction> result = history.getTransactions(category, from, to);

        if (result.isEmpty()) {
            System.out.println("Brak wyników.");
            return;
        }

        result.forEach(t ->
                System.out.printf(
                        "%.2f | %s | %s | %s%n",
                        Math.abs(t.getAmount()),
                        t.getCategory(),
                        t.getDate(),
                        t.getDescription()
                ));
    }

    // ============================
    // POMOCNICZE
    // ============================

    private static int readInt(Scanner scanner, String label) {
        while (true) {
            System.out.print(label);
            String input = scanner.nextLine();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Podaj poprawną liczbę.");
            }
        }
    }

    private static double readDouble(Scanner scanner) {
        while (true) {
            System.out.print("Kwota: ");
            String input = scanner.nextLine();

            if (input.isBlank()) {
                System.out.println("Wartość nie może być pusta.");
                continue;
            }

            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Podaj poprawną liczbę.");
            }
        }
    }

    private static LocalDate readDate(Scanner scanner, String label) {
        System.out.print(label);
        String input = scanner.nextLine();

        if (input.isBlank()) return null;

        try {
            return LocalDate.parse(input);
        } catch (DateTimeParseException e) {
            System.out.println("Błędna data — pomijam filtr.");
            return null;
        }
    }
}
