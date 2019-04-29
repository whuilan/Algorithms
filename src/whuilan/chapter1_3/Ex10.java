package whuilan.chapter1_3;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Ex10 {
    public static void InfixToPostfix(String infix){
        String[]  infixs = infix.split(" ");
        Stack<String> ops = new Stack<>();
        Stack<String> exps = new Stack<>();
        for(int i=0;i<infixs.length;i++){
            String s = infixs[i];
            if (s.equals("(")){}
            else if(s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")){
                ops.push(s);
            }
            else if(s.equals(")")){
                String exp1 = exps.pop();
                String exp2 = exps.pop();
                String op = ops.pop();
                exps.push(exp2+" "+exp1+" "+op);
            }else{
                exps.push(s);
            }
        }
        StdOut.println(exps.pop());
//        for(String exp:exps){
//            StdOut.print(exp+" ");
//        }
    }

    public static void main(String[] args){
         String str = "( ( 1 + 2 ) * ( ( 3 - 4 ) * ( 5 - 6 ) ) )"; // 输出：1 2 + 3 4 - 5 6 - * *
        // String str = "( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )"; // 输出：1 2 3 + 4 5 * * +
        InfixToPostfix(str);
    }
}
