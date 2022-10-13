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
    
}
