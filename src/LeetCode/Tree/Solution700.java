package LeetCode.Tree;

/**
 * 二叉搜索树中的搜索：给定二叉搜索树（BST）的根节点和一个值。 你需要在BST中找到节点值等于给定值的节点。
 * 返回以该节点为根的子树。 如果节点不存在，则返回 NULL
 * 分析：用前序遍历逐个来比较是可以，但是没有用到BST的特点，给了BST一般要用到它的特点
 */
public class Solution700 {
    // 其实就是简单的二分查找
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null){
            return null;
        }
        if (root.val < val){
            return searchBST(root.right, val);
        }
        else if(root.val > val){
            return searchBST(root.left, val);
        }
        else{
            return root;
        }
    }
}
