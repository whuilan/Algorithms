package sword_to_offer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * P174 分行从上到下打印二叉树，根据每一层节点的个数进行区分
 */
public class Problem32_2 {
    ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> twoDList = new ArrayList<>();
        if (pRoot == null){
            return twoDList;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(pRoot);
        while (!queue.isEmpty()){
            int cnt = queue.size();  // 本层有多少个节点
            ArrayList<Integer> list = new ArrayList<>();
            while (cnt-- > 0){
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null){
                    queue.offer(node.left);
                }
                if (node.right != null){
                    queue.offer(node.right);
                }
            }
            twoDList.add(list);
        }
        return twoDList;
    }

    public static void main(String[] args){
        TreeNode root1 = new TreeNode(8);
        TreeNode root2 = new TreeNode(6);
        TreeNode root3 = new TreeNode(10);
        TreeNode root4 = new TreeNode(5);
        TreeNode root5 = new TreeNode(7);
        TreeNode root6 = new TreeNode(9);
        TreeNode root7 = new TreeNode(11);
        root1.left = root2;
        root1.right = root3;
        root2.left = root4;
        root2.right = root5;
        root3.left = root6;
        root3.right = root7;
        Problem32_2 problem32_2 = new Problem32_2();
        ArrayList<ArrayList<Integer>> floors = problem32_2.Print(root1);
        System.out.println();
    }
}
