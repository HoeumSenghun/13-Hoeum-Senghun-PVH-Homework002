import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static CheckingAccount checkingAccount;
    private static SavingAccount savingAccount;

    public static void main(String[] args) {
        while (true) {
            //call method display first
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();//new line resist
            //option
            switch (choice) {
                case 1: createAccount(); break;
                case 2: depositMoney(); break;
                case 3: withdrawMoney(); break;
                case 4: transferMoney(); break;
                case 5: displayAccountInfo(); break;
                case 6: deleteAccount(); break;
                case 7:
                    System.out.println("Exiting the system...");
                    return;
                default:
                    System.out.println("Ple.");
            }
        }
    }
    //method display menu
    private static void displayMenu() {
        System.out.println("================== Online Banking System ==================");
        System.out.println("1. Create Account");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Transfer Money");
        System.out.println("5. Display Account Information");
        System.out.println("6. Delete Account");
        System.out.println("7. Exit");
        System.out.println("===========================================================");
        System.out.print("=> Choose option (1-7): ");
    }
    //method create account
    private static void createAccount() {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>> Creating Account <<<<<<<<<<<<<<<<<<<");
        System.out.println("1. Checking Account\n2. Saving Account\n3. Back");
        System.out.println("------------------------------------------------------------");
        System.out.print("=> Choose option (1-3): ");
        int option = scanner.nextInt();
        scanner.nextLine();//new line resist

        if (option == 3) return; //back to menu
        //input account info
        System.out.println(">>>>>>>>>>>>>>>>>>>>> Account Information <<<<<<<<<<<<<<<<<<<<");
        System.out.print("Enter username: ");
        String userName = scanner.nextLine();
        System.out.print("Enter date of birth (dd-mm-yyyy): ");
        String dob = scanner.nextLine();
        System.out.print("Enter gender: ");
        String gender = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();
        System.out.println("------------------------------------------------------------");

        //random account number
        String accountNumber = String.valueOf((int)(Math.random() * 1_000_000_000));

        if (option == 1 && checkingAccount == null) {
            checkingAccount = new CheckingAccount(accountNumber, userName, dob, gender, phoneNumber);
            System.out.println("\nYour checking account has been created successfully!");
        } else if (option == 2 && savingAccount == null) {
            savingAccount = new SavingAccount(accountNumber, userName, dob, gender, phoneNumber);
            System.out.println("\nYour saving account has been created successfully!");
        } else {
            System.out.println("Your account is already in use!");
        }
    }

    //method deposit
    private static void depositMoney() {
        if (checkAccountsExist()) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>> Deposit Money <<<<<<<<<<<<<<<<<<<");
            System.out.println("1. Deposit to Checking Account\n2. Deposit to Saving Account\n3. Back");
            System.out.println("===========================================================");
            System.out.println("=> Choose option (1-3): ");
            int option = scanner.nextInt();
            System.out.print("Enter money to deposit :");
            double amount = scanner.nextDouble();

            if (option == 1 && checkingAccount != null) {
                checkingAccount.deposit(amount);
            } else if (option == 2 && savingAccount != null) {
                savingAccount.deposit(amount);
            } else {
                System.out.println("Invalid option.");
            }
        }
    }
    //method withdraw
    private static void withdrawMoney() {
        if (checkAccountsExist()) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>> Withdraw Money <<<<<<<<<<<<<<<<<<<");
            System.out.println("1. Withdraw from Checking Account\n2. Withdraw from Saving Account\n3. Back");
            System.out.println("===========================================================");
            System.out.println("=> Choose option (1-3): ");
            int option = scanner.nextInt();
            System.out.print("Enter amount to withdraw: ");
            double amount = scanner.nextDouble();

            if (option == 1 && checkingAccount != null) {
                checkingAccount.withdraw(amount);
            } else if (option == 2 && savingAccount != null) {
                savingAccount.withdraw(amount);
            } else {
                System.out.println("Invalid option.");
            }
        }
    }
    //method transfer
    private static void transferMoney() {
        if (checkAccountsExist()) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>> Transfer Moneys <<<<<<<<<<<<<<<<<<<");
            System.out.println("1. Transfer from Checking Account -> Saving Account\n2. Transfer from Saving Account -> Checking Account\n3. Back");
            System.out.println("===========================================================");
            System.out.println("=> Choose option (1-3): ");
            int option = scanner.nextInt();
            System.out.print("Enter amount to transfer: ");
            double amount = scanner.nextDouble();

            if (option == 1 && checkingAccount != null && savingAccount != null) {
                checkingAccount.transfer(amount, savingAccount);
            } else if (option == 2 && savingAccount != null && checkingAccount != null) {
                savingAccount.transfer(amount, checkingAccount);
            } else {
                System.out.println("Invalid option.");
            }
        }
    }

    private static void displayAccountInfo() {
        if (checkingAccount != null) {
            checkingAccount.displayAccountInfo();
        }
        if (savingAccount != null) {
            savingAccount.displayAccountInfo();
        }
    }

    private static void deleteAccount() {
        if (checkAccountsExist()) {
            System.out.println("1. Delete Checking Account\n2. Delete Saving Account\n3. Back");
            int option = scanner.nextInt();

            if (option == 1 && checkingAccount != null) {
                checkingAccount.transfer(checkingAccount.getBalance(), savingAccount);
                checkingAccount = null;
                System.out.println("Checking Account deleted successfully!");
            } else if (option == 2 && savingAccount != null) {
                savingAccount.transfer(savingAccount.getBalance(), checkingAccount);
                savingAccount = null;
                System.out.println("Saving Account deleted successfully!");
            } else {
                System.out.println("Invalid option.");
            }
        }
    }

    private static boolean checkAccountsExist() {
        if (checkingAccount == null && savingAccount == null) {
            System.out.println("No accounts exist. Please create an account first.");
            return false;
        }
        return true;
    }
}
