import java.util.Arrays;
import java.util.Comparator;

public class EcommerceSearch {

    // Product Class
    static class Product {
        int productId;
        String productName;
        String category;

        Product(int productId, String productName, String category) {
            this.productId = productId;
            this.productName = productName;
            this.category = category;
        }

        @Override
        public String toString() {
            return "ID=" + productId +
                    ", Name=" + productName +
                    ", Category=" + category;
        }
    }

    // Linear Search
    public static Product linearSearch(Product[] products, String targetName) {

        for (Product product : products) {
            if (product.productName.equalsIgnoreCase(targetName)) {
                return product;
            }
        }
        return null;
    }

    // Binary Search
    public static Product binarySearch(Product[] products, String targetName) {

        int left = 0;
        int right = products.length - 1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            int comparison =
                    products[mid].productName.compareToIgnoreCase(targetName);

            if (comparison == 0) {
                return products[mid];
            }

            if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return null;
    }

    public static void main(String[] args) {

        Product[] products = {
                new Product(101, "Laptop", "Electronics"),
                new Product(102, "Mouse", "Accessories"),
                new Product(103, "Keyboard", "Accessories"),
                new Product(104, "Monitor", "Electronics"),
                new Product(105, "Headphones", "Audio")
        };

        String searchItem = "Monitor";

        // Linear Search
        Product result1 = linearSearch(products, searchItem);

        System.out.println("Linear Search Result:");
        System.out.println(result1);

        // Sort Array for Binary Search
        Arrays.sort(products,
                Comparator.comparing(p -> p.productName));

        Product result2 = binarySearch(products, searchItem);

        System.out.println("\nBinary Search Result:");
        System.out.println(result2);
    }
}