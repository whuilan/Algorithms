package LeetCode.BacktrackingAndDFS;

import java.util.ArrayList;
import java.util.List;

/**
 * 括号生成：数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的
 * 并且有效的括号组合。如n=2时，有两个有效的括号组合：(()) 和 ()()
 * 思路：自己分析时也发现了某个位置能不能取右括号是受到左括号的限制的！(739题判断
 * 有效的括号也有这个味道)，这种多个不同选择的（但不是很多哈），都可以暴搜 + 剪枝（就是回溯）
 * 比如这个题每步要么增加一个左括号，要么增加一个右括号，是一个二叉的选择，走每一步都有这两个选择
 * 但是并不是每个选择都是符合题目要求的（正确的括号匹配），所以加上if来剪枝，即不满足的条件的舍弃，
 * 走另一个选择，如果当前节点的这两个选择都走过了，那么当前节点就走完了，应该回到当前节点的上一个
 * 节点，走它的另一个选择。
 */
public class Solution22 {
    List<String> list = new ArrayList<>();
    public List<String> generateParenthesis(int n) {
        if (n > 0){
            dfs(n, n, "");
        }
        return list;
    }

    // 左括号剩余leftNum个，右括号剩余rightNum个，选择一个加到当前括号组合curStr上构成有效括号组合
    private void dfs(int leftNum, int rightNum, String curStr){
        // 回溯（递归）终止条件：左括号和右括号都没有剩余了，已经全部添加到curStr上
        if (leftNum == 0 && rightNum == 0){
            list.add(curStr);
            return;
        }
        // 选择一：选择左括号附加到现有括号组合curStr后面，只要左括号还有剩余就可以
        if (leftNum > 0){
            dfs(leftNum - 1, rightNum, curStr + "(");
        }
        // 选择二：选择右括号加到curStr后面，但必须满足rightNum > leftNum（不然就不是有效的了，草稿纸比划一下就知道）
        if (rightNum > leftNum){
            dfs(leftNum, rightNum - 1, curStr + ")");
        }
    }

    public static void main(String[] args){
        Solution22 solution22 = new Solution22();
        List<String> validBrackets = solution22.generateParenthesis(2);
        for (String s : validBrackets){
            System.out.println(s + " ");
        }
        System.out.println();
    }
}
