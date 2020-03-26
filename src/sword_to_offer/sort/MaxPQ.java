package sword_to_offer.sort;

/**
 * 基于堆的优先队列：优先队列由一个基于堆的完全二叉树表示，存储于数组pq[1..N]中，
 * pq[0]没有使用。对于一个含N个元素的优先队列，插入元素的操作需要不超过(lgN+ 1)次比较，
 * 删除最大元素的操作需要不超过2lgN次比较
 */
public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N = 0;

    public MaxPQ(int maxN){
        pq = (Key[]) new Comparable[maxN + 1];
    }

    public void insert(Key key){
        pq[++N] = key;  // 记住索引是从1开始的！0处没有放元素！
        swim(N);
    }

    public Key delMax(){
        Key max = pq[1];
        exch(1, N);
        pq[N] = null;
        N--;
        sink(1);
        return max;
    }

    private void swim(int k){
        while (k > 1 && less(k/2, k)){
            exch(k/2, k);
            k = k / 2;
        }
    }

    private void sink(int k){
        while (2 * k <= N){
            int j = 2 * k;
            if (j < N && less(j, j+1)){
                j += 1;
            }
            if (!less(k, j)){
                break;
            }
            exch(k, j);
            k = j;
        }
    }

    private boolean less(int i, int j){
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j){
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }
}
