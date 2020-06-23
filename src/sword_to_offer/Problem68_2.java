package sword_to_offer;

/**
 * 普通二叉树中两个节点的最低公共祖先
 * 所谓两个节点的最低公共祖先，指的是这两个节点都出现在某个节点的子树中，且该节点的深度尽可能大
 * 核心：通过递归对二叉树进行后序遍历（这样每个节点只用遍历一次），当遇到节点p或q时返回。从底至顶回溯，
 * 当节点p,q在节点root的异侧时，节点root即为最近公共祖先，则向上返回 root。
 * 时间复杂度 O(N) ：其中N为二叉树节点数；最差情况下，需要递归遍历树的所有节点。
 * 空间复杂度 O(N) ：最差情况下，递归深度达到N，系统使用O(N)大小的额外空间。
 */
public class Problem68_2 {
    // 在左右子树中查找是否存在 p 或者 q，如果 p 和 q 分别在两个子树中，
    // 那么就说明根节点就是最低公共祖先。即后序遍历！避免遍历重复遍历！
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q)
            return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        return left == null ? right : right == null ? left : root;
    }

    public static void main(String[] args){
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(5);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(6);
        TreeNode node6 = new TreeNode(2);
        TreeNode node7 = new TreeNode(7);
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
        TreeNode ancestor = problem68_2.lowestCommonAncestor(node1, node2, node4);
        System.out.println(ancestor.val);
    }
}
