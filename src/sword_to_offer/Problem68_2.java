package sword_to_offer;

/**
 * 普通二叉树中两个节点的最低公共祖先
 * 思路：从根节点开始，在左右子树中遍历，查找左右子数是否存在p,q，对于一个节点来说有三种情况：
 * （1）左右子树中均不存在p和q，则该节点返回null
 * （2）左/右子树中的一个存在p或q，则返回找到的p/q
 * （3）左/右子树中刚好一个存在p，一个存在q，则返回该节点本身
 * 最后返回的是第一个（3）的节点，即在左子树和右子树中分别找到了一个p/q。
 * （延伸：若每个节点有指向父节点的链接，那么问题就转换成了求两条链表的第一个公共节点）
 */
public class Problem68_2 {
    // 在左右子树中查找是否存在 p 或者 q，如果 p 和 q 分别在两个子树中，那么就说明根节点就是最低公共祖先。
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q)
            return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        return left == null ? right : right == null ? left : root;
    }

    public static void main(String[] args){
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(5);
        TreeNode node3 = new TreeNode(1);
        TreeNode node4 = new TreeNode(6);
        TreeNode node5 = new TreeNode(2);
        TreeNode node6 = new TreeNode(0);
        TreeNode node7 = new TreeNode(8);
//        TreeNode node8 = new TreeNode(7);
//        TreeNode node9 = new TreeNode(4);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
//        node5.left = node8;
//        node5.right = node9;
        Problem68_2 problem68_2 = new Problem68_2();
        TreeNode ancestor = problem68_2.lowestCommonAncestor(node1, node4, node5);
        System.out.println(ancestor.val);
    }
}
