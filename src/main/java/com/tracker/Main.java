package com.tracker;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        TransactionHistory history = new TransactionHistory();
        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("""
                    \n1. Dodaj wydatek
                    2. Usuń wydatek
                    3. Edytuj wydatek
                    4. Historia wydatków
                    5. Historia filtrowana
                    0. Wyjście
                    """);

            int option = readInt(scanner, "Wybierz opcję: ");

            switch (option) {
                case 1 -> addExpense(scanner, history);
                case 2 -> removeExpense(scanner, history);
                case 3 -> editExpense(scanner, history);
                case 4 -> showHistory(history);
                case 5 -> showFilteredHistory(scanner, history);
                case 0 -> {
                    System.out.println("Do widzenia!");
                    return;
                }
                default -> System.out.println("Nieprawidłowa opcja.");
            }
        }
    }


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


    private static void addExpense(Scanner scanner, TransactionHistory history) {

        double amount = readDouble(scanner);

        System.out.print("Kategoria: ");
        String category = scanner.nextLine();

        System.out.print("Opis: ");
        String description = scanner.nextLine();

        history.addTransaction(
                new Expense(
                        amount,
                        category,
                        LocalDate.now(),
                        description
                )
        );

        System.out.println("Wydatek dodany.");
    }

    private static void removeExpense(Scanner scanner, TransactionHistory history) {

        showHistory(history);

        int index = readInt(scanner, "Numer wydatku do usunięcia: ") - 1;

        List<Transaction> transactions = history.getHistory();

        if (index >= 0 && index < transactions.size()) {
            history.removeTransaction(transactions.get(index));
            System.out.println("Usunięto.");
        } else {
            System.out.println("Nieprawidłowy numer.");
        }
    }

    private static void editExpense(Scanner scanner, TransactionHistory history) {
        System.out.println("Funkcja edycji jeszcze nie została zaimplementowana.");
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
}