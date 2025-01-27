package gr.projects.project02;

public class MaximumSumSubarray {
    public static int maxSubArray(int[] arr) {
        // αν ο πίνακας είναι άδειος, επιστρέφει 0 (δυνητικά κενός υποπίνακας)
        if (arr == null || arr.length == 0) return 0;

        // αρχικοποίηση
        int localMax = arr[0];
        int globalMax = arr[0];

        // επανάληψη στον πίνακα
        for (int i = 1; i < arr.length; i++) {
            // υπολογίζει το τοπικό μέγιστο
            localMax = Math.max(localMax + arr[i], arr[i]);
            // ενημερώνει το συνολικό μέγιστο
            globalMax = Math.max(globalMax, localMax);
        }

        return globalMax;
    }

    public static void main(String[] args) {
        // παράδειγμα πίνακα
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Maximum sum of subarray: " + maxSubArray(arr));
    }
}
