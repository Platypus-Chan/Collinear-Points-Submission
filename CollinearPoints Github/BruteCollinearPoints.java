
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private final LineSegment[] segments;



    public BruteCollinearPoints(Point[] points){// finds all line segments containing 4 points
        if (points == null){
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < points.length ; i++) {

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
        ArrayList<LineSegment> res = new ArrayList<LineSegment>();
        if (copy.length > 3){
            Point[] temp = new Point[4];
            for (int a = 0; a < copy.length - 3; a++) {
                temp[0] = copy[a];
                for (int b = a + 1; b < copy.length - 2; b++) {
                    temp[1] = copy[b];
                    for (int c = b + 1; c < copy.length - 1 ; c++) {
                        temp[2] = copy[c];
                        for (int d = c+1; d < copy.length; d++) {
                            temp[3] = copy[d];
                            if (isColinear(temp)){
                                LineSegment line = makeSegment(temp);
                                res.add(line);
                            }

                        }
                    }
                }

            }
        }
        segments = res.toArray(new LineSegment[res.size()]);

    }

    private boolean isColinear(Point[] lst){
        Point p1 = lst[0];
        int temp0 = p1.slopeOrder().compare(lst[1],lst[2]);
        int temp1 = p1.slopeOrder().compare(lst[1],lst[3]);
        if (temp0 == temp1 && temp0 == 0) return true;
        return false;
    }

    private LineSegment makeSegment(Point[] lst){
        Arrays.sort(lst);
        Point david = lst[0];
        Point goliath = lst[3];
        for(Point i : lst){
            if (i.compareTo(goliath) > 0 ){
                goliath =i;
            }if (i.compareTo(david) <0 ){
                david = i;
            }
        }
        LineSegment a = new LineSegment(david, goliath);
        return a;
    }


    public int numberOfSegments(){
        return segments.length;
    }
    public LineSegment[] segments(){
        LineSegment[] res = segments.clone();


        return res;
    }

    public static void main(String[] args) {


    }
}
