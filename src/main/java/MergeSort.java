import java.util.Arrays;

public class MergeSort {

    public static void testMergeSorted() {
        System.out.println("Test results for mergeSorted");
        int[] left = { 1, 3, 5, 23, 61, 62};
        int[] right = { 2, 4, 6 , 22};
        int[] merged = Utils.merge2SortedArrays(left, right);
        for (int num : merged) {
            System.out.println(num);
        }
    }

    // merge sort O(nlogn)
    // divide in half recursively til you get to one, which is inherently sorted.
    // Then climb back up that tree, merging as you go.
    public static int[] mergeSort(int[] inpArray) {
        int len = inpArray.length;
        // if the array is 1 or less, then it's already sorted, so return it. 
        if (len < 2) {
            return inpArray;
        }
        // create the two halves to be recursivley sorted
        int midI = len/2;
        int[] left = new int[midI];
        int[] right = new int[len-midI];
        //populate left half
        for (int i = 0; i < midI; ++i){
            left[i] = inpArray[i];
        }
        // populate right half  0 1 2 3 4
        for (int i = 0; i < (len-midI); ++i){
            right[i] = inpArray[i+midI];
        }
        return Utils.merge2SortedArrays(mergeSort(left), mergeSort(right));
    }

    static void timeAndVerify(int[] control, int[] inputArray) {
        long start1 = System.nanoTime();
        int[] results = mergeSort(inputArray);
        long end1 = System.nanoTime();
        boolean isAccurate = Arrays.equals(results, control);
        System.out.println("mergeSort:     "
                + (end1 - start1)
                + " accurate: "
                + isAccurate);
    }
    
}
