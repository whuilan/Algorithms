package whuilan.chapter1_3;

import edu.princeton.cs.algs4.StdOut;
import textbook.chapter1_3.ResizingArrayStack;

public class Ex12 {
    public static void main(String[] args){
        ResizingArrayStack<String> stack = new ResizingArrayStack<>();
        stack.push("first");
        stack.push("second");
        stack.push("third");
        for(String s:stack){
            StdOut.print(s+" ");
        }
        StdOut.println();

        ResizingArrayStack<String> copyStack = new ResizingArrayStack<>();
        copyStack = ResizingArrayStack.copy(stack);
        for(String cs:copyStack){
            StdOut.print(cs+" ");
        }
    }

}
