package LeetCode.Array;

import java.util.*;

/**
 * 三数之和：给你一个包含n个整数的数组 nums，判断nums中是否存在三个元素 a，b，c ，
 * 使得 a+b+c=0 ？请你找出所有满足条件且不重复的三元组
 */
public class Solution15 {
    // 法一：排序+HashMap哈希表，时间复杂度为O(n^2)，空间复杂度为O(n)
    // 排序的目的是为了去重，虽然通过了，但是代码细节很容易出错，尤其是去重
    public static List<List<Integer>> threeSum1(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        if (nums != null && nums.length >= 3){
            int N = nums.length;
            Map<Integer, Integer> map = new HashMap<>();
            Arrays.sort(nums); // 排序是为了防止重复！
            for (int i = 0; i < N; i++){
                map.put(nums[i], i);
            }
            for (int i = 0; i < N - 2; i++){
                // 将三元组中每个元素的位置固定下来，每个三元组就只有一种情况
                if (i > 0 && nums[i] == nums[i-1]){
                    continue;
                }
                for (int j = i + 1; j < N; j++){
                    if (j > i + 1 && nums[j] == nums[j - 1]){
                        continue;
                    }
                    int c = -(nums[i] + nums[j]);
                    if (map.containsKey(c) && map.get(c) > j){
                        List<Integer> list = Arrays.asList(nums[i], nums[j], c);
                        lists.add(list);
                    }
                }
            }
        }
        return lists;
    }

    // 法二：排序+双指针，时间复杂度为O(n^2)，空间复杂度为O(1)
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        if (nums != null && nums.length > 2){
            Arrays.sort(nums);
            int N = nums.length;
            for (int i = 0; i < N - 2; i++){
                if (nums[i] > 0){
                    break;  // 从i开始都是正数，不可能存在和为零的三元组
                }
                if (i > 0 && nums[i] == nums[i - 1]){
                    continue;  // 去重，避免相同的三元组
                }
                int lo = i + 1, hi = N - 1;
                while (lo < hi){
                    int sum = nums[i] + nums[lo] + nums[hi];
                    if(sum < 0){
                        lo++;
                    }
                    else if(sum > 0){
                        hi--;
                    }
                    else{
                        List<Integer> list = Arrays.asList(nums[i], nums[lo], nums[hi]);
                        lists.add(list);
                        while(lo < hi && nums[lo+1] == nums[lo]){
                            lo++;
                        }
                        while(lo < hi && nums[hi-1] == nums[hi]){
                            hi--;
                        }
                        lo++;
                        hi--;
                    }
                }
            }
        }
        return lists;
    }

    public static void main(String[] args){
        int[] nums = {-1, 0, 0, 1, 1, 1, 2, -1, -4};
        List<List<Integer>> re = threeSum(nums);
        System.out.println();
    }
}
