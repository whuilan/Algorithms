package LeetCode.Tree;

/**
 * 二叉树的最近公共祖先（又是剑指上的，最后一题）
 * 核心：通过递归对二叉树进行后序遍历（这样每个节点只用遍历一次），当遇到节点p或q时返回。从底至顶回溯，
 * 当节点p,q在节点root的异侧时，节点root即为最近公共祖先，则向上返回 root。
 * 时间复杂度 O(N) ：其中N为二叉树节点数；最差情况下，需要递归遍历树的所有节点。
 * 空间复杂度 O(N) ：最差情况下，递归深度达到N，系统使用O(N)大小的额外空间。
 */
public class Solution236 {
    // 太棒了，仅仅六行代码！巧妙！
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q){
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        return left == null ? right : right == null ? left : root;
    }

    public static void main(String[] args){
        TreeNode root1 = new TreeNode(3);
        TreeNode root2 = new TreeNode(5);
        TreeNode root3 = new TreeNode(1);
        TreeNode root4 = new TreeNode(6);
        TreeNode root5 = new TreeNode(2);
        TreeNode root6 = new TreeNode(0);
        TreeNode root7 = new TreeNode(8);
        TreeNode root8 = new TreeNode(7);
        TreeNode root9 = new TreeNode(4);
        root1.left = root2;
        root1.right = root3;
        root2.left = root4;
        root2.right = root5;
        root3.left = root6;
        root3.right = root7;
        root5.left = root8;
        root5.right = root9;
        Solution236 solution236 = new Solution236();
        TreeNode anc = solution236.lowestCommonAncestor(root1, root5, root2);
        System.out.println(anc.val);
    }

}
