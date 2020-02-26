package sword_to_offer;

/**
 * P187复杂链表的复制
 */
public class Problem35 {
    public class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int label) {
            this.label = label;
        }
    }

    public RandomListNode Clone(RandomListNode pHead)
    {
        if (pHead == null){
            return null;
        }
        CloneNodes(pHead);  // 第一步：根据原始链表中的每个节点N创建对应的节点N',并将N'放在N后面
        SetRandomForCloneNodes(pHead);  // 第二步：设置复制出来的节点的random指向节点
        return EvenNode(pHead);  // 第三步：将长链表按奇偶位置拆分成两个链表
    }

    private void CloneNodes(RandomListNode pHead){
        RandomListNode node = pHead;
        while (node != null){
            RandomListNode cloneNode = new RandomListNode(node.label);
            cloneNode.next = node.next;
            node.next = cloneNode;
            node = cloneNode.next;
        }
    }

    private void  SetRandomForCloneNodes(RandomListNode pHead){
        RandomListNode node = pHead;
        while (node != null){
            RandomListNode cloneNode = node.next;
            if (node.random != null){
                cloneNode.random = node.random.next;
            }
            node = cloneNode.next;
        }
    }

    // 注意应该从长链表中完全分离原链表和复制链表，而不仅仅是使用next将复制的链表节点连接起来
    // 因为这样返回的复制链表中还有原链表的链接，并不完全等于原链表
    private RandomListNode EvenNode(RandomListNode pHead){
        RandomListNode cloneHead = pHead.next;
        RandomListNode node = pHead;
        while (node.next != null){
            RandomListNode nextNode = node.next;
            node.next = nextNode.next;
            node = nextNode;
        }
        return cloneHead;
    }

    private void CloneNodes2(RandomListNode node){
        if (node == null){
            return;
        }
        RandomListNode cloneNode = new RandomListNode(node.label);
        RandomListNode nextNode = node.next;
        node.next = cloneNode;
        cloneNode.next = nextNode;
        CloneNodes(nextNode);
    }

    private void SetRandomForCloneNodes2(RandomListNode node){
        if (node == null){
            return;
        }
        RandomListNode randomNode = node.random;
        if (randomNode != null){
            node.next.random = randomNode.next;
        }
        SetRandomForCloneNodes(node.next.next);
    }

    private RandomListNode EvenNode2(RandomListNode node){
        if (node.next == null){
            return node;
        }
        node.next = EvenNode2(node.next.next);
        return node;
    }
}
