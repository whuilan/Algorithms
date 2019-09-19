package textbook.chapter3_4;

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
    private int hash(Key key){
        return (key.hashCode() & 0x7fffffff) % M;
    }
    public Value get(Key key){
        return (Value) st[hash(key)].get(key);
    }
    public void put(Key key,Value val){
        st[hash(key)].put(key,val);
    }
    // public Iterable<Key> keys(){return null;}
}
