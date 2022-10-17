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
    public static int[] merge2SortedArrays(int[] presortedLeft, int[] presortedRight) {
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
}
