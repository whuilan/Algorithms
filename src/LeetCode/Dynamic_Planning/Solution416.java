package LeetCode.Dynamic_Planning;

/**
 * 分割等和子集：给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * 分析：这是个隐形0-1背包问题，假设输入数组总和为sum，如果存在题目所说的情况，那么这两个子集的和均为sum/2。
 * 那我们就可以只考虑一个子集，也就是只要我们能从数组中找到几个元素，使得这几个元素的和为sum/2，那这几个元素
 * 就可以组成满足题意得一个子集了，其他的元素就组成了另一个和为sum/2的子集。
 * 把原问题转换为背包问题：给定容量为sum/2的背包和N个物品，第i个物品的重量为nums[i]，从这N个物品中挑选出若干
 * 个物品装进背包，是否存在刚好装满背包的情况？
 * 注意：这个题的初始状态和要设置，和0-1背包不同!应该是dp[..][0] = true, dp[0][..] = false
 * 因为容量为0时就代表装满了呀！而容量不为0，没有物品可选肯定时没装满。（特别的，对于dp[0][0]，没有物品可选，
 * 但也已经是满的了，不用选，所以应该是true）
 */
public class Solution416 {
    public static boolean canPartition(int[] nums) {
        // 先求出数组总和，那么每个子集的和应该为sum / 2;
        int sum = 0;
        for (int num : nums){
            sum += num;
        }
        if (sum % 2 != 0){
            return false; // 总和为奇数，不可能存在两个等和子集
        }
        int s = sum / 2;
        int n = nums.length;
        // dp[i][j]表示对于前i个物品/数，是否能从中挑选出若个物品刚好装满容量为j的背包，布尔值true/false
        boolean[][] dp = new boolean[n + 1][s + 1];
        for (int k = 0; k < n+1; k++){
            dp[k][0] = true;
        }
        // 一个个去看每个面值
        for (int i = 1; i <= n; i++){
            // 一点点扩大容量
            for (int j = 1; j <= s; j++){
                if (nums[i - 1] > j){
                    dp[i][j] = dp[i - 1][j];
                }else {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i-1]];
                }
            }
        }
        return dp[n][s];
    }

    public static void  main(String[] args){
        int[] nums = {1,5,11,5};
        boolean existed = canPartition(nums);
        System.out.println(existed);
    }
}
