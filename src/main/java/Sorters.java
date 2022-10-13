import java.util.*;

public class Sorters {


    

    // ************* sorters and tests ***********************

    

    // shellsort (my favorite)

    

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

    public static void test() {
        System.out.println("Hello World! ");
        int[] testArray = { 1, 2, 3 };
        System.out.println("the length of the test array is: " + testArray.length);
    }

    public static void main(String[] args) {
        int[] randomArray = Utils.genRandArray(100_000, 1000);
        InsertionSort.testInsertionSort(Arrays.copyOf(randomArray, randomArray.length), true);
        // test Bogosort
        // testBogosort();
        // test bubbleSort
        // randomArray = genRandArray(1000, 1000);
        BubbleSort.testBubbleSort(Arrays.copyOf(randomArray, randomArray.length), true);
        // testGenRandomArray();
        // test mergeSorted
        // testMergeSorted();
        MergeSort.testMergeSort(Arrays.copyOf(randomArray, randomArray.length));
    }


}
