package exercises.chapter1_3;

/**
 * 编写一个方法removeAfter()，接受一个链表结点作为参数并删除该结点的后续结点
 * （如果参数结点或参数结点的后续结点为空则什么也不做）。*/
public class Ex24<Item> {
    private static class Node<Item>{
        Item item;
        Node<Item> next;
    }
    public void removeAfter(Node<Item> node){
        if(node == null || node.next == null) return;
        node.next = node.next.next;
    }
    public static void main(String[] args){
        Node first = new Node();
        first.item = "apple";
        Node second = new Node();
        second.item = "banana";
        first.next = second;
        Node third = new Node();
        third.item = "orange";
        third.next = null;
        second.next = third;
        for(Node current = first;current!=null;current=current.next){
            System.out.print(current.item+" ");
        }
        System.out.println();
        Ex24<String> test = new Ex24<>();
        test.removeAfter(first);
        for(Node current = first;current!=null;current=current.next){
            System.out.print(current.item+" ");
        }
    }
}
