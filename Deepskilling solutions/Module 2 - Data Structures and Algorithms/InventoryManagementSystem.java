import java.util.HashMap;

public class InventoryManagementSystem {

    // Product Class
    static class Product {
        int productId;
        String productName;
        int quantity;
        double price;

        Product(int productId, String productName,
                int quantity, double price) {
            this.productId = productId;
            this.productName = productName;
            this.quantity = quantity;
            this.price = price;
        }

        @Override
        public String toString() {
            return "ID=" + productId +
                    ", Name=" + productName +
                    ", Quantity=" + quantity +
                    ", Price=" + price;
        }
    }

    // Inventory Storage
    private HashMap<Integer, Product> inventory = new HashMap<>();

    // Add Product
    public void addProduct(Product product) {
        inventory.put(product.productId, product);
        System.out.println("Added: " + product.productName);
    }

    // Update Product
    public void updateProduct(int productId,
                              int quantity,
                              double price) {

        Product product = inventory.get(productId);

        if (product != null) {
            product.quantity = quantity;
            product.price = price;
            System.out.println("Updated Product ID: " + productId);
        } else {
            System.out.println("Product Not Found");
        }
    }

    // Delete Product
    public void deleteProduct(int productId) {

        if (inventory.remove(productId) != null) {
            System.out.println("Deleted Product ID: " + productId);
        } else {
            System.out.println("Product Not Found");
        }
    }

    // Display Inventory
    public void displayInventory() {

        System.out.println("\nInventory:");

        for (Product product : inventory.values()) {
            System.out.println(product);
        }
    }

    // Main Method
    public static void main(String[] args) {

        InventoryManagementSystem ims =
                new InventoryManagementSystem();

        ims.addProduct(
                new Product(101, "Laptop", 10, 55000));

        ims.addProduct(
                new Product(102, "Mouse", 50, 500));

        ims.addProduct(
                new Product(103, "Keyboard", 30, 1200));

        ims.displayInventory();

        ims.updateProduct(102, 60, 550);

        ims.deleteProduct(103);

        ims.displayInventory();
    }
}