package LeetCode.Tree;

/**
 * 翻转二叉树
 * 其实就是简单的二叉树的遍历，遍历的时候交换左右子节点的位置，一直到叶节点
 */
public class Solution226 {
    public TreeNode invertTree(TreeNode root) {
        mirror(root);
        return root;
    }

    private void mirror(TreeNode node){
        if (node == null || (node.left == null && node.right == null)){
            return;
        }
        exch(node);
        mirror(node.left);
        mirror(node.right);
    }

    private void exch(TreeNode node){
        TreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;
    }

    public static void main(String[] args){
        TreeNode node1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(7);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(3);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(9);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        Solution226 solution226 = new Solution226();
        TreeNode mirroTree = solution226.invertTree(node1);
        System.out.println("end");
    }
}
