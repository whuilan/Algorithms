package LeetCode.BacktrackingAndDFS;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;

/**
 * 数组全排列：给定一个 没有重复 数字的数组，返回其所有可能的全排列。
 * 和剑指38字符串的排列一毛一样！
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
            // List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());
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
