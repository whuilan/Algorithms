package LeetCode.Tree;

/**
 * 把二叉搜索树转换为累加树
 * 想到了肯定是“右根左”的中序顺序来遍历，但是不知道具体怎么处理节点值的累加。
 * 关键就是设置一个全局的累加和sum，并随着遍历的过程不断更新，每个节点都是和这个全局的累加和进行计算
 * 并更新，因为sum每一步都进行了更新，所以遍历都某个节点的时候，sum已经是所有比它的节点的和了！
 * 而不是仅仅加上它所有的右子节点，举一个左右节点为叶节点的例子就知道这样明显是错的！
 * 跟剑指54题二叉搜索树的第k个节点非常类似，本质是一样的。
 * 此外还暴露了遍历过程想得不够透彻的问题，一般来说左右是对称的，即inOrder(root.right)和
 * inOrder(root.right)会成对出现，形式相同！可以把叶节点的情况代进去想想。每个右节点在遍历过程中
 * 都会变成当前节点！
 */
public class Solution538 {
    private int curSum = 0;
    public TreeNode convertBST(TreeNode root) {
        reverseInOrder(root);
        return root;
    }

    private void reverseInOrder(TreeNode root){
        if (root == null){
            return;
        }
        // 递归处理右子节点
        reverseInOrder(root.right);
        // 处理当前节点
        root.val += curSum;
        curSum = root.val;
        // 递归处理左子节点
        reverseInOrder(root.left);
    }

    public static void main(String[] args){
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        node4.left = node2;
        node4.right = node6;
        node2.left = node1;
        node2.right = node3;
        node6.left = node5;
        node6.right = node7;
        Solution538 solution538 = new Solution538();
        TreeNode sumTree = solution538.convertBST(node4);
        System.out.println(sumTree.val);
    }
}
