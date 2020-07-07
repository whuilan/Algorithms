package LeetCode.BacktrackingAndDFS;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合：给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 * 分析：套回溯算法模板就行，这里k限制了树的深度，n限制了树的宽度（每次的选择）
 */
public class Solution77 {
    List<List<Integer>> lists;
    int N,K;

    public List<List<Integer>> combine(int n, int k) {
        lists = new ArrayList<>();
        if (n < 1 || k < 1 || k > n){
            return lists;
        }
        N = n;
        K = k;
        List<Integer> track = new ArrayList<>();
        backTracking(1, track);
        return lists;
    }

    private void backTracking(int start, List<Integer> track){
        if (track.size() == K){
            lists.add(new ArrayList<>(track));
            return;
        }
        for (int i = start; i <= N; i++){
            track.add(i);
            backTracking(i+1, track);
            track.remove(track.size() - 1);
        }
    }

    public static void main(String[] args){
        Solution77 solution77 = new Solution77();
        List<List<Integer>> lists = solution77.combine(4, 2);
        System.out.println("finish");
    }
}
