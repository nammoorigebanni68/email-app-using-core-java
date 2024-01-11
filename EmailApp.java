package emailapp;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class EmailApp {

    public static void main(String[] args) {
        System.out.println("------------------------");
        System.out.println("StyleSphere: Chic in every way");
        System.out.println("------------------------");
        System.out.println("Welcome to StyleSphere Employee Email Generator");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please, Enter your first name:");
        String firstName = getStringInput(scanner);

        System.out.println("Please, Enter your last name:");
        String lastName = getStringInput(scanner);

        Email email = new Email(firstName, lastName);

        // Set the flag indicating that employee information is set
        email.setInfoSet(true);

        int choice = -1;
        do {
            printMenu();
            choice = getIntInputInRange(scanner, 1, 4);
            switch (choice) {
                case 1:
                    email.showInfo();
                    break;
                case 2:
                    email.setPassword();
                    break;
                case 3:
                    email.setMailboxCapacity();
                    break;
                case 4:
                    System.out.println("\nTHANKS!!!");
                    break;
                default:
                    System.out.println("INVALID CHOICE! ENTER AGAIN!");
            }

        } while (choice != 4);
    }

    private static void printMenu() {
        System.out.println("\n**************************");
        System.out.println("ENTER YOUR CHOICE");
        System.out.println("1.Show Info");
        System.out.println("2.Change Password");
        System.out.println("3.Change Mailbox Capacity");
        System.out.println("4.Exit");
        System.out.print("Enter your choice: ");
    }

    private static String getStringInput(Scanner scanner) {
        int attempts = 0;
        while (attempts < 3) {
            try {
                String input = scanner.nextLine();
                if (input.matches("[a-zA-Z]+")) {
                    return input;
                } else {
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                attempts++;
                System.out.println("Invalid input! Please enter a valid string. Attempts remaining: " + (3 - attempts));
            }
        }
        System.out.println("Exceeded maximum attempts. Exiting program.");
        System.exit(0);
        return null;
    }

    private static int getIntInputInRange(Scanner scanner, int min, int max) {
        int attempts = 0;
        while (attempts < 3) {
            try {
                int input = scanner.nextInt();
                if (input >= min && input <= max) {
                    return input;
                } else {
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                attempts++;
                System.out.println("Invalid input! Please enter a valid integer in the range " + min + " - " + max + ". Attempts remaining: " + (3 - attempts));
                scanner.nextLine();
            }
        }
        System.out.println("Exceeded maximum attempts. Exiting program.");
        System.exit(0);
        return 0;
    }
}
