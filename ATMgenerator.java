import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }
}

class ATM {
    private BankAccount userAccount;

    public ATM(BankAccount account) {
        this.userAccount = account;
    }

    public void displayOptions() {
        System.out.println("ATM Options:");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
    }

    public void processOption(int option, Scanner scanner) {

    switch (option) {
        case 1:
            System.out.print("Enter withdrawal amount: ");
            double withdrawalAmount = scanner.nextDouble();

            if (userAccount.withdraw(withdrawalAmount)) {
                System.out.println("Withdrawal successful. Remaining balance: $" + userAccount.getBalance());
            } else {
                System.out.println("Insufficient funds. Withdrawal failed.");
            }

            scanner.nextLine();
            break;

        case 2:
            System.out.print("Enter deposit amount: ");
            double depositAmount = scanner.nextDouble();

            userAccount.deposit(depositAmount);
            System.out.println("Deposit successful. New balance: " + userAccount.getBalance());

            scanner.nextLine();
            break;

        case 3:
            System.out.println("Current Balance: $" + userAccount.getBalance());
            break;

        case 4:
            System.out.println("Exiting ATM. Thank you!");
            System.exit(0);
            break;

        default:
            System.out.println("Invalid option. Please choose a valid option.");
    }
}

}

public class ATMgenerator {
    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(0);

        ATM atm = new ATM(userAccount);

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                atm.displayOptions();

                System.out.print("Enter your choice: ");

                if (scanner.hasNextInt()) {
                    int option = scanner.nextInt();

                    atm.processOption(option, scanner);
                } else {
                    scanner.next();
                    System.out.println("Invalid input. Please enter a valid integer.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
