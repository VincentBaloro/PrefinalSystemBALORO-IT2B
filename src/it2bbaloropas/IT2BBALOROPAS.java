package it2bbaloropas;

import java.util.Scanner;

public class IT2BBALOROPAS {
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int option;

        do {
        System.out.println("==========================================");
        System.out.println("            WELCOME TO PET ADOPTION      ");
        System.out.println("==========================================");
        System.out.println("              MAIN MENU                  ");
        System.out.println("==========================================");
        System.out.println(" 1. 🐾 Animals                          ");
        System.out.println(" 2. 🐶 Foster                           ");
        System.out.println(" 3. 📊 Reports                          ");
        System.out.println(" 4. ❌ Exit                             ");
        System.out.println("==========================================");
        System.out.print  (" Kunichiwaa \n");

            option = getValidIntegerInput(sc, "Enter Option: "); 

            switch (option) {
                case 1:
                    animal animals = new animal();
                    animals.AnimalMenu(); 
                    break;
                case 2:
                    foster fosters = new foster();
                    fosters.showMenu(); 
                    break;
                case 3:
                    report reports = new report();
                    reports.reportMenu(); 
                    break;
                case 4:
                    System.out.println("Thank you, see you soon!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (option != 4);
        
        sc.close(); 
    }

    private static int getValidIntegerInput(Scanner sc, String prompt) {
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