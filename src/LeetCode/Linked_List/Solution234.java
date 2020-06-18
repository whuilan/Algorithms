package LeetCode.Linked_List;

import java.util.ArrayList;
import java.util.List;

/**
 * 回文链表：请判断一个链表是否为回文链表。(注意空链表算回文链表！不清楚的都问一下面试官)
 */
public class Solution234 {
    public boolean isPalindrome1(ListNode head) {
        // 法一：我的第一反应很简单，把链表的数字提取出来放到list或数组中，再来判断，
        // 时间和空间复杂度，均为O(n)，和官方解法一一模一样
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
            if (!list.get(lo).equals(list.get(hi))){ // list.get(lo) != list.get(hi)，用这个会出错，因为是两个Integer比较！应该是if(!vals.get(front).equals(vals.get(back)))
                return false;
            }
            lo++;
            hi--;
        }
        return true;
    }

    // 法二：优化空间复杂度为O(1)，想到了用两个指针，但一快一慢或者一头一尾都没有办法比较
    // 又根据回文的特点想到了剑指上的反转链表，可以反转后半部分链表，然后两个指针分别从前半
    // 部分和后半部分的第一个节点开始比较，如果是回文子串，两个节点应该相等，官网也解法二也是这样的！
    // 我可太棒啦！但是这会更改输入，如果不能更改输入的话，再把后半部分反转回来。
    // 时间复杂度为O(n)，空间复杂度为O(1)
    public boolean isPalindrome2(ListNode head) {
        if (head == null || head.next == null){
            return true;
        }
        int len = 0;
        ListNode node = head;
        while (node != null){
            len++;
            node = node.next;
        }
        ListNode p1 = head, p2 = head;
        // p2先走到后半部分的首节点，注意链表长度为奇数时，可以跳过正中间的节点
        // 也可以不计算长度，一个一次走两步一个一次走一步，找到前半部分的最后一个节点
        for (int i = 1; i <= (len + 1) / 2; i++){
            p2 = p2.next;
        }
        // 反转后半部分的链表
        p2 = reverseList(p2);
        // 分别从前半部分和后半部分的首节点开始比较
        while (p2 != null){
            if (p1.val != p2.val){
                return false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        return true;
    }

    // 反转链表
    private ListNode reverseList(ListNode head){
        if (head == null || head.next == null){
            return head;
        }
        ListNode pre = head, cur = head.next, next = head.next.next;
        while (next != null){
            cur.next = pre;
            pre = cur;
            cur = next;
            next = next.next;
        }
        cur.next = pre;
        head.next = null;
        return  cur;
    }


    public static void main(String[] args){
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 =  new ListNode(3);
        ListNode node4 = new ListNode(2);
        ListNode node5 = new ListNode(1);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        Solution234 solution234 = new Solution234();
        boolean isPalin = solution234.isPalindrome2(node1);
        System.out.println(isPalin);
    }
}
