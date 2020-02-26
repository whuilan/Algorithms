package sword_to_offer;

/**
 * P65面试题8：二叉树的(中序遍历序列)下一个结点
 */
public class Problem8 {
    public class TreeLinkNode {
        int val;
        TreeLinkNode left = null;
        TreeLinkNode right = null;
        TreeLinkNode next = null;

        TreeLinkNode(int val) {
            this.val = val;
        }
    }
    // 这是我自己写的，有点冗长，但是通过啦！
    public TreeLinkNode GetNext0(TreeLinkNode pNode) {
        if(pNode == null){
            return null;
        }
        if(pNode.right != null){
            TreeLinkNode x = pNode.right;
            while(x.left != null){
                x = x.left;
            }
            return x;
        }
        else if(pNode.next != null && pNode == pNode.next.left){
            return pNode.next;
        }
        else if(pNode.next != null && pNode == pNode.next.right){
             TreeLinkNode x = pNode.next;
             while (x.next != null && x != x.next.left){
                 x= x.next;
             }
             return x.next;
        }
        else {
            return null;
        }
    }

    // 看过参考后发现更简洁的写法，后面几种情况其实是可以合并的
    public TreeLinkNode GetNext(TreeLinkNode pNode)
    {
        if(pNode == null){
            return null;
        }
        if(pNode.right != null){
            TreeLinkNode x = pNode.right;
            while (x.left != null){
                x = x.left;
            }
            return x;
        }
        else {
            while (pNode.next != null){
                TreeLinkNode parent = pNode.next;
                if(pNode == parent.left){
                    return parent;
                }
                pNode = parent;
            }
            return null;
        }
    }
}
