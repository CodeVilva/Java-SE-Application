package tellermachine;

public class Services {

    private final AccountDetails account;

    public Services(AccountDetails account) {
        this.account = account;
    }

    // User authentication
    public boolean authenticateUser(String pin, long accountNumber) {

        return account.getAccountNumber() == accountNumber
                && account.verifyPin(pin);
    }

    // Check balance
    public void checkBalance() {

        System.out.println("=========================================================");
        System.out.printf("Available Balance: %.2f Rs%n", account.getSavings());
        System.out.println("=========================================================");
    }

    // Deposit money
    public void deposit(double amount) {

        if (amount <= 0) {
            System.out.println("=========================================================");
            System.out.println("ERROR: Deposit amount must be greater than 0.");
            System.out.println("=========================================================");
            return;
        }

        account.addSavings(amount);

        System.out.println("=========================================================");
        System.out.printf("Amount Deposited: %.2f Rs%n", amount);
        System.out.printf("Total Available Balance: %.2f Rs%n",
                account.getSavings());
        System.out.println("=========================================================");
    }

    // Withdraw money
    public void withdraw(double amount) {

        if (amount <= 0) {
            System.out.println("=========================================================");
            System.out.println("ERROR: Withdrawal amount must be greater than 0.");
            System.out.println("=========================================================");
            return;
        }

        if (amount > account.getSavings()) {
            System.out.println("=========================================================");
            System.out.println("ERROR: Insufficient balance.");
            System.out.printf("Available Balance: %.2f Rs%n",
                    account.getSavings());
            System.out.println("=========================================================");
            return;
        }

        account.withdrawSavings(amount);

        System.out.println("=========================================================");
        System.out.printf("Amount Withdrawn: %.2f Rs%n", amount);
        System.out.printf("Available Balance: %.2f Rs%n",
                account.getSavings());
        System.out.println("=========================================================");
    }
}