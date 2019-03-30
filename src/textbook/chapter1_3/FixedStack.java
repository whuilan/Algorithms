package textbook.chapter1_3;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Stack;
import java.util.StringTokenizer;

public class FixedStack {
    public static void main(String[] args){
        // FixedCapacityStackOfStrings s = new FixedCapacityStackOfStrings(100);
        FixedCapacityStack<String> s = new FixedCapacityStack<  >(100);
        while(!StdIn.isEmpty()){
            String item = StdIn.readString();
            if(!item.equals("-")){
                s.push(item);
            }else if(!s.isEmpty()){
                StdOut.print(s.pop()+" ");
            }
        }
        StdOut.println("("+s.size()+" left on the stack)");
    }

    }
