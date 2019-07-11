package textbook.chapter2_4;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Transaction;
import textbook.chapter1_3_3.Stack;

public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N=0;
    public MaxPQ(int maxN){
        pq = (Key[]) new Comparable[maxN+1];
    }
    public boolean isEmpty(){
        return N == 0;
    }
    public int size(){
        return N;
    }
    public void insert(Key key){
        pq[++N] = key;
        swim(N);
    }
    public Key delMax(){
        Key maxKey = pq[1];
        pq[1] = pq[N];
        pq[N] = null;
        N--;
        sink(1);
        return maxKey;
    }
    public boolean less(int i,int j){
        return pq[i].compareTo(pq[j])<0;
    }
    public void exch(int i,int j){
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }
    public void swim(int k){
        while (k>1 && less(k/2,k)) { // 因为k要和k/2比较，所以要满足k/2>=1(根结点索引)，则k>=2,即k>1
            exch(k/2,k);
            k = k/2;
        }
    }
    public void sink(int k){
        while (2*k<N){ // 因为k要和2k或者2k+1比较，所以要满足2k+1<=N（最后一个结点），即2k<N
            int j = 2*k;
            if(less(j,j+1)){
                j++;
            }
            if(!less(k,j))
                break;
            exch(k,j);
            k = j;
        }
    }
    public static void main(String[] args){
        MaxPQ<Transaction> pq = new MaxPQ<>(6);
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
