import java.util.ArrayList;
import java.util.Scanner;

class BankAccount {
    private String accountNumber;
    private String accountHolder;
    private double balance;
    private ArrayList<String> transactionHistory;

    public BankAccount(String accountNumber, String accountHolder, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
        this.transactionHistory.add("Initial balance: " + initialBalance);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public ArrayList<String> getTransactionHistory() {
        return transactionHistory;
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add("Deposit: +" + amount);
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrawal: -" + amount);
        } else {
            System.out.println("Insufficient funds!");
        }
    }

    public void transfer(BankAccount receiver, double amount) {
        if (amount <= balance) {
            balance -= amount;
            receiver.deposit(amount);
            transactionHistory.add("Transfer to " + receiver.getAccountNumber() + ": -" + amount);
        } else {
            System.out.println("Insufficient funds!");
        }
    }

    public void printTransactionHistory() {
        System.out.println("Transaction History for Account " + accountNumber);
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }
}

public class OnlineBankingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create two sample bank accounts
        BankAccount account1 = new BankAccount("123456789", "John Doe", 1000.0);
        BankAccount account2 = new BankAccount("987654321", "Jane Doe", 1500.0);

        // Simple menu for user interaction
        while (true) {
            System.out.println("\n1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Transaction History");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Balance: $" + account1.getBalance());
                    break;
                case 2:
                    System.out.print("Enter deposit amount: $");
                    double depositAmount = scanner.nextDouble();
                    account1.deposit(depositAmount);
                    System.out.println("Deposit successful. New balance: $" + account1.getBalance());
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: $");
                    double withdrawalAmount = scanner.nextDouble();
                    account1.withdraw(withdrawalAmount);
                    System.out.println("Withdrawal successful. New balance: $" + account1.getBalance());
                    break;
                case 4:
                    System.out.print("Enter recipient's account number: ");
                    String recipientAccountNumber = scanner.next();
                    BankAccount recipientAccount = (recipientAccountNumber.equals(account2.getAccountNumber())) ? account2 : null;

                    if (recipientAccount != null) {
                        System.out.print("Enter transfer amount: $");
                        double transferAmount = scanner.nextDouble();
                        account1.transfer(recipientAccount, transferAmount);
                        System.out.println("Transfer successful. New balance: $" + account1.getBalance());
                    } else {
                        System.out.println("Recipient account not found!");
                    }
                    break;
                case 5:
                    account1.printTransactionHistory();
                    break;
                case 6:
                    System.out.println("Exiting Online Banking System. Thank you!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
