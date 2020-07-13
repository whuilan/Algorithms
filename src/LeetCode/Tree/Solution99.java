package LeetCode.Tree;

/**
 * 恢复二叉搜索树
 * 分析：这个题的难点是先找到那两个顺序颠倒了的节点，找到之后就简单了，交换它们的值就ok了。
 *      我就卡在怎么确定这两个节点。既然是BST，肯定少不了中序遍历。有两个节点顺序颠倒了的话，
 *      那么在中序遍历的过程中，一定会出现当前节点的值小于上一个节点的值（到这一步我都想到了！）
 *      但是分析到这里再看给的两个例子，不知道怎么往下了，出现这种的情况的时候，要找的逆序节点
 *      到底是上一个节点还是当前节点？？看给的两个例子，这两个选择都不对。特别是[1,3,2,4]的例子
 *      两个要交换的节点位置是相邻的！那在中序遍历的过程中只会遇到一次当前节点的值小于上一个节点值
 *      这种情况，那难道只能找到一个节点？？想到这里以为我的思路是错的，就放弃了。
 *      其实没有!可以再举一个例子分析一下呀！两个要交换的节点刚好相邻是一种特殊情况！再举一个不相邻
 *      的例子，就能发现，只要不相邻就会遇到两次当前节点值小于上一个节点值的情况，第一次的时候应该
 *      记录下当前节点的上一个节点pOne，第二次记录下当前节点pTwo，这两个节点就是要交换的节点！为了
 *      处理相邻的特殊情况，第一次的时候也应该记录下当前节点作为pTwo，但是如果后面又出现的话让它被
 *      覆盖就行。
 */
public class Solution99 {
    // 这也是个需要注意的点，pre代表上一个节点，并且在遍历过程中不断更新
    TreeNode pre = new TreeNode(Integer.MIN_VALUE);
    TreeNode pOne;
    TreeNode pTwo;

    public void recoverTree(TreeNode root) {
        inOrder(root);
        int t = pOne.val;
        pOne.val = pTwo.val;
        pTwo.val = t;
    }

    // 找到两个乱序的节点
    private void inOrder(TreeNode root){
        if (root == null){
            return;
        }
        inOrder(root.left);
        if (root.val < pre.val){
            // pOne为空表示第一次遇到，将其设置为上一个节点；不为空表示已经找到了第一个节点，不应该更新
            pOne = pOne == null ? pre : pOne;
            // pTwo会被更新，始终是最后一次遇到逆序对时的当前节点。
            pTwo = root;
        }
        pre = root; // 更新pre!这一步老是忘！review的时候第三遍看还是忘
        inOrder(root.right);
    }

    public static void main(String[] args){
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(1);
        TreeNode node4 = new TreeNode(3);
        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        Solution99 solution99 = new Solution99();
        solution99.recoverTree(node1);
        System.out.print("end");
    }
}
