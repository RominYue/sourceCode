package algorithms.week3;

import java.util.*;

/**
 * Created by DELL on 2016/9/24.
 */
public class BruteCollinearPoints {
    private int cnt;
    private List<LineSegment> list;
    private Set<Point> set;
    double EPS = 1e-6;

    public BruteCollinearPoints(Point[] points){
        if (points == null) throw new NullPointerException();

        //check repeated points
        set = new HashSet<>();

        for(int i = 1; i < points.length; i++)
        {
            if (set.contains(points[i]))
            {
                throw new IllegalArgumentException();
            }
            set.add(points[i]);
        }

        //brute force
        Arrays.sort(points);
        bruteForce(points);
    }
    public int numberOfSegments(){
        return cnt;
    }

    public LineSegment[] segments(){
        return (LineSegment[]) list.toArray();
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

    }
}
