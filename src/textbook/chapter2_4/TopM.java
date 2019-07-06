package textbook.chapter2_4;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Transaction;
import textbook.chapter1_3_3.Stack;

public class TopM {
    public static void main(String[] arg){
        int M  = Integer.parseInt(arg[0]);
        MinPQ<Transaction> pq = new MinPQ<>(M+1);
        while (!StdIn.isEmpty()){
            pq.insert(new Transaction(StdIn.readLine()));
            if(pq.size()>M){
                pq.delMin();
            }
        }
        Stack<Transaction> stack = new Stack<>();
        while (!pq.isEmpty()) {
            stack.push(pq.delMin());
        }
        for(Transaction t:stack){
            StdOut.println(t);
        }
    }
}
