import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class PercolationStats {
   private double threshold[];
   private int size_grid, num_exp;
   public PercolationStats(int N, int T)    {
   	    int i,row,column,openedSites;
   	    size_grid=N;
   	    num_exp=T;
        threshold=new double[T];
        Percolation pc;
   	    if (N <= 0 || T <= 0)
            throw new IllegalArgumentException("Arguments out of bound");
        for(i=0;i<T;i++) {
        	openedSites=0;
        	pc=new Percolation(N);
        	while(!pc.percolates()){
        		row=StdRandom.uniform(1,N+1);
        		column=StdRandom.uniform(1,N+1);
        		if (pc.isOpen(row,column)) {
        			continue;
        		}
        		pc.open(row,column);
        		openedSites+=1;
        	}
        	threshold[i]=(double)openedSites/(N*N);
        }

    } // perform T independent experiments on an N-by-N grid
   public double mean()   {
          return StdStats.mean(threshold);
   }                   // sample mean of percolation threshold
   public double stddev() {
   	      if(num_exp==1){
   	      	return Double.NaN;
   	      }
   	      	return StdStats.stddev(threshold);
   }                   // sample standard deviation of percolation threshold
   public double confidenceLo()   {
   	     return mean()-(1.96*stddev()/Math.sqrt(num_exp));
   }           // low  endpoint of 95% confidence interval
   public double confidenceHi()  {
   	     return mean()+(1.96*stddev()/Math.sqrt(num_exp));
   }            // high endpoint of 95% confidence interval

   public static void main(String[] args)    {// test client (described below)
   	     PercolationStats pls=new PercolationStats(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
   	     System.out.printf("mean                     = %f\n", pls.mean());
         System.out.printf("stddev                   = %f\n", pls.stddev());
         System.out.printf("95%% confidence Interval  = %f, %f\n",
                pls.confidenceLo(), pls.confidenceHi());
    }
}
