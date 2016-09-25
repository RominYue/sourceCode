package algorithms.week2;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
/**
 * Created by DELL on 2016/9/17.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] q;       // queue elements
    private int n;          // number of elements on queue
    private int first;      // index of first element of queue
    private int last;       // index of next available slot

    public RandomizedQueue() {
        q = (Item[]) new Object[2];
        n = 0;
        first = 0;
        last = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException();
        if(n == q.length) resize(2*q.length);
        q[last++] = item;
        if (last == q.length) last = 0;
        n++;
    }

    public Item dequeue() {
        if(isEmpty()) throw new NoSuchElementException();
        int index = StdRandom.uniform(n);
        index = (first + index) % q.length;

        Item item = q[first];
        q[first] = q[index];
        q[index] = item;
        item = q[first];

        q[first] = null;
        n--;
        first++;
        if (first == q.length) {
            first = 0;
        }
        if (n > 0 && n == q.length/4) resize(q.length/2);
        return item;
    }

    public Item sample() {
        if(isEmpty()) throw new NoSuchElementException();
        int index = StdRandom.uniform(n);
        index = (first + index) % q.length;
        return q[index];
    }

    public Iterator<Item> iterator() {
        Item[] tmp = (Item[] )new Object[n];
        for(int i = 0; i < n; i++)
        {
            tmp[i] = q[(i + first) % q.length];
        }
        StdRandom.shuffle(tmp);
        return new ArrayIterator(tmp);
    }

    private class ArrayIterator implements Iterator<Item> {
        private Item[] a;
        ArrayIterator(Item[] a){
            this.a = a;
        }
        private int i = 0;
        public boolean hasNext()  { return i < n; }
        public void remove()      { throw new UnsupportedOperationException(); }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = a[i];
            i++;
            return item;
        }
    }

    // resize the underlying array
    private void resize(int capacity) {
        assert capacity >= n;
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = q[(first + i) % q.length];
        }
        q = temp;
        first = 0;
        last  = n;
    }

    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if(item.equals("q"))break;
            queue.enqueue(item);
        }
        for(String x: queue){
            System.out.println(x);
        }
        StdOut.println("(" + queue.size() + " left on queue)");
    }
}
