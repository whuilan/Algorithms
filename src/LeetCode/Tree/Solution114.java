package LeetCode.Tree;

/**
 * 二叉树展开为链表：给定一个二叉树，原地将它展开为一个单链表
 * 很明显一看就是要前序遍历preOrder（根左右，再依次相连），关键在于怎么连起来，受到538题的启发和教训，
 * 不应该在递归中直接将root.right置为root.left（很明显，此时对于右子树中的节点是有bug的），
 * 应该是先对根点做某种处理，然后对称地遍历preOrder(root.left),preOrder(root.right)，
 * 这个题难就难在先对根节点做什么处理，再递归的去访问其左右子节点，同样根据538题猜测应该是维护一个
 * 全局变量并且在递归过程中更新这个全局变量，还是想了很久没想出来，最后找了一个为右节点的叶子节点试了
 * 一下，试出来了！
 */
public class Solution114 {
    private TreeNode preNode = new TreeNode(0);
    public void flatten(TreeNode root) {
        if (root == null){
            return;
        }
        preNode.right = root;
        preNode = root;
        // 注意：因为flatten(root.left)会改变root.right的指向，所以应该先把右节点取出来暂存！
        TreeNode rigthNode = root.right;
        flatten(root.left);
        // 左边的指针清理掉！
        root.left = null;
        flatten(rigthNode);
    }

    public static void main(String[] args){
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        node1.left = node2;
        node1.right = node5;
        node2.left = node3;
        node2.right = node4;
        node5.right = node6;
        Solution114 solution114 = new Solution114();
        solution114.flatten(node1);
        System.out.println("end");
    }
}
