package LeetCode.Linked_List;

// 两数相加：给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，
// 并且它们的每个节点只能存储一位数字。如果我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
// 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
public class Solution2 {
    // 自己写的，将链表中的数字转换成实际的数字，再把两个数字相加得到和，再把和转换成链表
    // 思路可以，但是没有考虑到链表的长度！加入链表的长度为几十，几百，int和long是装不下的！
    // 因此这种方法又很多测试用例都通过不了。
    public ListNode addTwoNumbers0(ListNode l1, ListNode l2) {
        int num1 = getNumOfList(l1);
        int num2 = getNumOfList(l2);
        int sum = num1 + num2;
        if (sum == 0){
            return new ListNode(0);
        }
        return constructList(sum);
    }

    private int getNumOfList(ListNode head){
        int index = 0;
        int num = 0;
        for (ListNode x = head; x != null; x = x.next){
            num += x.val * Math.pow(10, index);
            index++;
        }
        return num;
    }

    private ListNode constructList(int sum){
        if (sum == 0){
            return null;
        }
        ListNode node = new ListNode(sum % 10);
        node.next = constructList(sum / 10);
        return node;
    }

    // 我想复杂啦，就按照普通加法，再考虑进位进位就行啦！有三点需要注意：
    // 1 返回结果为头结点时，通常需要先初始化一个预先指针 pre，该指针的下一个节点指向真正的头结点head。
    // 2 链表遍历尽量使用循环迭代来实现而不要用递归
    // 3 注意一些特殊情况，如[9,9]和1
    // 时间复杂度为：O(max(m,n))，m和n分别表示l1和l2的长度
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null){
            return l2;
        }
        if (l2 == null){
            return l1;
        }
        ListNode p1 = l1, p2 = l2;
        // 初始化一个哑节点0，它指向我们要返回的头节点
        ListNode dummyHead = new ListNode(0);
        // 将当前节点初始化为这个哑节点
        ListNode pre = dummyHead;
        // 将进位初始化为0
        int carry = 0;
        // 两个链表可能不等长，一直计算到长的那个的尾节点才行
        while (p1 != null || p2 != null){
            int x = p1 == null ? 0 : p1.val;
            int y = p2 == null ? 0 : p2.val;
            int curSum = x + y + carry;
            ListNode curNode = new ListNode(curSum % 10);
            pre.next = curNode;
            pre = curNode;
            // 更新carry
            carry = curSum / 10;
            p1 = p1 == null ? null : p1.next;
            p2 = p2 == null ? null : p2.next;
        }
        if (carry == 1){
            pre.next = new ListNode(1);
        }
        return dummyHead.next;
    }

    public static void main(String[] args){
        ListNode node1 = new ListNode(9);
        ListNode node2 = new ListNode(9);
        ListNode node3 = new ListNode(1);
        ListNode node4 = new ListNode(5);
        ListNode node5 = new ListNode(6);
        ListNode node6 = new ListNode(4);
        node1.next = node2;
//        node2.next = node3;
//        node4.next = node5;
//        node5.next = node6;
        Solution2 solution2 = new Solution2();
        ListNode sumHead = solution2.addTwoNumbers(node1, node3);
        System.out.println(sumHead.val);
    }
}
