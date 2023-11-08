import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private final LineSegment[] segments;
    public FastCollinearPoints(Point[] points){
        if (points == null){
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null){
                throw new IllegalArgumentException();
            }
        }
        Point[] copy = points.clone();
        Arrays.sort(copy);
        if (copy.length > 1){
            for (int i = 1; i < copy.length ; i++) {
                if(copy[i].compareTo(copy[i-1]) == 0){
                    throw new IllegalArgumentException();
                }
            }
        }
        ArrayList<LineSegment> a = new ArrayList<>();
        if (copy.length > 3){
            Point[] temp = copy.clone();
            for (Point p:copy){
                Arrays.sort(temp, p.slopeOrder()); 
                helper(temp, p, a);
            }
        }
        segments = a.toArray(new LineSegment[a.size()]);

    }
    private void helper(Point[] points, Point p, ArrayList<LineSegment> res){
        int start = 1;
        double sl = p.slopeTo(points[1]);

        for (int i =2; i <points.length ; i++){
            double tempsl = p.slopeTo(points[i]);
            if (Double.compare(tempsl, sl) != 0){
                if (i - start >=3 ){
                    Point[] lst = get_seg( points, p, start, i);

                    if (lst[0].compareTo(p) == 0){
                        res.add(new LineSegment(lst[0], lst[1]));
                    }
                }
                start = i;
                sl = tempsl;
            }
        }
        if (points.length - start >= 3 ){
            Point[] lastpoints = get_seg(points,p,start,points.length);
            if (lastpoints[0].compareTo(p) == 0){
                res.add(new LineSegment(lastpoints[0],lastpoints[1]));
            }
        }

    }
    private Point[] get_seg(Point[] points, Point p, int start, int end){
        ArrayList<Point> lst = new ArrayList<>();
        lst.add(p);
        for (int i = start; i < end ; i++) {
            lst.add(points[i]);
        }
        lst.sort(null);
        return new Point[] {lst.get(0),lst.get(lst.size() -1 )};
    }

    public int numberOfSegments() {
        return segments.length;
    }
    public LineSegment[] segments() {
        return segments.clone();
    }    

    public static void main(String[] args) {

        In in = new In("input8.txt");
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
            System.out.println(points[i]);
        }
    
        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        /*
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
        */

        // print and draw the line segments 
        
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
        
    }
}
