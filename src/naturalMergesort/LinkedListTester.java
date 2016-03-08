
package naturalMergesort;

/**
 * Created by Pete Wilcox on 2/7/2016.
 */
public class LinkedListTester
{
    public static void main(String[] args)
    {
        LinkedList<String> sort = new LinkedList<>();
        sort.addToBack("S");
        sort.addToBack("O");
        sort.addToBack("R");
        sort.addToBack("T");
        sort.addToBack("E");
        sort.addToBack("X");
        sort.addToBack("A");
        sort.addToBack("M");
        sort.addToBack("P");
        sort.addToBack("L");
        sort.addToBack("E");

        System.out.println("Made a list: " + sort);
        sort.mergeSort();

        System.out.println("It should be sorted: " + sort);
    }
}

/* Program output:
Made a list.
Currently the list is empty: true
Added Dick to front, size is now 1.
Added Harry to back, size is now 2.
Added Tom to front, size is now 3.
The list is currently (Tom, Dick, Harry)
Let's get the size, it's 3.
Adding Jane before Dick and after Tom.
Size is now 4.
Adding Vivian after Dick and before Harry.
Size is now 5.
The list is currently (Tom, Jane, Dick, Vivian, Harry)
Removing Dick.
Moving Vivian to the back of the list.
The list is currently (Tom, Jane, Harry, Vivian)
Moving Harry to the front of the list.
The list is currently (Harry, Tom, Jane, Vivian)
Popped Harry from front, size is now 3.
Popped Vivian from back, size is now 2.
Popped Tom from front, size is now 1.
The list is currently (Jane)

Process finished with exit code 0
 */