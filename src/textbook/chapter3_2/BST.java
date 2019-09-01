package textbook.chapter3_2;

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
        return x.N;
    }
    public Value get(Key key){
        return get(root,key);
    }
    private Value get(Node x,Key key){
        // 在以x为结点的子树中查找并返回key对应的值
        // 如果找不到则返回null
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
        return min(root).key;
    }
    private Node min(Node x){
        if(x.left == null)
            return x;
        return min(x.left);
    }
    public Key max(){
        return max(root).key;
    }
    private Node max(Node x){
        if(x.right == null)
            return  x;
        return max(x.right);
    }
    public Key floor(Key key){
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
}
