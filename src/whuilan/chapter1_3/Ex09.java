package whuilan.chapter1_3;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Ex09 {
    public static void main(String[] args){
        Stack<String> ops = new Stack<>();
        Stack<String> exp = new Stack<>();
        while(!StdIn.isEmpty()){
            String s = StdIn.readString();
            if(s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")){
                ops.push(s);
            }else if(s.equals(")")){
                String s1 = exp.pop();
                String s2 =  exp.pop();
                String op = ops.pop();
                exp.push("( "+s2+" "+op+" "+s1+" )");
            }else{
                exp.push(s);
            }
        }
        StdOut.println(exp.pop());
    }
}
