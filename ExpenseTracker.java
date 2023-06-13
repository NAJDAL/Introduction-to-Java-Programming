import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;

class ExpenseTracker {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Expense> expenseItems = new ArrayList<>();

        // Step 1: Enter expense items and their costs
        System.out.println("Enter the expense items and their costs. Enter 'done' to finish.");
        while (true) {
            System.out.print("Expense item: ");
            String item = scanner.nextLine();
            if (item.equalsIgnoreCase("done")) {
                break;
            }
            double cost = 0;
            boolean validInput = false;
            while (!validInput) {
                System.out.print("Cost ($): ");
                try {
                    cost = scanner.nextDouble();
                    validInput = true;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a numeric value for the cost.");
                    scanner.nextLine(); // Clear the invalid input from the scanner
                }
            }
            scanner.nextLine(); // Clear the newline character
            Expense expense = new Expense(item, cost);
            expenseItems.add(expense);
        }

        // Step 2: Enter the start and end dates for expense calculation
        System.out.println("\nEnter the start and end dates for the expense calculation.");
        System.out.print("Start date: ");
        String startDate = scanner.nextLine();
        System.out.print("End date: ");
        String endDate = scanner.nextLine();

        // Step 3: Calculate the total expenses for the specified time period
        double totalExpenses = 0;
        for (Expense expense : expenseItems) {
            if (expense.dateIsWithinRange(startDate, endDate)) {
                totalExpenses += expense.getCost();
            }
        }

        // Step 4: Display the total expenses to the user
        String message = String.format("Total expenses for %s - %s: $%.2f", startDate, endDate, totalExpenses);
        displayTotalExpenses(message);

        scanner.close();
    }

    // Method to display total expenses to the user (using GUI)
    private static void displayTotalExpenses(String message) {
        JOptionPane.showMessageDialog(null, message, "Total Expenses", JOptionPane.INFORMATION_MESSAGE);
    }
}

// Expense class to represent an individual expense item
class Expense {
    private String item;
    private double cost;

    // Expense constructor
    public Expense(String item, double cost) {
        this.item = item;
        this.cost = cost;
    }

    // Getter for cost
    public double getCost() {
        return cost;
    }

    // Method to check if the expense date is within the specified range
    public boolean dateIsWithinRange(String startDate, String endDate) {
        // Logic to check if the expense date falls within the specified range
        // Implementation omitted for brevity
        return true; // Placeholder return value
    }
}
