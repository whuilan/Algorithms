package LeetCode.Dynamic_Planning;

/**
 * 零钱兑换II:给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。
 * 假设每一种面额的硬币有无限个。
 * 分析：刚开始想用和322零钱兑换I相同的方法，设dp[i]为当总金额为i时，有dp[i]种方法可以凑齐
 * 在草稿纸上演算的过程中，发现这样做答案不对（从i=3开始），仔细检查发现是因为出现了重复情况，其实零钱兑换I中的解法
 * 也会出现重复情况，但是因为I求的是最少的硬币数，所以没有影响。而我们这道题求的就是有多少种组合方法，组合与顺序无关，
 * 当然就出错了！
 * 这个题其实是完全背包问题：
 * 有一个背包，最大容量为 amount，有几种不同的物品 coins，每种类型的物品的重量分别为 coins[i]，且每种物品的数量
 * 无限。请问有多少种方法，能够把背包恰好装满？
 * 这和0-1背包最大的区别就是0-1背包中每种类型的物品只有一个，但是完全背包每种物品有无数个！也就是说，对于每个物品
 * 而已，【选择】不仅仅是【选】和【不选】这两种情况，如果选的话，还可能选很多个！
 * 但其实大体都是一样的：状态仍然设置为【物品的种类】和【背包的容量】，用dp[i][j]表示对于前i种物品，当容量为j时，
 * 有dp[i][j]种方法装满背包，背包问题都是这个套路！只是状态转移方程有一点变化而已。
 * 完全背包还有：牛客七月份的模拟笔试第三题
 */
public class Solution518 {
    // 法一：最直观的方法：三层循环，求dp[i][j]时讨论当前面额选取0张，1张，2张的情况
    // 时间复杂度为O(n*amount^2)，空间复杂度为O(n*amount)
    public static int change1(int amount, int[] coins) {
        if (amount == 0){
            return 1;  // 只要需要凑的金额为0，默认啥不选也是一种凑法！
        }
        if (amount < 0 || coins ==  null || coins.length == 0){
            return 0;
        }
        int N = coins.length;
        int M = amount;
        // dp[i][j]表示前i中面额能够凑成总金额的组合数
        int[][] dp = new int[N+1][M+1];
        // 初始化
        for (int i = 0; i <= N; i++){
            dp[i][0] = 1; // 需要凑的金额为0时，只有一种凑法，就是每种面额的硬币都不选
        }
        // 填表
        for (int i = 1; i <= N; i++){
            for (int j = 1; j <= M; j++){
                // k代表选取当前面额coins[i-1]的数目，可以不选，选的话可以选多张，但加起来不能超过j
                for (int k = 0; k * coins[i-1] <= j; k++){
                    dp[i][j] += dp[i - 1][j - k * coins[i-1]];
                }
            }
        }
        return dp[N][M];
    }


    // 法二：优化，两层循环
    // 还是像0-1背包问题一样，对于当前遍历到的物品，只考虑选和不选两种情况，显然【不选】的时候，dp[i][j]=
    // dp[i-1][j],即前i-1种有多少种方法凑齐j，i就有几种，相当于法一种k=0的情况;
    // 【选】：只需要将状态转移方程改为dp[i][j] = dp[i][j-coins[i-1]]，第一个坐标是i不是i-1！巧妙！这
    // 就是和0-1背包不一样的地方。因为选coins[i−1]时，总的金额减少到j-coins[i-1],但由于是完全背包问题，
    // 每个物品可以选无限次，所以，剩下的j-coins[i-1]还是可以重新选择当前的物品，即coins[i-1]！
    // 所以综合这两种情况，状态转移方程应该为：p[i][j] = dp[i - 1][j] + dp[i][j-coins[i-1]];
    public static int change2(int amount, int[] coins) {
        if (amount == 0){
            return 1;  // 只要需要凑的金额为0，默认啥不选也是一种凑法！
        }
        if (amount < 0 || coins ==  null || coins.length == 0){
            return 0;
        }
        int N = coins.length;
        int[][] dp = new int[N+1][amount+1];
        for (int i = 0; i <= N; i++){
            dp[i][0] = 1;
        }
        for (int i = 1; i <= N; i++){
            for (int j = 1; j <= amount; j++){
                if (j < coins[i-1]){
                    dp[i][j] = dp[i-1][j];
                }else {
                    dp[i][j] = dp[i-1][j] + dp[i][j - coins[i-1]];
                }
            }
        }
        return dp[N][amount];
    }

    public static void main(String[] args){
        int amount = 5;
        int[] coins = {1,2,5};
        int nums = change2(amount, coins);
        System.out.println(nums);
    }
}
