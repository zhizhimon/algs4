import edu.princeton.cs.algs4.StdDraw;
import java.util.Comparator;
public class Point implements Comparable<Point> {

    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    public Point(int x, int y) {
        /* DO NOT MODIFY */
        if( !((x >= 0) && (x <= 32767) && (y >= 0) && (y <= 32767))) {
              throw new java.lang.IllegalArgumentException();

        } 
        this.x = x;
        this.y = y;
    }
    // public int getX(){
    //   return this.x;
    // }
    // public int getY(){
    //   return this.y;
    // }
   
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }


    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

   public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }                      // string representation

   public  int compareTo(Point that)  {
   	    if ((this.y < that.y) || (this.y == that.y && this.x < that.x)) {
   	    	return -1;
   	    } 
   	    else {  if (this.x == that.x && this.y == that.y){ 
                    return 0;
   	                }
   	            else
   	                 return 1; 
   	          }	    
    }   // compare two points by y-coordinates, breaking ties by x-coordinates

   public   double slopeTo(Point that)   {
         if (this.x == that.x){
         	 if (this.y != that.y)
         	 	return Double.POSITIVE_INFINITY;
         	 else 
         	 	return Double.NEGATIVE_INFINITY;
          }
         else {
          if (this.y == that.y){
            double a=1.0;
            return (double)(a-a)/a;
          }
          else 
            return (double) (that.y - this.y)/(that.x - this.x);
         }
   }    // the slope between this point and that point

   public Comparator<Point> slopeOrder()  {
   	    return new SlopeOrder();
   }            // compare two points by slopes they make with this point
   

   private class SlopeOrder implements Comparator<Point> {
        public int compare(Point a, Point b) {
        	double aSlope = Point.this.slopeTo(a);
        	double bSlope = Point.this.slopeTo(b);
        	if (aSlope < bSlope) {
        		return -1;
        	}
        	else if (aSlope == bSlope) {
        		return 0;
        	}
        	else return 1;
        }
   }
}
