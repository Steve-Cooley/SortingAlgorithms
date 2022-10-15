import java.util.Arrays;

public class InsertionSort {

    /**
     * Insertion sort
     * 
     * @param inputArray
     * @return
     */
    public static int[] insertionSort(int[] inputArray) {
        int length = inputArray.length;
        if (length < 2) {
            return inputArray;
        }
        // outer loop (i) 1.) starts at inputArray[1], copies that value into temp,
        // executes inner loop, repeats until it reaches end of inputArray.
        for (int i = 1; i < length; ++i) {
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

    static void timeAndVerify(int[] control, int[] inputArray) {
        long start1 = System.nanoTime();
        int[] results = insertionSort(inputArray);
        long end1 = System.nanoTime();
        boolean isAccurate = Arrays.equals(inputArray, control);
        System.out.println("insertionSort: "
                + (end1 - start1)
                + " accurate: "
                + isAccurate);
    }

}
