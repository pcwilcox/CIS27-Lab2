package pq;

import java.util.Random;

/**
 * Created by Pete Wilcox on 3/20/2016.
 * petercwilcox@gmail.com
 */
public class PQTester
{
    public static void main(String[] args) {
        MaxHeap<Integer> heapTest = new MaxHeap<>();
        Random rand = new Random();

        for (int i = 0; i < 10; i++) {
            int j = rand.nextInt(100);
            System.out.println("Adding " + j);
            heapTest.insert(j);
            System.out.println(heapTest);
        }
    }
}
