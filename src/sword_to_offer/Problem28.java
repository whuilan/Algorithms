package sword_to_offer;

/**
 * P159对称的二叉树，核心思想：前序遍历（根左右）和自定义对称的前序遍历（根右左）
 * 是否相同。自己举例子画图表示！随便选取两个对称的节点，如果它们的值相等，接下来就
 * 应该比较节点1的左节点和节点2的右节点是否相等，节点1的右节点和节点2的左节点是否想等。
 */
public class Problem28 {
    boolean isSymmetrical(TreeNode pRoot)
    {
        if (pRoot == null){
            return true;   // 注意空节点默认是对称的二叉树
        }
        return isSymmetrical(pRoot.left, pRoot.right);
    }

    // pRoot1和pRoot2为每次递归调用的一对对称的根节点
    private boolean isSymmetrical(TreeNode pRoot1, TreeNode pRoot2){
        if (pRoot1 == null && pRoot2 == null){
            return true;
        }
        if (pRoot1 == null || pRoot2 == null){
            return false;
        }
        if (pRoot1.val != pRoot2.val){
            return false;
        }
        return isSymmetrical(pRoot1.left, pRoot2.right) && isSymmetrical(pRoot1.right, pRoot2.left);
    }

    public static void main(String[] args){
        TreeNode node1 = new TreeNode(8);
        TreeNode node2 = new TreeNode(6);
        TreeNode node3 = new TreeNode(6);
        TreeNode node4 = new TreeNode(5);
        TreeNode node5 = new TreeNode(7);
        TreeNode node6 = new TreeNode(7);
        TreeNode node7 = new TreeNode(5);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        Problem28 problem28 = new Problem28();
        boolean sym = problem28.isSymmetrical(node1);
        System.out.println(sym);
    }
}
