package textbook.chapter1_4.chapter1_4_2;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class TestStopwatch {
    public static void main(String[] args){
        int N = Integer.parseInt(args[0]);
        int[] a = new int[N];
        for(int i=0;i<N;i++){
            a[i] = StdRandom.uniform(-1000000,1000000);
        }
        Stopwatch timer = new Stopwatch();
        int total = ThreeSum.count(a);
        double time = timer.elapsedTime();
        StdOut.println(total+"个三元组， "+time+"秒");
    }
}
