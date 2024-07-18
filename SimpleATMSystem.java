import java.util.Scanner;

public class SimpleATMSystem {
    private static double balance = 4000.0;
    private static String[] transactionHistory = new String[5];
    private static int transactionCount = 0;
    private static final String userId = "12345";
    private static final String pin = "6789";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Simple ATM System");
        System.out.print("Enter User ID: ");
        String inputUserId = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String inputPin = scanner.nextLine();

        if (authenticate(inputUserId, inputPin)) {
            int option;
            do {
                System.out.println("\nATM Menu:");
                System.out.println("1. Transaction History");
                System.out.println("2. Withdraw");
                System.out.println("3. Deposit");
                System.out.println("4. Quit");
                System.out.print("Choose an option: ");
                option = scanner.nextInt();

                switch (option) {
                    case 1:
                        showTransactionHistory();
                        break;
                    case 2:
                        withdraw(scanner);
                        break;
                    case 3:
                        deposit(scanner);
                        break;
                    case 4:
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } while (option != 4);
        } else {
            System.out.println("Invalid User ID or PIN.");
        }
        scanner.close();
    }

    private static boolean authenticate(String inputUserId, String inputPin) {
        return userId.equals(inputUserId) && pin.equals(inputPin);
    }

    private static void showTransactionHistory() {
        System.out.println("\nTransaction History:");
        for (int i = 0; i < transactionHistory.length; i++) {
            if (transactionHistory[i] != null) {
                System.out.println(transactionHistory[i]);
            }
        }
    }

    private static void addTransaction(String transaction) {
        if (transactionCount < transactionHistory.length) {
            transactionHistory[transactionCount] = transaction;
        } else {
            for (int i = 1; i < transactionHistory.length; i++) {
                transactionHistory[i - 1] = transactionHistory[i];
            }
            transactionHistory[transactionHistory.length - 1] = transaction;
        }
        transactionCount++;
    }

    private static void withdraw(Scanner scanner) {
        System.out.print("\nEnter amount to withdraw: ");
        double amount = scanner.nextDouble();
        if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            balance -= amount;
            addTransaction("Withdrew: " + amount);
            System.out.println("Withdraw successful. New balance: " + balance);
        }
    }

    private static void deposit(Scanner scanner) {
        System.out.print("\nEnter amount to deposit: ");
        double amount = scanner.nextDouble();
        if (amount > 0) {
            balance += amount;
            addTransaction("Deposited: " + amount);
            System.out.println("Deposit successful. New balance: " + balance);
        } else {
            System.out.println("No money was deposited. Please enter a valid amount greater than zero.");
        }
    }
}
