package sword_to_offer;

/**
 * P157二叉树的镜像，其实就是从根节点开始，通过遍历把所有节点的左子节点和右子节点交换位置
 */
public class Problem27 {
    //  将原树变换为二叉树的镜像（不另外生成一颗新的树）
    public void Mirror(TreeNode root) {
        if (root == null){
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

    // 生成一颗新的树
    public static TreeNode mirrorTree(TreeNode root) {
        if (root == null){
            return null;
        }
        TreeNode newRoot = new TreeNode(root.val);
        newRoot.left = mirrorTree(root.right);
        newRoot.right = mirrorTree(root.left);
        return newRoot;
    }

    public static void main(String[] args){
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node3.right = node5;
        TreeNode newTree = mirrorTree(node1);
        System.out.println();
    }


}
