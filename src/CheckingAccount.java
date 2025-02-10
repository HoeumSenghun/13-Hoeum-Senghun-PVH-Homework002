public class CheckingAccount implements Account {
    private String accountNumber;
    private String userName;
    private String dob;
    private String gender;
    private String phoneNumber;
    private double balance;

    //constructor checking account
    public CheckingAccount(String accountNumber, String userName, String dob, String gender, String phoneNumber) {
        this.accountNumber = accountNumber;
        this.userName = userName;
        this.dob = dob;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.balance = 0.0;  // default money = 0
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("                           |                  ");
            System.out.println("                           V                  ");
            System.out.println("\t\t\t\t\tChecking Account");
            System.out.println("Received: " + amount + "$");
            System.out.println("Total Balance: " + balance + "$");
            System.out.println("===========================================================");
            System.out.println("\nDeposit successful!");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("\t\t\t\t\tChecking Account");
            System.out.println("Withdrawn: " + amount + "$");
            System.out.println("Total Balance: " + balance + "$");
            System.out.println("===========================================================");
            System.out.println("\nWithdrawal successful!");
            return true;
        } else {
            System.out.println("Invalid withdrawal amount or insufficient balance.");
            return false;
        }
    }

    @Override
    public boolean transfer(double amount, Account targetAccount) {
        if (withdraw(amount)) {
            targetAccount.deposit(amount);
            System.out.println("Transferred: " + amount + "$ to account.");
            return true;
        }
        return false;
    }

    @Override
    public void displayAccountInfo() {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>> Checking Account <<<<<<<<<<<<<<<<<<<");
        System.out.println("Account Type: Checking Account");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("User Name: " + userName);
        System.out.println("Date of Birth: " + dob);
        System.out.println("Gender: " + gender);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Balance: " + balance + "$");
        System.out.println("===========================================================");
    }

    //getter
    public String getAccountNumber() {
        return accountNumber;
    }
    //setter
    public double getBalance() {
        return balance;
    }
}
