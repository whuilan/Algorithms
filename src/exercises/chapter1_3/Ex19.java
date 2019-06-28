package exercises.chapter1_3;

import edu.princeton.cs.algs4.StdOut;

public class Ex19<Item>{
    private static class Node<Item>{
        Item item;
        Node<Item> next;
    }
    public static void main(String[] args){
        Node<String> first = new Node<>();
        first.item = "apple";
        Node<String> second = new Node<>();
        second.item = "banana";
        first.next = second;
        Node<String> third = new Node<>();
        third.item = "pear";
        second.next = third;
        Node<String> fourth = new Node<>();
        fourth.item = "orange";
        fourth.next = null;
        third.next = fourth;
        //Node<String> x = first;
        for(Node x = first;x!=null;x=x.next){
            StdOut.println(x.item);
        }
        StdOut.println();
        // 给出一段代码，删除链表的尾结点，其中链表的首结点为first。
        for(Node x = first;x!=null;x=x.next){
            if(x.next.next==null){
                x.next = null;
                break;
            }
        }
        for(Node x = first;x!=null;x=x.next){
            StdOut.print(x.item+" ");
        }
    }
}
