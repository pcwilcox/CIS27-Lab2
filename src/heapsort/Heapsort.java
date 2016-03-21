package heapsort;

import java.util.Random;

/**
 * Created by Pete Wilcox on 3/20/2016.
 * petercwilcox@gmail.com
 */
public class Heapsort
{
    public static void main(String[] args) {
        MaxHeap test = new MaxHeap();
        Random rand = new Random();
        for (int i = 0; i < 100; i++) {
            int j = rand.nextInt(500);
            System.out.println("Adding " + j);
            test.insert(j);
        }

        System.out.println(test);
        System.out.println();
        test.heapSort();
        System.out.println(test);

    }
}
