import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
public class Percolation {
   private int size;
   private WeightedQuickUnionUF uf;
   private byte[] status;
   private boolean percolated;

   public Percolation(int N) {
   	    if(N<=0){
   	    	throw new IllegalArgumentException("Arguments out of bound");
   	    }
   	    size=N;
        uf=new WeightedQuickUnionUF(N*N);  //size&size+1:head node; size*size+2:bottom node
        status=new byte[N*N];
        int i;
        percolated=false;
        for(i=0;i<N*N;i++){
        	status[i]=0;     //x3x2x1----x1==1/0:opened/blocked;x2==1/0:connected/unconnected to head; x3==1/0:connected/unconnected to bottom
        }
   }              // create N-by-N grid, with all sites blocked

   public void open(int i, int j)  {
         if(i<=0||i>size||j<=0||j>size){
          throw new IndexOutOfBoundsException("Arguments out of bound");
        }
   	     int index=(i-1)*size+j-1;
         if((status[index]&1)==1){
             return;
         }
   	     byte flag=1;
   	     int root;
   	     status[index]|=0x01;       //
   	     if(j!=1 && (status[index-1]&0x01)==1){
   	     	root=uf.find(index-1);
   	     	if((status[root]&0x02)==2){   //the top site connected to head 
                flag |=2;
   	     	}
   	     	if((status[root]&0x04)==4){   //the top site connected to head 
                flag |=4;
   	     	}
   	     	uf.union(index-1, index);
   	     }
   	     if(j!=size && (status[index+1]&0x01)==1){
   	     	root=uf.find(index+1);
   	     	if((status[root]&0x02)==2){   //the top site connected to head 
                flag |=2;
   	     	}
   	     	if((status[root]&0x04)==4){   //the top site connected to head 
                flag |=4;
   	     	}
   	     	uf.union(index+1,index);
   	     }
   	     if(i!=1 && (status[index-size]&0x01)==1){
   	     	root=uf.find(index-size);
   	     	if((status[root]&0x02)==2){   //the top site connected to head 
                flag |=2;
   	     	}
   	     	if((status[root]&0x04)==4){   //the top site connected to head 
                flag |=4;
   	     	}
   	     	uf.union(index-size,index);
   	     }
   	     if(i!=size && (status[index+size]&0x01)==1){
   	     	root=uf.find(index+size);
   	     	if((status[root]&0x02)==2){   //the top site connected to head 
                flag |=2;
   	     	}
   	     	if((status[root]&0x04)==4){   //the top site connected to head 
                flag |=4;
   	     	}
   	     	uf.union(index+size,index);
   	     }
   	     if(i==1){
   	     	flag |=2;
   	     }
   	     if(i==size){
   	     	flag |=4;
   	     }
   	     root=uf.find(index);
   	     status[root]=flag;
   	     if((status[root] & 0x06)==6){
             percolated=true;
   	     }
   }        // open site (row i, column j) if it is not open already{}
   public boolean isOpen(int i, int j) {
    if(i<=0||i>size||j<=0||j>size){
          throw new IndexOutOfBoundsException("Arguments out of bound");
        }
   	      return (status[(i-1)*size+j-1] & 0x01)==1;
   }    // is site (row i, column j) open?

   public boolean isFull(int i, int j) {
    if(i<=0||i>size||j<=0||j>size){
          throw new IndexOutOfBoundsException("Arguments out of bound");
        }
   	      return (status[uf.find((i-1)*size+j-1)] & 0x02)==2;
   }    // is site (row i, column j) full?
   public boolean percolates()      {
   	      return percolated;
   }       // does the system percolate?

   public static void main(String[] args) {

   } // test client (optional)

}