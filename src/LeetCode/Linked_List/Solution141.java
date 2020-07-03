package LeetCode.Linked_List;

/**
 * 环形链表I：给定一个链表，判断链表中是否有环。
 * 剑指23原题，核心：双指针法
 * 时间复杂度：O(n)，一次遍历
 * 空间复杂度: O(1), 只需要用到快慢两个指针
 */
public class Solution141 {
    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null){
            return false;
        }
        // 遍历之前就让p1先走一步，p2走两步，这样才能满足循环的条件p1 != p2
        ListNode p1 = head.next, p2 = head.next.next;
        while(p2 != null && p2.next != null && p1 != p2){
            p1 = p1.next;
            p2 = p2.next.next;
        }
        // 循环终止后根据p1和p2是否相等判断链表是否有环
        return p1 == p2 ? true : false;
    }
}
