package pq;

/**
 * Created by Pete Wilcox on 3/20/2016.
 * petercwilcox@gmail.com
 */
public class Queue<Item>
{
    // Super simple FIFO queue
    private Node head;
    private Node tail;
    private int  size;

    private class Node
    {
        Node next;
        Item item;
    }

    public Queue()
    {
        size = 0;
    }

    public void enqueue(Item i)
    {
        Node temp = new Node();
        temp.item = i;

        if (head == null)
        {
            head = temp;
        }
        else
        {
            tail.next = temp;
        }
        tail = temp;
        size++;
    }

    public Item dequeue()
    {
        if (size > 0)
        {
            Node temp = head;
            head = head.next;
            size--;
            return temp.item;
        }
        return null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }
}
