package sword_to_offer;

/**
 * 0-1背包问题动态规划：
 * n件物品的重量不同，带来的价值不同，在给定最大重量W下，找出能带来最大价值的组合
 * 状态转移方程：
 * dp[i][j]=dp[i-1][j]                               (j<w[i])
 * dp[i][j]=max{dp[i-1][j],dp[i-1][j-w[i]]+v[i]}     (j≥w[i])
 */
public class Knapsack {
    public static int maxValue(int W, int n, int[] w, int[] v) {
        // dp[i][j]前i件物品中选择若干件放在承重为j的背包中，可以取得的最大价值
        // 将子问题的解存储在二维数组中
        int[][] dp = new int[n + 1][W + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= W; j++) {
                if (w[i] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    int noPut = dp[i - 1][j];
                    int put = dp[i - 1][j - w[i]] + v[i];
                    dp[i][j] = Math.max(noPut, put);
                }
            }
        }
            return dp[n][W];
    }

    public static void main(String[] args){
        int W = 10, n = 5;
        int[] w = {0,2,2,6,5,4};
        int[] v = {0,6,3,5,4,6};
        int maxV = maxValue(W, n, w, v);
        System.out.println(maxV);
    }
}
