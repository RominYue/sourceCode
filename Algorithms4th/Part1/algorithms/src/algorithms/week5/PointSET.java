package algorithms.week5;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by DELL on 2016/10/8.
 */
public class PointSET {
    private TreeSet<Point2D> set;
    // construct an empty set of points
    public PointSET(){
        set = new TreeSet<>();
    }
    // is the set empty?
    public boolean isEmpty(){
        return set.isEmpty();
    }
    // number of points in the set
    public int size(){
        return set.size();
    }
    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p){
        if (p == null) throw new NullPointerException();
        set.add(p);
    }
    // does the set contain point p?
    public boolean contains(Point2D p){
        if (p == null) throw new NullPointerException();
        return set.contains(p);
    }
    // draw all points to standard draw
    public void draw(){
        set.forEach(p -> p.draw());
    }
    // all points that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect){
        if (rect == null) throw new NullPointerException();
        List<Point2D> list = new ArrayList<>();
        for (Point2D q: set){
            if (rect.contains(q)){
                list.add(q);
            }
        }
        return list;
    }
    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p){
        if (p == null) throw new NullPointerException();
        Point2D ret = null;
        double minDis = Double.MAX_VALUE;
        for(Point2D q: set){
            double tmp = p.distanceTo(q);
            if(tmp < minDis){
                minDis = tmp;
                ret = q;
            }
        }
        return ret;
    }

    public static void main(String[] args){

    }
}
