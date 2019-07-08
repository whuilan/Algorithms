package textbook.chapter2_4;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Transaction;

public class OrderecListMaxPQ<Key extends Comparable<Key>> {
    private Node first;
    private int N;
    private class Node{
        Key key;
        Node next;
    }
    public boolean isEmpty(){
        return first==null;
    }
    public int size(){
        return N;
    }
    public void insert(Key key){
        Node oldFirst = first;
        first = new Node();
        first.key = key;
        first.next = oldFirst;
        for(Node x=first;x.next!=null;x=x.next){
            if(x.key.compareTo(x.next.key)<0){
                exch(x,x.next);
            }
        }
        N++;
    }
    private void exch(Node x,Node y){
        Key temp = x.key;
        x.key = y.key;
        y.key = temp;
    }
    public Key delMax(){
        Key maxKey = first.key;
        first = first.next;
        N--;
        return maxKey;
    }
    public static void main(String[] args){
        OrderecListMaxPQ<Transaction> pq = new OrderecListMaxPQ<>();
        while (!StdIn.isEmpty()){
            pq.insert(new Transaction(StdIn.readLine()));
            while (pq.size()>5){
                pq.delMax();
            }
        }
        Stack<Transaction> stack = new Stack<>();
        while (!pq.isEmpty()) {
            stack.push(pq.delMax());
        }
        for(Transaction t:stack){
            StdOut.println(t);
        }
    }
}
