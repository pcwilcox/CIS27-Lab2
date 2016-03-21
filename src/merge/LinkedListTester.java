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
        for (int i = 0; i < 25; i++) {
            int j = rand.nextInt(50);
            list.push(j);
        }
        System.out.println(list);
        list.mergeSort();
        System.out.println(list);
    }
}

/* Program output:
 48 20 29 41 45 47 11 38 19 29 36 38 8 14 43 36 2 24 40 46 45 28 17 29 21
 2 8 11 14 17 19 20 21 24 28 29 29 29 36 36 38 38 40 41 43 45 45 46 47 48

 Actual merge trace would look something like:
 48 20-29-41-45-47 11-38 19-29-36-38 8-14-43 36 2-24-40-46 45 28 17-29 21
 20-29-41-45-47-48 11-19-29-36-38-38 8-14-36-43 2-24-40-45-46 17-28-29 21
 11-19-20-29-29-36-38-38-41-45-47-48 2-8-14-24-36-40-43-45-46 17-21-28-29
 2-8-11-14-19-20-24-29-29-36-36-38-38-40-41-43-45-45-46-47-48 17-21-28-29
 2-8-11-14-17-19-20-21-24-28-29-29-29-36-36-38-38-40-41-43-45-45-46-47-48
 */