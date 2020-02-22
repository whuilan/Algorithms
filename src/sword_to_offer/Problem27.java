package sword_to_offer;

/**
 * P157二叉树的镜像，其实就是从根节点开始，通过遍历把所有节点的左子节点和右子节点交换位置
 */
public class Problem27 {
    public void Mirror(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)){
            return;
        }
        swapLeftAndRight(root);
        Mirror(root.left);
        Mirror(root.right);  // 这三行的形式有点类似快排
    }

    private void swapLeftAndRight(TreeNode node){
        TreeNode tempNode = node.left;
        node.left = node.right;
        node.right = tempNode;
    }
}
