package textbook.chapter5_4;

import edu.princeton.cs.algs4.StdOut;

/**
 * NFA测试用例
 */
public class GREP {
    public static void main(String[] args){
        String regexp = "(" + args[0] + ")";
        String txt = args[1];
        NFA nfa = new NFA(regexp);
        if(nfa.recognizes(txt)){
            StdOut.println(txt);
        }else {
            StdOut.println("not recognized!");
        }
    }
}
