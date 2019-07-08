package textbook.chapter2_4;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Transaction;
import textbook.chapter1_3_3.Stack;

public class UnorderedArrayMaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N=0;
    public UnorderedArrayMaxPQ(){
        pq = (Key[])new Comparable[1];
    }
    public UnorderedArrayMaxPQ(int max){
        pq = (Key[])new Comparable[max];
    }
    public UnorderedArrayMaxPQ(Key[] b){
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
    public void insert(Key key){
        if(N==pq.length){
            resize(2*pq.length);
        }
        pq[N++] = key;
    }
    public Key max(){
        int maxIndex = 0;
        for(int i=1;i<N;i++){
            if(pq[i].compareTo(pq[maxIndex])>0){
                maxIndex = i;
            }
        }
        return pq[maxIndex];
    }
    public Key delMax(){
        int maxIndex = 0;
        for(int i=1;i<N;i++){
            if(pq[i].compareTo(pq[maxIndex])>0){
                maxIndex = i;
            }
        }
        Key maxKey = pq[maxIndex];
        N--;
        // if(N>0&&N==pq.length/4) resize(pq.length/2);加上这个对输出有影响
        // exch(maxIndex,N); // 其实用不上exch,因为反正要把pq[N]赋为null,而且已经拿到了最大值，所以只需要把pq[N]的值保留下来到最大索引处即可
        pq[maxIndex] = pq[N];
        pq[N] = null;
        return maxKey;
    }
    private void exch(int i,int j){
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
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
