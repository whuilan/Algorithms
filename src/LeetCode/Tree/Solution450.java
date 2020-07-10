package LeetCode.Tree;

/**
 * 删除二叉搜索树中的节点：给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，
 * 并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
 * 分析：要删除的节点有几种情况：
 * 1 就是叶节点，那么直接删除即可
 * 2 只有左子节点（or右子节点），另一边为空，那么直接返回左子节点（or 右子节点）
 * 3 左右子节点都不为空，要删除该节点同时又要保证BST结构不变，那么应该将该节点替换成右子树中的最小节点（or左子树）
 * 中的最大节点），我想到啦！棒棒的！就是我的实现是把把这个节点挪到当前根节点的位置，同时把该节点的父节点指向空，
 * 这样实现起来代码有点复杂，容易出错！实际上可以不用这么麻烦！直接把根节点的值替换成rightMin的值，再在根节点的
 * 右子树中删掉rightMin即可，巧妙！
 */
public class Solution450 {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null){
            return null;
        }
        if (root.val < key){
            root.right = deleteNode(root.right, key);
        }
        else if (root.val > key){
            root.left = deleteNode(root.left, key);
        }
        else {
            // 要删除的节点左子节点为空，只有右子节点，直接返回其右子节点
            if (root.left == null){
                return root.right;
            }
            // 要删除的节点右子节点为空，只有左子节点，直接返回其左子节点
            if (root.right == null){
                return root.left;
            }
            // 左右子节点都不为空，则将其替换成右子树中的最小节点rightMin（or左子树的最大节点）
            // 直接把值改成rightMin的值，然后再在右子树中删除rightMin就好了呀！不用那么麻烦！
            TreeNode rightMin = root.right;
            while (rightMin.left != null){
                rightMin = rightMin.left;
            }
            root.val = rightMin.val;
            root.right = deleteNode(root.right, root.val);
        }
        return root;
    }

    public static void main(String[] args){
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(6);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(7);
        TreeNode node7 = new TreeNode(3);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.right = node6;
        // node5.left = node7;
        Solution450 solution450 = new Solution450();
        TreeNode deletedTree = solution450.deleteNode(node1, 2);
        System.out.println(deletedTree.val);
    }
}
