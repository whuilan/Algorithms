package exercises.chapter1_3;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Ex11 {
    public static double EvaluatePostfix(String postfix){
       String[] posts = postfix.split(" ");
       Stack<Double>  vals = new Stack<>();
       for(int i = 0;i < posts.length;i++){
           String s = posts[i];
           if(s.equals("(")) {}
           else if(s.equals("+")){
               double val = vals.pop();
               double out = vals.pop() + val;
               vals.push(out);
           }
           else if(s.equals("-")){
               double val = vals.pop();
               double out = vals.pop() - val;
               vals.push(out);
           }
           else if(s.equals("*")){
               double val = vals.pop();
               double out = vals.pop() * val;
               vals.push(out);
           }
           else if(s.equals("/")){
               double val = vals.pop();
               double out = vals.pop() / val;
               vals.push(out);
           }
           else{
               vals.push(Double.parseDouble(s));
           }
       }
       return vals.pop();
    }

    public static void main(String[] args){
        // String str = "1 2 3 + 4 5 * * +"; // 输出：101.0
        String str = "1 2 + 3 4 - 5 6 - * *"; // 输出： 3.0
        StdOut.println(EvaluatePostfix(str));
    }
}
