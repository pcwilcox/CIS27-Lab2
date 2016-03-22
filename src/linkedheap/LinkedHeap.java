package linkedheap;

/**
 * Created by Pete Wilcox on 3/21/2016.
 * petercwilcox@gmail.com
 */
public class LinkedHeap<Item>
{
    private Node head;
    private Node tail;
    private int  size;

    private class Node
            implements Comparable<Node>
    {
        Node root;
        Node left;
        Node right;
        Item item;
        int size;

        public String toString()
        {
            if (left == null)
            {
                return item.toString();
            }
            else if (right == null)
            {
                return item.toString() + ", " + left.toString();
            }
            else
            {
                return item.toString() + ", " + left.toString() + ", " + right.toString();
            }
        }

        public int compareTo(Node other)
        {
            if (other == null)
            {
                throw new NullPointerException();
            }
            else if (other.item instanceof Comparable)
            {
                Comparable a = (Comparable) item;
                Comparable b = (Comparable) other.item;
                return a.compareTo(b);
            }
            else
            {
                throw new ClassCastException();
            }
        }
    }

    public LinkedHeap()
    {
        size = 0;
    }

    public void insert(Item i)
    {
        Node temp = newTail(tail);
        temp.item = i;

    }

    public Item remove()
    {
        Node temp = head;
        head = tail;
        tail = temp;
        if (temp.root.right == temp)
        {
            temp.root.right = null;
        }
        else
        {
            temp.root.left = null;
        }
        tail = findTail();
        size--;
        return temp.item;

    }

    private void sink(Node n)
    {
        while (true)
        {
            if (n.left == null && n.right == null)
            {
                break;
            }
            if (n.left != null && n.right != null)
            {
                Node m = greatest(n.left, n.right);
                if (less(n, m))
                {
                    exch(n, m);
                }
            }
            else if (n.left != null)
            {
                if (less(n, n.left))
                {

                    exch(n, n.left);
                }
            }
        }
    }

    private void swim(Node n)
    {
        while (n.root != null && less(n.root, n))
        {
            exch(n, n.root);
        }
    }

    private Node newTail(Node n)
    {
    }

    private Node greatest(Node n, Node m)
    {
        if (less(n, m))
        {
            return m;
        }
        return n;
    }

    private void exch(Node n, Node m)
    {
        Node temp = new Node();
        if (head == n)
        {
            head = m;
        }
        else if (head == m)
        {
            head = n;
        }
        if (tail == n)
        {
            tail = m;
        }
        else if (tail == m)
        {
            tail = n;

        }
        temp.root = n.root;
        temp.left = n.left;
        temp.right = n.right;
        n.root = m.root;
        n.left = m.left;
        n.right = m.right;
        m.root = temp.root;
        m.left = temp.left;
        m.right = temp.right;
    }

    private boolean less(Node n, Node m)
    {
        return n.compareTo(m) < 1;
    }

    public String toString()
    {
        return head.toString();
    }

    public boolean isEmpty()
    {
        return size == 0;
    }

    public int size()
    {
        return size;
    }
}
