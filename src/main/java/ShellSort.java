import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;

/*
I can't help but think that implementations and descriptions
of this algorithm that I've read online cannot be right.
What I mean is that it reduces the gap, swapping items until
it eventually gets down to 1, and then it automatically becomes
insertion sort. However, insertion sort is a little more
complicated than simply "if i is greater than i+1, swap them
and increment i"; it has an inner loop that once activated
continually pushes the item to the left as long as it is smaller
than the item to its left. Maybe that those descriptions work as
long as your gap sequence is n/2, or something like that, but
I can't see how it can possibly be 100% accurate unless it
finishes with the full-fat insertion sort (or something similar)
I'll try it first,and if it doesn't completely sort everything
perfectly, I'll make it automatically call on insertionSort
when the gap = 1.
 */

public class ShellSort {
    /*
    [701, 301, 132, 57, 23, 10, 4, 1] Ciura's gap sequence
    a(n) = 4^(n+1) + 3*2^n + 1.  is another sequence that is easier to generate, but still pretty good O(n^(4/3))
    if scaling up.
     */


    /**
     * Takes in the length of the array to be sorted, returns
     * the sedgwick sequence for optimal sorting of an array
     * of that length.
     *
     * @param arrsize size of array
     * @return sedgwick sequence
     */
    public static int[] sedgwickSequence(int arrsize) {
        int max = arrsize / 3;
        ArrayList<Integer> outpl = new ArrayList<>();
        int k = 1;
        int curr = 0;
        while (curr <= max) {
            curr = (int) (Math.pow(4, k) + 3 * Math.pow(2, k - 1) + 1);
            // Might perform better without this if statement,
            // meaning with the outpl.add line outside of if.
            // worth experimenting, but this is my reading of
            // the wikipedia article.
            if (curr <= max) {
                outpl.add(0, curr);
            }
            ++k;
        }
        //The formula given at wikipedia never returns a 1, so I'm doing so manually
        //outpl.add(1);
        // The following line required me to use maven and apache commons for the first time.
        // now that I've done all that, I think I should have just returned an arraylist. XD
        Integer[] wArr = outpl.toArray(new Integer[outpl.size()]);
        return ArrayUtils.toPrimitive(wArr);

    }

    public static void testSedgwick() {
        System.out.println("testing sedgwick");
        int[] sedg = sedgwickSequence(844);
        for (int num : sedg) {
            System.out.println(num);
        }
    }

    public static int[] shellSort(int[] inputArray) {
        int len = inputArray.length;
        int[] seq = sedgwickSequence(len);
        // for each gap
        for (int gap: seq){
            int lowI = 0;
            int highI = lowI + gap;
            // while gap is in range
            while (highI < len) {
                if (inputArray[lowI] > inputArray[highI]) {
                    // swap low with high
                    int temp = inputArray[lowI];
                    inputArray[lowI] = inputArray[highI];
                    inputArray[highI] = temp;
                }
                ++lowI;
                ++highI;
            }
        }
        // Insertion sort should always run for the final iteration
        return InsertionSort.insertionSort(inputArray);
    }


    public static void timeAndVerify(int[] control, int[] inputArray) {
        long start1 = System.nanoTime();
        int[] results = shellSort(inputArray);
        long end1 = System.nanoTime();
        boolean isAccurate = Arrays.equals(results, control);
        System.out.println("shellSort:     "
                + (end1 - start1)
                + " accurate: "
                + isAccurate);
    }
}
