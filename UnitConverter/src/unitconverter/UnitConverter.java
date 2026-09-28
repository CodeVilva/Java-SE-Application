package unitconverter;

import java.util.Scanner;

public class UnitConverter {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n==============================");
            System.out.println("       UNIT CONVERTER");
            System.out.println("==============================");
            System.out.println("1. Length");
            System.out.println("2. Weight");
            System.out.println("3. Temperature");
            System.out.println("4. Exit");
            System.out.println("==============================");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    lengthConverter();
                    break;

                case 2:
                    weightConverter();
                    break;

                case 3:
                    temperatureConverter();
                    break;

                case 4:
                    System.out.println("\nThank you for using Unit Converter!");
                    scanner.close();
                    return;

                default:
                    System.out.println("\nInvalid choice. Please try again.");
            }
        }
    }

    // Length Converter
    public static void lengthConverter() {

        System.out.println("\n---------- LENGTH ----------");
        System.out.println("1. Meter to Kilometer");
        System.out.println("2. Kilometer to Meter");
        System.out.println("3. Meter to Centimeter");
        System.out.println("4. Centimeter to Meter");
        System.out.println("5. Kilometer to Mile");
        System.out.println("6. Mile to Kilometer");
        System.out.println("7. Meter to Feet");
        System.out.println("8. Feet to Meter");

        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();

        System.out.print("Enter value: ");
        double value = scanner.nextDouble();

        double result;

        switch (choice) {

            case 1:
                result = value / 1000;
                System.out.println("Result: " + result + " km");
                break;

            case 2:
                result = value * 1000;
                System.out.println("Result: " + result + " m");
                break;

            case 3:
                result = value * 100;
                System.out.println("Result: " + result + " cm");
                break;

            case 4:
                result = value / 100;
                System.out.println("Result: " + result + " m");
                break;

            case 5:
                result = value * 0.621371;
                System.out.println("Result: " + result + " miles");
                break;

            case 6:
                result = value / 0.621371;
                System.out.println("Result: " + result + " km");
                break;

            case 7:
                result = value * 3.28084;
                System.out.println("Result: " + result + " feet");
                break;

            case 8:
                result = value / 3.28084;
                System.out.println("Result: " + result + " meters");
                break;

            default:
                System.out.println("Invalid choice.");
        }
    }

    // Weight Converter
    public static void weightConverter() {

        System.out.println("\n---------- WEIGHT ----------");
        System.out.println("1. Kilogram to Gram");
        System.out.println("2. Gram to Kilogram");
        System.out.println("3. Kilogram to Pound");
        System.out.println("4. Pound to Kilogram");

        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();

        System.out.print("Enter value: ");
        double value = scanner.nextDouble();

        double result;

        switch (choice) {

            case 1:
                result = value * 1000;
                System.out.println("Result: " + result + " g");
                break;

            case 2:
                result = value / 1000;
                System.out.println("Result: " + result + " kg");
                break;

            case 3:
                result = value * 2.20462;
                System.out.println("Result: " + result + " pounds");
                break;

            case 4:
                result = value / 2.20462;
                System.out.println("Result: " + result + " kg");
                break;

            default:
                System.out.println("Invalid choice.");
        }
    }

    // Temperature Converter
    public static void temperatureConverter() {

        System.out.println("\n-------- TEMPERATURE --------");
        System.out.println("1. Celsius to Fahrenheit");
        System.out.println("2. Fahrenheit to Celsius");
        System.out.println("3. Celsius to Kelvin");
        System.out.println("4. Kelvin to Celsius");

        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();

        System.out.print("Enter temperature: ");
        double value = scanner.nextDouble();

        double result;

        switch (choice) {

            case 1:
                result = (value * 9 / 5) + 32;
                System.out.println("Result: " + result + " °F");
                break;

            case 2:
                result = (value - 32) * 5 / 9;
                System.out.println("Result: " + result + " °C");
                break;

            case 3:
                result = value + 273.15;
                System.out.println("Result: " + result + " K");
                break;

            case 4:
                result = value - 273.15;
                System.out.println("Result: " + result + " °C");
                break;

            default:
                System.out.println("Invalid choice.");
        }
    }
}