public class MergeSort {

    /**
     * Takes two already sorted arrays, combines them preserving
     * that order, and returns the results. Will not work with
     * unsorted arrays. Written for mergeSort, but may be used
     * in elsewhere.
     * 
     * @param presortedLeft must be sorted
     * @param presortedRight must be sorted
     * @return merged array
     */
    public static int[] mergeSorted(int[] presortedLeft, int[] presortedRight) {
        int leftLen = presortedLeft.length;
        int rightlen = presortedRight.length;
        int[] merged = new int[leftLen + rightlen];
        int i = 0, j = 0, k = 0;
        while (i < leftLen && j < rightlen) {
            if (presortedLeft[i] <= presortedRight[j]) {
                merged[k] = presortedLeft[i];
                ++i;
                ++k;
            } else {
                merged[k] = presortedRight[j];
                ++j;
                ++k;
            }
        }
        while (i < leftLen) {
            merged[k] = presortedLeft[i];
            ++i;
            ++k;
        }
        while (j < rightlen) { 
            merged[k] = presortedRight[j];
            ++j;
            ++k;
        }
        return merged;
    }

    public static void testMergeSorted() {
        System.out.println("Test results for mergeSorted");
        int[] left = { 1, 3, 5, 23, 61, 62};
        int[] right = { 2, 4, 6 , 22};
        int[] merged = mergeSorted(left, right);
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
        return mergeSorted(mergeSort(left), mergeSort(right));
        // todo: this is just to keep the compiler happy for now.
        // return inpArray;
    }

    static void mergeSortTimer(int[] testArray) {
        // int[] testArray = {2, 4, 6, 1, 3, 5};
        long start1 = System.nanoTime();
        testArray = mergeSort(testArray);
        long end1 = System.nanoTime();
        System.out.println("mergeSort:     " + (end1-start1));
        // System.out.println("Results of mergeSort: ");
        // for (int num : testArray){
        //     System.out.println(num);
        // }
    }
    
}
