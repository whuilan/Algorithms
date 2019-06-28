package exercises.chapter1_3;

/**
 * 用环形链表实现Queue。
 * 环形链表也是一条链表，只是没有任何结点链接为空，且只要链表非空则last.next的值就为first。
 * 只能使用一个Node类型的实例变量（last）。*/
public class CircularQueue<Item> {
    private Node last;
    private int N;
    private class Node{
        Item item;
        Node next;
    }
    public boolean isEmpty(){return last==null;}
    public int size(){return N;}
//    public void enqueue(Item item){
//        Node oldlast = last;
//        last = new Node();
//        last.item = item;
//        if(isEmpty()) {                // 第一步就会出错，因为上一步last new了一个Node,所以在添加第一个元素时last不为空
//            last.next = last;
//        }
//        else {
//            last.next = oldlast.next;
//            oldlast.next = last;
//        }
//        N++;
//    }
    public void enqueue(Item item){
        Node newNode = new Node();
        newNode.item = item;
        if(isEmpty()){
            last = newNode;
            newNode.next = newNode;
        }else{
            newNode.next = last.next;
            last.next = newNode;
            last = newNode;
        }
        N++;
    }
    public Item dequeue(){
        if(isEmpty()) return null;
        Item item = last.next.item;
        if(last.next == last.next.next) {
            last=null;
        }
        else{
            last.next = last.next.next;
        }
        N--;
        return item;
    }
    public static void main(String[] args){
        CircularQueue<String> circularQueue = new CircularQueue<>();
        circularQueue.enqueue("apple");
        circularQueue.enqueue("banana");
        circularQueue.enqueue("orange");
        System.out.println(circularQueue.dequeue());
        System.out.println(circularQueue.dequeue());
        System.out.println(circularQueue.dequeue());
    }
}
