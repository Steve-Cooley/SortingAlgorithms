import java.util.Arrays;

public class SelectionSort {

    // selection sort
    public static int[] selectionSort(int[] inputArray) {
        int len = inputArray.length;
        int min_idx;
        int temp;
        for (int i = 0; i < len - 1; ++i) {
            min_idx = i;
            for (int j = i + 1; j < len; ++j) {
                if (inputArray[j] < inputArray[min_idx]) {
                    min_idx = j;
                }
            }
            temp = inputArray[min_idx];
            inputArray[min_idx] = inputArray[i];
            inputArray[i] = temp;
        }
        return inputArray;
    }

    public static void timeAndVerify(int[] control, int[] inputArray) {
        long start1 = System.nanoTime();
        selectionSort(inputArray);
        long end1 = System.nanoTime();
        boolean isAccurate = Arrays.equals(inputArray, control);
        System.out.println("SelectionSort: "
                + (end1 - start1)
                + " accurate: "
                + isAccurate);
    }
}
