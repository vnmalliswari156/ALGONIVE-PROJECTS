package shiftmanager;

import java.util.Scanner;

public class TestMain {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        UserDAO userDAO = new UserDAO();
        ShiftDAO shiftDAO = new ShiftDAO();

        while (true) {
            System.out.println("\n--- Shift Manager ---");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Create Shift");
            System.out.println("4. View My Shifts");
            System.out.println("5. Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Clear buffer

            if (choice == 1) {
                System.out.print("Enter name: ");
                String name = sc.nextLine();
                System.out.print("Enter email: ");
                String email = sc.nextLine();
                System.out.print("Enter password: ");
                String pass = sc.nextLine();

                userDAO.registerUser(name, email, pass);
                System.out.println("✅ User registered successfully!");

            } else if (choice == 2) {
                System.out.print("Enter email: ");
                String email = sc.nextLine();
                System.out.print("Enter password: ");
                String pass = sc.nextLine();

                if (userDAO.loginUser(email, pass)) {
                    System.out.println("✅ Login successful!");
                } else {
                    System.out.println("❌ Invalid login details");
                }

            } else if (choice == 3) {
                System.out.print("Enter employee email: ");
                String email = sc.nextLine();
                System.out.print("Enter shift date (YYYY-MM-DD): ");
                String date = sc.nextLine();
                System.out.print("Enter start time (HH:MM): ");
                String start = sc.nextLine();
                System.out.print("Enter end time (HH:MM): ");
                String end = sc.nextLine();

                shiftDAO.addShift(email, date, start, end);
                System.out.println("✅ Shift assigned!");

            } else if (choice == 4) {
                System.out.print("Enter your email: ");
                String email = sc.nextLine();
                shiftDAO.viewShifts(email);

            } else if (choice == 5) {
                System.out.println("Goodbye!");
                break;
            }
        }

        sc.close();
    }
}
