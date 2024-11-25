package it2bbaloropas;

import java.util.Scanner;

public class animal {
    private Scanner sc = new Scanner(System.in);
    
    public void AnimalMenu() {
        boolean running = true;

        while (running) {
        System.out.println("==========================================");
        System.out.println("                ANIMAL MENU              ");
        System.out.println("==========================================");
        System.out.println(" 1. 🐾 ADD ANIMAL                       ");
        System.out.println(" 2. 🐾 VIEW ANIMALS                     ");
        System.out.println(" 3. 🐾 UPDATE ANIMAL                    ");
        System.out.println(" 4. 🐾 DELETE ANIMAL                    ");
        System.out.println(" 5. ❌ EXIT                             ");
        System.out.println("==========================================");

            int action = getValidIntegerInput("Enter Action: ");
            
            switch (action) {
                case 1:
                    addAnimals();
                    break;
                case 2:
                    viewAnimals();
                    break;
                case 3:
                    viewAnimals();
                    updateAnimals();
                    viewAnimals();
                    break;
                case 4:
                    viewAnimals();
                    deleteAnimals();
                    viewAnimals();
                    break;
                case 5:
                    System.out.println("Thank you, see you soon!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice! Please select a valid option.");
            }
        }
    }

    public void addAnimals() {
        System.out.print("Animal Name: ");
        String aname = sc.next();
        System.out.print("Species: ");
        String aspecies = sc.next();
        System.out.print("Breed: ");
        String abreed = sc.next();
        System.out.print("Animal Size: ");
        String asize = sc.next();
        System.out.print("Animal Gender: ");
        String agender = sc.next();

        String sql = "INSERT INTO tbl_animals (a_name, a_species, a_breed, a_size, a_gender) VALUES (?, ?, ?, ?, ?)";
        config conf = new config();
        conf.addRecord(sql, aname, aspecies, abreed, asize, agender);
    }

   public void viewAnimals() {
    config conf = new config();
    String animalsQuery = "SELECT a_id, a_name, a_species, a_breed, a_size, a_gender FROM tbl_animals"; // Include ID
    String[] animalsHeaders = {"ID", "Name", "Species", "Breed", "Size", "Gender"}; // Add header for ID
    String[] animalsColumns = {"a_id", "a_name", "a_species", "a_breed", "a_size", "a_gender"}; // Add column for ID

    conf.viewRecords(animalsQuery, animalsHeaders, animalsColumns);
}

    private void updateAnimals() {
        System.out.print("Enter ID to update: ");
        int id = getValidIntegerInput("ID: ");
        
        System.out.print("New Name: ");
        String naname = sc.next();
        System.out.print("New Species: ");
        String naspecies = sc.next();
        System.out.print("New Breed: ");
        String nabreed = sc.next();
        System.out.print("New Size: ");
        String nasize = sc.next();
        System.out.print("New Gender: ");
        String nagender = sc.next();
        
        String qry = "UPDATE tbl_animals SET a_name = ?, a_species = ?, a_breed = ?, a_size = ?, a_gender = ? WHERE a_id = ?";
        config conf = new config();
        conf.updateRecord(qry, naname, naspecies, nabreed, nasize, nagender, id);
    }

    private void deleteAnimals() {
        System.out.print("Enter ID to Delete: ");
        int id = getValidIntegerInput("ID: ");
     
        String qry = "DELETE FROM tbl_animals WHERE a_id = ?";
        config conf = new config();
        conf.deleteRecord(qry, id);
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