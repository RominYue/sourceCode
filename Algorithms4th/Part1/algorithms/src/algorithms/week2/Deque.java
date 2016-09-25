package algorithms.week2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by DELL on 2016/9/17.
 */
public class Deque<Item> implements Iterable<Item> {
    private Node<Item> first;    // beginning of queue
    private Node<Item> last;     // end of queue
    private int n;               // number of elements on queue

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
        private Node<Item> pre;
    }

    public Deque(){
        first = null;
        last  = null;
        n = 0;
    }

    public boolean isEmpty(){
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void addFirst(Item item) {
        if (item == null) throw new NullPointerException();
        Node<Item> oldFirst = first;
        first = new Node<>();
        first.item = item;
        first.pre = null;
        first.next = oldFirst;
        if (isEmpty()) last = first;
        else oldFirst.pre = first;
        n++;
    }
    public void addLast(Item item) {
        if (item == null) throw new NullPointerException();
        Node<Item> oldlast = last;
        last = new Node<>();
        last.item = item;
        last.next = null;
        last.pre = oldlast;
        if(isEmpty()) first = last;
        else oldlast.next = last;
        n++;
    }
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = first.item;
        first = first.next;
        n--;
        if (isEmpty()) last = null;
        return item;
    }
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = last.item;
        last = last.pre;
        if(last != null)last.next = null;
        n--;
        if (isEmpty()) first = null;
        return item;
    }

    public Iterator<Item> iterator() {
        return new ListIterator<Item>(first);
    }

    private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Deque<String> queue = new Deque<>();
        int cnt = 1;
        queue.addLast("x");
        queue.addLast("x");
        queue.removeLast();
        queue.removeLast();

        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if(item.equals("q"))break;
            if(cnt % 2 == 0) queue.addFirst(item);
            else queue.addLast(item);
            cnt++;
            for(String x: queue){
                System.out.print(x + " ");
            }
            System.out.println();
        }

        System.out.println(queue.removeFirst());
        System.out.println(queue.removeLast());

        for(String x: queue){
            System.out.print(x + " ");
        }
        StdOut.println("(" + queue.size() + " left on queue)");
    }
}
