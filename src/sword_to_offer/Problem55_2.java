package sword_to_offer;

/**
 * P273判断一颗二叉树是不是平衡二叉树（任意节点的左、右子树的深度相差不超过1）
 * 后序（左右根）遍历二叉树，一边记录深度（遍历）一边判断
 * 每个节点是不是平衡的，这样所有的节点都只用遍历一遍，时间复杂度为O(n)
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
        if (Math.abs(leftLen - rightLen) > 1){
            isBalanced = false;
        }
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
        TreeNode root8 = new TreeNode(8);
        TreeNode root9 = new TreeNode(9);
        TreeNode root10 = new TreeNode(10);
        TreeNode root11= new TreeNode(11);
        root1.left = root2;
        root2.left = root3;
        root3.left = root4;
        root4.left = root5;
        root2.right = root6;
        root1.right = root8;
        root8.left = root7;
        root8.right = root9;
        root9.right = root10;
        root10.right = root11;
        Problem55_2 problem55_2 = new Problem55_2();
        boolean bool = problem55_2.IsBalanced_Solution(root1);
        System.out.println(bool);
    }
}
