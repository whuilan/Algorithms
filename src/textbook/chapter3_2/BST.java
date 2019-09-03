package textbook.chapter3_2;

import textbook.chapter1_3_3.Queue;

import java.util.NoSuchElementException;

public class BST<Key extends Comparable<Key>,Value> {

    private Node root;

    private class Node{
        Key key;
        Value val;
        Node left,right;
        int N;
        public Node(Key key,Value val,int N){
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    public int size(){
        return size(root);
    }
    private int size(Node x){
        if(x==null)
            return 0;
        return x.N;
    }
    public boolean isEmpty(){
        return size()==0;
    }
    public boolean contains(Key key){
        return get(key)!=null;
    }
    public Value get(Key key){
        return get(root,key);
    }
    private Value get(Node x,Key key){
        // 在以x为结点的子树中查找并返回key对应的值
        // 如果找不到则返回null
        if (key == null) throw new IllegalArgumentException("calls get() with a null key");
        if(x==null)
            return null;
        int cmp = key.compareTo(x.key);
        if(cmp<0)
            return get(x.left,key);
        else if(cmp>0)
            return get(x.right,key);
        else
            return x.val;
    }
    public void put(Key key,Value val){
         root = put(root,key,val);
    }
    private Node put(Node x,Key key,Value val){
        if(x==null)
            return new Node(key,val,1);
        int cmp = key.compareTo(x.key);
        if(cmp<0)
            x.left = put(x.left,key,val);
        else if(cmp>0)
            x.right = put(x.right,key,val);
        else
            x.val = val;
        x.N = size(x.left)+size(x.right)+1;
        return x;
    }
    public Key min(){
        if (isEmpty()) throw new NoSuchElementException("calls min() with empty symbol table");
        return min(root).key;
    }
    private Node min(Node x){
        if(x.left == null)
            return x;
        return min(x.left);
    }
    public Key max(){
        if (isEmpty()) throw new NoSuchElementException("calls min() with empty symbol table");
        return max(root).key;
    }
    private Node max(Node x){
        if(x.right == null)
            return  x;
        return max(x.right);
    }
    // 向上取整，返回小于等于key的最大键
    public Key floor(Key key){
        if (key == null) throw new IllegalArgumentException("argument to floor() is null");
        if (isEmpty()) throw new NoSuchElementException("calls floor() with empty symbol table");
        Node x = floor(root,key);
        // 注意二叉树中不一定有比给定key小的键，如果所有键都比给定的key大，则应返回null
        if(x == null)
            return null;
        return x.key;
    }
    private Node floor(Node x,Key key){
        if(x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if(cmp == 0)
            return x;
        if(cmp<0)
            return floor(x.left,key);
        Node t = floor(x.right,key);
        if(t!=null)
            return t;
        else
            return  x;
    }
    // 向下取整，返回大于等于key的最小建
    public Key ceiling(Key key){
        if (key == null) throw new IllegalArgumentException("argument to floor() is null");
        if (isEmpty()) throw new NoSuchElementException("calls floor() with empty symbol table");
        Node x = ceiling(root,key);
        if(x==null)
            return null;
        return x.key;
    }
    private Node ceiling(Node x,Key key){
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if(cmp==0)
            return x;
        if(cmp>0)
            return ceiling(x.right,key);
        Node t = ceiling(x.left,key);
        if(t!=null)
            return t;
        else
            return x;
    }
    // select方法实现：返回排名为k的键（即树中有k个键小于给定键）
    public Key select(int k){
        if (k < 0 || k >= size()) {
            throw new IllegalArgumentException("called select() with invalid argument: " + k);
        }
        if (isEmpty()) throw new NoSuchElementException("calls floor() with empty symbol table");
        return select(root,k).key;
    }
    private Node select(Node x,int k){
        // 返回排名为k的结点
        if(x==null)
            return null;
        int t = size(x.left);
        if(t==k)
            return x;
        else if(t>k)
            return select(x.left,k);
        else
            return select(x.right,k-t-1);
    }
    public int rank(Key key){
        return rank(root,key);
    }
    private int rank(Node x,Key key){
        if(x==null)
            return 0;
        int t = size(x.left);
        int cmp = key.compareTo(x.key);
        if(cmp==0)
            return t;
        else if(cmp<0)
            return rank(x.left,key);
        else
            return rank(x.right,key)+t+1;
    }
    public void deleteMin()
    {
        if (isEmpty()) throw new NoSuchElementException("Symbol table underflow");
        root = deleteMin(root);
    }
    private Node deleteMin(Node x){
        // 不断深入根结点的左子树中直至遇到一个空连接，然后将指向该结点的链接指向该结点的右子树（返回该结点的右链接即可）
        // 递归调用后更新链接和结点计数器
        if(x.left==null)
            return x.right;
        x.left = deleteMin(x.left);
        x.N = size(x.left)+size(x.right)+1;
        return x;
    }
    public void deleteMax(){
        root = deleteMax(root);
    }
    private Node deleteMax(Node x){
        if(x.right==null)
            return x.left;
        x.right = deleteMax(x.right);
        x.N = size(x.left)+size(x.right)+1;
        return x;
    }
    public void delete(Key key){
        //  默认key和二叉树非空
        root = delete(root,key);
    }
    private Node delete(Node x,Key key){
        if(x==null)
            return null;
        int cmp = key.compareTo(x.key);
        if(cmp<0)
             x.left = delete(x.left,key);
        else if(cmp>0)
             x.right = delete(x.right,key);
        else{
            if(x.left==null)
                return x.right;
            if(x.right==null)
                return x.left;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.N = size(x.left)+size(x.right)+1;
        return x;
    }
    public Iterable<Key> keys(){
        return keys(min(),max());
    }
    public Iterable<Key> keys(Key lo,Key hi){
        Queue<Key> queue = new Queue<>();
        keys(root,lo,hi,queue);
        return queue;
    }
    private void keys(Node x,Key lo,Key hi,Queue<Key> queue){
        if(x==null)
            return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = lo.compareTo(x.key);
        if(cmplo<0)
            keys(x.left,lo,hi,queue);
        if(cmplo<=0 && cmphi>=0)
            queue.enqueue(x.key);
        if(cmphi>0)
            keys(x.right,lo,hi,queue);
    }
}
