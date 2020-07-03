package LeetCode.Linked_List;

/**
 * 环形链表II:给定一个链表，返回链表开始入环的第一个节点。如果链表无环，则返回 null。
 *           说明：不允许修改给定的链表。
 * 剑指原题23，用两个指针来解决
 */
public class Solution142 {
    // 法一：分三个步骤，剑指offer上的解法，容易理解但是代码有点多
    public ListNode detectCycle1(ListNode head) {
        if(head == null || head.next == null){
            return null;
        }
        // 第一步，先用双指针判断链表中是否有环，就是141题
        ListNode p1 = head.next, p2 = head.next.next;
        while (p2 != null && p2.next != null && p1 != p2){ // 要仔细，不要把p1和p2写错！
            p1 = p1.next;
            p2 = p2.next.next;
        }
        if (p1 != p2){
            return null; // 说明没有环
        }
        // 第二步，相遇时，让p1接着遍历，一边遍历一边记录环的节点个数
        int n = 1;
        p1 = p1.next;
        while (p1 != p2){
            n++;
            p1 = p1.next;
        }
        // 第三步，得到环中节点个数后，再让p1,p2从头遍历，让p1先走n步，它们会在环的入口节点处相遇
        p1 = head;
        p2 = head;
        for (int i = 1; i <= n; i++){
            p1 = p1.next;
        }
        while (p1 != p2){
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }

    // 法二，第一次相遇后，让走得快的指针重新指向头部，然后两个指针都以一次一步的速度向前遍历，
    // 它们会在环的入口节点相遇，代码比法一简洁好多，证明过程见leetcode
    public ListNode detectCycle2(ListNode head) {
        if (head == null || head.next == null){
            return null;
        }
        // 第一次相遇和法一相同
        ListNode fast = head.next.next, slow = head.next;
        while (fast != null && fast.next != null && slow != fast){
            slow = slow.next;
            fast = fast.next.next;
        }
        if (slow != fast){
            return null;
        }
        // 第二次相遇，slow不变，让fast指向头部，并且速度变为一次一步，它们会在入口节点相遇
        fast = head;
        while (slow != fast){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
