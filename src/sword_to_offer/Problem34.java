package sword_to_offer;

import java.util.ArrayList;

/**
 * P182二叉树中和为某一值的路径，前序遍历
 */
public class Problem34 {
    private ArrayList<ArrayList<Integer>> paths;
    private int sum;

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        paths = new ArrayList<>();
        if (root != null) {
            ArrayList<Integer> path = new ArrayList<>();
            FindPath(root, target, path);
        }
        return paths;
    }

    private void FindPath(TreeNode node, int target, ArrayList<Integer> path){
        if (node == null){
            return;
        }
        path.add(node.val);
        sum += node.val;
        // 到达叶节点
        if (node.left == null && node.right == null){
            // 此时路径和等于target，则添加，不等于则直接执行40行移除该叶节点
            if (sum == target){
                paths.add(new ArrayList<>(path));
            }
        }
        else {  // 当前节点不是叶节点，则继续访问它的子节点
            FindPath(node.left, target,  path);
            FindPath(node.right, target, path);
        }
        // 当前节点（不管是不是叶节点）访问结束（说明经过这个节点的路径都已经计算过了）后应该回到其父节点
        sum -= node.val;
        path.remove(path.size() - 1);
    }

    public static void main(String[] args){
        TreeNode root1 = new TreeNode(10);
        TreeNode root2 = new TreeNode(5);
        TreeNode root3 = new TreeNode(12);
        TreeNode root4 = new TreeNode(4);
        TreeNode root5 = new TreeNode(7);
        root1.left = root2;
        root1.right = root3;
        root2.left = root4;
        root2.right = root5;
        Problem34 problem34 = new Problem34();
        ArrayList<ArrayList<Integer>> paths = problem34.FindPath(root1, 22);
        System.out.println(paths);
    }
}
