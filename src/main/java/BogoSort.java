import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BogoSort {

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

    static void testBogosort() {
        int[] testArray = { 2, 4, 6, 1, 3, 5 };
        int[] presorted = { 1, 2, 3, 4, 5, 6 };
        int[] bogoresults = bogosort(testArray, presorted);
        System.out.println("These are the results of bogosort: ");
        for (int i : bogoresults) {
            System.out.println(i);
        }
    }
    
}
