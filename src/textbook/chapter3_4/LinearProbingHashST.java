package textbook.chapter3_4;

import edu.princeton.cs.algs4.StdOut;
import textbook.chapter1_3_3.Queue;

public class LinearProbingHashST<Key, Value> {
    private int N;   // 键值对的数目
    private int M;   // 线性探测表的大小 注意：M > N
    private static final int INIT_CAPACITY = 4;
    private Key[] keys;   // 存放键的数组
    private Value[] vals;   // 存放值的数组

    public LinearProbingHashST(){ this(INIT_CAPACITY); }
    public LinearProbingHashST(int M){
        this.M = M;
        keys = (Key[]) new Object[M];
        vals = (Value[]) new Object[M];
    }

    public int size(){
        return N;
    }
    public boolean isEmpty(){
        return size() == 0;
    }
    public boolean contains(Key key){
        return get(key) != null;
    }

    private int hash(Key key){
        return (key.hashCode() & 0x7fffffff) % M;
    }

    private void resize(int newLength){
        Key[] newKeys = (Key[]) new Object[newLength];
        Value[] newVals = (Value[]) new Object[newLength];
        for(int i = 0; i< M; i++){
            newKeys[i] = keys[i];
            newVals[i] = vals[i];
        }
        keys = newKeys;
        vals = newVals;
        this.M = newLength;
    }

    public void put(Key key,Value val){
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");

        if (val == null) {
            delete(key);
            return;
        }
        // 每次put操作之前应该检查散列表是否达到了半满状态（利用率为1/2），如果是则进行放大
        if(N >= M/2){
            resize(2*M);
        }
        int i;
        // 将key与key的散列值（索引）所对应的键进行比较，不为空时，相等则替换值，否则就一直增大索引继续比较，直到键为空插入新键
        for(i = hash(key); keys[i] != null; i = (i+1) % M ){ // 注意此处的变量自增进行了“取余”，是为了当循环到了末尾键还不为空时折回数组开头
            if(key.equals(keys[i])){
                vals[i] = val;
                return;
            }
        }
        keys[i] = key;
        vals[i] = val;
        N++;
    }

    public Value get(Key key){
        for(int i = hash(key); keys[i] != null; i = (i + 1) % M){
            if(key.equals(keys[i])){
                return vals[i];
            }
        }
        return null;
    }

    public void delete(Key key){
        if(!contains(key)){
            return;
        }
        int i = hash(key);
        // 如果被查找的键和其散列值索引处对应的键不同，则继续往下查找
        while (!key.equals(keys[i])){
            i = (i + 1) % M;
        }
        keys[i] = null;
        vals[i] = null;
        // 将被查找的键值对赋为空之后，要检查它后面是否有键值对，并将每个键值对重新归位
        i = (i + 1) % M;
        while (keys[i] != null){
            Key resetKey = keys[i];
            Value restVal = vals[i];
            keys[i] = null;
            vals[i] = null;
            N--;
            put(resetKey,restVal);
            i = (i + 1) % M;
        }
        N--;
        if(N>0 && N <= M/8){
            resize(M/2);
        }
    }

    public Iterable<Key> keys(){
        Queue<Key> queue =  new Queue<>();
        for(int i = 0; i < M; i++){
            if(keys[i] != null){
                queue.enqueue(keys[i]);
            }
        }
        return queue;
    }

    public static void main(String[] args){
        LinearProbingHashST<String, Integer> st = new LinearProbingHashST<>();
        String[] arr ={"S","E","A","R","C","H","E","X","A","M","P","L","E"};
        for(int i=0; i<arr.length; i++){
            st.put(arr[i],i);
        }
        StdOut.println("LinearProbingHashST intialization accomplished");
        int val = st.get("C");
        StdOut.println(val);
        st.delete("S");
    }

}
