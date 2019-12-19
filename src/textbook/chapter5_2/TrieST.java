package textbook.chapter5_2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

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

    public static void main(String[] args){
        TrieST<Integer> st = new TrieST<>();
        for(int i = 0; !StdIn.isEmpty(); i++){
            String key = StdIn.readString();
            st.put(key, i);
        }
        String key = "by";
        int val = st.get(key);
        StdOut.println(val);
    }
}
