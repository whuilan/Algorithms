package LeetCode.Dynamic_Planning;

/**
 * 打家劫舍
 * 思路：假设dp[i]表示经过第i家时能够偷窃到的最高金额，对于经过的每一家都有两个选择：偷or不偷(先不
 * 考虑相邻限制，暴力枚举)，目的都是使打劫到这一家时偷窃的金额最大。再来考虑状态转移，如果不偷，那么
 * dp[i]=dp[i-1]，即和经过上一家时能偷到的最高金额相同。如果选择偷，那么上一家就一定不能偷，相当于
 * 跳过了上一家，再加上这一家的金额，即dp[i]=dp[i-2]+num[i]，注意dp[i-1]和dp[i-2]也都有偷和不偷
 * 两种情况/选择，不一定就包含了本身，dp[i]就只管用到它们的结果就好了，不关心它们是怎么算出来的，有
 * 没有包含自身，不管有没有包含自身，我们的状态转移逻辑都是对的（无后效性）。状态转移方程为：
 * dp[i]=Math.max(dp[i-1], dp[i-2]+nums[i])
 */
public class Solution198 {
    public static int rob(int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }
        // 易忘，只有一个元素就直接返回！
        if(nums.length == 1){
            return nums[0];
        }
        int N = nums.length;
        // 定义状态
        int[] dp = new int[N];
        // 初始化
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]); // 不能偷窃相邻的
        // 填表（状态转移）
        for (int i = 2; i < N; i++){
            dp[i] = Math.max(dp[i-1],dp[i-2]+nums[i]);
        }
        return dp[N-1];
    }

    public static void main(String[] args){
        int[] nums = {2,1,1,3};
        int max = rob(nums);
        System.out.println(max);
    }
}
