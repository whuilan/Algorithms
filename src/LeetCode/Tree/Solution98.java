package LeetCode.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 验证二叉搜索树
 * 给定一个二叉树（的根节点），判断其是否是一个有效的二叉搜索树。
 * 解这个题真是一波三折...
 * 思路一：递归的前序遍历，每遍历一个节点就判断是否满足大于左子节点，小于右子节点。 X
 *        比如节点5的左子节点为2，父节点为4，这种情况会被判定为true。所有只围绕当前的三个节点判断
 *        是不准确的，对于某个根节点，不仅要小于右子节点，还要小于以右子节点为根的右子树中的所有节点！
 * 思路二：想起了剑指33题给一个数字序列，判断该序列是不是一个二叉搜索树的后序遍历序列，于是想先遍历
 *        得到二叉树的前序遍历序列（中/后序也行），做可以是可以做，但运行超时。
 * 思路三：有点蠢了，一下忘了二叉搜索树有个特点就是中序遍历序列是递增的呀!因此可以通过遍历得到中序遍历序列，
 *        再判断这个序列是不是一个递增序列。
 *        时间复杂度为O(N)：中序遍历二叉树；空间复杂度也为O(N)，list的空间
 */
public class Solution98 {
    public boolean isValidBST(TreeNode root) {
        if (root == null){
            return true;
        }
        List<Integer> list = new ArrayList<>();
        // 得到二叉树的中序遍历序列
        inOrder(root, list);
        for (int i = 0; i < list.size() - 1; i++){
            if (list.get(i) >= list.get(i + 1)){
                return false;
            }
        }
        return true;
    }

    private void inOrder(TreeNode node, List<Integer> list){
       if (node == null){
           return;
       }
       inOrder(node.left, list);
       list.add(node.val);
       inOrder(node.right, list);
    }

    public static void main(String[] args){
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(1);
        TreeNode node3 = new TreeNode(5);
        TreeNode node4 = new TreeNode(2);
        TreeNode node5 = new TreeNode(6);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(9);
        node1.left = node2;
        node1.right = null;
//        node3.left = node4;
//        node3.right = node5;
        Solution98 solution98 = new Solution98();
        boolean valid = solution98.isValidBST(node1);
        System.out.println(valid);
    }
}
