package LeetCode.Linked_List;

/**
 * 排序链表：在 O(nlogn) 时间复杂度和常数级空间复杂度下，对链表进行排序。
 * 分析：限定了常数级的空间复杂度，也就是不能利用辅助空间，递归也是要用到辅助空间的！
 * 那么不能用快排，又根据时间复杂度可以推测是用归并，但常规的自顶向下的归并也是递归，
 * 因此要想到自底向上的归并！用循环的方法自底向上归并，没有用到递归！由于是链表也不需要
 * 辅助数组，满足题目要求！
 */
public class Solution148 {
    ListNode dummyNode;
    ListNode pre;

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        // 先求出链表的长度N
        int N = 0;
        for (ListNode x = head; x != null; x = x.next){
            N++;
        }
        dummyNode = new ListNode(-1);
        dummyNode.next = head; // review这一步忘了
        // 通过迭代的方式自底向上归并排序
        for (int i = 1; i < N; i = 2 * i){ // 第一层循环，分块，即要合并的两个子链表的长度分别为1,2,4..
            pre = dummyNode; // 这一步也没及时反应过来
            // j + i < n 表示只有一段就不归并了，因为已经是排好序的
            for (int j = 0; j + i < N; j += 2 * i){
                // 分两块，找到两块的起始节点
                ListNode left = pre.next, right = left;
                // second向前走k步（子链表长度）指向第二条子链表的起点
                for (int k = 0; k < i; k++){
                    right = right.next;
                }
                // 开始遍历两个子链表，进行合并
                merge(left, right, i);
            }
        }
        return dummyNode.next;
    }

    // 合并两条排序的链表left,right，其中left的长度为size，right的长度小于等于k
    private void merge(ListNode left, ListNode right, int k){
        int l = 0, r = 0; // 类似数组merge时的int i = lo, j = mid + 1
        while (l < k && r < k && right != null){
            if (left.val < right.val){
                pre.next = left;
                pre = left;
                left = left.next;
                l++; // 这一步给漏啦！
            }else {
                pre.next = right;
                pre = right;
                right = right.next;
                r++;
            }
        }
        // 归并之后可能有多余的没有处理
        while (l < k){
            pre.next = left;
            pre = left;
            left = left.next;
            l++;
        }
        while (r < k && right != null){
            pre.next = right;
            pre = right;
            right = right.next;
            r++;
        }
        // second已经更新到下一块的起点了，更新pre，使得pre指向下一块的起点
        pre.next = right;
    }

    public static void main(String[] args){
        ListNode node1 = new ListNode(4);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(1);
        ListNode node4 = new ListNode(3);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        Solution148 solution148 = new Solution148();
        ListNode sortedHead = solution148.sortList(node1);
        System.out.println(sortedHead.val);
    }
}
