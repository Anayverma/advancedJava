import java.time.LocalDateTime;
import java.util.*;

public class ATMSystem {

    static class Account {
        private String accountNumber;
        private int pin;
        private double balance;
        private List<Transaction> transactions;

        public Account(String accountNumber, int pin) {
            this.accountNumber = accountNumber;
            this.pin = pin;
            this.balance = 0.0;
            this.transactions = new ArrayList<>();
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public double getBalance() {
            return balance;
        }

        public void deposit(double amount) {
            balance += amount;
            transactions.add(new Transaction("Deposit", amount));
        }

        public boolean withdraw(double amount) {
            if (balance >= amount) {
                balance -= amount;
                transactions.add(new Transaction("Withdrawal", amount));
                return true;
            }
            return false;
        }

        public List<Transaction> getTransactions() {
            return transactions;
        }
    }

    static class Transaction {
        private String type;
        private double amount;
        private LocalDateTime timestamp;

        public Transaction(String type, double amount) {
            this.type = type;
            this.amount = amount;
            this.timestamp = LocalDateTime.now();
        }

        @Override
        public String toString() {
            return "Transaction{" +
                    "type='" + type + '\'' +
                    ", amount=" + amount +
                    ", timestamp=" + timestamp +
                    '}';
        }
    }

    static class ATM {
        private Map<String, Account> accounts;
        private Account currentAccount;
        private Scanner scanner;

        public ATM() {
            this.accounts = new HashMap<>();
            this.scanner = new Scanner(System.in);
        }

        public void addAccount(String accountNumber, int pin) {
            accounts.put(accountNumber, new Account(accountNumber, pin));
        }

        public void start() {
            System.out.println("Welcome to the ATM System!");
            authenticate();
            displayMenu();
        }

        private void authenticate() {
            System.out.print("Enter Account Number: ");
            String accountNumber = scanner.nextLine();
            System.out.print("Enter PIN: ");
            int pin = scanner.nextInt();
            scanner.nextLine(); 

            Account account = accounts.get(accountNumber);
            if (account != null && account.getAccountNumber().equals(accountNumber) && account.pin == pin) {
                currentAccount = account;
                System.out.println("Authentication successful.");
            } else {
                System.out.println("Authentication failed. Please try again.");
                authenticate();
            }
        }

        private void displayMenu() {
            while (true) {
                System.out.println("\nMenu:");
                System.out.println("1. Check Balance");
                System.out.println("2. Deposit");
                System.out.println("3. Withdraw");
                System.out.println("4. View Transactions");
                System.out.println("5. Change PIN");
                System.out.println("6. Exit");

                System.out.print("Enter choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); 

                switch (choice) {
                    case 1:
                        checkBalance();
                        break;
                    case 2:
                        deposit();
                        break;
                    case 3:
                        withdraw();
                        break;
                    case 4:
                        viewTransactions();
                        break;
                    case 5:
                        changePIN();
                        break;
                    case 6:
                        System.out.println("Thank you for using the ATM System.");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }

        private void checkBalance() {
            double balance = currentAccount.getBalance();
            System.out.println("Current Balance: $" + balance);
        }

        private void deposit() {
            System.out.print("Enter deposit amount: $");
            double amount = scanner.nextDouble();
            scanner.nextLine(); 

            currentAccount.deposit(amount);
            System.out.println("Deposit successful. Updated Balance: $" + currentAccount.getBalance());
        }

        private void withdraw() {
            System.out.print("Enter withdrawal amount: $");
            double amount = scanner.nextDouble();
            scanner.nextLine(); 

            if (currentAccount.withdraw(amount)) {
                System.out.println("Withdrawal successful. Updated Balance: $" + currentAccount.getBalance());
            } else {
                System.out.println("Insufficient funds.");
            }
        }

        private void viewTransactions() {
            System.out.println("Transaction History:");
            for (Transaction transaction : currentAccount.getTransactions()) {
                System.out.println(transaction);
            }
        }

        private void changePIN() {
            System.out.print("Enter new PIN: ");
            int newPIN = scanner.nextInt();
            scanner.nextLine(); 

            currentAccount.pin = newPIN;
            System.out.println("PIN changed successfully.");
        }
    }

    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.addAccount("121212", 1001);
        atm.addAccount("789012", 5678);
        atm.start();
    }
}
