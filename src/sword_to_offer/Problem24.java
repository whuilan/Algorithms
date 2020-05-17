package sword_to_offer;

/**
 * P142 反转链表，核心思想：使用三个指针分别表示上一个、当前、下一个节点！
 */
public class Problem24 {
    // 法一：三个指针（头插法）
    public ListNode ReverseList(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode pLast = head, pCurrent = head.next, pNext = head.next.next;
        while (pNext != null){
            pCurrent.next = pLast;
            pLast = pCurrent;
            pCurrent = pNext;
            pNext = pNext.next;
        }
        pCurrent.next = pLast;
        head.next = null;
        return pCurrent;
    }

    // 法二：递归（我的第一反应，面试时可以先说出来，只是当链表很长时，效率比较低，
    // 有可能导致递归调用栈溢出）
    public ListNode ReverseList2(ListNode head){
        if (head == null || head.next == null){
            return head;
        }
        ListNode nextNode = head.next;
        head.next = null;
        ListNode newHead = ReverseList2(nextNode);
        nextNode.next = head;   // 写在递归调用后面的代码最晚被执行
        return newHead;
    }


    public static void main(String[] args){
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        // ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        // node4.next = node5;
        Problem24 problem24 = new Problem24();
        ListNode reverseHead = problem24.ReverseList(node1);
        System.out.println();
    }
}
