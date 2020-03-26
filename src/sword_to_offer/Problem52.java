package sword_to_offer;

/**
 * P253两个链表的第一个公共节点，时间复杂度为O(m+n)
 */
public class Problem52 {
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null || pHead2 == null){
            return null;
        }
        int len1 = 0, len2 = 0;
        for (ListNode x = pHead1; x != null; x = x.next){
            len1++;
        }
        for (ListNode x = pHead2; x != null; x = x.next){
            len2++;
        }
        int gap = len1 - len2;
        if (gap > 0){
            for (int i = 0; i < gap; i++){
                pHead1 = pHead1.next;
            }
        }
        if (gap < 0){
            for (int i = 0; i < -gap; i++){
                pHead2 = pHead2.next;
            }
        }
        while (pHead1 != null && pHead1 != pHead2){
                pHead1 = pHead1.next;
                pHead2 = pHead2.next;
        }
        return pHead1;
    }
}
