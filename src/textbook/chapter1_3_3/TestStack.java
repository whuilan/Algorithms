package textbook.chapter1_3_3;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import whuilan.chapter1_3.Ex19;
import edu.princeton.cs.algs4.Stack;

public class TestStack {
    public static void main(String[] args){
        Stack<String> stack = new Stack<String>();
        while (!StdIn.isEmpty()){
            String item = StdIn.readString();
            if(!item.equals("-")){
                stack.push(item);
            }else if(!StdIn.isEmpty()){
                StdOut.print(stack.pop()+" ");
            }
        }
        StdOut.println();
        StdOut.println("("+stack.size()+")"+"left on the stack");
//        stack.push("apple");
//        stack.push("banana");
//        stack.push("orange");
//        stack.push("pear");
//        for(String s:stack){
//            StdOut.print(s+" ");
//        }
//        StdOut.println();

    }
}
