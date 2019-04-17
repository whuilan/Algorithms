package textbook.chapter1_4.chapter1_4_6;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import textbook.chapter1_4.chapter1_4_2.Stopwatch;
import textbook.chapter1_4.chapter1_4_2.ThreeSum;


public class DoublingRatio {
    public static double timeTrial(int N){
        int MAX = 1000000;
        int[] a = new int[N];
        for(int i=0;i<N;i++){
            a[i] = StdRandom.uniform(-MAX,MAX);
        }
        Stopwatch watch = new Stopwatch();
        int total = ThreeSum.count(a);
        return  watch.elapsedTime();
    }

    public static void main(String[] args){
        double prev = timeTrial(125);
        for(int N = 250;true;N+=N){
            double time = timeTrial(N);
            double ratio = time/prev;
            StdOut.printf("%6d %7.1f", N, time);
            StdOut.printf("%5.1f\n", ratio);
            prev = time;
        }
    }
}
