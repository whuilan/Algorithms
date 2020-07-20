package LeetCode.Dynamic_Planning;

/**
 * 最长公共子序列：给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列(LCS)的长度
 * 分析：首先要注意“子序列”是可以不连续的。这个题的难点是有两个字符串
 *      刚开始做的时候自作聪明，以为子序列问题一定就是像300最长上升子序列和53连续子数组的最大和一样，状态要设置
 *      为以num[i]为结尾的子序列的最长...其实是没有必要的，自己举例分析的过程中也发现了好像不是一定要知道上一个
 *      公共子序列最后一个字符的具体位置。而是只需要知道上一个公共子序列的长度是多少就行了，如果当前两个字符是相等
 *      的，直接在上一个公共子序列的长度值上加一就可以。不像300题那样要求元素递增，或者53那样要求相加的子元素连续
 *      就是常规的动态规划解法！，“动态”体现在遍历字符串进行比较，因此直接将【状态】设置为遍历到某个位置时的最长公共
 *      子序列的长度就行，难一点的是要对两个字符串遍历，所以要用二维数组来分别记录两个字符串的索引！具体为：
 *      dp[i][j]表示text1[0..i-1]和text2[0..j-1]的最长公共子序列的长度，如果当前的两个字符text1.charAt(i-1)
 *      和text2.charAt(j-1)相等，就意味着在text1[0..i-2]和text2[0..j-2]构成的最长公共子序列的尾部又加了一个
 *      公共字符，即dp[i][j]=dp[i-1][j-1]+1；如果当前的两个字符不想等，说明至少有一个不在最终的LCS里面，应该
 *      丢弃一个，至于丢弃哪个留哪个，就根据谁能使dp[i][j]最大呗！即dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
 * 反思：有两个字符串的动态规划，或者需要从两头开始比（如回文），一般都是这样用二维数组来记录状态，因为这样方便得出
 *      状态转移方程。此外，也不要过于依赖什么套路框架，尤其是我现在还不熟，先自己想一下，然后没解出来的话就多试一下！
 *      根据我已经知道的动态规划的套路/状态设置方法，这个不行就换一个状态设置方法，差不多就能试出来了！
 */
public class Solution1143 {
    public static int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0){
            return 0;
        }
        int M = text1.length(), N = text2.length();
        // dp[i][j]表示text1的[0..i-1]部分和text2的[0..j-1]部分所能找到的最长公共子序列的长度
        int[][] dp = new int[M+1][N+1]; // 注意新增了一行/列
        for (int i = 1; i <= M; i++){
            for (int j = 1; j <= N; j++){
                if (text1.charAt(i-1) == text2.charAt(j-1)){ // 第i个字符的索引是i-1，但不影响状态转移方程的写法
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[M][N];
    }

    public static void main(String[] args){
        String s1 = "cea" ,s2 = "abcde";
        int maxLen = longestCommonSubsequence(s1, s2);
        System.out.println(maxLen);
    }
}
