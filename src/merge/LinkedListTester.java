package merge;

import java.util.Random;

/**
 * Created by Pete Wilcox on 3/20/2016.
 * petercwilcox@gmail.com
 */
public class LinkedListTester
{
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            int j = rand.nextInt(50);
            list.push(j);
        }
        System.out.println(list);
        list.sort();
        System.out.println(list);
    }
}
