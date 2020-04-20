package sword_to_offer;

/**
 * 装箱问题（一维）：有一个箱子容量为V（正整数，0＜＝V＜＝20000），同时有n个物品
 * （0＜n＜＝30），每个物品有一个体积（正整数）。要求n个物品中，任取若干个装入箱内，
 * 使箱子的剩余空间为最小。
 * 与背包问题类似！相当于背包问题中的价值就等于其重量，求剩余空间最小，其实就相当于
 * 求放到箱子中的物品的最大填充体积。因此动态规划的公式也和背包问题相同，设dp[i][j]
 * 表示前i个物品任取若干个装到体积为j的箱子中时的最大填充体积，则：
 * dp[i][j]=dp[i-1][j]                               (j<w[i])
 * dp[i][j]=max{dp[i-1][j],dp[i-1][j-w[i]]+v[i]}     (j≥w[i])
 */
public class boxFilling {
    public static int minRest(int V, int n, int[] v){
        int[][] dp = new int[n+1][V+1];
        for (int i = 1; i <= n; i++){
            for (int j = 1; j <= V; j++){
                if (v[i] > j){
                    dp[i][j] = dp[i-1][j];
                }else {
                    int noPut = dp[i-1][j];
                    int put = dp[i-1][j-v[i]]+v[i];
                    dp[i][j] = Math.max(noPut, put);
                }
            }
        }
        return V - dp[n][V];
    }

    public static void main(String[] args){
        int V = 24, n = 6;
        int[] v = {0,8,3,12,7,9,7};
        int least = minRest(V, n, v);
        System.out.println(least);
    }
}
