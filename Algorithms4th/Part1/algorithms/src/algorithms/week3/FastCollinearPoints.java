package algorithms.week3;

import java.util.*;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
/**
 * Created by DELL on 2016/9/24.
 */
public class FastCollinearPoints {
    private int cnt;
    private List<LineSegment> list;
    private Set<String> slope;
    private Point[] copyedArray;

    public FastCollinearPoints(Point[] points){
        if (points == null) throw new NullPointerException();

        //check repeated points
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

        //Fast alogrithm
        FastSort(copyedArray);
    }

    public int numberOfSegments(){
        return cnt;
    }

    public LineSegment[] segments(){
        LineSegment[] ans = new LineSegment[list.size()];
        return list.toArray(ans);
    }

    private void FastSort(Point[] points){
        cnt = 0;
        list = new ArrayList<>();
        slope = new HashSet<>();

        for(int i = 0; i < points.length; i++){
            Point[] tmp = Arrays.copyOf(points, points.length);
            Arrays.sort(tmp, points[i].slopeOrder());

            double preSlope = tmp[0].slopeTo(points[i]);

            int seq = 1;
            Point maxPoint = tmp[0];
            Point minPoint = tmp[0];
            for(int j = 1; j < tmp.length; j++){
                if(tmp[j].slopeTo(points[i]) == preSlope){
                    while(j < tmp.length && tmp[j].slopeTo(points[i]) == preSlope){
                        if(tmp[j].compareTo(maxPoint) > 0)maxPoint = tmp[j];
                        if(tmp[j].compareTo(minPoint) < 0)minPoint = tmp[j];
                        j++;
                        seq++;
                    }

                    //success
                    StringBuilder sb = new StringBuilder();
                    sb.append(minPoint.toString());
                    sb.append(maxPoint.toString());
                    String key = sb.toString();

                    if(seq >= 3 && !slope.contains(key)) {
                        cnt++;
                        slope.add(key);
                        list.add(new LineSegment(minPoint, maxPoint));
                    }
                    j--;
                }else{
                    preSlope = tmp[j].slopeTo(points[i]);
                    maxPoint = tmp[0];
                    minPoint = tmp[0];
                    if(tmp[j].compareTo(maxPoint) > 0)maxPoint = tmp[j];
                    if(tmp[j].compareTo(minPoint) < 0)minPoint = tmp[j];
                    seq = 1;
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
