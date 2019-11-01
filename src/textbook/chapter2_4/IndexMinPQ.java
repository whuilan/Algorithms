package textbook.chapter2_4;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Transaction;

import java.util.NoSuchElementException;

/**
 * 关联索引的泛型优先队列
 */
public class IndexMinPQ<Key extends Comparable<Key>> {
    private int maxN;    // 优先队列的大小，即所能容纳元素的最大数量
    private int N;       // 优先队列中实际有多少个元素
    private int[] pq;    // 索引二叉堆，由1开始
    private int[] qp;    // pq的逆序：qp[i]的值是i在pq中的位置，即qp[pq[i]] = pq[qp[i]] = i
    private Key[] keys;  // 有优先级之分的元素

    public IndexMinPQ(int maxN){
        this.maxN = maxN;
        N = 0;
        pq = new int[maxN + 1];
        qp = new int[maxN + 1];
        keys = (Key[]) new Comparable[maxN + 1];
        for(int i = 0; i <= maxN; i++){
            qp[i] = -1;          // 判断i是否在队列pq中，初始化时队列为空，因此qp[]的值均为-1
        }
    }
    public boolean contains(int i){
        return qp[i] != -1;
    }
    public boolean isEmpty(){
        return N == 0;
    }
    public int size(){
        return N;
    }
    public void insert(int i, Key key){
        if(i < 0 || i >= maxN){ // 注意索引的范围必须在0到maxN-1之间:0 <= i < maxN
            throw new IllegalArgumentException("i must be between 0 and maxN-1");
        }
        if(contains(i)){
            throw new IllegalArgumentException("index is already in the priority");
        }
        N++;
        pq[N] = i;
        qp[i] = N;
        keys[i] = key;
        swim(N);
    }
    private void swim(int k){
        if(k > 1 && greater(k/2, k)){
            exch(k/2, k);
            k = k / 2;
        }
    }
    private void sink(int k){
        while (2 * k <= N){
            int j = 2 * k;
            if(j < N && greater(j,j+1)){
                j++;
            }
            if(!greater(k,j)){
                break;
            }
            exch(k, j);
            k = j;
        }
    }
    private boolean greater(int i, int j){
        return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
    }
    private void exch(int i, int j){
        int t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }
    // 返回最小元素的索引
    public int minIndex(){
        if(N == 0){
            throw new NoSuchElementException("Priority queue underflow");
        }
        return pq[1];
    }
    // 返回最小元素
    public Key minKey(){
        if(N == 0){
            throw new NoSuchElementException("Priority queue underflow");
        }
        return keys[pq[1]];
    }
    // 删除最小元素并返回它的索引
    public int deleteMin(){
        if(N == 0){
            throw new NoSuchElementException("Priority queue underflow");
        }
        int minInd = pq[1];
        exch(1,N--);   // 注意交换第一个元素和最后一个元素后，要将N-1再往下沉，不然sink中可能会将元素沉到N的位置
        sink(1);
        pq[N+1] = -1;
        keys[minInd] = null;
        qp[minInd] = -1;
        return minInd;
    }
    // 删去索引k及其相关联的元素
    public void delete(int k){
        if (k < 0 || k >= maxN) throw new IllegalArgumentException();
        if(!contains(k)){
            throw new NoSuchElementException("index is not in the priority queue");
        }
        int i = qp[k];
        exch(i,N--);
        swim(i);
        sink(i);  // 注意上浮和下沉这两种操作只会执行一个
        keys[k] = null;
        qp[k] = -1;
    }
    // 将索引为k的元素改为key,注意改完之后会破坏二叉堆的顺序，因此需要恢复顺序
    public void change(int k, Key key){
        if (!contains(k)) throw new NoSuchElementException("index is not in the priority queue");
        keys[k] = key;
        int i = qp[k];
        swim(i);
        sink(i);
    }

    public static void main(String[] args){
        double[] amounts = {644.08, 837.42, 66.10, 2520.97, 708.95, 1025.70};
        IndexMinPQ<Double> impq = new IndexMinPQ<>(amounts.length);
        for(int i = 0; i < amounts.length; i++){
            impq.insert(i , amounts[i]);
        }
        // impq.change(3, 888.88);
        while (!impq.isEmpty()){
            int i = impq.deleteMin();
            StdOut.println(i + " " + amounts[i]);
        }
    }
}
