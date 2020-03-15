package sword_to_offer;

/**
 * P233礼物的最大价值，核心：使用二维数组存储子问题的最优解
 */
public class Problem47 {
    // 法一：普通动态规划
    public static int getMost1(int[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0){
            return 0;
        }
        int r = board.length, c = board[0].length;
        // dp[i][j]表示到达坐标为(i,j)的格子时，能拿到的礼物价值总和的最大值
        int[][] dp = new int[r][c];
        for (int i = 0; i < r; i++){
            for (int j = 0; j < c; j++){
                int up = 0, left = 0;
                if (i > 0){ // 只要i>0就有上一个
                    up = dp[i - 1][j];
                }
                if (j > 0){
                    left = dp[i][j - 1];  //
                }
                dp[i][j] = Math.max(up, left) + board[i][j];
            }
        }
        return dp[r - 1][c - 1];
    }

    // 法二：动态规划的优化
    public static int getMost(int[][] board){
        if (board == null || board.length == 0 || board[0].length == 0){
            return 0;
        }
        int r = board.length, c = board[0].length;
        // 没有必要保存第i-2行及以上的所有格子的价值最大值。用一维数组即可，该数组前面j（0~j-1）个数字
        // 分别时当前第i行前面j个格子的价值最大值，从索引j开始则保存的是i-1行c-j个格子的价值最大值，
        // debug一下就知道啦~
        int[] optimizedDP = new int[c];
        for (int i = 0; i < r; i++){
            for (int j = 0; j < c; j++){
                int up = 0, left = 0;
                if (i > 0){
                    up = optimizedDP[j];
                }
                if (j > 0){
                    left = optimizedDP[j - 1];
                }
                optimizedDP[j] = Math.max(up, left) + board[i][j];
            }
        }
        return optimizedDP[c - 1];
    }

    public static void main(String[] args){
        int[][] gifts = {{1, 10, 3, 8}, {12, 2, 9, 6},{5, 7, 4, 11},{3, 7, 16, 4}};
        int maxValue = getMost(gifts);
        System.out.println(maxValue);
    }
}
