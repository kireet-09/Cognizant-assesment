import java.util.Scanner;

public class InventoryManagementSystem {

    private static InventoryManager manager;
    private static Scanner scanner;

    public static void main(String[] args) {
        manager = new InventoryManager();
        scanner = new Scanner(System.in);

        displayWelcome();
        loadSampleData();

        boolean running = true;
        while (running) {
            displayMenu();
            System.out.print("Enter your choice (1-6): ");
            
            String input = scanner.nextLine().trim();
            
            switch (input) {
                case "1":
                    addProductMenu();
                    break;
                case "2":
                    updateProductMenu();
                    break;
                case "3":
                    deleteProductMenu();
                    break;
                case "4":
                    searchProductMenu();
                    break;
                case "5":
                    displayInventoryMenu();
                    break;
                case "6":
                    running = false;
                    System.out.println("\nThank you for using Inventory Management System. Goodbye!");
                    break;
                default:
                    System.out.println("Error: Invalid choice. Please enter 1-6.");
            }
            System.out.println();
        }
        
        scanner.close();
    }

    private static void displayWelcome() {
        System.out.println("\n=== INVENTORY MANAGEMENT ===\n");
    }

    private static void displayMenu() {
        System.out.println("\n1.Add 2.Update 3.Delete 4.Search 5.Display 6.Exit");
    }

    private static void addProductMenu() {
        int id = getIntInput("ID: ");
        if (id <= 0) return;
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) return;
        int qty = getIntInput("Qty: ");
        if (qty < 0) return;
        double price = getDoubleInput("Price: ");
        if (price < 0) return;
        manager.addProduct(new Product(id, name, qty, price));
    }

    private static void updateProductMenu() {
        int id = getIntInput("ID: ");
        if (id <= 0) return;
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) return;
        int qty = getIntInput("Qty: ");
        if (qty < 0) return;
        double price = getDoubleInput("Price: ");
        if (price < 0) return;
        manager.updateProduct(id, name, qty, price);
    }

    private static void deleteProductMenu() {
        int id = getIntInput("ID: ");
        if (id <= 0) return;
        System.out.print("Confirm? (yes/no): ");
        if (scanner.nextLine().trim().toLowerCase().equals("yes")) manager.deleteProduct(id);
    }

    private static void searchProductMenu() {
        int id = getIntInput("ID: ");
        if (id > 0) manager.searchProduct(id);
    }

    private static void displayInventoryMenu() {
        manager.displayProducts();
        manager.displayInventorySummary();
    }
    
    private static void loadSampleData() {
        manager.addProduct(new Product(101, "Laptop", 10, 50000));
        manager.addProduct(new Product(102, "Mouse", 50, 500));
        manager.addProduct(new Product(103, "Keyboard", 25, 1500));
        manager.addProduct(new Product(104, "Monitor", 15, 12000));
        System.out.println("Loaded.\n");
    }
    
    private static int getIntInput(String p) {
        System.out.print(p);
        try { return Integer.parseInt(scanner.nextLine().trim()); }
        catch (NumberFormatException e) { return -1; }
    }
    private static double getDoubleInput(String p) {
        System.out.print(p);
        try { return Double.parseDouble(scanner.nextLine().trim()); }
        catch (NumberFormatException e) { return -1; }
    }
}