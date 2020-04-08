package sword_to_offer;

/**
 * P271二叉树的深度
 */
public class Problem55 {
    public int TreeDepth(TreeNode root) {
        if (root == null){
            return 0;
        }
        return 1 + Math.max(TreeDepth(root.left), TreeDepth(root.right));
    }
}
