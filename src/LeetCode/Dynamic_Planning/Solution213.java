package LeetCode.Dynamic_Planning;

import java.util.Arrays;

/**
 * 打家劫舍II：是Solution198基础版打家劫舍的拓展，多了首尾相连的限制，即第一家和最后一家是相连的
 * 分析：知道总的动态规划思路和过程与198类型，但是限制了最后一家和第一家不能同时抢，而如果直接用198的方法
 * dp[N-1]中的最大金额可能是包含了第一家的nums[0]，于是想怎么解决这个问题，我思考，如果dp[N-1]中包含
 * 了nums[0]，仅仅把nums[0]减掉或者直接返回dp[N-2]都是不对的（原来由于相邻限制，选了nums[0]就不能
 * 选nums[1]，如果把nums[0]减掉的话说不定就可选nums[1],会影响整个dp数组取值;另外如果最后一个元素很大，
 * 也不能简单地返回dp[N-2]，那就直接把最后一个元素排除在外了）
 * 其实我的思考非常接近解法啦！就是太混乱了，把情况都混在一起考虑！我们可以把环状排列转换为两个单排排列！
 * 第一个就是保证不偷第一间：即取nums[1...N-1]，返回最大金额dp[N-1]
 *  其实就是我上面去掉nums[0]的想法，只是去掉nums[0]之后，相对于198题，要重新计算1~N-1范围的dp并返回dp[N-1]
 * 第二个就是保证不偷最后一间：即取nums[0...N-2]，返回最大金额dp[N-2]
 *  假如nums[0]很大呢，就不应该去掉num[0]，而应该去掉最后一个元素，即在0~N-2之间计算，返回dp[N-2]
 * 此外：善用Arrays.copyOfRange(T[] original,int from,int to)来复制指定索引范围的数组！不包括to!
 */
public class Solution213 {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }
        int N = nums.length;
        if (N == 1){
            return nums[0];
        }
        // 不偷第一家
        int dp1 = rob_dp(Arrays.copyOfRange(nums, 1, N));
        // 不偷最后家
        int dp2 = rob_dp(Arrays.copyOfRange(nums, 0, N - 1));
        return Math.max(dp1, dp2);
    }

    private int rob_dp(int[] nums){
        // 这里又忘了，有可能拆分后的单排排列只有一个元素啊！
        if (nums.length == 1){
            return nums[0];
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++){
            dp[i] = Math.max(dp[i-1], dp[i-2]+nums[i]);
        }
        return dp[nums.length - 1];
    }

    public static void main(String[] args){
        int[] nums = {2,1,1,3};
        Solution213 solution213 = new Solution213();
        int max = solution213.rob(nums);
        System.out.println(max);
    }
}
