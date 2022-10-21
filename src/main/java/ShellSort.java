import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;

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
     * @param arrsize
     * @return
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
                outpl.add(curr);
            }
            ++k;
        }
        // The following line required me to use maven and apache commons for the first time.
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
}
