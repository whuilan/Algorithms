package sword_to_offer;

/**
 * P191 二叉搜索树与双向链表
 */
public class Problem36 {
    public TreeNode Convert(TreeNode pRootOfTree) {
        if (pRootOfTree == null){
            return null;
        }
        pRootOfTree = RecurConvert(pRootOfTree);
        // 注意不仅仅时把二叉搜索树转换完成就行，返回的是双向链表的头节点，不是原来的根节点！
        while (pRootOfTree.left != null){
            pRootOfTree = pRootOfTree.left;
        }
        return pRootOfTree;
    }

    private TreeNode RecurConvert(TreeNode node){  // 和归并排序思路相同
        if (node == null){
            return null;
        }
        // 将左子树和右子树分别先转换好
        TreeNode leftChild = RecurConvert(node.left);
        TreeNode rightChild = RecurConvert(node.right);
        // 左右子树转换好后，按左子树最右节点-当前根节点-右子树最左节点的顺序进行连接
        if (leftChild != null){
            while (leftChild.right != null){
                leftChild = leftChild.right;
            }
            leftChild.right = node;
            node.left = leftChild;
        }
        if (rightChild != null){
            while (rightChild.left != null){
                rightChild = rightChild.left;
            }
            rightChild.left = node;
            node.right = rightChild;
        }
        return node;
    }

    public static void main(String[] args){
        TreeNode root1 = new TreeNode(10);
        TreeNode root2 = new TreeNode(6);
        TreeNode root3 = new TreeNode(14);
        TreeNode root4 = new TreeNode(4);
        TreeNode root5 = new TreeNode(8);
        TreeNode root6 = new TreeNode(12);
        TreeNode root7 = new TreeNode(16);
        root1.left = root2;
        root1.right = root3;
        root2.left = root4;
        root2.right = root5;
        root3.left = root6;
        root3.right = root7;
        Problem36 problem36 = new Problem36();
        TreeNode minNode = problem36.Convert(root1);
        System.out.println("finish");
    }


}
