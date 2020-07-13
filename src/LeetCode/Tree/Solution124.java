package LeetCode.Tree;

/**
 * 二叉树的最大路径和（高频hard题）：本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。
 * 该路径至少包含一个节点，且不一定经过根节点。
 * 思路：（先表扬一下自己，自己做出来啦！棒棒哒~）
 * 这道题有很多道题的影子：比如543二叉树的直径、剑指42连续子数组的最大和。第一反应和543一样，最大和的这条路径
 * 虽然可能不经过整棵树的根节点，但肯定有它自己的根节点。也就是这条路径肯定是以树中的某个节点为根节点，然后再
 * 加上它左边和最大的那条左路径和右边和最大的那条右路径。此处的思路和543一样，接下来就是后序遍历树来找到这个系点，为此需要维护一个
 * 全局变量最大路径和，在后序遍历的过程中依次判断当前节点root是不是就是最大和的路径的根节点。因此在遍历过程中要记录
 * 左子树能提供的最大左子路径和leftMax，右子树能提供的最大右子路径和rightMax，那么root.val+leftMax+rightMax
 * 就是以当前节点为根节点的最大路径和curSum，将其和maxSum比较来不断更新maxSum。有三点需要注意，容易出错：
 * （1）全局变量maxSum应该初始化为整数的最小值Integer.MIN_VALUE，而不是0，因为树中的节点可能全为负数！
 *      那整棵树的最大路径和必然也为负数；
 * （2）leftMax和rightMax不一定是正数，有可能小于0，如果小于0的话，应该直接丢弃掉，不应该再加到当前节点上
 *  以上这两点其实和剑指42一样
 * （3）递归的返回值不是curSum！返回就意味着当前节点计算完了，现在要把它作为子节点连到父节点上，不能把当前
 *    节点的leftMax和rightMax都加上再返回，因为路径不可能分叉呀！应该是加上二者中最大的那个返回，这一点和
 *    543一样。
 *   另外：举例分析非常重要！可以先分析最简单的情况，比如只有三个节点，然后再分析叶节点的情况（很多思路都是在这里找到的）
 *   再分析一般情况
 */
public class Solution124 {
    // 易错点1：初始化最大路径和为最小值！注意不能是0，因为树中的节点值可能全为负值！
    private int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        postOrder(root);
        return maxSum;
    }

    // 代码好简洁！主体就4行（每行一个Math.max哈哈哈）！
    private int postOrder(TreeNode root){
        if (root == null){
            return 0;
        }
        int leftMax = Math.max(0, postOrder(root.left));
        int rightMax = Math.max(0, postOrder(root.right));
        maxSum = Math.max(maxSum, root.val + leftMax + rightMax);
        return root.val + Math.max(leftMax, rightMax);
    }
}
