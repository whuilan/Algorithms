package LeetCode.Linked_List;

import java.util.ArrayList;
import java.util.List;

/**
 * 回文链表：请判断一个链表是否为回文链表。(注意空链表算回文链表！不清楚的都问一下面试官)
 */
public class Solution234 {
    public boolean isPalindrome(ListNode head) {
        // 法一：我的第一反应很简单，把链表的数字提取出来放到list或数组中，再来判断，时间和空间复杂度
        // 均为O(n)，和官方解法一一模一样
        if (head == null || head.next == null){
            return true;
        }
        List<Integer> list = new ArrayList<>();
        ListNode curNode = head;
        while (curNode != null){
            list.add(curNode.val);
            curNode = curNode.next;
        }
        return palindrome(list);
    }

    private boolean palindrome(List<Integer> list){
        int lo = 0, hi = list.size() - 1;
        while (lo < hi){
            int a = list.get(lo);
            int b = list.get(hi);
            if (a != b){ // list.get(lo) != list.get(hi)，用这个会出错，因为是两个Integer比较！应该是if(!vals.get(front).equals(vals.get(back)))
                return false;
            }
            lo++;
            hi--;
        }
        return true;
    }

    // 法二：优化空间复杂度为O(1)，想到了用两个指针，但一快一慢或者一头一尾都没有办法比较
    // 又根据回文的特点想到了剑指上的反转链表，可以反转后半部分链表，然后灵感指针分别从前半
    // 部分和后半部分的第一个节点开始比较，如果是回文子串，两个节点应该相等，官网也是这样的！
    // 我可太棒啦！

    public static void main(String[] args){
        ListNode node1 = new ListNode(-129);
        ListNode node2 = new ListNode(-129);
        node1.next = node2;
        Solution234 solution234 = new Solution234();
        boolean isPalin = solution234.isPalindrome(node1);
        System.out.println(isPalin);
    }
}
