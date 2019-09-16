package textbook.chapter3_3;

import edu.princeton.cs.algs4.StdOut;
import textbook.chapter3_2.BST;

import java.util.NoSuchElementException;

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
    public int size(){
        return size(root);
    }
    private int size(Node x){
        if(x==null){
            return 0;
        }
        return x.N;
    }
    public boolean isEmpty(){
        return root == null;
    }
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("calls get() with a null key");
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        // 在以x为结点的子树中查找并返回key对应的值
        // 如果找不到则返回null,此时程序会抛出NoPointer的错误！
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
    public boolean contains(Key key){
        return get(key) != null;
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
        // 如果右子结点为红色而左子结点为黑色，则只需要进行左旋
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
    public void flipColors(Node h){
        h.color = !h.color ;
        h.left.color = !h.left.color ;
        h.right.color = !h.right.color ;
    }
    public Key min(){
        if (isEmpty()) throw new NoSuchElementException("calls min() with empty symbol table");
        return min(root).key;
    }
    private Node min(Node h){
        if(h.left == null){
            return h;
        }
        return min(h.left);
    }
    // 删除最小键，需要先沿着左链接向下进行变换，确保当前结点不是2-结点.疑问：为啥不是保证最小的那个结点不是2-结点就行？
    public void deleteMin(){
        if(isEmpty()){
            throw new NoSuchElementException("BST underflow");
        }
        // 如果根结点的两个子结点都是黑色，就将根结点设置为红色
        if(!isRed(root.left) && !isRed(root.right)){
            root.color = RED;
        }
        root = deleteMin(root);
        root.color = BLACK;
    }
    private Node deleteMin(Node h){
        if(h.left == null){
            return null;
        }
        // 两个连续的左子结点均为2-结点
        if(!isRed(h.left) && !isRed(h.left.left)){
            h = moveRedLeft(h);
        }
        h.left = deleteMin(h.left);
        return balance(h);
    }
    // 假设父子结点为红色，两个连续的左子结点均为黑色（均为2-结点），则将其中一个左子结点变红（即变为3-结点）
    private Node moveRedLeft(Node h){
        flipColors(h);
        if(isRed(h.right.left)){
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }
    private Node moveRedRight(Node h){
        flipColors(h);
        if(isRed(h.left.left)){
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }
    private Node balance(Node h){
        if(isRed(h.right) && !isRed(h.left))
            h = rotateLeft(h);
        if(isRed(h.left)&&isRed(h.left.left))
            h = rotateRight(h);
        if(isRed(h.left) && isRed(h.right))
            flipColors(h);
        h.N = size(h.left)+size(h.right)+1;
        return h;
    }
    public void deleteMax(){
        if(isEmpty()){
            throw new NoSuchElementException("BST underflow");
        }
        if(!isRed(root.left)&&!isRed(root.right)){
            root.color = RED;
        }
        root = deleteMax(root);
        root.color = BLACK;
    }
    private Node deleteMax(Node h){
        if(isRed(h.left)){
            h = rotateRight(h);
        }
        if (h.right == null){
            return null;
        }
        if(!isRed(h.right) && !isRed(h.right.left)){
            h = moveRedRight(h);
        }
        h.right = deleteMax(h.right);
        return balance(h);
    }
    public void delete(Key key){
        if(key == null){
            throw new IllegalArgumentException("argument to delete() is null");
        }
        if(!contains(key))
            return;
        if(!isRed(root.left) && !isRed(root.right)){
            root.color = RED;
        }
        root = delete(root,key);
        root.color = BLACK;
    }
    // 删除过程中确保当前结点不是2-结点
    private Node delete(Node h,Key key){
        if(key.compareTo(h.key) < 0){
            if(!isRed(h.left) && !isRed(h.left.left)){
                h = moveRedLeft(h);
            }
            h.left = delete(h.left,key);
        }
        else{
            if(isRed(h.left)){
                h = rotateRight(h);
            }
            if(key.compareTo(h.key) ==0 && (h.right == null)){
                return null;
            }
            if(!isRed(h.right) && !isRed(h.right.left)){
                h = moveRedRight(h);
            }
            if(key.compareTo(h.key) == 0){
                Node x = min(h.right);
                h.key = x.key;
                h.val = x.val;
                h.right = deleteMin(h.right);
            }
            else {
                h.right = delete(h.right,key);
            }
        }
        return balance(h);
    }
    // 测试用例
    public static void main(String[] args) {
        RedBlackBST<String,Integer> rbBst = new RedBlackBST<>();
        // String[] arr = {"S", "E", "A", "R", "C", "H", "X", "M", "P", "L"};
        String[] arr = {"A", "C", "E", "H", "L", "M", "P",  "R", "S", "X"};
        for (int i = 0; i < arr.length; i++) {
            rbBst.put(arr[i], i);
        }
       // rbBst.delete("L");
        int v = rbBst.get("D");
        StdOut.println(v);
//        int size = rbBst.size();
//        StdOut.println("The size of the tree is:"+size);
//        for (String key : rbBst.keys()) {
//            StdOut.println(key + " " + rbBst.get(key));
//        }
    }
}
