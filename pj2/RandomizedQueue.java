import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
public class RandomizedQueue<Item> implements Iterable<Item> {
    private int arraySize;
    private int queueSize;
    private Item[] queue = (Item[]) new Object[2];
    public RandomizedQueue() {
      // Item[] queue = (Item[]) new Object[2];
      arraySize = 2;
      queueSize = 0;
    }                // construct an empty randomized queue

    public boolean isEmpty()  {
      return queueSize==0;
    }               // is the queue empty?

    public int size() {
      return queueSize; 
    }                       // return the number of items on the queue

    public void enqueue(Item item) {
      if (item == null) {
             throw new NullPointerException();
      } 
      if (queueSize + 1 > arraySize) {
          resize(2* arraySize);
      }

      // if (queueSize == 0) {
      //    queue[queueSize++] = item;
      // }
      // else {
      // int index = StdRandom.uniform(queueSize);
      // Item temp = queue[index];
      // queue[index] = item;
      // queue[queueSize++] = temp;
      // }
       queue[queueSize++] = item;
    }       

    private void resize(int N) {
      Item[] newQueue = (Item[]) new Object[N];
      int flag;
      if (N > queueSize) {
        flag = queueSize;
      }
      else 
        flag = N;
      for (int i = 0; i < flag; i++) {
        newQueue[i] = queue[i];
      }
      queue = newQueue;
      arraySize = N;
    }
    public Item dequeue()  {
      if (queueSize==0){
         throw new NoSuchElementException();
      }
      int index = StdRandom.uniform(queueSize);
      Item x = queue[index];
      queue[index] = queue[queueSize - 1];
      queue[queueSize - 1] = null;
      queueSize = queueSize - 1;
      if (queueSize < arraySize/4) {
          resize(arraySize/4);
      }
      return x;
    }                  // remove and return a random item
    public Item sample()   {
      if (queueSize==0){
         throw new NoSuchElementException();
      }
      int index = StdRandom.uniform(queueSize);
      return queue[index];
    }                  // return (but do not remove) a random item
    public Iterator<Item> iterator() {
      return new RandomizedIterator();
    }       
     private class RandomizedIterator implements Iterator<Item> {

        private Item[] temp = (Item[]) new Object[queueSize];
        private int j = 0;

        private RandomizedIterator() {
          for (int i = 0; i < queueSize; i++) {
            temp[i] = queue[i];
          }
          StdRandom.shuffle(temp);
        }

        public boolean hasNext() {
           return j < queueSize;
         }

        public void remove() {
           throw new UnsupportedOperationException();
        }

        public Item next() {
           if (!hasNext()) {
            throw new NoSuchElementException();
           }
           return temp[j++];
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
        a.dequeue();
        a.dequeue();
        for (Integer s : a)
           System.out.println(s);      
       

   }  
}