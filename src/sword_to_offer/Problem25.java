package sword_to_offer;

/**
 * P145合并两个排序的链表（美团一面考到了！）
 */
public class Problem25 {
    public static ListNode Merge(ListNode list1,ListNode list2) {
        if (list1 == null){
            return list2;    // 如果两个链表都为空，那么也返回为空
        }
        if (list2 == null){
            return list1;
        }
        // 两个链表均不为空
        if (list1.val < list2.val){
            list1.next = Merge(list1.next, list2);
            return list1;
        }else {
            list2.next = Merge(list1, list2.next);
            return list2;
        }
    }

    public static void main(String[] args){
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        // ListNode node5 = new ListNode(5);
        node1.next = node3;
        node2.next = node4;
        ListNode first = Merge(node1, node2);
        System.out.println(first.val);
    }
}
