package LeetCode.Dynamic_Planning;

/**
 * 最大子序和：给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），
 * 返回其最大和。（即剑指42题：连续子数组的最大和）
 * 分析：这个题除了可以用42题直接分析数学规律的方法，还可以用动态规划的解法，用动态规划来解最重要
 * 的就是确定状态和状态转移方程。这个题设置状态的方式和300【最长递增子序列一模一样！】
 * 设dp[i]表示【以nums[i]为结尾的】连续子数组的最大和（而不是索引从0到i处的连续子数组最大和！）
 * 因为只有这样定义状态，才可以将dp[i]和dp[i-1]联系起来！从而写出状态转移方程，对于遍历到的每个nums[i]
 * dp[i]的取值有两种情况，
 * 当dp[i-1]<=0时，num[i]可以选择“自立门户”,不接在上一个元素后面，就以它自己开头以它自己结尾，
 * 即dp[i]=nums[i];
 * 当dp[i-1]>0时，num[i]就可以接在nums[i-1]后面，因为这时候dp[i-1]+nums[i]肯定是大于num[i]的，
 * 即dp[i]=dp[i-1]+nums[i]
 * 综上dp[i]=Math.max(nums[i]，d[i-1]+nums[i])
 * 可以看出，其实真个思路和剑指42的做法是一样的
 */
public class Solution53 {
    // 法一：动态规划，时间复杂度和空间复杂度均为O(n)
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }
        // dp[i]表示以nums[i]为结尾的连续子数组的最大和
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++){
            dp[i] = Math.max(nums[i], dp[i-1]+nums[i]);
        }
        int maxSum = Integer.MIN_VALUE;
        for (int sum : dp){
            if (sum > maxSum){
                maxSum = sum;
            }
        }
        return maxSum;
    }

    // 剑指42题的解法，实质上和法一是一样的！只是改进了法一的空间复杂度
    public static int FindGreatestSumOfSubArray(int[] array) throws Exception {
        if (array == null || array.length == 0){
            throw new Exception("Empty array!");
        }
        int curSum = 0, maxSum = Integer.MIN_VALUE; // 注意maxSum不能初始化为0，要考虑到全负的数组
        for (int i = 0; i < array.length; i++){
            if (curSum <= 0){
                curSum = array[i];
            }else {
                curSum += array[i];
            }
            if (curSum > maxSum){
                maxSum = curSum;
            }
        }
        return maxSum;
    }

}
