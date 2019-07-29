package textbook.chapter3_1;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class BinarySearchST<Key extends Comparable<Key>,Value>{
    private Key[] keys;
    private Value[] vals;
    private int N;
    public BinarySearchST(int capacity){
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }
    public Value get(Key key){
        if(isEmpty())
            return null;
        int i = rank(key);
        if(i<N && key.compareTo(keys[i])==0)
            return vals[i];
        else
            return null;
    }
    public void put(Key key,Value val){
        int i = rank(key);
        if(i<N && key.compareTo(keys[i])==0){
            vals[i] = val;  // 符号表中已经存在键key,更新其对应的value
            return;
        }
        for(int j=N;j>i;j--){
            keys[j] = keys[j-1];
            vals[j] = vals[j-1];
        }
        keys[i] = key;
        vals[i] = val;
        N++;
    }
    public int rank(Key key){
        int lo = 0,hi = N-1;
        while (lo<=hi){
            int mid = lo + (hi - lo)/2;
            int cmp = key.compareTo(keys[mid]);
            if(cmp<0)
                hi = mid-1;
            else if(cmp>0)
                lo = mid+1;
            else
                return mid;
        }
        return lo;
    }
    public boolean isEmpty(){
        return size()==0;
    }
    public int size(){
        return N;
    }
    public Iterable<Key> keys(){
        Queue<Key> queue = new Queue<>();
        for(int i=0;i<size();i++){
            queue.enqueue(keys[i]);
        }
        return queue;
    }
    public void show(){
        for(int i=0;i<size();i++){
            StdOut.println(keys[i]+" "+vals[i]);
        }
    }
    // 测试用例
    public static void main(String[] args){
        BinarySearchST<String,Integer> st = new BinarySearchST<>(20);
        String[] arr = {"S","E","A","R","C","H","E","X","A","M","P","L","E"};
        for(int i=0;i<arr.length;i++){
            st.put(arr[i],i);
        }
        // st.show();
        for(String key:st.keys()){
            StdOut.println(key+" "+st.get(key));
        }
    }
}
