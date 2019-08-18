package textbook.chapter3_1;

import edu.princeton.cs.algs4.StdOut;
import textbook.chapter1_3_3.Queue;

import java.util.NoSuchElementException;

public class BinarySearchST<Key extends Comparable<Key>,Value>{
    private Key[] keys;
    private Value[] vals;
    private int N;
    public BinarySearchST(int capacity){
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }
    public void resize(int max){
        Key[] tempk = (Key[]) new Comparable[max];
        Value[] tempv = (Value[]) new Object[max];
        for(int i=0;i<N;i++){
            tempk[i] = keys[i];
            tempv[i] = vals[i];
        }
        keys = tempk;
        vals = tempv;
    }
    public Value get(Key key){
        if(isEmpty())
            return null;
        int i = rank(key);
        if(i<N && key.compareTo(keys[i])==0)
            return vals[i];
        return null;
    }
    public void put(Key key,Value val){
        if(val == null){
            delete(key);
            return;
        }
        int i = rank(key);
        if(i<N && key.compareTo(keys[i])==0){
            vals[i] = val;  // 符号表中已经存在键key,更新其对应的value
            return;
        }
        // 插入新的值之前检查数组是否已满，是否需要调整大小
        if(N == keys.length)
            resize(2*keys.length);
        // 插入新元素之前将所有比插入元素大的键向后移动一格使插入的键和值在合适的位置
        for(int j=N;j>i;j--){
            keys[j] = keys[j-1];
            vals[j] = vals[j-1];
        }
        keys[i] = key;
        vals[i] = val;
        N++;
    }
    // 从表中删除key（及其对应的值）
    public void delete(Key key){
        if(isEmpty()) return;
        int i = rank(key);
        // 如果存在键则将键右侧的键依次向左移动一位
        if(i<N && key.compareTo(keys[i])==0){
            for(int j=i;j<N-1;j++){
                keys[j] = keys[j+1];
                vals[j] = vals[j+1];
            }
            N--;
            keys[N] = null; // 防止对象游离
            vals[N] = null;
            if(N>0 && N == keys.length/4)
                resize(keys.length/2);
        }
        return;
    }
    // 小于key的键的数量（以及键在表中的位置，如果键存在于表中）
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
    // 排名为k的键(注意排名从0排起，故排名为k意味着key前面有k个人)
    public Key select(int k){
        if (k < 0 || k >= size()) {
            throw new IllegalArgumentException("called select() with invalid argument: " + k);
        }
        return keys[k];
    }
    public boolean contains(Key key){
        return get(key)!=null;
    }
    public boolean isEmpty(){
        return size()==0;
    }
    public int size(){
        return N;
    }
    public Key min(){
        if (isEmpty())
            throw new NoSuchElementException("called min() with empty symbol table");
        return keys[0];
    }
    public Key max(){
        if (isEmpty())
            throw new NoSuchElementException("called max() with empty symbol table");
        return keys[N-1];
    }
    // 返回小于等于key的最大键
    public Key floor(Key key){
        int i = rank(key);
        if(i<N && key.compareTo(keys[i])==0){
            return keys[i];
        }
        if(i==0)
            return null;
        else
            return keys[i-1];
    }
    // 返回大于等于key的最小键,需注意和floor的不同之处，只要i在0~N-1之间，不管是否查找到键，返回都是keys[i]
    public Key ceiling(Key key){
        int i = rank(key);
        if(i==N){
            return null;
        }
        else
            return keys[i];
    }
    // 删除最小的键
    public void deleteMin(){
        delete(min());
    }
    // 删除最大的键
    public void deleteMax(){
        delete(max());
    }
    // [lo..hi]之间的键的数量
    public int size(Key lo,Key hi){
       if(lo.compareTo(hi)>0)
           return 0;
       if(contains(hi))
           return rank(hi)-rank(lo)+1;
       else
           return rank(hi)-rank(lo);
    }
    // 表中所有键的集合，已排序
    public Iterable<Key> keys(){
        Queue<Key> queue = new Queue<>();
        for(int i=0;i<size();i++){
            queue.enqueue(keys[i]);
        }
        return queue;
    }
    // [lo..hi]之间的所有键，已排序
    public Iterable<Key> keys(Key lo,Key hi){
        Queue<Key> queue = new Queue<>();
        if(lo.compareTo(hi)>0) return queue;
        // 不管lo是否存在于符号表中，都是从rank(lo)开始。hi不存在于表中时，不能取到rank(hi)
        for(int i=rank(lo);i<rank(hi);i++){
            queue.enqueue(keys[i]);
        }
        // 若hi存在于表中，则应加上rank(hi)
        if(contains(hi))
            queue.enqueue(keys[rank(hi)]);
        return queue;
    }
    // 测试用例
    public static void main(String[] args){
        BinarySearchST<String,Integer> st = new BinarySearchST<>(20);
        String[] arr = {"S","E","A","R","C","H","E","X","A","M","P","L","E"};
        for(int i=0;i<arr.length;i++){
            st.put(arr[i],i);
        }
        // st.delete("L");
        for(String key:st.keys()){
            StdOut.println(key+" "+st.get(key));
        }
        StdOut.print(st.size("A","N"));
    }
}
