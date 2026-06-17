public class LinearSearch {
    /**
     * Linear search scans each element one by one until the target is found.
     * Time Complexity: O(n)
     * Best case: O(1) when the item is at the first position.
     * Average case: O(n/2) which is O(n).
     * Worst case: O(n) when the item is at the end or not present.
     */
    public static Product search(Product[] products, int key) {
        for (Product product : products) {
            if (product.getProductId() == key) {
                return product;
            }
        }
        return null;
    }
}