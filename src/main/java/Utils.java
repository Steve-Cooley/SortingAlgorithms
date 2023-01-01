import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Utils {

    public static int[] genRandArray(int len, int max) {
        Random rand = new Random();
        List<Integer> numberList = new ArrayList<>();
        for (int i = 0; i < len; ++i) {
            numberList.add(rand.nextInt(max));
        }
        return numberList.stream().mapToInt(i -> i).toArray();
    }

    public static void testGenRandomArray( ) {
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
     * elsewhere.
     *
     * @param presortedLeft must be sorted
     * @param presortedRight must be sorted
     * @return merged array
     */
    public static int[] merge2SortedArrays(int[] presortedLeft, int[] presortedRight) {
        int leftLen = presortedLeft.length;
        int rightlen = presortedRight.length;
        int[] merged = new int[leftLen + rightlen];
        int left_i = 0, right_i = 0, merged_i = 0;
        while (left_i < leftLen && right_i < rightlen) {
            if (presortedLeft[left_i] <= presortedRight[right_i]) {
                merged[merged_i] = presortedLeft[left_i];
                ++left_i;
                ++merged_i;
            } else {
                merged[merged_i] = presortedRight[right_i];
                ++right_i;
                ++merged_i;
            }
        }
        while (left_i < leftLen) {
            merged[merged_i] = presortedLeft[left_i];
            ++left_i;
            ++merged_i;
        }
        while (right_i < rightlen) {
            merged[merged_i] = presortedRight[right_i];
            ++right_i;
            ++merged_i;
        }
        return merged;
    }
}
