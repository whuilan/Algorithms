package textbook.chapter3_3;

import edu.princeton.cs.algs4.StdOut;
import textbook.chapter3_2.BST;

public class RedBlackBST<Key extends Comparable<Key>,Value> {

    private Node root;
    private static boolean RED = true;
    private static boolean BLACK = false;

    private class Node{
        Key key;
        Value val;
        Node left,right;
        int N;
        boolean color;
        public Node(Key key,Value val,int N,boolean color){
            this.key = key;
            this.val = val;
            this.N = N;
            this.color = color;
        }
    }
    public boolean isRed(Node x){
        if(x==null){
            return false;
        }
        return x.color == RED;
    }
    public Node rotateLeft(Node h){
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1+size(h.left)+size(h.right);
        return x;
    }
    public Node rotateRight(Node h){
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1+size(h.left)+size(h.right);
        return x;
    }
    public int size(){
        return size(root);
    }
    public void flipColors(Node h){
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }
    private int size(Node x){
        if(x==null){
            return 0;
        }
        return x.N;
    }
    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        // 在以x为结点的子树中查找并返回key对应的值
        // 如果找不到则返回null
        if (key == null) throw new IllegalArgumentException("calls get() with a null key");
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            return get(x.left, key);
        else if (cmp > 0)
            return get(x.right, key);
        else
            return x.val;
    }

    public void put(Key key,Value val){
        root = put(root,key,val);
        root.color = BLACK;
    }
    private Node put(Node h,Key key,Value val){
        if(h==null){
            return new Node(key,val,1,RED);
        }
        int cmp = key.compareTo(h.key);
        if(cmp<0){
            h.left = put(h.left,key,val);
        }
        else if(cmp>0){
            h.right = put(h.right,key,val);
        }
        else{
            h.val = val;
        }
        // 如果右子结点为红色而左子结点为黑丝，则只需要进行左旋
        if(isRed(h.right)&&!isRed(h.left)){
            h = rotateLeft(h);
        }
        // 如果左子结点和该左子结点的左子结点均为红色（即出现两条连续的红链接），进行右旋转
        if(isRed(h.left)&&isRed(h.left.left)){
            h = rotateRight(h);
        }
        // 如果右子结点与左子结点均为红色，则进行颜色翻转，并将父结点变为红色（不影响树的黑色平衡性）
        if(isRed(h.right)&&isRed(h.left)){
            flipColors(h);
        }

        h.N = size(h.left)+size(h.right)+1;
        return h;
    }
    
    // 测试用例
    public static void main(String[] args) {
        RedBlackBST<String,Integer> rbBst = new RedBlackBST<>();
        String[] arr = {"S", "E", "A", "R", "C", "H", "E", "X", "A", "M", "P", "L", "E"};
        // String[] arr = {"A", "C", "E", "H", "L", "M", "P",  "R", "S", "X"};
        for (int i = 0; i < arr.length; i++) {
            rbBst.put(arr[i], i);
        }
        int v = rbBst.get("E");
        System.out.println(v);
//        rbBst.delete("M");
//        int size = rbBst.size();
//        StdOut.println("The size of the tree is:"+size);
//        for (String key : rbBst.keys()) {
//            StdOut.println(key + " " + rbBst.get(key));
//        }
    }
}
