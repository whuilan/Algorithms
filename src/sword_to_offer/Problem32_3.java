package sword_to_offer;

import java.util.*;

/**
 * P176之字形打印二叉树，在奇数层使用Collections.reverse()方法进行反向
 */
public class Problem32_3 {
    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> twoDList = new ArrayList<>();
        if (pRoot == null){
            return twoDList;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(pRoot);
        boolean reverse = false; // 记录是否需要反向，第一层的根节点从左往右不反向，因此将reverse初始化为false
        while (!queue.isEmpty()){
            ArrayList<Integer> list = new ArrayList<>();
            int cnt = queue.size();
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
            if (reverse){
                Collections.reverse(list);
            }
            twoDList.add(list);
            reverse = !reverse;
        }
        return twoDList;
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
        Problem32_3 problem32 = new Problem32_3();
        ArrayList<ArrayList<Integer>> twoDList = problem32.Print(root1);
        System.out.println("finish");
    }
}
