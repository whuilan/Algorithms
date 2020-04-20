package sword_to_offer;

/**
 * 装箱问题的变种：有很多个盒子，v1， v2...vn，有两个房间，体积为V1+V2，所有盒子体积
 * 加起来比房间总面积大，现在让两个机器人来分别拿盒子填充房间，要使房间内的盒子数尽可能地多
 * 该怎么拿？盒子数最多为多少个？
 * 任然类似背包问题，对于每个盒子，有三种状态；不装，装到V1房间，装到V2房间。假设
 * dp[i][v1][v2]为前i个盒子在给定两个房间的体积分别为v1、v2时能装入的最多盒子数目。
 * 则状态转移方程为：
 * dp[i][v1][v2] = Math.max(dp[i-1][v1][v2], dp[i-1][v1-v[i]][v2]+1, dp[i-1][v1][v2-v[i]]+1)
 */
public class boxFilling_JHJ {
    public static int maxNum(int V1, int V2, int n, int[] v){
        int[][][] dp = new int[n+1][V1+1][V2+1];
        for (int i = 1; i <= n; i++){
            for (int v1 = 1; v1 <= V1; v1++){
                for (int v2 = 1; v2 <= V2; v2++){

                }
            }
        }
        return dp[n][V1][V2];
    }
}
