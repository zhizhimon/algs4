import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
public class Deque<Item> implements Iterable<Item> {
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
   public Deque() {
       sentinel = new Node(); 
       sentinel.next = new Node();
       sentinel.prev = new Node();
       sentinel.next.next = sentinel.prev;
       sentinel.prev.prev = sentinel.next;
       size = 0;
   }   // construct an empty deque
   public boolean isEmpty()  {
   	   return size == 0;
   }   // is the deque empty?
   public int size()   {
   	   return size;
   }                     // return the number of items on the deque

   public void addFirst(Item item)  {
    if (item == null) {
         throw new NullPointerException();
      } 
   	   Node a = new Node(item);
   	   Node oldNext = sentinel.next.next;
   	   sentinel.next.next = a;
   	   a.next = oldNext;
   	   a.prev = sentinel.next;
   	   oldNext.prev = a;
   	   size = size + 1;
   }   // add the item to the front

   public void addLast(Item item)  {
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
   }   // add the item to the end

   public Item removeFirst()    {
   	   if (isEmpty()) {
   	   	  throw new NoSuchElementException();
   	   }
   	   Node removed = sentinel.next.next;
   	   Node a = removed.next;
   	   sentinel.next.next = a;
   	   a.prev = sentinel.next;
   	   size = size - 1; 
   	   return removed.item;
   }    // remove and return the item from the front

   public Item removeLast()    {
   	   if (isEmpty()) {
   	   	  throw new NoSuchElementException();
   	   }
   	   Node removed = sentinel.prev.prev;
   	   Node a = removed.prev;
   	   a.next = sentinel.prev;
   	   sentinel.prev.prev = a;
   	   size = size - 1; 
   	   return removed.item;
   }             // remove and return the item from the end

   public Iterator<Item> iterator()  {
   	   return new ListIterator();
   }       // return an iterator over items in order from front to end

   private class ListIterator implements Iterator<Item> {
        private Node current = sentinel.next.next;
        private int i = 0;
        public boolean hasNext() {
        	return i < size;
        }
        public void remove() {
        	throw new UnsupportedOperationException();
        }
        public Item next() {
        	if (!hasNext()) {
        		throw new NoSuchElementException();
        	}
        	Item item = current.item;
        	current = current.next;
        	i = i + 1;
        	return item;
        }
   }
   public static void main(String[] args)  {
   	    Deque<Integer> a = new Deque<Integer>();
   	    a.addFirst(3);
   	    a.addLast(4);
   	    a.addFirst(2);
   	    a.addLast(5);
   	    a.addFirst(1);
   	    a.addLast(6);
   	    a.removeLast();
   	    a.removeFirst();
   	   // Deque<Integer>.Node p=a.sentinel.next.next;
   	    
   	    // for(int i=0; i<a.size; i++){
   	    // 	System.out.println(p.item);
   	    // 	p=p.next;
   	    	
   	    // }
   	    for (Integer s : a)
   	    	System.out.println(s);
   	    	
   	    

   } // unit testing
}
