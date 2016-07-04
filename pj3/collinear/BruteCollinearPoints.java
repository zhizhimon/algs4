import java.io.File;
import java.util.Comparator;
import java.util.ArrayList;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;
import java.util.NoSuchElementException;


public class BruteCollinearPoints {
	private int num_Seg;
	private LineSegment[] line_Seg;
	private ArrayList<LineSegment> segment;
    private Point minPoint(Point p1, Point p2, Point p3, Point p4) {
    	Point min = p1;
    	if (min.compareTo(p2) > 0)
    	    min = p2;
    	if (min.compareTo(p3) > 0)
    	    min = p3;
    	if (min.compareTo(p4) > 0)
    	    min = p4;
    	return min;
    } 

    private Point maxPoint(Point p1, Point p2, Point p3, Point p4) {
    	Point max = p1;
    	if (max.compareTo(p2) < 0)
    	    max = p2;
    	if (max.compareTo(p3) < 0)
    	    max = p3;
    	if (max.compareTo(p4) < 0)
    	    max = p4;
    	return max;
    } 
	public BruteCollinearPoints(Point[] points)  {
		assert points.length >= 4;
        if (points==null)
            throw new java.lang.NullPointerException();
		int N = points.length;
		segment = new ArrayList<LineSegment>();/////!!!
		num_Seg = 0;
		for (int p = 0; p < N ; p++ ) {
			for (int q = p + 1; q < N; q++ ) {
                 Point p1 = points[p], p2 = points[q];
                 if(p1.compareTo(p2)==0)
                    throw new java.lang.IllegalArgumentException();
				 for (int r = q + 1; r < N ; r++ ) {
				 	 for (int s = r + 1; s < N ; s++ ) {
				 	 	  Point p3 = points[r], p4 = points[s]; 
				 	 	  if(p1==null || p2==null ||p3==null ||p4==null){
                            throw new java.lang.NullPointerException();
                          }
				 	 	  if ( (p1.slopeTo(p2)==p2.slopeTo(p3) ) 
				 	 	  	   && (p2.slopeTo(p3)==p3.slopeTo(p4)) ){
				 	 	  	   // System.out.println("p1: "+p1.getX()+p1.getY());
				 	 	  	   // System.out.println("p2: "+p2.getX()+p2.getY());
				 	 	  	   // System.out.println("p3: "+p3.getX()+p3.getY());
				 	 	  	   // System.out.println("p4: "+p4.getX()+p4.getY());
                               LineSegment newItem = new LineSegment(minPoint(p1,p2,p3,p4),maxPoint(p1,p2,p3,p4));
                               segment.add(newItem);
                               num_Seg++;
				 	 	  }
				 	 }
				 }
			}
		}
		line_Seg = new LineSegment[num_Seg];
		// for (int i = 0;i < num_Seg; i++){
  //           line_Seg[i] = segment.get(i);
		// }

	}  // finds all line segments containing 4 points
    public int numberOfSegments()    {
    	return num_Seg;
    }    // the number of line segments
    public LineSegment[] segments()  {
    	 return segment.toArray(new LineSegment[segment.size()]);
    }              // the line segments
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
    BruteCollinearPoints collinear = new BruteCollinearPoints(points);
    for (LineSegment segment : collinear.segments()) {
        StdOut.println(segment);
        segment.draw();
    }
    
}
}