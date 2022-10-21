import java.util.Arrays;

public class SteveSort2 {
    /*
     * Steve Sort 2:
     * I've noticed that insertion sort is very fast for small arrays
     * (~100). What if I used a recursive algorithm like mergeSort
     * which breaks down the array into smaller bits until the array
     * size is about 100 then switch to insertion sort? This should
     * combine the strength of merge sort for large arrays, and  the
     * speed of insertion for short arrays. 100 might not be the optimal
     * switch, so it might be worth experimenting with that number.
     * I think 50 is closer to optimum.
     *
     * Okay, so this scales well, meaning it works better the larger
     * the array.  It's still slow compared to the
     * sort provided by the java library, but it's pretty fast!
     * I've messed around with the threshold number below which
     * it switches to insertion sort, and it seems like 100 is a good
     * number for that, at least on my computer.  My testing puts it
     * about twice as fast as vanilla mergeSort.
     *
     * I just noticed that it seems like java's sort algorithm seems
     * to be multithreaded, so this isn't a fair fight.  My algorithm
     * may be just as fast as Java's Arrays.sorted(). Maybe I can
     * figure out how to multi-thread my algorithm and compete on a
     * level playing field.
     */
    public static int[] steveSort2(int[] inpArray) {
       int len = inpArray.length;
        // use insertion sort for arrays 100 and less. Seems like a sweet spot for large arrays,
        // at least on my computer.
        if (len < 101) {
            return InsertionSort.insertionSort(inpArray);
        }
        // create the two halves to be recursively sorted
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
        return Utils.merge2SortedArrays(steveSort2(left), steveSort2(right));
    }

        static void timeAndVerify(int[] control, int[] inputArray) {
        long start1 = System.nanoTime();
        int[] results = steveSort2(inputArray);
        long end1 = System.nanoTime();
        boolean isAccurate = Arrays.equals(results, control);
        System.out.println("steveSort2:    "
                + (end1 - start1)
                + " accurate: "
                + isAccurate);
    }
}
