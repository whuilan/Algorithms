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
}
