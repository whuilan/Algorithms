package LeetCode.Tree;

/**
 * 合并二叉树
 * 就是树的前序遍历框架！
 */
public class Solution617 {
    public static TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null){
            return t2;
        }
        if (t2 == null){
            return t1;
        }
        // 合并t1和t2，生成一个新的合并节点mergeNode
        TreeNode mergeNode = new TreeNode(t1.val +t2.val);
        // t1和t2的左节点也要进行同样的合并，然后作为mergeNode的左节点
        mergeNode.left = mergeTrees(t1.left, t2.left);
        // 右节点也是一样
        mergeNode.right = mergeTrees(t1.right, t2.right);
        return mergeNode;
    }

    public static void main(String[] args){

    }
}

