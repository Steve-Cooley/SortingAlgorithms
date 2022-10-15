import java.util.*;

public class Sorters {

    // shellsort (my favorite)

    // radix sort

    /*
     * Steve sort (turns out this is called "double selection sort" and saves about
     * 25% vs selection sort)
     * Just an idea I had to iterate through the array, identifying the biggest and
     * smallest values, storing them in a couple of external arrays (or four
     * variables) with their values and index
     * locations, then moving them to the beginning and end (figure it out). Lots of
     * comparisons, not so many writes. This will shorten the search on both ends of
     * the array each iteration through, meaning that each search will get
     * progressively faster. This is similar to selection sort, but double ended.
     */

    /*
     * Steve Sort 2:
     * I've noticed that insertion sort is very fast for small arrays
     * (~100). What if I used a recursive algorithm like mergeSort
     * which breaks down the array into smaller bits until the array
     * size is about 100 then switch to insertion sort? This should
     * combine the strength of merge sort for large arrays, and the
     * speed of insertion for short arrays. 100 might not be the optimal
     * switch, so it might be worth experimenting with that number.
     */

    private static void controlTimer(int[] control) {
        long controlStart = System.nanoTime();
        Arrays.sort(control);
        long controlStop = System.nanoTime();
        System.out.println("control:       " + (controlStop - controlStart));
    }

    public static void main(String[] args) {
        int len = 100;
        int intMax = 1_000;
        int[] randomArray = Utils.genRandArray(len, intMax);
        int[] control = Arrays.copyOf(randomArray, len);
        controlTimer(control);

        InsertionSort.timeAndVerify(control, Arrays.copyOf(randomArray, len));

        // test Bogosort (don't use this method if values > 10 or so)
        // BogoSort.testBogosort();

        // test bubbleSort
        BubbleSort.bubbleSortTimer(Arrays.copyOf(randomArray, len), true);

        // test mergeSorted
        // testMergeSorted();
        MergeSort.mergeSortTimer(Arrays.copyOf(randomArray, len));

        SelectionSort.timeAndVerify(control, Arrays.copyOf(randomArray, len));
    }
}
