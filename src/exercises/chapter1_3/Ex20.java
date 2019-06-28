package exercises.chapter1_3;

import edu.princeton.cs.algs4.StdOut;
import textbook.chapter1_3_3.Stack;

public class Ex20 {
    public static void main(String[] args){
        Stack<String> stack = new Stack<>();
        stack.push("apple");
        stack.push("banana");
        stack.push("orange");
        stack.push("pear");
        for(String s:stack){
            StdOut.print(s+" ");
        }
        StdOut.println();
        stack.delete(1);
        for(String s:stack){
            StdOut.print(s+" ");
        }
    }
}
