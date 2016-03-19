package naturalMergesort;

/**
 * Created by Pete Wilcox on 2/6/2016.
 * petercwilcox@gmail.com
 */
public class LinkedList<Item>
{

    private DoubleNode head;
    private DoubleNode tail;
    private int        size;

    private class DoubleNode
    {
        Item       item;
        DoubleNode ahead;
        DoubleNode back;

        // Calls the recursive helper function
        public String toString()
        {
            return "(" + toStringHelper() + ")";
        }

        // Recursive function to print the list to a string
        public String toStringHelper()
        {
            if (this.back == null)
            {
                return this.item.toString();
            }
            else
            {
                return this.item.toString() + ", " + this.back.toStringHelper();
            }
        }


    }

    // Constructor
    public LinkedList(Item i)
    {
        head = new DoubleNode();
        head.item = i;
        tail = head;
        size++;
    }

    // Default constructor
    public LinkedList()
    {
        size = 0;
    }

    // Add a node to the front of the list
    public void addToFront(Item i)
    {
        DoubleNode temp = new DoubleNode();
        temp.item = i;
        temp.back = head;
        if (size > 0)
        {
            head.ahead = temp;
        }
        else
        {
            tail = temp;
        }
        head = temp;
        size++;

        System.out.println("Added " + temp.item + " to front, size is now " + size + ".");
    }

    // Add a node to the back of the list
    public void addToBack(Item i)
    {
        DoubleNode temp = new DoubleNode();
        temp.item = i;

        if (size > 0)
        {
            temp.ahead = tail;
            tail.back = temp;
        }
        tail = temp;
        size++;
        System.out.println("Added " + temp.item + " to back, size is now " + size + ".");
    }

    // Return the head node
    public Item popFromFront()
    {
        if (size > 0)
        {
            DoubleNode temp = head;
            if (head.back != null)
            {
                head.back.ahead = null;
                head = head.back;
            }
            size--;
            System.out.println("Popped " + temp.item + " from front, size is now " + size + ".");
            return temp.item;
        }
        else
        {
            return null;
        }
    }

    // Return the tail node
    public Item popFromBack()
    {
        if (size > 0)
        {
            DoubleNode temp = tail;
            tail.ahead.back = null;
            tail = tail.ahead;
            size--;
            System.out.println("Popped " + temp.item + " from back, size is now " + size + ".");
            return temp.item;
        }
        else
        {
            return null;
        }
    }

    // Return true if the list is empty
    public boolean isEmpty()
    {
        return size == 0;
    }

    // Return the number of nodes
    public int getSize()
    {
        return size;
    }

    // Add a node before the nth node
    public void addBefore(Item item, int n)
    {
        if (n < 2)
        {
            // Adding a node before element 0 or 1 is the same as adding it to the front
            addToFront(item);
        }
        else if (n > size)
        {
            // Adding a node after the tail element is the same as adding it to the back
            addToBack(item);
        }
        else
        {
            DoubleNode temp = new DoubleNode();
            temp.item = item;
            DoubleNode current = head;
            for (int i = 1; i < n; i++)
            {
                current = current.back;
            }

            System.out.println("Adding " + temp.item.toString() + " before " + current.item.toString() + " and after " +
                               current.ahead.item.toString() + ".");
            temp.ahead = current.ahead;
            temp.back = current;
            current.ahead.back = temp;
            current.ahead = temp;

            size++;
        }

    }

    // Add a node after the nth node
    public void addAfter(Item item, int n)
    {
        if (n < 1)
        {
            // Adding after any element before the head one is the same as adding to the front
            addToFront(item);
        }
        else if (n >= size)
        {
            // Adding after the tail element is the same as adding to the back
            addToBack(item);
        }
        else
        {
            DoubleNode temp = new DoubleNode();
            temp.item = item;
            DoubleNode current = head;
            for (int i = 1; i < n; i++)
            {
                // Iterate through the list until you get to the nth element
                current = current.back;
            }

            System.out.println("Adding " + temp.item.toString() + " after " + current.item.toString() + " and before " +
                               current.back.item.toString() + ".");
            temp.ahead = current;
            current.back.ahead = temp;

            temp.back = current.back;
            current.back = temp;

            size++;
        }
    }

    // Removes the nth element
    public void remove(int n)
    {
        if (n > 0 && n <= size)
        {
            // Can't remove an element outside the list
            DoubleNode current = head;
            for (int i = 1; i < n; i++)
            {
                // Iterate through to the nth element
                current = current.back;
            }

            System.out.println("Removing " + current.item.toString() + ".");

            // Special conditions apply if the element to be removed is either the head or the tail
            if (head == current)
            {
                if (current.back != null)
                {
                    head = current.back;
                }
                else
                {
                    head = null;
                }
            }
            else if (tail == current)
            {
                current.ahead.back = null;
                tail = current.ahead;
            }
            else
            {
                current.ahead.back = current.back;
                current.back.ahead = current.ahead;
            }
            current = null;
            size--;


        }
    }

    // Move the nth node to the front
    public void moveToFront(int n)
    {
        if (n > 1 && n <= size)
        {
            DoubleNode current = head;
            for (int i = 1; i <= n; i++)
            {
                current = current.back;
            }

            System.out.println("Moving " + current.item.toString() + " to the front of the list.");

            if (current.back != null)
            {
                current.ahead.back = current.back;
                current.back.ahead = current.ahead;
            }
            else
            {
                current.ahead.back = null;
                tail = current.ahead;
            }

            current.back = head;
            current.ahead = null;
            head.ahead = current;
            head = current;
        }
    }

    // Move the nth node to the back
    public void moveToBack(int n)
    {
        if (n > 0 && n < size)
        {
            DoubleNode current = head;
            for (int i = 1; i <= n; i++)
            {
                current = current.back;
            }

            System.out.println("Moving " + current.item.toString() + " to the back of the list.");

            current.ahead.back = current.back;
            current.back.ahead = current.ahead;
            current.back = null;
            current.ahead = tail;
            tail.back = current;
            tail = current;
        }
    }

    // Wrapper function
    public String toString()
    {
        if (head == null)
        {
            return null;
        }
        else
        {
            return head.toString();
        }
    }

    // Natural linkedList method, will call helper methods to sort the list
    public void mergeSort()
    {
        // Index that moves through the list
        DoubleNode current = head;

        // Start and end references for the two sub-lists to be merged
        DoubleNode startFirst  = null;
        DoubleNode startSecond = null;
        DoubleNode endFirst    = null;
        DoubleNode endSecond   = null;

        // If merged stays false, that means the list is sorted
        boolean merged = true;

        while (merged == true)
        {
            merged = false;

            if (startFirst == null)
            {
                startFirst = current;
            }

            if (this.compareNode(current, current.back) > 0)
            {
                endFirst = current;
            }


            // If we've reached the end of the list, go back to the beginning
            if (current.back == null)
            {

                current = head;
            }
            else
            {
                current = current.back;
            }

        }
    }

    private LinkedList merge(DoubleNode startFirst, DoubleNode endFirst, DoubleNode startSecond, DoubleNode endSecond)
    {

    }


    private int compareNode(DoubleNode a, DoubleNode b)
    {
        if (a == null || b == null)
        {
            throw new NullPointerException();
        }
        else if (a.item instanceof Comparable == false || b.item instanceof Comparable == false)
        {
            throw new ClassCastException();
        }
        else
        {
            Comparable i = (Comparable) a.item;
            return i.compareTo(b.item);
        }
    }
}