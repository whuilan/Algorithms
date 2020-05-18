package sword_to_offer;

import  sword_to_offer.ListNode;

/**
 * P139链表中环的入口节点，同样要用两个指针P1、P2
 * （1）首先要判断链表中是否含有环，此时两个指针都从首节点开始遍历，但是速度不同：P1一次走两步，
 * P2一次走两步。若链表中含有环，那么两个指针一定会在环中的某个节点相遇。
 * （2）然后根据刚刚找到的环中的某个节点来遍历，得到环中的节点数目n
 * （3）最后将P1和P2都指向链表的首节点，P1先走n步，然后P1和P2同时向前遍历，那么当P2到达环的
 * 入口节点时，P1已经绕着环走了一圈并回到了入口，即它们会在环的入口节点处相遇。
 * (牛客cyc的方法：当第一次P1和P2相遇时，让P1从头开始遍历，并且速度变为一次一步，同时P2
 * 从相遇点开始遍历，P1、P2再次相遇的地方即为环的入口节点，虽然是对的，但不知如何验证）
 */
public class Problem23 {
      private class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode EntryNodeOfLoop(ListNode pHead)
    {
        if (pHead == null || pHead.next == null){ // 显然没有环
            return null;
        }
        // 第一步，判断链表中是否含有环，若含有环，得到环中的某一个节点
        ListNode p1 = pHead, p2 = pHead;
        do {
            p1 = p1.next.next;
            p2 = p2.next;
        }while (p1 != null && p1.next != null && p1 != p2);
        if (p1 != p2){
            return null; // p1到了尾节点还没有和p2相遇，说明链表中没有环
        }
        // 第二步，计算环中含有的节点数目
        ListNode start = p1;
        p1 = p1.next;
        int n = 1;
        while (p1 != start){
            n++;
            p1 = p1.next;
        }
        // 第三步，找到环的入口节点
        p1 = pHead;
        p2 = pHead;
        // p1先走n步
        for (int i = 1; i <= n; i++){
            p1 = p1.next;
        }
        while (p1 != p2){
            p1 = p1.next;
            p2 = p2.next;
        }
        // p1和p2会在环的入口节点处相遇
        return p1;
    }

    public ListNode EntryNodeOfLoop2(ListNode pHead)
    {
        if (pHead == null || pHead.next == null){
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
//        ListNode node1 = new sword_to_offer.ListNode(1);
//        ListNode node2 = new ListNode(2);
//        ListNode node3 = new ListNode(3);
//        ListNode node4 = new ListNode(4);
//        ListNode node5 = new ListNode(5);
//        ListNode node6 = new ListNode(6);
//        node1.next = node2;
//        node2.next = node3;
//        node3.next = node4;
//        node4.next = node5;
//        node5.next = node6;
//        node6.next = node4;
//        Problem23 problem23 = new Problem23();
//        ListNode entryNode = problem23.EntryNodeOfLoop(node1);
        System.out.println();
    }
}
