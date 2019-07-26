package textbook.chapter1_3_3;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class Queue<Item> implements Iterable<Item>{
    private Node first;
    private Node last;
    private int N;
    private class Node{
        Item item;
        Node next;
    }
    public boolean isEmpty(){return first == null;}
    public int size(){return N;}
    public void enqueue(Item item){
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if(isEmpty()) first = last;
        else oldLast.next = last;
        N++;
    }
    public Item dequeue(){
        Item item = first.item;
        first = first.next;
        if(isEmpty()) last = null;
        N--;
        return item;
    }
    public Iterator<Item> iterator(){
        return new ListIterator();
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
        public void remove(){ }
    }
    public static void main(String[] args){
        Queue<String> queue = new Queue<String>();
        while (!StdIn.isEmpty()){
            String item = StdIn.readString();
            if(!item.equals("-")){
                queue.enqueue(item);
            }else if(!StdIn.isEmpty()){
                StdOut.print(queue.dequeue()+" ");
            }
        }
        StdOut.println();
        StdOut.println(+queue.size()+" element(s)"+" left on stack");
    }
}
