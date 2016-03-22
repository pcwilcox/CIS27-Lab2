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
        int  size;

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
        if (head == null)
        {
            head = new Node();
            tail = head;
        }
        else
        {
            tail = newTail();
        }
        tail.item = i;
        tail.size = 0;
        grow(tail);
        swim(tail);
        size++;
    }

    public Item remove()
    {
        Node temp = head;
        exch(head, tail);
        shrink(tail);
        if (temp.root.right == temp)
        {
            temp.root.right = null;
        }
        else
        {
            temp.root.left = null;
        }

        tail = findTail();
        sink(head);
        size--;
        return temp.item;
    }

    private void sink(Node n)
    {
        while (n.left != null && n.right != null)
        {
            Node next = greatest(n.left, n.right);
            if (less(n, next))
            {
                exch(n, next);
            }
            else
            {
                break;
            }
        }
        if (less(n, n.left))
        {
            exch(n, n.left);
        }
    }

    private void swim(Node n)
    {
        while (n.root != null && less(n.root, n))
        {
            exch(n, n.root);
        }
    }

    private void shrink(Node n)
    {
        while (n != null)
        {
            n.size--;
            n = n.root;
        }
    }

    private void grow(Node n)
    {
        while (n != null)
        {
            n.size++;
            n = n.root;
        }
    }

    private Node newTail()
    {
        Node current = head;
        while (current.right != null)
        {
            current = smaller(current);
        }
        Node newTail = new Node();
        newTail.root = current;
        newTail.size = 0;
        grow(newTail);

        if (current.left == null)
        {
            current.left = newTail;
        }
        else
        {
            current.right = newTail;
        }
        return newTail;

    }

    // Returns last inserted node
    private Node findTail()
    {
        Node current = head;
        while (true)
        {
            if (current.left == null)
            {
                return current;
            }
            if (current.right == null)
            {
                return current.left;
            }
            current = smaller(current);
        }
    }

    private Node smaller(Node n)
    {
        if (n.right == null)
        {
            return n.left;
        }
        if (n.right.size < n.left.size)
        {
            return n.right;
        }
        return n.left;
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
        temp.size = n.size;
        n.root = m.root;
        n.left = m.left;
        n.right = m.right;
        n.size = m.size;
        m.root = temp.root;
        m.left = temp.left;
        m.right = temp.right;
        m.size = temp.size;
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
