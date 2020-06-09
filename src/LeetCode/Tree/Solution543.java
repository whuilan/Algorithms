package LeetCode.Tree;

/**
 * 二叉树的直径：给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度
 * 中的最大值。这条路径可能穿过也可能不穿过根结点。
 * 思路：第一直觉没错！就是某个节点左边最长的路径和右边最长的路径连起来，为了避免重复遍历，应该用后序
 * 遍历的方式依次遍历每个节点，一遍记录子树的长度，一边判断以该节点为根的树，是不是就是要返回的路径穿过
 * 的节点。和剑指中判断二叉树是否是平衡二叉树55_2极其相似！
 */
public class Solution543 {
    int maxLen = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        depth(root);
        return maxLen;
    }

    // 求以root为根节点的树的长度（指根节点到叶节点包含的节点数目！）
    private int depth(TreeNode root){
        if (root == null){
            return 0;
        }
        int leftDepth = depth(root.left);
        int rightDepth = depth(root.right);
        if (leftDepth + rightDepth > maxLen){
            maxLen = leftDepth + rightDepth;
        }
        return 1 + Math.max(leftDepth, rightDepth);
    }

    public static void main(String[] args){
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        Solution543 solution543 = new Solution543();
        int maxLen = solution543.diameterOfBinaryTree(node1);
        System.out.println(maxLen);
    }
}
