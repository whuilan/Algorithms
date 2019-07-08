package textbook.chapter2_4;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Transaction;
import textbook.chapter1_3_3.Stack;

public class UnorderedListMaxPQ<Key extends Comparable<Key>>{
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
        N++;
    }
    public Key delMax(){
        Node maxNode = first;
        for(Node x=first.next;x!=null;x=x.next){
            if(x.key.compareTo(maxNode.key)>0){
                maxNode = x;
            }
        }
        Key maxKey = maxNode.key;
        maxNode.key = first.key;
        first = first.next;
        N--;
        return maxKey;
    }
    public static void main(String[] args){
        UnorderedListMaxPQ<Transaction> pq = new UnorderedListMaxPQ<>();
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
