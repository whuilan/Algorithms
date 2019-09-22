package textbook.chapter3_4;

import textbook.chapter1_3_3.Queue;
import textbook.chapter3_1.SequentialSearchST;

public class SeparateChainingHashST<Key,Value> {
    private int N; //键值对总数
    private int M; //散列表大小
    private SequentialSearchST<Key,Value>[] st; // 存放链表对象的数组;

    public SeparateChainingHashST(){  // 注意：构造函数后面不用再加上泛型说明
        this(997);  // 注意：此处的this即转到下面有参构造函数中执行
    }
    public SeparateChainingHashST(int M){
        // 创建M条链表(即将存放链表对象的数组大小设置为M),每个数组元素对应一条链表。数组的索引即键的散列值
        this.M = M;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        // 注意：初始化完st的大小之后，要对每个数组元素进行初始化创建一个新的SequentialSearchST，不然每个数组元素会取默认值null,
        // 这样就不能直接进行put()和get()操作
        for(int i=0;i<M;i++){
            st[i] = new SequentialSearchST<>();
        }
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
    public Value get(Key key){
        int i = hash(key);
        return  st[i].get(key);
    }
    public void put(Key key,Value val){
        int i = hash(key);
        if(!st[i].contains(key)){
            N++;
        }
        st[i].put(key,val);
    }
    public void delete(Key key){
        int i = hash(key);
        if(st[i].contains(key)){
            N--;
        }
        st[i].delete(key);
    }
    public Iterable<Key> keys(){
        Queue<Key> queue = new Queue<>();
        for(int i = 0; i < M; i++){
            for(Key key:st[i].keys()){
                queue.enqueue(key);
            }
        }
        return queue;
    }

    public static void main(String[] args){
        SeparateChainingHashST<String,Integer> st = new SeparateChainingHashST<>(5);
        String[] arr ={"S","E","A","R","C","H","E","X","A","M","P","L","E"};
        for(int i=0; i<arr.length; i++){
            st.put(arr[i],i);
        }
        System.out.println("intialization accomplished");
        int val = st.get("E");
        System.out.println(val);
    }

}
