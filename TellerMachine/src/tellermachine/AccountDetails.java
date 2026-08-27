package tellermachine;

public class AccountDetails {

    private final long accountNumber = 36035734590L;
    private final String name = "S Vilva";
    private final String mobile = "6374008684";
    private double savings = 24000.00;
    private final String pin = "1623";

    public long getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public double getSavings() {
        return savings;
    }

    public boolean verifyPin(String enteredPin) {
        return pin.equals(enteredPin);
    }

    public void addSavings(double amount) {
        savings += amount;
    }

    public boolean withdrawSavings(double amount) {

        if (amount > savings) {
            return false;
        }

        savings -= amount;
        return true;
    }
}