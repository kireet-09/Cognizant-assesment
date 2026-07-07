public class BinarySearch {
    /**
     * Binary search works on a sorted array and repeatedly divides the search interval in half.
     * Time Complexity: O(log n)
     * Best case: O(1) when the middle element is the target.
     * Average case: O(log n) since the range is reduced by half each step.
     * Worst case: O(log n) when the target is found after several halvings or is not present.
     *
     * Assumes the products array is sorted by productId.
     */
    public static Product search(Product[] products, int key) {
        int left = 0;
        int right = products.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midId = products[mid].getProductId();

            if (midId == key) {
                return products[mid];
            }
            if (midId < key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return null;
    }
}