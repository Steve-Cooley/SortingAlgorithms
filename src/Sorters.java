import java.util.*;
import java.util.stream.Collectors;
//import com.google.common.primitives.Ints;

public class Sorters {

    // **************** utils *****************
    // I thought a few simple utilities would be nice to avoid redundancy

    //util: generate random array of ints
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


    // merge sort O(nlogn)
    // divide recursively til you get to one, which is inherently sorted. Then climb
    // back up that
    // tree, merging as you go.
    public static int[] mergeSort(int[] inpArray) {
        return inpArray;
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
//                    System.out.println(String.format("swapped index %d with %d.", i, i + 1));
                }
                ++forLoops;
            }
            whileLoops++;
//            len--; // This might be fun to see what kind of speedup this would produce
        }
        System.out.println(String.format("while loops: %d for loops %d: ", whileLoops, forLoops));
        return inpArray;
    }

    public static void testBubbleSort() {
        System.out.println("These are the results of bubbleSort:");
//        int[] testArray = {2, 4, 6, 1, 3, 5};
        int[] testArray = genRandArray(50, 5000);
        int len = testArray.length;
        //System.out.println(String.format("the length of the test array is: %d", len));
        int[] bubbleResults = bubbleSort(testArray);
        for (int num : bubbleResults) {
            System.out.println(num);
        }
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
        int[] testArray = {2, 4, 6, 1, 3, 5};
        int[] presorted = {1, 2, 3, 4, 5, 6};
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
    // progressively faster.

    // Insertion sort
    public static int[] insertionSort(int[] inputArray) {
        // For some reason, I couldn't get this to run with an inner "for-loop" with
        // a break rather than an inner while loop.
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
        int[] testArray = {2, 4, 6, 1, 3, 5};
        // This line actually sorts the testArray in place, which is not what I
        // intended. I don't really understand why it's doing that or how to stop it.
        int[] results = insertionSort(testArray);
        System.out.println("Printing results for insertionSort");
        for (int i : results) {
            System.out.println(i);
        }
    }

    public static void test() {
        System.out.println("Hello World! ");
        int[] testArray = {1, 2, 3};
        System.out.println("the length of the test array is: " + testArray.length);
    }

    public static void main(String[] args) {
        // test();
//        testInsertionSort();
        // test Bogosort
//        testBogosort();
        // test bubbleSort
        testBubbleSort();
//        testGenRandomArray();
    }

}
