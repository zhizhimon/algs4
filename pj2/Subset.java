import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
public class Subset
{
    public static void main(String[] args)
    {
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        int k = Integer.parseInt(args[0]);
        int num;
        int index;
        int i;
       
        	for (i = 0; i < k; i++) {
                String item = StdIn.readString();
                q.enqueue(item);
            }
            i = k+1;
            while (!StdIn.isEmpty()) {
                num = StdRandom.uniform(i);
                if (num < k) {
                	q.dequeue();
                	q.enqueue(StdIn.readString());
                }
                i = i + 1;

            }
         
        
        while (k > 0)
        {
            StdOut.println(q.dequeue());
            k--;
        }
        return;
    }
    
}