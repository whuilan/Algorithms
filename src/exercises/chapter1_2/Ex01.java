package exercises.chapter1_2;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class Ex01 {
    public static void geratePoints(Point2D[] a){
        for(int i=0;i<a.length;i++){
            a[i] = new Point2D(Math.random(),Math.random());
            StdDraw.setPenRadius(.005);
            a[i].draw();
        }
    }

    public static double distance(Point2D[] a){
        double min = 200;
        for(int i=0;i<a.length;i++){
            for(int j=i+1;j<a.length;j++){
                double d = a[i].distanceTo(a[j]);
                min = min>d?d:min;
            }
        }
        return min;
    }

    public static void main(String[] args){
        int N = 10;
        Point2D[] a = new Point2D[N];
        geratePoints(a);
        StdOut.println("距离最近的两点之间的最短距离为："+distance(a));
    }
}
