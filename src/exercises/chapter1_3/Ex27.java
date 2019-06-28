package exercises.chapter1_3;

/**编写一个方法max()，接受一个链表的首结点作为参数，返回链表中键最大的节点的值。
假设所有键均为正整数，如果链表为空则返回0。*/
public class Ex27 {
    private static class Node{
        int item;
        Node next;
    }
    public int max(Node first){
        if(first==null) return 0;
        int maxVaule = 0;
        Node current = first;
        while (current!=null){
          if(current.item>maxVaule){
              maxVaule = current.item;
          }
          current = current.next;
        }
        return maxVaule;
    }
    public static void main(String[] args){
        Node first = new Node();
        first.item = 2;
        Node second = new Node();
        second.item = 4;
        first.next = second;
        Node third = new Node();
        third.item = 8;
        second.next = third;
        Node fourth = new Node();
        fourth.item = 3;
        third.next = fourth;
        Ex27 test = new Ex27();
        int result = test.max(first);
        System.out.print(result);
    }
}
