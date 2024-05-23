package bank;

public class Account {
    private static String accountNumber;
    private static String accountName;
    private static double accountBalance;

    public Account(String accountNumber, String accountName, double accountBalance) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.accountBalance = accountBalance;
    }

    // Getters and setters
    public static String getAccountNumber() {
        return accountNumber;
    }

    public static String getAccountName() {
        return  accountName;
    }

    public static double getAccountBalance() {
        return accountBalance;
    }
}
