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
                    // System.out.println(String.format("swapped index %d with %d.", i, i + 1));
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

    public static void testBubbleSort() {
        System.out.println("These are the results of bubbleSort:");
        // int[] testArray = {2, 4, 6, 1, 3, 5};
        int[] testArray = Utils.genRandArray(50, 5000);
        int[] bubbleResults = bubbleSort(testArray);
        for (int num : bubbleResults) {
            System.out.println(num);
        }
    }

    public static void testBubbleSort(int[] testArray) {
        int len = testArray.length;
        System.out.println("These are the results of bubbleSort:");
        System.out.println(String.format("Test length = %d", len));
        long start1 = System.nanoTime();
        int[] bubbleResults = bubbleSort(testArray);
        long end1 = System.nanoTime();
        System.out.println("elapsed time in nanoseconds: " + (end1 - start1));
        // for (int num : bubbleResults) {
        // System.out.println(num);
        // }
    }

    public static void testBubbleSort(int[] testArray, boolean compact) {
        int len = testArray.length;
        long start1 = System.nanoTime();
        int[] bubbleResults = bubbleSort(testArray);
        long end1 = System.nanoTime();
        System.out.println("bubbleSort:    " + (end1 - start1));
    
    }
    
}
