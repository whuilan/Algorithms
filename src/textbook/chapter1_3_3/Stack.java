package textbook.chapter1_3_3;

import whuilan.chapter1_3.Ex19;

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
    /*练习1_3_20 编写一个方法delete()，接受一个int参数k，删除链表的第k个元素（如果它存在的话）*/
    public void delete(int k){
        if(k==1){ first=first.next; }
        if(k==size()) {
            for(Node x = first; x!=null; x=x.next){
                if(x.next.next==null){
                    x.next = null;
                    break;
                }
            }
        }
        Node x=first;
        for(int i=1;i<k;i++){
            if(i==k-1){
                x.next = x.next.next;
            }
            x = x.next;
        }
    }
}
