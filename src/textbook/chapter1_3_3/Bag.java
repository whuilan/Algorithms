package textbook.chapter1_3_3;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class Bag<Item> implements Iterable<Item>{
    private Node first;
    private int N;
    private class Node{
        Item item;
        Node next;
    }

    public boolean isEmpty(){return first == null;}
    public int size(){return N;}
    public void add(Item item){
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        N++;
    }

    public Iterator<Item> iterator(){
        return new ListIterator();
    }
    private  class ListIterator implements Iterator<Item>{
        private Node current = first;
        public boolean hasNext(){return current != null;}
        public Item next(){
            Item item = current.item;
            current = current.next;
            return item;
        }
        public void remove(){}
    }

    public static void main(String[] args){
        Bag<String> bag = new Bag<>();
        while (!StdIn.isEmpty()){
            String item = StdIn.readString();
            if(!item.equals("-")){
                bag.add(item);
            }
        }
        for(String b:bag){
        StdOut.print(b+" ");
        }
    }
}
