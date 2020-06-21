package LeetCode.Linked_List;

import java.util.PriorityQueue;

/**
 * 合并K个排序链表,返回合并后的排序链表。请分析和描述算法的复杂度。
 * 这道题是剑指25合并两个排序链表的延伸，因此我的思路是将多个链表合并化成多次两个链表合并，
 * 难点：怎样让前两个链表合并后的结果作为一条链表继续与链表数组中的下一条合并，想得有点久
 * 启发：说明上一次的运算结果又在下一次运算中作为参数，类似x = x + 2;然后外面套上循环
 * 即用一个变量res来维护已经合并的链表,第i次循环将第i个链表和res合并，res最后就是合并了
 * 所有链表的最终排序链表。
 */
public class Solution23 {
    // 法一：虽然思路最简单，但是是自己写的，棒棒的~时间复杂度O(k^2*n)
    public ListNode mergeKLists1(ListNode[] lists) {
        if(lists == null || lists.length == 0){
            return null;
        }
        ListNode res = null;
        for(int i = 0; i < lists.length; i++){
            res = mergeTwoLists(res, lists[i]);
        }
        return res;
    }

    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null){
            return l2;
        }
        if(l2 == null){
            return l1;
        }
        if(l1.val < l2.val){
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }else{
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    // 法二：最小堆，初始时把k个链表的首节点都放入堆中，然后重复：堆不为空时，弹出最小的，这个弹出的
    // 最小节点还有下一个节点，就再把它的下一个节点插到堆中，堆的插入和删除的时间复杂度和空间复杂度均为
    // O(logK),所有链表的所有节点都要重复这个过程，因此时间复杂度为：O(KNlogK)，KN表示所有节点数
    public ListNode mergeKLists2(ListNode[] lists) {
        if(lists == null || lists.length == 0){
            return null;
        }
        // 创建最小堆并指定排序规则
        PriorityQueue<ListNode> pq = new PriorityQueue<>((v1,v2) -> v1.val - v2.val);
        // 初始化最小堆：把每条链表的首节点插入最小堆
        for (ListNode listNode : lists){
            // listNode可能为空，不要忘了判断！
            if (listNode != null){
                pq.offer(listNode);
            }
        }
        // 又借助了哑节点！第2题求两数之和中也用到了!实际上返回的是哑节点的下一个节点
        ListNode dummyNode = new ListNode(-1);
        ListNode pre = dummyNode;
        while (!pq.isEmpty()){
            ListNode minNode = pq.poll();
            pre.next = minNode;
            pre = minNode;
            // 如果这个节点还有下一个节点，要继续假如栈中！这么总要的一步竟然忘了
            if (minNode.next != null){
                pq.offer(minNode.next);
            }
        }
        return dummyNode.next;
    }
}

