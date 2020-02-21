package sword_to_offer;

/**
 * P148树的子结构
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
        if (root1.val == root2.val){
            return DoesTree1HasTree2(root1.left, root2.left)
                    && DoesTree1HasTree2(root1.right, root2.right);
        }
        return false;
    }
}
