package textbook.chapter5_2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import textbook.chapter1_3_3.Queue;

/**
 * 基于单词查找树的符号表
 */
public class TrieST<Value> {
    private static int R = 256; // 基数
    private Node root; // 单词查找树的根结点

    private static class Node{
        private Object val;
        private Node[] next = new Node[R]; // 每个结点都含有R个链接，对应每个可能出现的字符
    }
    // 获得键key所对应的值，如果键不存在则返回null
    public Value get(String key){
        Node x = get(root, key, 0);
        if(x == null){
            return null;
        }
        return (Value) x.val;
    }
    // 返回以x作为根结点的子单词查找树中与key相关联的值
    private Node get(Node x, String key, int d){
        if(x == null){
            return null;
        }
        if(d == key.length()){
            return x;
        }
        char c = key.charAt(d);
        return get(x.next[c], key, d + 1);
    }
    // 向表中插入键值对（如果值为null则删除键key及其对应的值）
    public void put(String key, Value val){
        root = put(root, key, val, 0);
    }
    // 先查找，如果key存在于以x为根结点的子单词查找树中则更新与它相关联的值
    private Node put(Node x, String key, Value val, int d){
        if(x == null){
            x = new Node();
        }
        if(d == key.length()){
            x.val = val;
            return x;
        }
        char c = key.charAt(d); // 找到第d个字符所对应的子单词查找树
        x.next[c] = put(x.next[c], key, val, d + 1);
        return x; // 没想到
    }
    // 键值对的数量
    public int size(){
        return size(root);
    }
    // 单词查找树的延时递归方法size()，即遍历单词查找树的所有结点并记录非空值结点的总数(实际使用时不推荐这种方式，
    // 因为会给用例造成性能上的问题)
    private int size(Node x){
        if(x == null){
            return 0;
        }
        int cnt = 0;
        if(x.val != null){
            cnt++;
        }
        for(char c = 0; c < R; c++){  // 注意此处，会将字符c转换成相应的编码再跟R比较
            cnt += size(x.next[c]);   // 这里写的时候只写了右半部分，那每次递归调用size()的时候都会把cnt清零！
        }
        return cnt;
    }
    // 符号表中的所有键
    public Iterable<String> keys(){
        return keysWithPrefix("");
    }
    // 所有以pre为前缀的键
    public Iterable<String> keysWithPrefix(String pre){
        Queue<String> q = new Queue<>();
        Node childTrie = get(root, pre, 0);
        collect(childTrie, pre, q);
        return q;
    }
    // 递归地访问结点来找到并显式地表示键(pre为从根路径到结点x上的所有字符)
    private void collect(Node x, String pre, Queue<String> q){
        if(x == null){
            return;
        }
        if(x.val != null){
            q.enqueue(pre);
        }
        for(char c = 0; c < R; c++){
            collect(x.next[c], pre + c, q);
        }
    }

    public static void main(String[] args){
        TrieST<Integer> st = new TrieST<>();
        for(int i = 0; !StdIn.isEmpty(); i++){
            String key = StdIn.readString();
            st.put(key, i);
        }
        for(String key : st.keysWithPrefix("se")){
            StdOut.println(key);
        }
        StdOut.println();
        for(String key : st.keys()){
            StdOut.println(key);
        }
//        int size = st.size();
//        StdOut.println(size);
    }
}
