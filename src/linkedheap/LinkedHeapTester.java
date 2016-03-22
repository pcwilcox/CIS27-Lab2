package linkedheap;

import java.util.Random;

/**
 * Created by Pete Wilcox on 3/21/2016.
 * petercwilcox@gmail.com
 */
public class LinkedHeapTester
{
    public static void main(String[] args)
    {
        LinkedHeap<Integer> link = new LinkedHeap<>();

        Random rand = new Random();
        for (int i = 0; i < 10; i++)
        {
            int j = rand.nextInt(100);
            System.out.println(j);
            link.insert(j);
        }
        System.out.println(link);
    }
}
