package LeetCode.BacktrackingAndDFS;

import java.util.ArrayList;
import java.util.List;

/**
 * 数组全排列：给定一个 没有重复 数字的数组，返回其所有可能的全排列。
 * 和剑指38字符串的排列一毛一样，只是把字符串的排列改成了数组而已。
 * 时间复杂度都不可能低于 O(N!)，因为穷举整棵决策树是无法避免的。这也是回溯算法的一个特点，不
 * 像动态规划存在重叠子问题可以优化，回溯算法就是纯暴力穷举，复杂度一般都很高。类似还有51 N皇后
 */
public class Solution46 {
    List<List<Integer>> lists;

    public List<List<Integer>> permute(int[] nums) {
        lists = new ArrayList<>();
        if(nums != null && nums.length != 0){
            List<Integer> list = new ArrayList<>();
            for (int num : nums){
                list.add(num);
            }
            permuteCore(list,0);
        }
        return lists;
    }

    private void permuteCore(List<Integer> list, int start){
        if(start == list.size() - 1){
            lists.add(new ArrayList<>(list));
            return;
        }
        for(int i = start; i < list.size(); i++){
            exch(list, start, i);
            permuteCore(list, start + 1);
            exch(list, start, i);
        }
    }

    private void exch(List<Integer> list, int i, int j){
        int temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}
