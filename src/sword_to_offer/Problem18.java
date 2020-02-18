package sword_to_offer;

/**
 * P119 在O(1)时间内删除链表的节点，将待删除的点的下一个节点的内容复制到
 * 需要删除的节点上覆盖原有内容，再把下一个节点删除
 */
public class Problem18 {
    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public void deleteNode(ListNode head, ListNode toBeDeleted){
        if (head == null || toBeDeleted == null){
            return;
        }

        if(toBeDeleted.next != null){ // 要删除的节点不是尾节点，显然链表也不只有一个节点
            ListNode nextNode = toBeDeleted.next;  // 要删除节点的下一个节点
            toBeDeleted.val = nextNode.val;
            toBeDeleted.next = nextNode.next;
        }
        else {
            if(toBeDeleted == head){   // 首尾节点相同，即只有一个节点
                // toBeDeleted = null;
                head = null;
            }
            else {   // 有多个节点，删除尾节点
                ListNode node = head;
                while (node.next != toBeDeleted){
                    node = node.next;
                }
                node.next = null;
            }
        }
    }
}
