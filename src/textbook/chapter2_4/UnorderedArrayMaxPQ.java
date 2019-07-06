package textbook.chapter2_4;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Transaction;
import textbook.chapter1_3_3.Stack;

public class UnorderedArrayMaxPQ<Key extends Comparable<Key>> {
    private Key[] a;
    private int N=0;
    public UnorderedArrayMaxPQ(){
        a = (Key[])new Comparable[1];
    }
    public UnorderedArrayMaxPQ(int max){
        a = (Key[])new Comparable[max];
    }
    public UnorderedArrayMaxPQ(Key[] b){
        a = b;
    }
    public boolean isEmpty(){
        return N==0;
    }
    public int size(){
        return N;
    }
    public void resize(int max){
        Key[] temp = (Key[])new Comparable[max];
        for(int i=0;i<N;i++){
            temp[i] = a[i];
        }
        a = temp;
    }
    public void insert(Key key){
        if(N==a.length){
            resize(2*a.length);
        }
        a[N++] = key;
    }
    public Key max(){
        int maxIndex = 0;
        for(int i=1;i<N;i++){
            if(a[i].compareTo(a[maxIndex])>0){
                maxIndex = i;
            }
        }
        return a[maxIndex];
    }
    public Key delMax(){
        int maxIndex = 0;
        for(int i=1;i<N;i++){
            if(a[i].compareTo(a[maxIndex])>0){
                maxIndex = i;
            }
        }
        Key maxKey = a[maxIndex];
        N--;
        exch(maxIndex,N);
        a[N] = null;
        return maxKey;
    }
    private void exch(int i,int j){
        Key temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args){
        UnorderedArrayMaxPQ<Transaction> pq = new UnorderedArrayMaxPQ<>(6);
        while (!StdIn.isEmpty()){
            pq.insert(new Transaction(StdIn.readLine()));
            if(pq.size()>5){
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
