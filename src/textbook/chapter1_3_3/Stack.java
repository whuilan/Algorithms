package textbook.chapter1_3_3;

public class Stack<Item> {
    private Node first;
    private int N;
    private class Node{
        Item item;
        Node next;
    }
    public boolean isEmpty(){return first == null;}
    public int size(){return N;}
    public void push(Item item){
        Node oldFirst  = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        N++;
    }
    public Item pop(){
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }
    /*练习1_3_7 添加一个方法peek()返回栈中最近添加的元素（而不是弹出它）*/
    public Item peek(){
        Item item = first.item;
        return item;
    }
}
