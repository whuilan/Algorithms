package exercises.chapter1_3;

/**
 *   编写一个方法insertAfter()，接受两个链表结点作为参数，
 *   将第二结点插入链表并使之成为第一个结点的后续结点
 *   （如果两个参数为空则什么也不做）。*/
public class Ex25<Item>{
    private static class Node<Item>{
        Item item;
        Node<Item> next;
    }
    public void insertAfter(Node<Item> x,Node<Item> y){
        if( x == null || y == null) return;
        y.next = x.next;
        x.next = y;
    }
    public static void main(String[] args){
        Node<String> first = new Node<String>();
        first.item = "apple";
        Node<String> second = new Node<String>();
        second.item = "banana";
        first.next = second;
        Node<String> third = new Node<String>();
        third.item = "orange";
        third.next = null;
        second.next = third;
        Node<String> insert = new Node<String>();
        insert.item = "pear";
        //insert.next = null;
        for(Node<String> current = first;current!=null;current=current.next){
            System.out.print(current.item+" ");
        }
        System.out.println();
        Ex25<String> test = new Ex25<>();
        test.insertAfter(first,insert);
        for(Node<String> current = first;current!=null;current=current.next){
            System.out.print(current.item+" ");
        }

    }
}
