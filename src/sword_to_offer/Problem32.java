package sword_to_offer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * P171(不分行)从上到下打印二叉树，核心思想：宽度优先搜索：借助先进先出的队列数据结构，而不使用递归
 */
public class Problem32 {
    // 最开始想到的方法，节点在进入队列时就添加它的值
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        if (root == null){
            return list;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        list.add(root.val);
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            if (node.left != null){
                list.add(node.left.val);
                queue.offer(node.left);
            }
            if (node.right != null){
                list.add(node.right.val);
                queue.offer(node.right);
            }
        }
        return list;
    }

    // 跟上面的其实是一样，只是节点在出队列时才添加其值
    public ArrayList<Integer> PrintFromTopToBottom2(TreeNode root) {
        ArrayList<Integer> tree = new ArrayList<>();
        if (root == null){
            return tree;    // 注意空的ArrayList和null还不一样的
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            tree.add(node.val);
            if (node.left != null){
                queue.offer(node.left);
            }
            if (node.right != null){
                queue.offer(node.right);
            }
        }
        return tree;
    }

    public ArrayList<Integer> PrintFromTopToBottom3(TreeNode root) {
        ArrayList<Integer> tree = new ArrayList<>();
        if (root == null){
            return tree;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int cnt = queue.size();
            while (cnt-- > 0){
                TreeNode node = queue.poll();
                if (node == null){
                    continue;
                }
                tree.add(node.val);
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }
        return tree;
    }

    public static void main(String[] args){
        TreeNode root1 = new TreeNode(1);
        TreeNode root2 = new TreeNode(2);
        TreeNode root3 = new TreeNode(3);
        TreeNode root4 = new TreeNode(4);
        TreeNode root5 = new TreeNode(5);
        TreeNode root6 = new TreeNode(6);
        TreeNode root7 = new TreeNode(7);
        root1.left = root2;
        root1.right = root3;
        root2.left = root4;
        root2.right = root5;
        root3.left = root6;
        root3.right = root7;
        Problem32 problem32 = new Problem32();
        ArrayList<Integer> arrayList = problem32.PrintFromTopToBottom(root1);
        System.out.println("finish");
    }
}
