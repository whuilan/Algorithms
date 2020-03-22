package sword_to_offer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * P194序列化（和反序列化）二叉树,注意二叉树节点中的值不一定全是0-9的个位数，还可能有几十，几百等等
 */
public class Problem37 {
    private int index = 0;

    String Serialize(TreeNode root) {
        if (root == null){
            return "#";
        }
        return root.val + "!" + Serialize(root.left) + "!" + Serialize(root.right);
    }

    // 法一：使用全局变量设置一个索引来记录反序列化的位置
    public TreeNode deserialize(String data) {
        String[] vals = data.split("!");
        return deserialize(vals);
    }

    private TreeNode deserialize(String[] vals){
        String firstVal = vals[index++];
        if (firstVal.equals("#")){
            return null;
        }
        int rootVal = Integer.parseInt(firstVal);
        TreeNode root = new TreeNode(rootVal);
        root.left = deserialize(vals);
        root.right = deserialize(vals);
        return root;
    }

    // 法二：不使用全局
    public TreeNode Deserialize(String data){
        String[] vals = data.split("!");
        Queue<String> queue = new LinkedList<>();
        for (String str : vals){
            queue.offer(str);
        }
        return Deserialize(queue);
    }

    private TreeNode Deserialize(Queue<String> queue){
        String firstVal = queue.poll();
        if (firstVal.equals("#")){
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(firstVal));
        root.left = Deserialize(queue);
        root.right = Deserialize(queue);
        return root;
    }

    public static void main(String[] args){
        TreeNode root1 = new TreeNode(1);
        TreeNode root2 = new TreeNode(2);
        TreeNode root3 = new TreeNode(3);
        TreeNode root4 = new TreeNode(4);
        TreeNode root5 = new TreeNode(5);
        TreeNode root6 = new TreeNode(6);
        root1.left = root2;
        root1.right = root3;
        root2.left = root4;
        root3.left = root5;
        root3.right = root6;
        Problem37 problem37 = new Problem37();
        String treeStr = problem37.Serialize(root1);
        TreeNode root = problem37.Deserialize(treeStr);
        System.out.println("Finish Serialization");
    }
}
