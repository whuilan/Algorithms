package whuilan.chapter1_3;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import textbook.chapter1_3.FixedCapacityStackOfStrings;

public class Ex02 {
    public static void main(String[] args){
        FixedCapacityStackOfStrings stackOfStrings = new FixedCapacityStackOfStrings(100);
        while (!StdIn.isEmpty()){
            String s = StdIn.readString();
            if(!s.equals("-")){
                stackOfStrings.push(s);
            }else if(!stackOfStrings.isEmpty()){
                StdOut.print(stackOfStrings.pop()+" ");
            }
        }
    }
}
