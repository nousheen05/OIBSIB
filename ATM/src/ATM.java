import java.util.HashMap;
import java.util.Scanner;

public class ATM {
    private HashMap<String, User> users;
    private User currentUser;
    private Scanner scanner;

    public ATM() {
        users = new HashMap<>();
        scanner = new Scanner(System.in);
        // Add some users for demonstration
        users.put("user1", new User("user1", "1234", 5000.0));
        users.put("user2", new User("user2", "5678", 3000.0));
    }

    public void start() {
        System.out.println("Welcome to the ATM!");
        authenticateUser();
        showMenu();
    }

    private void authenticateUser() {
        while (currentUser == null) {
            System.out.print("Enter user ID: ");
            String userId = scanner.nextLine();
            System.out.print("Enter user PIN: ");
            String userPin = scanner.nextLine();

            User user = users.get(userId);
            if (user != null && user.getUserPin().equals(userPin)) {
                currentUser = user;
                System.out.println("Authentication successful!");
            } else {
                System.out.println("Invalid user ID or PIN. Please try again.");
            }
        }
    }

    private void showMenu() {
        int choice = -1;
        while (choice != 5) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Choose an option: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    showTransactionHistory();
                    break;
                case 2:
                    performWithdrawal();
                    break;
                case 3:
                    performDeposit();
                    break;
                case 4:
                    performTransfer();
                    break;
                case 5:
                    quit();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void showTransactionHistory() {
        System.out.println("Transaction History:");
        for (String transaction : currentUser.getTransactionHistory()) {
            System.out.println(transaction);
        }
    }

    private void performWithdrawal() {
        System.out.print("Enter amount to withdraw: ");
        double amount = Double.parseDouble(scanner.nextLine());
        if (currentUser.withdraw(amount)) {
            System.out.println("Withdrawal successful.");
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    private void performDeposit() {
        System.out.print("Enter amount to deposit: ");
        double amount = Double.parseDouble(scanner.nextLine());
        currentUser.deposit(amount);
        System.out.println("Deposit successful.");
    }

    private void performTransfer() {
        System.out.print("Enter recipient user ID: ");
        String recipientId = scanner.nextLine();
        System.out.print("Enter amount to transfer: ");
        double amount = Double.parseDouble(scanner.nextLine());

        User recipient = users.get(recipientId);
        if (recipient != null && currentUser.transfer(recipient, amount)) {
            System.out.println("Transfer successful.");
        } else {
            System.out.println("Transfer failed. Please check the recipient ID and your balance.");
        }
    }

    private void quit() {
        System.out.println("Thank you for using the ATM. Goodbye!");
        currentUser = null;
    }

    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.start();
    }
}
