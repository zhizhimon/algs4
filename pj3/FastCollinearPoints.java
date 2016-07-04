import java.io.File;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Arrays;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;

public class FastCollinearPoints {

   private int num_Seg;
   private LineSegment[] line_Seg;
   private ArrayList<LineSegment> segment;

   public FastCollinearPoints(Point[] points) {
    if (points==null)
            throw new java.lang.NullPointerException();
   	    num_Seg = 0;
   	    int num_point = points.length;
   	    segment = new ArrayList<LineSegment>();
   	    
   	    for (int i = 0; i < num_point; i++) {
            if(points[i]==null){
              throw new java.lang.NullPointerException();
            }
   	    	  Point[] temp = new Point[num_point - 1];
   	    	  Point origin = points[i];
   	    	  int flag = 0;
            /* Sort the rest elements(excluding the current origin) of the array */
   	    	  for (int j = 0; j < num_point; j++){   //
   	    	       if (i==j) {
   	    	       	flag = 1;
   	    	       	continue;
   	    	       }
                 if(points[j].compareTo(origin)==0){
                  throw new java.lang.IllegalArgumentException();
                 }
   	    	  	   temp[j-flag] = points[j];
   	    	  }   
             Arrays.sort(temp,origin.slopeOrder());
             // if(i==0){
             //  for(int k = 0;k<temp.length;k++){
             //              System.out.println("x:"+temp[k].getX()+" y:"+temp[k].getY());
             //            }
             // }
             scan(temp, origin);
             
   	    } 
   }    // finds all line segments containing 4 or more points

   public int numberOfSegments()   {
   	    return num_Seg;
   }     // the number of line segments

   public LineSegment[] segments()  {
   	    // return line_Seg;
     return segment.toArray(new LineSegment[segment.size()]);
   }              // the line segments


   
   private  void scan(Point[] a, Point origin){
   	     int gap = 1;
   	     for (int i = 0; i <= a.length - 2; i += gap) {  /* Find the lined elements of the samr slope to t he origin*/
             if (origin.slopeTo(a[i+1]) != origin.slopeTo(a[i])){
             	gap = 1;
             }
             else{
                int j = i + 2;
                gap = 2;
                while (j<a.length && origin.slopeTo(a[j]) == origin.slopeTo(a[i])){
                	j++;
                	gap++;
                }
                if (gap >= 3){  /*find more-than four continued elements*/
                    Point[] temp = new Point[gap+1];
                    temp[0] = origin;
                    int max = 0, min = 0;
                    for (int p = 1; p < temp.length && (i+p-1<a.length); p++){
                         temp[p] = a[i + p - 1];
                         if(temp[p].compareTo(temp[max]) > 0){
                         	max = p;
                         }
                         if(temp[p].compareTo(temp[min]) < 0){
                         	min = p;
                         }
                    }
                    if(min == 0){   /*make sure the origin is the minimum one to avoid repetition*/
                    	LineSegment newItem = new LineSegment(temp[min],temp[max]);
                        segment.add(newItem);
                        num_Seg++;
                        // for(int k = 0;k<temp.length;k++){
                        //   System.out.println("segment"+num_Seg+"x:"+temp[k].getX()+" y:"+temp[k].getY());
                        // }

                    }                
                }
             }
   	     } 
    /*copy the contents of the arrayList to a array */
  //  	line_Seg = new LineSegment[num_Seg];
		// for (int i = 0;i < num_Seg; i++){
  //           line_Seg[i] = segment.get(i);
		// }
    
   }
   public static void main(String[] args) {

    // read the N points from a file
    In in = new In(args[0]);
    int N = in.readInt();
    Point[] points = new Point[N];
    for (int i = 0; i < N; i++) {
        int x = in.readInt();
        int y = in.readInt();
        points[i] = new Point(x, y);
    }

    // draw the points
    StdDraw.show(0);
    StdDraw.setXscale(0, 32768);
    StdDraw.setYscale(0, 32768);
    for (Point p : points) {
        p.draw();
    }
    StdDraw.show();

    // print and draw the line segments
    FastCollinearPoints collinear = new FastCollinearPoints(points);
    for (LineSegment segment : collinear.segments()) {
        StdOut.println(segment);
        segment.draw();
    }
    
}
}
