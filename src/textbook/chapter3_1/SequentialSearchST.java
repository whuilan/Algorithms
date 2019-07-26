package textbook.chapter3_1;


import edu.princeton.cs.algs4.StdOut;
import textbook.chapter1_3_3.Queue;

public class SequentialSearchST<Key,Value> {
    private Node first;  //第一对键值对所在的首结点
    private int N; // 键值对数量
    private class Node{
        Key key;
        Value val;
        Node next;
        public Node(Key key,Value val,Node next){
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }
    public void put(Key key,Value val){
        if(val == null){
            delete(key);
            return;
        }
        for(Node x=first;x!=null;x=x.next){
            if(key.equals(x.key)){
                x.val = val;
                return;
            }
        }
        first = new Node(key,val,first);
        N++;
    }
    public Value get(Key key){
        for(Node x=first;x!=null;x=x.next){
            if (key.equals(x.key))
                return x.val;
        }
        return null;
    }
    public void delete(Key key){
        if(first==null) return;
        // 要删除的键值对刚好是首结点
        if(key.equals(first.key)){
            first = first.next;
            N--;
            return;
        }
        for(Node x=first;x.next!=null;x=x.next){
            if(key.equals(x.next.key)){
                x.next = x.next.next; // 删除紧邻x结点的结点
                N--;
                return;
            }
        }
    }
    public boolean contains(Key key){
        return get(key)!=null;
    }
    public boolean isEmpty(){
        return size() == 0;
    }
    public int size(){
        return N;
    }
    public Iterable<Key> keys(){
        Queue<Key> queue = new Queue<>();
        for(Node x=first;x!=null;x=x.next){
            queue.enqueue(x.key);
        }
        return queue;
    }
    public static void main(String[] args){
        SequentialSearchST<String,Integer> st = new SequentialSearchST<>();
        String[] arr = {"S","E","A","R","C","H","E","X","A","M","P","L","E"};
        for(int i=0;i<arr.length;i++){
            String key = arr[i];
            st.put(key,i);
        }
        st.delete("B");
        for(String s:st.keys()){
            StdOut.println(s+" "+st.get(s));
        }
    }
}
