package tellermachine;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TellerMachine {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        AccountDetails account = new AccountDetails();
        Services atmService = new Services(account);

        long accountNumber;
        String pin;

        System.out.println("=================== VILVA BANK ATM =====================");
        System.out.println("              Welcome to Vilva Bank ATM");
        System.out.println("=========================================================");

        // Account number
        System.out.println("Step 1: Validate your card");
        System.out.print("Enter your account number: ");

        try {
            accountNumber = scanner.nextLong();
        } catch (InputMismatchException e) {

            System.out.println("=========================================================");
            System.out.println("ERROR: Invalid account number.");
            System.out.println("=========================================================");

            scanner.close();
            return;
        }

        // PIN
        System.out.println("Step 2: Authorize your account");
        System.out.print("Enter your PIN: ");
        pin = scanner.next();

        // Authentication
        boolean authenticated =
                atmService.authenticateUser(pin, accountNumber);

        if (!authenticated) {

            System.out.println("=========================================================");
            System.out.println("ERROR: Invalid account number or PIN.");
            System.out.println("=========================================================");

            scanner.close();
            return;
        }

        // Authentication successful
        System.out.println("=========================================================");
        System.out.println("User Authentication Successful!");
        System.out.println("=========================================================");

        // Account details
        System.out.println("=================== VILVA BANK ATM =====================");
        System.out.println("                 Account Holder Details");
        System.out.println("=========================================================");
        System.out.println("Name: " + account.getName());
        System.out.println("Mobile: " + account.getMobile());
        System.out.println("Account Number: " + account.getAccountNumber());
        System.out.println("=========================================================");

        // ATM menu
        boolean running = true;

        while (running) {

            System.out.println();
            System.out.println("========================= MENU =========================");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Amount");
            System.out.println("3. Withdraw Amount");
            System.out.println("4. Exit");
            System.out.println("=========================================================");
            System.out.print("Enter your choice: ");

            String service = scanner.next();

            switch (service) {

                case "1":

                    System.out.println("=========================================================");
                    System.out.println("Service Selected: Check Balance");
                    System.out.println("=========================================================");

                    atmService.checkBalance();

                    break;

                case "2":

                    System.out.println("=========================================================");
                    System.out.println("Service Selected: Deposit Amount");
                    System.out.println("=========================================================");

                    System.out.print("Enter the amount to deposit: ");

                    try {

                        double depositAmount = scanner.nextDouble();

                        atmService.deposit(depositAmount);

                    } catch (InputMismatchException e) {

                        System.out.println("=========================================================");
                        System.out.println("ERROR: Invalid amount.");
                        System.out.println("=========================================================");

                        scanner.nextLine();
                    }

                    break;

                case "3":

                    System.out.println("=========================================================");
                    System.out.println("Service Selected: Withdraw Amount");
                    System.out.println("=========================================================");

                    System.out.print("Enter the amount to withdraw: ");

                    try {

                        double withdrawAmount = scanner.nextDouble();

                        atmService.withdraw(withdrawAmount);

                    } catch (InputMismatchException e) {

                        System.out.println("=========================================================");
                        System.out.println("ERROR: Invalid amount.");
                        System.out.println("=========================================================");

                        scanner.nextLine();
                    }

                    break;

                case "4":

                    System.out.println("=========================================================");
                    System.out.println("You have exited.");
                    System.out.println("Thank you for using Vilva Bank ATM!");
                    System.out.println("=========================================================");

                    running = false;

                    break;

                default:

                    System.out.println("=========================================================");
                    System.out.println("ERROR: Invalid choice. Please select 1, 2, 3, or 4.");
                    System.out.println("=========================================================");

                    break;
            }
        }

        scanner.close();
    }
}