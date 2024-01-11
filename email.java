package emailapp;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Email {
    private Scanner scanner = new Scanner(System.in);

    // MissingAlternateEmailException as a nested class
    public static class MissingAlternateEmailException extends Exception {
        public MissingAlternateEmailException(String message) {
            super(message);
        }
    }

    private String firstName;
    private String lastName;
    private String department;
    private String email;
    private String password;
    private int mailboxCapacity = 500;
    private String alternateEmail;

    private boolean infoSet = false; // Flag to check if employee information is set

    public Email(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        System.out.println("NEW EMPLOYEE: " + this.firstName + " " + this.lastName);

        // Validate and set the alternate email during object creation
        setAlternateEmail();

        this.department = setDepartment();
        this.password = generatePassword(8);

        try {
            this.email = generateEmail();
        } catch (MissingAlternateEmailException e) {
            System.out.println(e.getMessage());
            System.exit(0); // Terminate the program if the alternate email is missing
        }
    }

    private void setAlternateEmail() {
        boolean isValidEmail = false;
        do {
            System.out.print("Enter alternate gmail account mail ID (mandatory): ");
            this.alternateEmail = scanner.next();
            isValidEmail = validateEmail(this.alternateEmail);
            if (!isValidEmail) {
                System.out.println("Please enter a valid email address!");
            }
        } while (!isValidEmail);
        System.out.println("ALTERNATE EMAIL SET SUCCESSFULLY!");
    }

    private boolean validateEmail(String email) {
        return email.contains("@gmail") && email.contains(".");
    }

    private String generateEmail() throws MissingAlternateEmailException {
        System.out.print("Enter a unique number: ");
        int userNumber = getIntInput();

        if (this.alternateEmail == null || this.alternateEmail.isEmpty()) {
            throw new MissingAlternateEmailException("Alternate email is mandatory. Please set it.");
        }

        return this.firstName.toLowerCase() + "." + this.lastName.toLowerCase() +
                userNumber + "@" + this.department.toLowerCase() + "stylesphere.com";
    }

    private String setDepartment() {
        System.out.println("DEPARTMENT CODES");
        System.out.println("1 for Sales");
        System.out.println("2 for Development");
        System.out.println("3 for Accounting");
        System.out.println("4 for Manager");
        System.out.println("0 for None(to exit the program)");

        while (true) {
            System.out.print("Enter Department Code: ");
            int choice = getIntInput();
            switch (choice) {
                case 1:
                    return "Sales";
                case 2:
                    return "Development";
                case 3:
                    return "Accounting";
                case 4:
                    return "Manager";
                case 0:
                    System.out.println("Exiting program...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("***INVALID CHOICE***");
            }
        }
    }

    private String generatePassword(int length) {
        String capitalChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String smallChars = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String symbols = "!@#$%&?";
        String values = capitalChars + smallChars + numbers + symbols;

        Random random = new Random();
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            password.append(values.charAt(random.nextInt(values.length())));
        }
        return password.toString();
    }

    public void setMailboxCapacity() {
        System.out.println("Current capacity = " + this.mailboxCapacity + " MB");
        System.out.print("Enter new capacity (max 1GB): ");
        int newCapacity = getIntInput();

        if (newCapacity <= 1024) {
            this.mailboxCapacity = newCapacity;
            System.out.println("MAILBOX CAPACITY CHANGED SUCCESSFULLY!");
        } else {
            System.out.println("Mailbox capacity cannot exceed 1024MB.");
        }
    }

    public void setPassword() {
        System.out.print("ARE YOU SURE YOU WANT TO CHANGE YOUR PASSWORD? (Y/N): ");
        char choice = scanner.next().charAt(0);
        if (Character.toLowerCase(choice) == 'y') {
            System.out.print("Enter current password: ");
            String temp = scanner.next();
            if (temp.equals(this.password)) {
                System.out.print("Enter new password: ");
                this.password = scanner.next();
                System.out.println("PASSWORD CHANGED SUCCESSFULLY!");
            } else {
                System.out.println("Incorrect Password!");
            }
        } else if (Character.toLowerCase(choice) == 'n') {
            System.out.println("PASSWORD CHANGE CANCELED!");
        } else {
            System.out.println("***ENTER A VALID CHOICE***");
        }
    }

    public void showInfo() {
        if (infoSet) {
            System.out.println("NAME: " + this.firstName + " " + this.lastName);
            System.out.println("DEPARTMENT: " + this.department);
            System.out.println("EMAIL: " + this.email);
            System.out.println("PASSWORD: " + this.password);
            System.out.println("MAILBOX CAPACITY: " + this.mailboxCapacity + " MB");
            System.out.println("ALTERNATE EMAIL: " + this.alternateEmail);
        } else {
            System.out.println("Employee information is not set yet.");
        }
    }

    private int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input! Please enter a valid number.");
            scanner.next(); // Clear the invalid input
        }
        return scanner.nextInt();
    }

    // Method to set the flag indicating that employee information is set
    public void setInfoSet(boolean infoSet) {
        this.infoSet = infoSet;
    }
}
