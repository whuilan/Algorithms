package LeetCode.Linked_List;

// 反转链表
public class Solution206 {
    // 法一：迭代
    public static ListNode reverseList1(ListNode head) {
        if(head == null || head.next ==null){
            return head;
        }
        ListNode pre = head, cur = head.next, next = head.next.next;
        while(next != null){
            cur.next = pre;
            pre = cur;
            cur = next;
            next = next.next;
        }
        cur.next = pre;
        head.next = null;
        return cur;
    }

    // 法二：递归
    ListNode reversedHead;
    public ListNode reverseList2(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        reverse(head);
        head.next = null;
        return reversedHead;
    }

    private void reverse(ListNode head){
        if (head.next == null){
            reversedHead = head;
            return;
        }
        ListNode next = head.next;
        reverse(next);
        next.next = head;
    }

    public static void main(String[] args){
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
//        node3.next = node4;
//        node4.next = node5;
        Solution206 solution206 = new Solution206();
        ListNode reversedHead = solution206.reverseList2(node1);
        System.out.println(reversedHead.val);
    }
}
