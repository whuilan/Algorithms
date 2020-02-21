package sword_to_offer;

import java.util.List;

/**
 * P134 链表中倒数第k个节点,核心：1使用两个指针不同时出发来遍历一次链表 2鲁棒性，注意k的
 * 各种取值范围假设链表有n个节点，那么倒数第k个节点其实就是从头数第(n-k+1)个节点，所以要
 * 使第一个指针先走k-1步，从第k步开始两个指针一起走，那么第一个指针走到尾节点n时，第二个
 * 指针刚好走到第(n-k+1)个节点
 */
public class Problem22 {
    public static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode FindKthToTail(ListNode head,int k) {
        if (head == null || k <= 0){
            return null;
        }
        // 第一个指针先走k-1步
        ListNode pointerOne = head;
        for (int i = 1; i <= k - 1; i++){
            if (pointerOne.next != null){  // 至少这k-1步还没走到尾节点
                pointerOne = pointerOne.next;
            }else {
                return null;   // 说明链表中的节点整数小于k
            }
        }
        // 从第k步开始，第二个指针也从头开始遍历
        ListNode pointerTwo = head;
        while (pointerOne.next != null){   // 第一个指针到达尾节点后应该终止循环
            pointerOne = pointerOne.next;
            pointerTwo = pointerTwo.next;
        }
        return pointerTwo;
    }

    public static void main(String[] args){
        ListNode first = new ListNode(1);
        ListNode node_2 = new ListNode(2);
        ListNode node_3 = new ListNode(3);
        ListNode node_4 = new ListNode(3);
        // ListNode node_5 = new ListNode(4);
        first.next = node_2;
        node_2.next = node_3;
        node_3.next = node_4;
        Problem22 problem22 = new Problem22();
        ListNode KthToTail = problem22.FindKthToTail(first, 5);
        System.out.println("finish");
    }
}
