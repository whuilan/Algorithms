package sword_to_offer;

/**
 * P122 删除重复的节点，重复的结点不保留，返回链表头指针。
 * 重点/核心：递归
 */
public class Problem18_2 {
    public static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode deleteDuplication(ListNode pHead) {
        if (pHead == null || pHead.next == null) {  // 链表中至少含有两个节点
            return pHead;
        }
        ListNode nextNode = pHead.next;
        if (nextNode.val == pHead.val) {  // 首节点和下一个节点重复,则循环一直到找到不和首节点重复的节点
            while (nextNode != null && nextNode.val == pHead.val) {
                nextNode = nextNode.next;
            }
            return deleteDuplication(nextNode); // 找到不和首节点重复的节点后，返回以该节点作为首节点的子链表，这样就已删除所有和首节点重复的节点
        }
        pHead.next = deleteDuplication(pHead.next); // 没有和首节点重复的节点，继续判断首节点的下一个节点
        return pHead;  // 这一步的return不能忘了呀！
    }

    public static void main(String[] args){
        ListNode first = new ListNode(1);
        ListNode node_2 = new ListNode(2);
        ListNode node_3 = new ListNode(2);
        ListNode node_4 = new ListNode(2);
        ListNode node_5 = new ListNode(3);
        first.next = node_2;
        node_2.next = node_3;
        node_3.next = node_4;
        node_4.next = node_5;
        Problem18_2 problem18_2 = new Problem18_2();
        ListNode newFirst = problem18_2.deleteDuplication(first);
        System.out.println("finish");
    }
}
