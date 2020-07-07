package LeetCode.BacktrackingAndDFS;

import java.util.ArrayList;
import java.util.List;

/**
 * 子集：给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * 说明：解集不能包含重复的子集。
 */
public class Solution78 {
    List<List<Integer>> lists;

    public List<List<Integer>> subsets(int[] nums) {
        lists = new ArrayList<>();
        if (nums != null){
            List<Integer> list = new ArrayList<>();
            backTracking(nums, 0, list);
        }
        return lists;
    }

    private void backTracking(int[] nums, int index, List<Integer> list){
        // 相当于前序遍历
        lists.add(new ArrayList<>(list));
        // 关键：选择添加到当前子集的可以是从index开始的任何一个元素！
        for (int i = index; i < nums.length; i++){
            // 做选择
            list.add(nums[i]);
            // 回溯
            backTracking(nums, i + 1, list); // 注意这里是i，不是index！这就是和全排列中交换方法不一样的地方！
            // 撤销选择
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args){
        int[] nums = {0,1,2};
        Solution78 solution78 = new Solution78();
        List<List<Integer>> lists = solution78.subsets(nums);
        System.out.println("finish");
    }
}
