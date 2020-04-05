package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和：给定一个整数数组 nums 和一个目标值 target，
 * 请你在该数组中找出和为目标值的那两个整数，并返回他们的数组下标
 */
public class Solution1 {
    // 法一：双层循环的暴力解法，时间复杂度为O(n^2)，空间复杂度为O(1)
    public int[] twoSum0(int[] nums, int target) {
        int[] re = new int[2];
        if (nums != null && nums.length >= 2){
            for (int i = 0; i < nums.length; i++){
                int a = nums[i];
                for (int j = i + 1; j < nums.length;j++){
                    int b = nums[j];
                    if (a + b == target){
                        re[0] = i;
                        re[1] = j;
                        break;
                    }
                }
            }
        }
        return re;
    }

    // 空间换时间：借助HashMap哈希表，将查找时间从O(n)降低到O(1)，时间复杂度为O(n)，空间复杂度也为O(n)
    public static int[] twoSum(int[] nums, int target) {
        if (nums != null &&  nums.length >= 2){
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++){
                map.put(nums[i], i); // 键为数组中的元素，值为索引
            }
            for (int i = 0; i < nums.length; i++){
                int complement = target - nums[i];
                if (map.containsKey(complement) && map.get(complement) != i){
                    return new int[]{i, map.get(complement)};
                }
            }
        }
        return null;
    }

    public static void main(String[] args){
        int[] arr = {3,4,4,7,9};
        int[] re = twoSum(arr, 10);
        System.out.println();
    }
}

