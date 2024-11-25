package it2bbaloropas;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.sql.*;

public class report {
   
    private Scanner input = new Scanner(System.in);
    private config conf = new config();

    public void reportMenu() {
        boolean exit = true;
        while (exit) {
        System.out.println("==========================================");
        System.out.println("                REPORT MENU              ");
        System.out.println("==========================================");
        System.out.println(" 1. 📄 View All Reports                 ");
        System.out.println(" 2. 🔍 Individual Report                ");
        System.out.println(" 3. 🚪 Exit                             ");
        System.out.println("==========================================");

            int choice = getValidIntegerInput("Choose an option (1-3): ");
            switch (choice) {
                case 1:
                    viewAllReports();
                    break;
                case 2:
                    individualReport();
                    break;
                case 3:
                    exit = false;
                    break;
                default:
                    System.out.println("Invalid option, Try Again");
            }
        }
        System.out.println("Thank you, see you soon!");
    }

    private void viewAllReports() {
        String query = "SELECT r_id, a_id, f_id, f_name, f_email, f_pnumber FROM tbl_reports";
        String[] headers = {"Report ID", "Animal ID", "Foster ID", "Foster Name", "Email", "Phone Number"};
        String[] columns = {"r_id", "a_id", "f_id", "f_name", "f_email", "f_pnumber"};
        conf.viewRecords(query, headers, columns);
    }

    private void individualReport() {
        viewAllReports();
        int reportId = getValidIntegerInput("Enter Report ID: ");

        
        String query = "SELECT r_id, a_id, f_id, f_name, f_email, f_pnumber FROM tbl_reports WHERE r_id = ?";
        
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:pas.db"); 
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
           
            pstmt.setInt(1, reportId);
            
            
            ResultSet rs = pstmt.executeQuery();
            
           
            if (rs.next()) {
                
                System.out.println("================Report Details:================");
                System.out.printf("Report ID: %d\n", rs.getInt("r_id"));
                System.out.printf("Animal ID: %d\n", rs.getInt("a_id"));
                System.out.printf("Foster ID: %d\n", rs.getInt("f_id"));
                System.out.printf("Foster Name: %s\n", rs.getString("f_name"));
                System.out.printf("Email: %s\n", rs.getString("f_email"));
                System.out.printf("Phone Number: %s\n", rs.getString("f_pnumber"));
                System.out.println("===============================================");
            } else {
                System.out.println("No report found with the given ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving report: " + e.getMessage());
        }
    }

    
    private int getValidIntegerInput(String prompt) {
        int value;
        while (true) {
            System.out.print(prompt);
            try {
                value = input.nextInt();
                input.nextLine(); 
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                input.nextLine(); 
            }
        }
    }
}