package LeetCode.Tree;

/**
 * 二叉搜索树中的插入操作：给定二叉搜索树（BST）的根节点和要插入树中的值，将值插入二叉搜索树。
 * 返回插入后二叉搜索树的根节点。 保证原始二叉搜索树中不存在新值。
 * 方法：分析所给测试用例,还是套框架，但是一旦涉及“改”，函数就要返回 TreeNode 类型，
 * 并且对递归调用的返回值层层往上进行接收
 */
public class Solution701 {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null){
            return new TreeNode(val);
        }
        if (root.val < val){
            root.right = insertIntoBST(root.right, val);
        }else {
            root.left = insertIntoBST(root.left, val);
        }
        return root;
    }
}
