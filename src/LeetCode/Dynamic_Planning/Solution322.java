package LeetCode.Dynamic_Planning;

import java.util.Arrays;

/**
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额
 * 所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 * 时间复杂度为O(N*K),空间复杂度为O(N)
 * 其中N为总金额，k为面值种类
 */
public class Solution322 {
    public static int coinChange(int[] coins, int amount) {
        if(coins == null || coins.length == 0 || amount < 0){
            return -1;
        }
        int N = amount;
        // dp[i]表示当总金额为i时所需的最少硬币个数
        int[] dp = new int[N+1];
        // ***初始化，将除0以为的dp[i]设置为一个不可能的值，便于后续取最小值
        Arrays.fill(dp, N+1);
        dp[0] = 0;
        for(int i = 1; i <= amount; i++){
            // 填表：就是在做选择！就是动态规划中的“规划”呀！
            for(int coin : coins){
                if(i < coin){
                    continue;
                }
                dp[i] = Math.min(dp[i], 1+dp[i-coin]);
            }
        }
        return dp[N] == N+1 ? -1 : dp[N];
    }

    public static void main(String[] args){
        int[] coins = {2};
        int amount = 3;
        int leastNum = coinChange(coins, amount);
        System.out.println(leastNum);
    }
}
