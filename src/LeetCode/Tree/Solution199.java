package LeetCode.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树的右视图：给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，
 * 返回从右侧所能看到的节点值。
 * 分析：（先表扬一下自己~马上想到方法，并且编程也通过啦~）看了一会儿示例就想到，这不就是102树的层序遍历嘛
 * 只是对于每一层，只记录下最右边的节点而不是全部节点，方法还是借助队列queue的广度优先搜索嘛！代码都差不多，
 * 棒棒的！
 * 时间复杂度：O(n)，每个节点都要入队出队一次
 * 空间复杂度：O(n)，额外的空间queue
 */
public class Solution199 {
    // 这类题目刚开始这五六行都是一样的，循环里面的主体部分也是一样的。。。
    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null){
            return list;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int cnt = queue.size();
            while (cnt-- > 0){ // cnt要减1不要忘了！在这里减和在下面的循环体内部减是一样的！
                TreeNode peek = queue.poll();
                if (peek.left != null){
                    queue.offer(peek.left);
                }
                if (peek.right != null){
                    queue.offer(peek.right);
                }
                // 刚刚弹出的就是该层最后一个元素，添加到返回列表
                if (cnt == 0){
                    list.add(peek.val);
                }
            }
        }
        return list;
    }

    public static void main(String[] args){
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(5);
        TreeNode node5 = new TreeNode(4);
        node1.left = node2;
        node1.right = node3;
        node2.right = node4;
        node3.right = node5;
        List<Integer> rightView = rightSideView(node1);
        for (int i : rightView){
            System.out.print(i + " ");
        }
    }
}
