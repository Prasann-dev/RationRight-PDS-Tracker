import java.util.ArrayList;
import java.util.Scanner;

public class RationRight {
    private static ArrayList<Family> families = new ArrayList<>();
    private static Inventory stock = new Inventory(100.0, 100.0, 50.0); 
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n========================================");
            System.out.println("   RATIONRIGHT: PDS DISTRIBUTION TRACKER ");
            System.out.println("========================================");
            System.out.println("1. Register a New Family Card");
            System.out.println("2. Calculate & View Monthly Entitlements");
            System.out.println("3. Record Distribution & Block Duplicates");
            System.out.println("4. Check Shop Stock & Inventory");
            System.out.println("5. Generate Reports (Pending & Stock)");
            System.out.println("6. Exit");
            System.out.print("Enter your choice (1-6): ");
            
            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Enter a number between 1-6: ");
                scanner.next();
            }
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    registerFamily();
                    break;
                case 2:
                    viewEntitlements();
                    break;
                case 3:
                    recordDistribution();
                    break;
                case 4:
                    stock.displayStock();
                    break;
                case 5:
                    generateReports();
                    break;
                case 6:
                    System.out.println("Exiting RationRight. Program terminated.");
                    break;
                default:
                    System.out.println("Invalid choice! Please choose between 1 and 6.");
            }
        } while (choice != 6);
    }

    private static void registerFamily() {
        System.out.println("\n--- Register New Family Card ---");
        System.out.print("Enter Card ID (e.g., R001): ");
        String cardId = scanner.nextLine().trim();

        if (cardId.isEmpty()) {
            System.out.println("Error: Card ID cannot be empty!");
            return;
        }

        for (Family f : families) {
            if (f.cardId.equalsIgnoreCase(cardId)) {
                System.out.println("Error: Card ID already exists!");
                return;
            }
        }

        System.out.print("Enter Head of Family Name: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Error: Head of Family Name cannot be empty!");
            return;
        }

        System.out.print("Enter Card Type (AAY / PHH / NPHH): ");
        String inputType = scanner.nextLine().trim().toUpperCase();

        if (!inputType.equals("AAY") && !inputType.equals("PHH") && !inputType.equals("NPHH")) {
            System.out.println("Error: Invalid card type! Only AAY, PHH, or NPHH are allowed.");
            return;
        }

        System.out.print("Enter Number of Members: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid integer for members: ");
            scanner.next();
        }
        int members = scanner.nextInt();
        scanner.nextLine();

        if (members <= 0) {
            System.out.println("Error: Number of members must be at least 1.");
            return;
        }

        families.add(new Family(cardId, name, inputType, members));
        System.out.println("Family successfully registered!");
    }

    private static void viewEntitlements() {
        System.out.println("\n--- Monthly Entitlement Calculator ---");
        System.out.print("Enter Card ID to check entitlement: ");
        String cardId = scanner.nextLine().trim();

        Family target = findFamily(cardId);
        if (target == null) {
            System.out.println("Family not found with ID: " + cardId);
            return;
        }

        double ricePerMember = 0, wheatPerMember = 0, sugarPerFamily = 0;

        if (target.cardType.equalsIgnoreCase("AAY")) {
            ricePerMember = 5.0;
            wheatPerMember = 3.0;
            sugarPerFamily = 2.0;
        } else if (target.cardType.equalsIgnoreCase("PHH")) {
            ricePerMember = 4.0;
            wheatPerMember = 2.0;
            sugarPerFamily = 1.0;
        } else { // NPHH
            ricePerMember = 2.0;
            wheatPerMember = 1.0;
            sugarPerFamily = 0.5;
        }

        double totalRice = target.members * ricePerMember;
        double totalWheat = target.members * wheatPerMember;

        System.out.println("\nEntitlement Details for " + target.headName + " (" + target.cardType + "):");
        System.out.println("----------------------------------------");
        System.out.println("Rice (" + target.members + " members * " + ricePerMember + "kg): " + totalRice + " kg");
        System.out.println("Wheat (" + target.members + " members * " + wheatPerMember + "kg): " + totalWheat + " kg");
        System.out.println("Sugar (Fixed quota): " + sugarPerFamily + " kg");
    }

    private static void recordDistribution() {
        System.out.println("\n--- Record Ration Distribution ---");
        System.out.print("Enter Card ID: ");
        String cardId = scanner.nextLine().trim();

        Family target = findFamily(cardId);
        if (target == null) {
            System.out.println("Family not found!");
            return;
        }

        System.out.print("Enter Current Collection Month (YYYY-MM, e.g., 2026-11): ");
        String currentMonth = scanner.nextLine().trim();

        if (!isValidMonth(currentMonth)) {
            System.out.println("Error: Invalid month format! Please enter a valid year and month in YYYY-MM format (e.g., 2026-11).");
            return;
        }

        boolean alreadyCollected = false;
        for (String m : target.collectedMonths) {
            if (m.equalsIgnoreCase(currentMonth)) {
                alreadyCollected = true;
                break;
            }
        }

        if (alreadyCollected) {
            System.out.println("ACCESS DENIED: This family has already collected their quota for " + currentMonth + "!");
            return;
        }

        double r = 0, w = 0, s = 0;
        if (target.cardType.equalsIgnoreCase("AAY")) {
            r = target.members * 5.0; w = target.members * 3.0; s = 2.0;
        } else if (target.cardType.equalsIgnoreCase("PHH")) {
            r = target.members * 4.0; w = target.members * 2.0; s = 1.0;
        } else { // NPHH
            r = target.members * 2.0; w = target.members * 1.0; s = 0.5;
        }

        if (stock.riceKg < r || stock.wheatKg < w || stock.sugarKg < s) {
            System.out.println("ERROR: Insufficient shop inventory to complete this distribution!");
            System.out.println("Required  -> Rice: " + r + " kg, Wheat: " + w + " kg, Sugar: " + s + " kg");
            System.out.println("Available -> Rice: " + stock.riceKg + " kg, Wheat: " + stock.wheatKg + " kg, Sugar: " + stock.sugarKg + " kg");
            return;
        }

        stock.riceKg -= r;
        stock.wheatKg -= w;
        stock.sugarKg -= s;
        target.collectedMonths.add(currentMonth);

        System.out.println("Distribution successful! Rations issued and stock updated for " + target.headName);
        System.out.println("Issued -> Rice: " + r + " kg, Wheat: " + w + " kg, Sugar: " + s + " kg");
    }
    private static void generateReports() {
        System.out.println("\n--- PDS Status & Collection Report ---");
        System.out.print("Enter reporting month to check (YYYY-MM, e.g., 2026-11): ");
        String month = scanner.nextLine().trim();

        if (!isValidMonth(month)) {
            System.out.println("Error: Invalid month format! Please enter a valid year and month in YYYY-MM format (e.g., 2026-11).");
            return;
        }

        System.out.println("\n[1] Families Yet to Collect for " + month + ":");
        if (families.isEmpty()) {
            System.out.println(" No families are registered in the system yet.");
        } else {
            int pendingCount = 0;
            for (Family f : families) {
                boolean collectedThisMonth = false;
                for (String m : f.collectedMonths) {
                    if (m.equalsIgnoreCase(month)) {
                        collectedThisMonth = true;
                        break;
                    }
                }
                
                if (!collectedThisMonth) {
                    System.out.println(" - " + f.cardId + " | " + f.headName + " (" + f.cardType + ")");
                    pendingCount++;
                }
            }
            if (pendingCount == 0) {
                System.out.println(" All registered families have collected their rations for this month.");
            }
        }

        System.out.println("\n[2] Remaining Stock Summary:");
        stock.displayStock();
    }

    private static boolean isValidMonth(String monthStr) {
        return monthStr.matches("^(20\\d{2})-(0[1-9]|1[0-2])$");
    }

    private static Family findFamily(String cardId) {
        for (Family f : families) {
            if (f.cardId.equalsIgnoreCase(cardId)) {
                return f;
            }
        }
        return null;
    }
}