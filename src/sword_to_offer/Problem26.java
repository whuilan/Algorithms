package sword_to_offer;

/**
 * P148判断一个树是不是另一个树的子结构
 * 思路/着手：分两步
 * 第一步，先找出root1中和root2的值相等的节点（其实就是一个遍历的过程）
 * 第二步，比较root1和root2的左右子节点是否也是相等的，还是一个遍历的过程
 */
public class Problem26 {
    public boolean HasSubtree(TreeNode root1,TreeNode root2) {
        if (root1 == null || root2 == null){
            return false;
        }
        boolean result = false;
        // 先遍历root1找到和root2根节点的值相等的节点，找到了就代入到下面的函数继续比较左右节点
        if (root1.val == root2.val){
            result = DoesTree1HasTree2(root1, root2);
        }
        // 注意一次失败了并不意味着root1中就没有其他的子树和root2匹配了，应该继续向下查找，此外
        // || 运算符会先计算左边的表达式，如果为true就不会再计算右边了（&&同理）
        result = result || HasSubtree(root1.left, root2) || HasSubtree(root1.right, root2);
        return result;
    }

    public boolean DoesTree1HasTree2(TreeNode root1, TreeNode root2){
        if (root2 == null){
            return true;
        }
        if (root1 == null){  // 注意判断是否重复，能通过第一个if那肯定满足root2 != null
            return false;
        }
        // 根节点相等了再递归地去比较左右子树中的节点是否相等
        if (root1.val == root2.val){
            return DoesTree1HasTree2(root1.left, root2.left)
                    && DoesTree1HasTree2(root1.right, root2.right);
        }
        return false;
    }

    public static void main(String[] args){
        TreeNode node1 = new TreeNode(6);
        TreeNode node2 = new TreeNode(8);
        TreeNode node3 = new TreeNode(7);
        TreeNode node4 = new TreeNode(9);
        TreeNode node5 = new TreeNode(2);
        TreeNode node6 = new TreeNode(7);
        TreeNode node7 = new TreeNode(9);
        TreeNode node8 = new TreeNode(2);
        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node3.right = node5;
        node6.left = node7;
        node6.right = node8;
        Problem26 problem26 = new Problem26();
        boolean b = problem26.HasSubtree(node1, node6);
        System.out.println(b);
    }
}
