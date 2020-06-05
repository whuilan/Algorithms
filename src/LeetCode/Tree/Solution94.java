package LeetCode.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个二叉树，返回它的中序 遍历。
 */
public class Solution94 {
    // 法一：经典的遍历树的方法，递归，时间复杂度为O(n)，空间最坏情况下需要空间O(n)，
    // 平均情况为O(logn)，其实就是树的高度
    public List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root != null){
            inorder(root, list);
        }
        return list;
    }

    private void inorder(TreeNode node, List<Integer> list){
        if(node == null){
            return;
        }
        inorder(node.left, list);
        list.add(node.val);
        inorder(node.right, list);
    }

    // 法二：迭代。想到了要借用栈，因为递归的本质就是一个隐式的栈，但是不知道具体要怎么用
    // 其实就是用栈模拟递归，仔细分析递归的过程就是递归的调用过程是不断往左边走(显然这是一层循环)，当
    // 左边走不下去了，就打印当前节点，并转向右边，然后右边继续这个过程。(继续这个过程又是一个循环，
    // 所以有两层循环！)时间复杂度为O(n)，空间复杂度也为O(n)
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root != null){
            Stack<TreeNode> stack = new Stack<>();
            TreeNode curNode = root;
            while (curNode!= null || !stack.isEmpty()){
                while (curNode != null){
                    // 栈的一次push就相当于递归里面的一次调用！
                    stack.push(curNode);
                    curNode = curNode.left;
                }
                // 没有左子节点了，则打印最后一个非空左子节点（即为此时的栈顶元素！）
                // 或是curNode为某个节点的右节点且为null，则说明这个节点已经处理完，
                // 也应该继续弹出
                curNode = stack.pop();
                list.add(curNode.val);
                // 在右边重复这个过程！
                curNode = curNode.right;
            }
        }
        return list;
    }

    public static void main(String[] args){
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        Solution94 solution94 = new Solution94();
        List<Integer> list = solution94.inorderTraversal2(node1);
        for(int i : list){
            System.out.print(i + " ");
        }
    }
}
