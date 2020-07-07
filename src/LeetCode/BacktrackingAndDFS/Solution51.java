package LeetCode.BacktrackingAndDFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * N皇后：n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 核心：就是全排列问题，用回溯法解决。关键：创建一个cols[n]数组，其中cols[i]表示第i行皇后所处的
 * 列号，对col进行全排列找到所有可能的排列，再一一判断每个排列是否在同一个对角线上
 */
public class Solution51 {
    List<List<String>> lists;
    int N;

    public List<List<String>> solveNQueens(int n) {
        lists = new ArrayList<>();
        if (n <= 0){
            return lists;
        }
        N = n;
        // cols[i]表示第i行皇后所在的列号
        int[] cols = new int[n];
        for (int i = 0; i < n; i++){
            cols[i] = i; // 每行皇后初始化为不同的列，这样就已经保证每个皇后不同行不同列了
        }
        permutation(cols, 0);
        return lists;
    }

    private void permutation(int[] cols, int start){
        // 找到一个符合条件的列的排列，构建String列表
        if (start == N - 1){
            if (isValid(cols)){
                List<String> list = new ArrayList<>();
                char[] cs;
                // 每一行
                for (int i = 0; i < N; i++){
                    // 每一列
                    cs = new char[N];
                    Arrays.fill(cs, '.');
                    int col = cols[i];
                    cs[col] = 'Q';
                    list.add(String.valueOf(cs));
                }
                lists.add(list);
            }
            return;
        }
        // 回溯核心部分，遍历选择列表，每个start都可以和后面的元素依次交换位置（包括自身）
        for (int i = start; i < N; i++){
            // 做选择
            exch(cols, start, i);
            // 回溯
            permutation(cols, start + 1);
            // 撤销选择
            exch(cols, start, i);
        }

    }

    // 判断是否存在两个皇后在同一条对角线上
    private boolean isValid(int[] cols){
        for (int i = 0; i < cols.length; i++){
            for (int j = i + 1; j < cols.length; j++){
                if (i - j == cols[i] - cols[j] || i - j == cols[j] - cols[i]){
                    return false;
                }
            }
        }
        return true;
    }

    private void exch(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args){
        Solution51 solution51 = new Solution51();
        List<List<String>> lists = solution51.solveNQueens(4);
        System.out.println();
    }
}
