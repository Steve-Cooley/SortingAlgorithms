import java.util.*;
import java.util.stream.Collectors;

public class Sorters {

    // **************** utils *****************
    // I thought a few simple utilities would be nice to avoid redundancy

    // util: generate random array of ints
    public static int[] genRandArray(int len, int max) {
        Random rand = new Random();
        List<Integer> numberList = new ArrayList<>();
        for (int i = 0; i < len; ++i) {
            numberList.add(rand.nextInt(max));
        }
        return numberList.stream().mapToInt(i -> i).toArray();
    }

    public static void testGenRandomArray() {
        System.out.println("Testing genrandarray");
        int[] test = genRandArray(6, 6);
        for (int num : test) {
            System.out.println(num);
        }
    }

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

    // ************* sorters and tests ***********************

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

    private static void testMergeSort(int[] testArray) {
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

    // bubble sort
    public static int[] bubbleSort(int[] inpArray) {
        boolean recentSwap = true;
        int forLoops = 0;
        int whileLoops = 0;
        int len = inpArray.length;
        while (recentSwap) {
            recentSwap = false;
            for (int i = 0; i < len - 1; ++i) { // notice the < symbol here to avoid OOB
                if (inpArray[i] > inpArray[i + 1]) {
                    recentSwap = true;
                    int temp = inpArray[i];
                    inpArray[i] = inpArray[i + 1];
                    inpArray[i + 1] = temp;
                    // System.out.println(String.format("swapped index %d with %d.", i, i + 1));
                }
                ++forLoops;
            }
            whileLoops++;
            // len--; // This might be fun to see what kind of speedup this would produce
        }
        // System.out.println(String.format("while loops: %d for loops %d: ",
        // whileLoops, forLoops));
        return inpArray;
    }

    public static void testBubbleSort() {
        System.out.println("These are the results of bubbleSort:");
        // int[] testArray = {2, 4, 6, 1, 3, 5};
        int[] testArray = genRandArray(50, 5000);
        int[] bubbleResults = bubbleSort(testArray);
        for (int num : bubbleResults) {
            System.out.println(num);
        }
    }

    public static void testBubbleSort(int[] testArray) {
        int len = testArray.length;
        System.out.println("These are the results of bubbleSort:");
        System.out.println(String.format("Test length = %d", len));
        long start1 = System.nanoTime();
        int[] bubbleResults = bubbleSort(testArray);
        long end1 = System.nanoTime();
        System.out.println("elapsed time in nanoseconds: " + (end1 - start1));
        // for (int num : bubbleResults) {
        // System.out.println(num);
        // }
    }

    public static void testBubbleSort(int[] testArray, boolean compact) {
        int len = testArray.length;
        long start1 = System.nanoTime();
        int[] bubbleResults = bubbleSort(testArray);
        long end1 = System.nanoTime();
        System.out.println("bubbleSort:    " + (end1 - start1));

    }

    // shellsort (my favorite)

    // bogosort The preSortedArray var is there to have something to test against to
    // see if it is sorted or not. int to Integer, array to List conversions
    // are necessary for shuffling. I'm sure this has some overhead, but should
    // be nothing compared to this awful algorithm.
    public static int[] bogosort(int[] inpArray, int[] preSorted) {
        // convert to Integer List
        List<Integer> ul = Arrays.stream(inpArray).boxed().collect(Collectors.toList());
        List<Integer> sl = Arrays.stream(preSorted).boxed().collect(Collectors.toList());
        int counter = 0;
        while (!ul.equals(sl)) {
            // shake em up, shake em up, shake em up , shake em!
            Collections.shuffle(ul);
            counter++;
        }
        System.out.println(String.format("Shuffled %d times.", counter));
        // convert back to int[]
        return ul.stream().mapToInt(i -> i).toArray();
    }

    private static void testBogosort() {
        int[] testArray = { 2, 4, 6, 1, 3, 5 };
        int[] presorted = { 1, 2, 3, 4, 5, 6 };
        int[] bogoresults = bogosort(testArray, presorted);
        System.out.println("These are the results of bogosort: ");
        for (int i : bogoresults) {
            System.out.println(i);
        }
    }

    // selection sort

    // radix sort

    // Steve sort
    // Just an idea I had to iterate through the array, identifying the biggest and
    // smallest values, storing them in a couple of external arrays (or four
    // variables) with their values and index
    // locations, then moving them to the beginning and end (figure it out). Lots of
    // comparisons, not so many writes. This will shorten the search on both ends of
    // the array each iteration through, meaning that each search will get
    // progressively faster. This is similar to selection sort, but double ended.

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

    private static void testInsertionSort(int[] testArray, boolean compact) {
        // This line actually sorts the testArray in place, which is not what I
        // intended. I don't really understand why it's doing that or how to stop it.
        long start1 = System.nanoTime();
        int[] results = insertionSort(testArray);
        long end1 = System.nanoTime();
        System.out.println("insertionSort: " + (end1 - start1));
    }

    public static void test() {
        System.out.println("Hello World! ");
        int[] testArray = { 1, 2, 3 };
        System.out.println("the length of the test array is: " + testArray.length);
    }

    public static void main(String[] args) {
        int[] randomArray = genRandArray(100_000, 1000);
        testInsertionSort(Arrays.copyOf(randomArray, randomArray.length), true);
        // test Bogosort
        // testBogosort();
        // test bubbleSort
        // randomArray = genRandArray(1000, 1000);
        testBubbleSort(Arrays.copyOf(randomArray, randomArray.length), true);
        // testGenRandomArray();
        // test mergeSorted
        // testMergeSorted();
        testMergeSort(Arrays.copyOf(randomArray, randomArray.length));
    }


}
