package textbook.chapter5_2;

/**
 * 基于三向单词查找树的符号表
 */
public class TST<Value> {
    private Node root;
    private class Node{
        private char c;
        private Value val;
        private Node left, mid, right;
    }

    public Value get(String key){
        Node x = get(root, key, 0);
        if(x == null){
            return null;
        }
        return x.val;
    }
    // 自己写出来的，棒棒的~
    private Node get(Node x, String key, int d){
        if(x == null){
            return null;
        }
        char c = key.charAt(d);
        if(c < x.c){
            return get(x.left, key, d);
        }
        else if(c > x.c){
            return get(x.right, key, d);
        }
        else {
            d++;
            if(d == key.length()){
                return x;
            }
            return get(x.mid, key, d);
        }
    }

    public void put(String key, Value val){
        root = put(root, key, val, 0);
    }
    // 也是自己写的，棒！
    private Node put(Node x, String key, Value val, int d){
        char c = key.charAt(d);
        if(x == null){
            x = new Node();
            x.c = c;
        }
        if(c < x.c){
            x.left = put(x.left, key, val, d);
        }
        else if(c > x.c){
            x.right = put(x.right, key, val,d);
        }
        else {
            d++;
            if(d == key.length()){
                x.val = val;
                return x;
            }
            x.mid = put(x.mid, key, val, d);
        }
        return x;
    }
}
