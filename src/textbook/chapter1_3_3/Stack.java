package textbook.chapter1_3_3;

import java.util.Iterator;

public class Stack<Item> implements Iterable<Item>{
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
    public Iterator<Item> iterator(){
        return  new ListIterator();
    }
    private class ListIterator implements Iterator<Item>{
        private Node current = first;
        public boolean hasNext(){
            return current != null;
        }
        public Item next(){
            Item item = current.item;
            current = current.next;
            return item;
        }
        public void remove(){}
    }
    /*练习1_3_7 添加一个方法peek()返回栈中最近添加的元素（而不是弹出它）*/
    public Item peek(){
        Item item = first.item;
        return item;
    }
    /*练习1_3_20 编写一个方法delete()，接受一个int参数k，删除链表的第k个元素（如果它存在的话）*/
    public void delete(int k){
        if (k < 0 || k>size() || first == null) return;
        if(k==1){ first = first.next; }
        Node current = first;
        for(int i=1;i<k;i++){
            if(i==k-1){
                current.next = current.next.next;
                break;
            }
            current = current.next;
        }
    }
}
