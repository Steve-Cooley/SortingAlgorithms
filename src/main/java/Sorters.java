import java.util.*;

public class Sorters {

    // shellsort (my favorite)

    // radix sort

    /*
     * Steve sort (turns out this is called "double selection sort" and saves about
     * 25% vs selection sort)
     * Just an idea I had to iterate  through the array, identifying the biggest and
     * smallest values, storing them in a couple of external arrays (or four
     * variables) with their values and index
     * locations, then moving them to the beginning and end (figure it out). Lots of
     * comparisons, not so many writes. This will shorten the search on both ends of
     * the array each iteration through, meaning that each search will get
     * progressively faster. This is similar to selection sort, but double ended.
     */

    private static void controlTimer(int[] control) {
        long controlStart = System.nanoTime();
        Arrays.sort(control);
        long controlStop = System.nanoTime();
        System.out.println("timsort:       " + (controlStop - controlStart));
    }

    public static void main(String[] args) {
        int len = 500_000;
        int intMax = 1_000_000;
        int[] randomArray = Utils.genRandArray(len, intMax);
        int[] control = Arrays.copyOf(randomArray, len);
        controlTimer(control);

//        InsertionSort.timeAndVerify(control, Arrays.copyOf(randomArray, len));

        // test Bogosort (don't use this method if values > 10 or so)
        // BogoSort.testBogosort();

        // test bubbleSort
//        BubbleSort.timeAndVerify(control, Arrays.copyOf(randomArray, len));

        // test mergeSorted

//        SelectionSort.timeAndVerify(control, Arrays.copyOf(randomArray, len));

        SteveSort2.timeAndVerify(control, Arrays.copyOf(randomArray, len));
        MergeSort.timeAndVerify(control, Arrays.copyOf(randomArray, len));
//        ShellSort.testSedgwick();
        ShellSort.timeAndVerify(control, Arrays.copyOf(randomArray, len));
        InsertionSort.timeAndVerify(control, Arrays.copyOf(randomArray, len));
    }
}
