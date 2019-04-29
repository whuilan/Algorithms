package whuilan.chapter1_3;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Ex04 {
    public static void main(String[] args){
        Stack<String> opsLeft = new Stack<>();
        boolean isMatched = true;
        while (!StdIn.isEmpty()){
            String s = StdIn.readString();
            if(s.equals("(") || s.equals("[") || s.equals("{")) opsLeft.push(s);
            else if(s.equals(")")){
                String l = opsLeft.pop();
                if(!l.equals("(")){
                    isMatched = false;
                    break;
                }
            }
            else if(s.equals("]")){
                String l = opsLeft.pop();
                if(!l.equals("[")){
                    isMatched = false;
                    break;
                }
            }
            else if(s.equals("}")){
                String l = opsLeft.pop();
                if(!l.equals("{")){
                    isMatched = false;
                    break;
                }
            }else{
                // do nothing
            }
        }
        if(!opsLeft.isEmpty()) isMatched = false;
        if(isMatched) StdOut.println(true);
        else StdOut.println(false);
    }
}
