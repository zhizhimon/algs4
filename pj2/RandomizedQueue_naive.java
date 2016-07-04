import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
public class RandomizedQueue<Item> implements Iterable<Item> {
    private int size;
    private Node sentinel;


    private class Node {
   	    private Item item;
   	    private Node prev;
        private Node next;

        public Node(Item a) {
    	    item = a;
    	    prev = null;
    	    next = null;
        }
        public Node() {
    	    prev = null;
    	    next = null;
        }
    }
    public RandomizedQueue() {
   	    sentinel = new Node(); 
        sentinel.next = new Node();
        sentinel.prev = new Node();
        sentinel.next.next = sentinel.prev;
        sentinel.prev.prev = sentinel.next;
        size = 0;
    }                 // construct an empty randomized queue
    public boolean isEmpty() {
   	    return size == 0;
    }                // is the queue empty?
    public int size()  {
   	    return size;
    }                     // return the number of items on the queue
    public void enqueue(Item item) {
   	    if (item == null) {
   	  	    throw new NullPointerException();
   	    } 
   	    Node a = new Node(item);
   	    Node oldPrev = sentinel.prev.prev;
   	    oldPrev.next = a;
   	    a.next = sentinel.prev;
   	    sentinel.prev.prev = a;
   	    a.prev = oldPrev;
   	    size = size + 1;
    }          // add the item to the end of the queue
   public Item dequeue()  {
   	    if (size==0){
   	    	throw new NoSuchElementException();
   	    }
   	    int index = StdRandom.uniform(size);
        // System.out.println("The index of the removed number is: " + index);
   	    Node p=sentinel.next.next;
   	    for (int i = 0; i < index; i++){
             p = p.next; 
   	    }
   	    p.prev.next = p.next;
   	    p.next.prev = p.prev;
   	    size = size - 1;
   	    return p.item;
    }                  // remove and return a random item
    public Item sample()  {
   	    if (size==0){
   	    	throw new NoSuchElementException();
   	    }
   	    int index = StdRandom.uniform(size);
        // System.out.println("The index of the number is: " + index);
          int k = 0;
   	    Node p=sentinel.next.next;
   	    for (int i = 0; i < index; i++){
             p = p.next; 
   	    }
   	    return p.item;
    }                   // return (but do not remove) a random item
    public Iterator<Item> iterator()  {
         return new RandomizedIterator();
    }       // return an independent iterator over items in random order
    private class RandomizedIterator implements Iterator<Item> {
        boolean[] mask = new boolean[size];
        int i = size;
        private Node head = sentinel.next.next;
        
        public boolean hasNext() {
        	return i > 0;
        }
        public void remove() {
        	throw new UnsupportedOperationException();
        }
        public Item next() {
        	if (!hasNext()) {
        		throw new NoSuchElementException();
        	} 
        	int index = StdRandom.uniform(i);
        	int k = 0;
          Node current = head;
        	for (int j = 0; j < index; j++) {
        		while (mask[k] == true) {
        			current = current.next;
        			k = k + 1;
        		}
            
            
        		current = current.next;
        		k = k + 1;
        	}
          while (mask[k] == true) {
              current = current.next;
              k = k + 1;
            }

          mask[k] = true;
          i = i - 1;
          return current.item;
        }
    }
   public static void main(String[] args) {
        RandomizedQueue<Integer> a = new RandomizedQueue<Integer>();
        a.enqueue(1);
        a.enqueue(2);
        a.enqueue(3);
        a.enqueue(4);
        a.enqueue(5);
        a.enqueue(6);
        // for (Integer s : a)
        //   System.out.println(s);      
        System.out.println(a.sample());
        System.out.println(a.sample());
        System.out.println(a.sample());
        System.out.println(a.dequeue());
        System.out.println(a.dequeue());

   }  // unit testing
}