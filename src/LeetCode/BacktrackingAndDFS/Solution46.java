package LeetCode.BacktrackingAndDFS;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;

/**
 * 数组全排列：给定一个 没有重复 数字的数组，返回其所有可能的全排列。
 * 和剑指38字符串的排列一毛一样！
 * 时间复杂度都不可能低于 O(N!)，因为穷举整棵决策树是无法避免的。这也是回溯算法的一个特点，不
 * 像动态规划存在重叠子问题可以优化，回溯算法就是纯暴力穷举，复杂度一般都很高。类似还有51 N皇后
 */
public class Solution46 {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        if(nums != null && nums.length != 0){
            permuteCore(nums, 0, lists);
        }
        return lists;
    }

    private void permuteCore(int[] nums, int index, List<List<Integer>> lists){
        if(index == nums.length - 1){
            // 数组转换为list
            lists.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
            return;
        }
        for(int i = index; i < nums.length; i++){
            exch(nums, index, i);
            permuteCore(nums, index + 1, lists);
            exch(nums, index, i);
        }
    }

    private void exch(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
