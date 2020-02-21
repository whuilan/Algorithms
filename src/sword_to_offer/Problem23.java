package sword_to_offer;

/**
 * P139链表中环的入口节点，同样要用两个指针P1、P2
 * 首先要判断链表中是否含有环，此时两个指针都从首节点开始遍历，但是速度不同P1次走一步，
 * P2一次走两步。若链表中含有环，那么两个指针一定会在环中的某个节点相遇。此时再让P1从
 * 头开始遍历，并且速度变为一次一步，同时P2从相遇点开始遍历，P1、P2再次相遇的地方即为
 * 环的入口节点
 */
public class Problem23 {
    public static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }


    public ListNode EntryNodeOfLoop(ListNode pHead)
    {
        if (pHead == null || pHead.next == null){  // 至少含有两个节点，不然一定没有环
            return null;
        }
        ListNode p1 = pHead, p2 = pHead;
        // 找出p1和p2相遇的节点，注意要用do..while循环，不然刚开始p1,p2相等进不去while循环
        do {
            p1 = p1.next.next;
            p2 = p2.next;
        } while (p1 != null && p1.next != null && p1 != p2);  // 注意循环终止条件
        if (p1 != p2){
            return null; // 说明链表中没有环
        }
        p1 = pHead;
        while (p1 != p2){
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }

    public static void main(String[] args){
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node3;
        Problem23 problem23 = new Problem23();
        ListNode entryNode = problem23.EntryNodeOfLoop(node1);
        System.out.println();
    }
}
