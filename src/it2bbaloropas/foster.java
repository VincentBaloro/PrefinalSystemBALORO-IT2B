package it2bbaloropas;

import java.util.Scanner;

public class foster {
    private Scanner sc = new Scanner(System.in);
    private animal animals = new animal();
    private config conf = new config();

    public void showMenu() {
        int choice;
        do {
        System.out.println("==========================================");
        System.out.println("      PET ADOPTION AND FOSTERING MENU    ");
        System.out.println("==========================================");
        System.out.println(" 1. 🐾 Adopt a Pet                      ");
        System.out.println(" 2. 📋 View Adoptions                   ");
        System.out.println(" 3. 🐶 View Fosters                     ");
        System.out.println(" 4. 🔄 Update Foster                    ");
        System.out.println(" 5. ❌ Delete Foster                    ");
        System.out.println(" 6. 🚪 Exit                             ");
        System.out.println("==========================================");
            choice = getValidIntegerInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    adoptPet();
                    break;
                case 2:
                    viewAdoptions();
                    break;
                case 3:
                    viewFosters();
                    break;
                case 4:
                    viewFosters();
                    updateFoster();
                    viewFosters();
                    break;
                case 5:
                    viewFosters();
                    deleteFoster();
                    viewFosters();
                    break;
                case 6:
                    System.out.println("Exiting the application. Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);
    }

    
    public void adoptPet() {
        animals.viewAnimals();

        int aid = getValidIntegerInput("Enter the ID of the animal you want to adopt: ");

        System.out.print("Enter your name: ");
        String fname = sc.nextLine();
        System.out.print("Enter your email address: ");
        String femail = sc.nextLine();
        System.out.print("Enter your phone number: ");
        String fpnum = sc.nextLine();
        System.out.print("Do you want to foster the animal? (yes/no): ");
        String fresponse = sc.nextLine();

        String qry;
        if (fresponse.equalsIgnoreCase("yes")) {
            qry = "INSERT INTO tbl_fosters (a_id, f_name, f_email, f_pnumber) VALUES (?, ?, ?, ?)";
            conf.addRecord(qry, aid, fname, femail, fpnum);
            System.out.println("Foster Information saved successfully!");
        }

        qry = "INSERT INTO tbl_reports (a_id, f_name, f_email, f_pnumber) VALUES (?, ?, ?, ?)";
        conf.addRecord(qry, aid, fname, femail, fpnum);
        System.out.println("Adoption Information saved successfully!");
    }

    
    public void viewAdoptions() {
        String query = "SELECT a_id, f_name, f_email, f_pnumber FROM tbl_reports"; 
        String[] columnHeaders = {"Animal ID", "Adopter Name", "Adopter Email", "Adopter Phone"};
        String[] columnNames = {"a_id", "f_name", "f_email", "f_pnumber"}; 
        conf.viewRecords(query, columnHeaders, columnNames);
    }

   
    public void viewFosters() {
        String query = "SELECT a_id, f_name, f_email, f_pnumber FROM tbl_fosters"; 
        String[] columnHeaders = {"Animal ID", "Foster Name", "Foster Email", "Foster Phone"};
        String[] columnNames = {"a_id", "f_name", "f_email", "f_pnumber"}; 
        conf.viewRecords(query, columnHeaders, columnNames);
    }

    
    public void updateFoster() {
        int fid = getValidIntegerInput("Enter the ID of the foster to update: ");

        System.out.print("Enter new name: ");
        String fname = sc.nextLine();
        System.out.print("Enter new email address: ");
        String femail = sc.nextLine();
        System.out.print("Enter new phone number: ");
        String fpnum = sc.nextLine();

        String qry = "UPDATE tbl_fosters SET f_name = ?, f_email = ?, f_pnumber = ? WHERE id = ?";
        conf.addRecord(qry, fname, femail, fpnum, fid);
        System.out.println("Foster Information updated successfully!");
    }

    
    public void deleteFoster() {
        int fid = getValidIntegerInput("Enter the ID of the foster to delete: ");

        String qry = "DELETE FROM tbl_fosters WHERE id = ?";
        conf.addRecord(qry, fid);
        System.out.println("Foster Information deleted successfully!");
    }

    
    private int getValidIntegerInput(String prompt) {
        int value;
        while (true) {
            System.out.print(prompt);
            if (sc.hasNextInt()) {
                value = sc.nextInt();
                sc.nextLine(); 
                return value;
            } else {
                System.out.println("Invalid input. Please enter a valid integer.");
                sc.nextLine(); 
            }
        }
    }
}