package merge;

/**
 * Created by Pete Wilcox on 3/20/2016.
 * petercwilcox@gmail.com
 */
public class LinkedList<Item>
{
    // Basic linked list used to show natural mergesort
    private Node head;
    private int  size;

    private class Node
            implements Comparable<Node>
    {
        Node next;
        Item item;

        // Inner class needs to implement compareTo or explicitly be a comparable type like int
        public int compareTo(Node n)
        {
            if (n == null)
            {
                throw new NullPointerException();

            }
            else
            {
                if (item instanceof Comparable && n.item instanceof Comparable)
                {
                    Comparable i = (Comparable) item;
                    Comparable o = (Comparable) n.item;
                    return i.compareTo(o);
                }
                else
                {
                    throw new ClassCastException();
                }
            }
        }
    }

    public LinkedList()
    {
        size = 0;
    }

    public LinkedList(Item i)
    {
        head = new Node();
        head.item = i;
        size = 1;
    }

    // Add item to front
    public void push(Item i)
    {
        Node temp = new Node();
        temp.item = i;
        temp.next = head;
        head = temp;
        size++;
    }

    // Remove item from front
    public Item pop()
    {
        Node temp = head;
        head = head.next;
        size--;
        return temp.item;
    }

    public boolean isEmpty()
    {
        return size == 0;
    }

    public int size()
    {
        return size;
    }

    // Calls the recursive sort() method
    public void mergeSort()
    {
        head = sort(head);
    }

    // Recursively: find first natural set, second natural set, merge(merge(first, second), remainder)
    private Node sort(Node n)
    {
        // Single node in list, return it - this also covers if n is null
        if (n.next == null)
        {
            return n;
        }

        Node first = n;
        n = getEnd(n);

        Node second = n.next;

        // This catches odd numbers of natural lists - if there's only a first and no second it'll stop here
        if (second == null)
        {
            return first;
        }

        n.next = null;

        if (second.next != null)
        {
            n = getEnd(second);

            // Save the tail
            Node tail = n.next;

            n.next = null;

            // Merge the first two, then merge that with whatever's left
            return merge(merge(first, second), sort(tail));
        }
        else
        {
            // If there's no tail, just merge the two
            return merge(first, second);
        }
    }

    private Node merge(Node first, Node second)
    {
        // Make a dummy head, we return the .next reference
        Node newHead = new Node();

        // Iterator for merged list
        Node current = newHead;

        // Loop continues while the two lists still have elements
        while (first != null && second != null)
        {
            // <1 so that it remains stable
            if (first.compareTo(second) < 1)
            {
                current.next = first;
                first = first.next;
            }
            else
            {
                current.next = second;
                second = second.next;
            }
            current = current.next;
        }

        // Once one sublist is empty, attach the remainder of the other
        if (first == null)
        {
            current.next = second;
        }
        else
        {
            current.next = first;
        }

        // Return the merged list
        return newHead.next;
    }

    // This method finds the end of a naturally-ordered list
    private Node getEnd(Node n)
    {
        if (n == null)
        {
            return null;
        }

        Node end = n;

        while (end.next != null && end.compareTo(end.next) < 1)
        {
            end = end.next;
        }

        return end;
    }

    // Simple method to concatenate every element to a string
    public String toString()
    {
        Node   current = head;
        String out     = "";
        while (current != null)
        {
            out = out + " " + current.item;
            current = current.next;
        }
        return out;
    }
}
