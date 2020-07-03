package LeetCode.Tree;

/**
 * 剑指原题，分别用前序遍历（根左右）和自定义的对称前序遍历（根右左）来对根节点的左右子节点分别遍历
 */
public class Solution101 {
    public boolean isSymmetric(TreeNode root) {
        if (root == null){
            return true; // 面试需要确认空节点是否算对称二叉树；
        }
        return symmetricCore(root.left, root.right);
    }

    // 三个if加一个return，代码很优美
    private boolean symmetricCore(TreeNode root1, TreeNode root2){
        // 两个节点都为空（说明比较到了叶节点），返回true
        if (root1 == null && root2 == null){
            return true;
        }
        // 跳过了前面的if说明至少有一个节点不为空，此时如果其中一个为空一个不为空，那肯定不对称，直接返回false
        if (root1 == null || root2 == null){
            return false;
        }
        // 两个均不为空，如果值不相等则不对称，直接返回false
        if (root1.val != root2.val){
            return false;
        }
        return symmetricCore(root1.left, root2.right) && symmetricCore(root1.right, root2.left);
    }

    public static void main(String[] args){
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(2);
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(3);
//        TreeNode node6 = new TreeNode(4);
//        TreeNode node7 = new TreeNode(3);
        node1.left = node2;
        node1.right = node3;
        node2.right = node4;
        node3.right = node5;
//        node3.left = node6;
//        node3.right = node7;
        Solution101 solution101 = new Solution101();
        boolean b = solution101.isSymmetric(node1);
        System.out.println();
    }
}
