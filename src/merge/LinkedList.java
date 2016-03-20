package merge;

/**
 * Created by Pete Wilcox on 3/20/2016.
 * petercwilcox@gmail.com
 */
public class LinkedList<Item>
{
    private Node head;
    private int  size;

    private class Node
            implements Comparable<Node>
    {
        Node next;
        Item item;

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

    public void push(Item i)
    {
        Node temp = new Node();
        temp.item = i;
        temp.next = head;
        head = temp;
        size++;
    }

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

    private Node getEnd(Node n)
    {
        Node end = n;
        while (end.next != null && end.compareTo(end.next) < 1)
        {
            end = end.next;
        }
        return end;
    }

    public void sort()
    {
        while (true)
        {
            Node endFirst = getEnd(head);
            if (endFirst.next == null)
            {
                return;
            }
            Node startSecond = endFirst.next;
            merge(startSecond);
        }
    }

    private void merge(Node n)
    {
        
    }
}
