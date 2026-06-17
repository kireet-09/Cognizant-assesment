import java.util.HashMap;

public class InventoryManager {

    private HashMap<Integer, Product> inventory;

    public InventoryManager() {
        inventory = new HashMap<>();
    }

    public void addProduct(Product product) {
        if (product == null || inventory.containsKey(product.getProductId())) {
            System.out.println("Error: Product ID already exists");
            return;
        }
        inventory.put(product.getProductId(), product);
        System.out.println("✓ Added");
    }

    public void updateProduct(int productId, String name, int quantity, double price) {
        if (quantity < 0 || price < 0) {
            System.out.println("Error: Invalid values");
            return;
        }
        Product p = inventory.get(productId);
        if (p != null) {
            p.setProductName(name);
            p.setQuantity(quantity);
            p.setPrice(price);
            System.out.println("✓ Updated");
        } else System.out.println("Error: Product not found");
    }

    public void deleteProduct(int productId) {
        if (inventory.remove(productId) != null) System.out.println("✓ Deleted");
        else System.out.println("Error: Product not found");
    }

    public void searchProduct(int productId) {
        Product p = inventory.get(productId);
        if (p != null) System.out.println(p);
        else System.out.println("Error: Not found");
    }

    public void displayProducts() {
        if (inventory.isEmpty()) { System.out.println("Empty"); return; }
        System.out.println("\n--- Products ---");
        for (Product p : inventory.values()) System.out.println(p);
    }

    public int getTotalProducts() {
        return inventory.size();
    }

    public double getTotalInventoryValue() {
        double total = 0;
        for (Product p : inventory.values()) total += p.getQuantity() * p.getPrice();
        return total;
    }

    public void displayInventorySummary() {
        System.out.println("Total: " + getTotalProducts() + " | Value: $" + String.format("%.2f", getTotalInventoryValue()));
    }
}
