package algorithms.week3;

import java.util.*;

/**
 * Created by DELL on 2016/9/24.
 */
public class FastCollinearPoints {
    private int cnt;
    private List<LineSegment> list;
    private Set<Point> set;
    private Set<Double> slope;

    public FastCollinearPoints(Point[] points){
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

        //Fast alogrithm
        FastSort(points);
    }

    public int numberOfSegments(){
        return cnt;
    }

    public LineSegment[] segments(){
        return (LineSegment[]) list.toArray();
    }

    private void FastSort(Point[] points){
        cnt = 0;
        list = new ArrayList<>();
        slope = new HashSet<>();

        for(int i = 0; i < points.length; i++){
            Point[] tmp = Arrays.copyOf(points, points.length);
            Arrays.sort(tmp, points[i].slopeOrder());

            double preSlope = Double.NEGATIVE_INFINITY;
            int seq = 0;
            for(int j = 0; j < tmp.length; j++){
                while(j < tmp.length && tmp[j].slopeTo(points[i]) == preSlope){
                    j++;
                    seq++;
                }
                //success
                if(seq >= 4 && !slope.contains(preSlope))
                {
                    slope.add(preSlope);
                    list.add(new LineSegment(tmp[j - seq], tmp[j-1]));
                }

                if(j < tmp.length){
                    preSlope = tmp[j].slopeTo(points[i]);
                }
                j--;
                seq = 0;
            }
        }
    }

    public static void main(String[] args){

    }
}
