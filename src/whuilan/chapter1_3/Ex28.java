package whuilan.chapter1_3;

/**编写一个方法max()，接受一个链表的首结点作为参数，
 * 返回链表中键最大的节点的值。
 * 假设所有键均为正整数，如果链表为空则返回0。
 * 用递归的方法解答*/
public class Ex28 {
    private static class Node{
        int item;
        Node next;
    }
//    public int max(Node first){
//        int maxVaule = first.item;// 设置全局maxValue就是不对的，不然每一次迭代最大值都会被重新赋值
//        Node current = first.next;
//        if(current==null) return maxVaule;
//        if(current.item>maxVaule){
//            maxVaule = current.item;
//        }
//        return max(current.next);
//    }
    /*链表之间依次两两比较，删除较小的一个，直到只剩最大的那一个，直接返回其item*/
    public int max(Node first){
        Node current = first;
        if(current==null) return 0;
        if(current.next==null) return current.item;
        if(current.next.item>current.item || current.next.item==current.item){
            current = current.next;
        }else {
            current.next = current.next.next;
        }
        return max(current);
    }

    public static void main(String[] args){
        Node first = new Node();
        first.item = 6;
        Node second = new Node();
        second.item = 1;
        Node third = new Node();
        third.item = 8;
        first.next = second;
        second.next = third;
        third.next = null;
        Ex28 test = new Ex28();
        int result = test.max(first);
        System.out.print(result);
    }
}
