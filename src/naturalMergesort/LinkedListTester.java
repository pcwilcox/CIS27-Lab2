
package naturalMergesort;

/**
 * Created by Pete Wilcox on 2/7/2016.
 */
public class LinkedListTester
{
    public static void main(String[] args)
    {
        LinkedList list = new LinkedList();
        System.out.println("Made a list.");

        System.out.println("Currently the list is empty: " + list.isEmpty());
        list.addToFront("Dick");
        list.addToBack("Harry");
        list.addToFront("Tom");
        System.out.println("The list is currently " + list.toString());

        System.out.println("Let's get the size, it's " + list.getSize() + ".");

        list.addBefore("Jane", 2);
        System.out.println("Size is now " + list.getSize() + ".");

        list.addAfter("Vivian", 3);
        System.out.println("Size is now " + list.getSize() + ".");

        System.out.println("The list is currently " + list.toString());
        list.remove(3);

        list.moveToBack(2);
        System.out.println("The list is currently " + list.toString());
        list.moveToFront(2);
        System.out.println("The list is currently " + list.toString());
        list.popFromFront();
        list.popFromBack();
        list.popFromFront();
        System.out.println("The list is currently " + list.toString());
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