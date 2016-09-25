package algorithms.week3;

import java.util.*;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
/**
 * Created by DELL on 2016/9/24.
 */
public class BruteCollinearPoints {
    private int cnt;
    private List<LineSegment> list;
    private double EPS = 1e-6;
    private Point[] copyedArray;

    public BruteCollinearPoints(Point[] points){
        if (points == null) throw new NullPointerException();

        copyedArray = points.clone();
        Arrays.sort(copyedArray);
        //check repeated points
        for(int i = 1; i < copyedArray.length; i++)
        {
            if (copyedArray[i].compareTo(copyedArray[i-1]) == 0)
            {
                throw new IllegalArgumentException();
            }
        }

        //brute force
        bruteForce(copyedArray);
    }
    public int numberOfSegments(){
        return cnt;
    }

    public LineSegment[] segments(){
        LineSegment[] ans = new LineSegment[list.size()];
        return list.toArray(ans);
    }

    private void bruteForce(Point[] points){
        cnt = 0;
        list = new ArrayList<>();

        for(int i = 0; i < points.length; i++)
        {
            for(int j = i+1; j < points.length; j++)
            {
                double slope1 = points[i].slopeTo(points[j]);
                for(int k = j + 1; k < points.length; k++)
                {
                    double slope2 = points[i].slopeTo(points[k]);
                    if(Math.abs(slope1 - slope2) > EPS)continue;
                    for(int m = k + 1; m < points.length; m++)
                    {
                        double slope3 = points[i].slopeTo(points[m]);
                        if(Math.abs(slope1 - slope3) > EPS)continue;
                        cnt++;
                        list.add(new LineSegment(points[i], points[m]));
                    }
                }
            }
        }
    }

    public static void main(String[] args){
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
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
        StdDraw.show();
    }
}
