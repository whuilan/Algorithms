package sword_to_offer;

import java.util.HashMap;
import java.util.Map;

/**
 * 重建二叉树
 */
public class Problem7 {
    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    // 保存中序遍历结果中的索引位置
    private Map<Integer, Integer> inIndexes = new HashMap<>();

    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        if(pre == null || in == null || pre.length <= 0 || pre.length != in.length){
            return null;
        }
        for(int i = 0; i < in.length;i++){
            inIndexes.put(in[i], i);
        }
        return reConstructBinaryTree(pre, 0, pre.length - 1,0);
    }

    private TreeNode reConstructBinaryTree(int[] pre, int preStart, int preEnd, int inStart){
        if(preStart > preEnd){
            return null;
        }
        TreeNode root = new TreeNode(pre[preStart]);  // 前序遍历结果中的第一个数就是根节点
        int rootIndex = inIndexes.get(root.val);   // 获取根结点在中序遍历结果中的索引位置
        int leftTreeSize = rootIndex - inStart;
        root.left = reConstructBinaryTree(pre, preStart + 1, preStart + leftTreeSize, inStart);
        root.right = reConstructBinaryTree(pre, preStart + leftTreeSize + 1, preEnd, rootIndex + 1);
        return root;
    }

    public static void main(String[] args){
//        int[] pre = {1,2,4,7,3,5,6,8};
//        int[] in = {4,7,2,1,5,3,8,6};
        int[] pre = {3,9,20,15,7};
        int[] in = {9,3,15,20,7};
        Problem7 tree = new Problem7();
        TreeNode reConstructedTree = tree.reConstructBinaryTree(pre, in);
        System.out.println("success!");
    }
}
