import java.util.Arrays;

public class BubbleSort {

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
                }
                ++forLoops;
            }
            whileLoops++;
            // len--; // This might be fun to see what kind of speedup this would produce
        }
        // System.out.println(String.format("while loops: %d for loops %d: ",
        // whileLoops, forLoops));
        return inpArray;
    }

    public static void timeAndVerify(int[] control, int[] inputArray) {
        int len = inputArray.length;
        long start1 = System.nanoTime();
        int[] bubbleResults = bubbleSort(inputArray);
        long end1 = System.nanoTime();
        boolean isAccurate = Arrays.equals(inputArray, control);
        System.out.println("bubbleSort:    "
                + (end1 - start1) + " accurate: "
                + isAccurate );
    
    }
    
}
