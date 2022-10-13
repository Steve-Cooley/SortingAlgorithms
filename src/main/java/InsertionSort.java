public class InsertionSort {

    // Insertion sort
    public static int[] insertionSort(int[] inputArray) {
        if (inputArray.length < 2) {
            return inputArray;
        }
        // outer loop (i) 1.) starts at inputArray[1], copies that value into temp,
        // executes inner loop, repeats until it reaches end of inputArray.
        for (int i = 1; i < inputArray.length; ++i) {
            int temp = inputArray[i];
            // inner loop (j) starts at i-1, moves anything smaller than inputArray[i]
            // to the right until it reaches the beginning of the array, or
            // it reaches a number smaller than the one in the temp var.
            int j = i - 1;
            while (j >= 0 && inputArray[j] > temp) {
                inputArray[j + 1] = inputArray[j];
                j--;
            }
            inputArray[j + 1] = temp; // This must be j+1 b/c j is decremented at end of j loop
        }
        return inputArray;
    }

    private static void testInsertionSort() {
        int[] testArray = { 2, 4, 6, 1, 3, 5 };
        // This line actually sorts the testArray in place, which is not what I
        // intended. I don't really understand why it's doing that or how to stop it.
        int[] results = insertionSort(testArray);
        System.out.println("Printing results for insertionSort");
        for (int i : results) {
            System.out.println(i);
        }
    }

    private static void testInsertionSort(int[] testArray) {
        // This line actually sorts the testArray in place, which is not what I
        // intended. I don't really understand why it's doing that or how to stop it.
        // Pass by value is the reason. You have to copy the array b/c it's an object.
        long start1 = System.nanoTime();
        int[] results = insertionSort(testArray);
        long end1 = System.nanoTime();
        System.out.println("Printing results for insertionSort");
        System.out.println("elapsed time in nanoseconds: " + (end1 - start1));
    }

    static void testInsertionSort(int[] testArray, boolean compact) {
        // This line actually sorts the testArray in place, which is not what I
        // intended. I don't really understand why it's doing that or how to stop it.
        long start1 = System.nanoTime();
        int[] results = insertionSort(testArray);
        long end1 = System.nanoTime();
        System.out.println("insertionSort: " + (end1 - start1));
    }
    
}
