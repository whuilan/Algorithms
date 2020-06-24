package sword_to_offer;

import java.util.ArrayList;

/**
 * P269二叉搜索树的第K个节点，其实就是二叉搜索/查找树的中序（左根右）遍历序列
 * 的第K个节点，即二叉搜索树的中序遍历序列是一个排序的递增序列
 */
public class Problem54 {
    // 法一：自己写的，获得完整的中序遍历序列再输出其第k项，
    // 缺点：需要额外的空间ArrayList，空间复杂度为O(n)
    private ArrayList<TreeNode> list;
    TreeNode KthNode1(TreeNode pRoot, int k)
    {
        if (pRoot == null || k <= 0){
            return null;
        }
        list = new ArrayList<>();
        inOrderKthNode(pRoot);
        if (k > list.size()){
            return null;
        }
        return list.get(k - 1);
    }

    private void inOrderKthNode(TreeNode node){
        if (node == null){
            return;
        }
        inOrderKthNode(node.left);
        list.add(node);
        inOrderKthNode(node.right);
    }

    // 法二：设置一个计数器变量，而不需要消耗ArrayList的额外空间
    private TreeNode ret;
    private int N;
    TreeNode KthNode(TreeNode pRoot, int k)
    {
        if (pRoot == null || k <= 0){
            return null;
        }
        inOrder(pRoot, k);
        return ret;
    }

    // 按照中序遍历序列对节点数目N进行计数
    private void inOrder(TreeNode node, int k){
        if (node == null){
            return;
        }
        inOrder(node.left, k);
        N++;  // 其实和法一我自己的思路是一样的，这里的计数N++就相当于上面的list.add(node)
        if (N == k){
            ret = node;
            return;
        }
        inOrder(node.right, k);
    }

    public static void main(String[] args){
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        TreeNode four = new TreeNode(4);
        TreeNode five = new TreeNode(5);
        TreeNode six = new TreeNode(6);
        TreeNode seven = new TreeNode(7);
        TreeNode eight = new TreeNode(8);
        five.left = three;
        five.right = seven;
        three.left = two;
        three.right = four;
        seven.left = six;
        seven.right = eight;
        Problem54 problem54 = new Problem54();
        TreeNode node = problem54.KthNode(five, 3);
        if (node != null){
            System.out.println(node.val);
        }
    }
}
