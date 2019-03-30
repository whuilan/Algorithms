package test;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class TestAccumulator {
    public static void main(String[] args){
        int T = Integer.parseInt(args[0]);
        // Accumulator a = new Accumulator();
        VisualAccumulator a = new VisualAccumulator(T,1.0);
        for(int i=0;i<T;i++){
            a.addDataValue(StdRandom.uniform());
        }
        StdOut.println(a);
    }

}
