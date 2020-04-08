package sword_to_offer;

import sun.reflect.generics.tree.Tree;

/**
 * P273判断一颗二叉树是不是平衡二叉树: 后序遍历二叉树，一边记录深度一边判断，
 * 这样所有的节点都只用遍历一遍
 */
public class Problem55_2 {
    private boolean isBalanced = true;

    public boolean IsBalanced_Solution(TreeNode root) {
        depth(root);
        return isBalanced;
    }

    private int depth(TreeNode node){
        if (node == null){
            return 0;
        }
        int leftLen = depth(node.left);
        int rightLen = depth(node.right);
        isBalanced = Math.abs(leftLen - rightLen) > 1 ? false : true;
        return 1 + Math.max(leftLen, rightLen);
    }

    public static void main(String[] args){
        TreeNode root1 = new TreeNode(1);
        TreeNode root2 = new TreeNode(2);
        TreeNode root3 = new TreeNode(3);
        TreeNode root4 = new TreeNode(4);
        TreeNode root5 = new TreeNode(5);
        TreeNode root6 = new TreeNode(6);
        TreeNode root7 = new TreeNode(7);
        root1.left = root2;
        root1.right = root3;
        root2.left = root4;
        root2.right = root5;
        root5.left = root7;
        root3.right = root6;
        Problem55_2 problem55_2 = new Problem55_2();
        boolean bool = problem55_2.IsBalanced_Solution(root1);
        System.out.println(bool);
    }
}
