package textbook.chapter2_4;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Transaction;
import textbook.chapter1_3_3.Stack;

public class OrderedArrayMaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N=0;
    public OrderedArrayMaxPQ(){
        pq = (Key[]) new Comparable[1];
    }
    public OrderedArrayMaxPQ(int max){
        pq = (Key[])new Comparable[max];
    }
    public OrderedArrayMaxPQ(Key[] b){
        pq = b;
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
            temp[i] = pq[i];
        }
        pq = temp;
    }
    /*和插入排序的区别在于只需要一层循环，因为栈的大小N就是从零开始逐步增大的，所以没一次insert操作就相当于插入排序中的外层循环i增加了一层，
    * 其实就是插入排序*/
    public void insert(Key key) {
        if (N == pq.length) {
            resize(2 * pq.length);
        }
        pq[N] = key;
        for (int j = N; j > 0 && (pq[j].compareTo(pq[j - 1])) < 0; j--) {
            exch(j,j-1);
        }
        N++;
    }
    private void exch(int i,int j){
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }
    public Key max(){
        return pq[--N];
    }
    public Key delMax(){
        Key maxKey = pq[--N];
        pq[N]=null;
        if(N>0 && N==pq.length/4){
            resize(pq.length/2);
        }
        return maxKey;
    }
    public static void main(String[] args){
        OrderedArrayMaxPQ<Transaction> pq = new OrderedArrayMaxPQ<>(6);
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
