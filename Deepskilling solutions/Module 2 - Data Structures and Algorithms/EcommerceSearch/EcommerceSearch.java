public class EcommerceSearch {
    public static void main(String[] args) {
        Product[] products = {
            new Product(101, "Wireless Mouse", "Electronics"),
            new Product(203, "Running Shoes", "Footwear"),
            new Product(152, "Coffee Maker", "Home Appliances"),
            new Product(187, "Bluetooth Speaker", "Electronics"),
            new Product(229, "Notebook", "Stationery")
        };

        int searchId = 187;

        System.out.println("Product searched: Product ID = " + searchId);

        Product linearResult = LinearSearch.search(products, searchId);
        System.out.println("Linear Search result: " + (linearResult != null ? linearResult : "Product not found"));

        // For binary search, products should be sorted by productId.
        Product[] sortedProducts = {
            new Product(101, "Wireless Mouse", "Electronics"),
            new Product(152, "Coffee Maker", "Home Appliances"),
            new Product(187, "Bluetooth Speaker", "Electronics"),
            new Product(203, "Running Shoes", "Footwear"),
            new Product(229, "Notebook", "Stationery")
        };

        Product binaryResult = BinarySearch.search(sortedProducts, searchId);
        System.out.println("Binary Search result: " + (binaryResult != null ? binaryResult : "Product not found"));

        System.out.println("Comparison of both algorithms:");
        System.out.println("- Linear Search time complexity: O(n)");
        System.out.println("- Binary Search time complexity: O(log n)");
        System.out.println("- Linear search checks each item sequentially.");
        System.out.println("- Binary search requires sorted data and divides search range by half.");
    }
}