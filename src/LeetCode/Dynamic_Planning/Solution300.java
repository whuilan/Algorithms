package LeetCode.Dynamic_Planning;

import java.util.Arrays;

/**
 * 最长上升子序列：给定一个无序的整数数组，找到其中最长上升子序列的长度。
 * 思路：动态规划，看到“最长”这种求最优解的，就想到可能可以用动态规划，对于这道题来说，就是
 * 一个数一个数地去考虑。我想到了，但是我的问题出在状态转移（方程上），受限于以往做的小部分
 * 题目，老以为当前状态只和上一个状态有关系，发现这样会有问题，就不知道怎么办了。实际上这个
 * 题就是和之前所有的状态都有关系！而不仅仅是上一个！多在草稿纸上举几个例子就能分析出来，反正
 * dp[]一般都把前面所有状态都记下来了。
 * 时间复杂度为O(n^2)，空间复杂度O(n)
 * 反思：遇到瓶颈时可以转换思路，比如计算时从尾到头有问题就试试从头到尾，还有问题就换一个状态
 * 定义等等...这个题的重点是状态的定义：即设dp[i]表示以第i个字符为结尾的最长上升子序列的长度
 */
public class Solution300 {
    /*
    状态定义：由于所求上升子序列一定会以某个字符nums[i]结尾，因此将状态定义为: dp[i]表示以nums[i]
    为结尾的最长上升子序列的长度（解决子序列问题的一个套路！比如还有53题连续子数组的最大和（剑指42））。
    注意：这个定义中 nums[i] 必须被选取，且必须是这个子序列的最后一个元素。
    状态转移：分析，如果num[i]大于在i之前的某个数字nums[j](j<i),那么num[i]就可以接在以nums[j]
    为结尾的递增子序列后面构成一个长度+1的新的递增子序列，即dp[i]=1+max(dp[j])(j<i&&num[j]<nums[i])
    因此遍历到nums[i]时，需要把下标i之前的所有的数都看一遍，找到比num[i]小，同时dp又最大的那个！
     */
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }
        int N = nums.length;
        int[] dp = new int[N];
        // 初始化，每个数字都可以单独构成一个只有它自己的长度为1的递增子序列
        Arrays.fill(dp, 1);
        for (int i = 1; i < N; i++){
            for (int j = 0; j < i; j++){
               if (nums[i] > nums[j]){
                   dp[i] = Math.max(dp[i], dp[j]+1); // 写法很巧妙！避免了又去找所有比num[i]小的数里面dp的最大值
               }
            }
        }
        int maxLen = 1;
        for(int len : dp){
            if(len > maxLen){
                maxLen = len;
            }
        }
        // 注意不是直接返回dp[N-1]，又是和常规动态规划不太一样的地方！
        return maxLen;
    }


    public static void main(String[] args){
        int[] nums = {10,9,2,5,3,7,101,18};
        Solution300 solution300 = new Solution300();
        int maxRisingLen = solution300.lengthOfLIS(nums);
        System.out.println(maxRisingLen);
    }
}
