package LeetCode.Dynamic_Planning;

/**
 * 编辑距离：给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数。
 * 可以进行的操作为：插入/删除/替换一个字符
 * 分析：受到1143【最长公共子序列】的启发，涉及到两个字符串之间的比较并且求最值肯定要用动态规划了，
 * 而且因为是两个字符串，比较的过程中，两个字符串当前遍历到的索引都是【变量】，因此备忘录dp要设置
 * 成二维数组：dp[i][j]表示word1[1..i]转换成word2[1..j]所需要的最少操作次数。
 * 这个题的难点在于怎么列状态转移方程。很好想到的是先判断word1[i]和word2[j]是不是相等的，如果是相等的，
 * 那么当前遍历到的两个字符就不需要转换，dp[i][j]取决于word1[1..i-1]转换成word2[1..j-1]所需要的次数，
 * 即dp[i][j] = dp[i-1][j-1]。如果word1[i]和word2[j]不相等呢？刚开始直觉就是那就把word1[i]替换
 * 成word2[j]，那么总体dp[i][j]=dp[i-1][j-1]+1(当前两个字符的一次替换)，带入到例子中发现不对。
 * 仔细分析了一下例子，并且列出了dp，发现错在哪里了!上面只包含替换一种情况，但题目给了三种可行操作。
 * 还应该包括插入和删除，举例分析，插入：ho-ros，如果将o替换成s，再将h转成ro，就需要三次操作。但其实
 * 不需要的！明显可以将h替换成r，此时就匹配了ros的ro了，再在后面插入一个s就行了！只需要2次操作，所以
 * dp[i][j]=dp[i][j-1]+1(word1前i个字符匹配到word2前j-1个字符，然后再在word1尾部插入word2[j]就行了)
 * 同理，分析hor-ro可知，此时应该是dp[i][j]=d[i-1][j]+1，也就是当word1[i]和word2[j]不相等的时候，
 * dp[i][j]应该是取替换/插入/删除三者中的最小值！其实和1143题非常非常类似
 */
public class Solution72 {
    public static int minDistance(String word1, String word2) {
        if (word1 == null || word2 == null){
            return -1;
        }
        int M = word1.length(), N = word2.length();
        // dp[i][j]表示将word1[1..i]转成成word[1..j]所使用的最少操作次数
        int[][] dp = new int[1+M][1+N];
        // 初始化，也很重要，要在草稿纸上分析！
        for (int i = 0; i <= M; i++){
            dp[i][0] = i;
        }
        for (int j = 0; j <= N; j++){
            dp[0][j] = j;
        }
        for (int i = 1; i <= M; i++){
            for (int j = 1; j <= N; j++){
                if (word1.charAt(i-1) == word2.charAt(j-1)){ // 注意这里索引要减1，容易出错！
                    dp[i][j] = dp[i-1][j-1];
                }else {
                    dp[i][j] = 1 + Math.min(dp[i-1][j-1], Math.min(dp[i][j-1], dp[i-1][j]));
                }
            }
        }
        return dp[M][N];
    }

    public static void main(String[] args){
        String word1 = "horse", word2 = "ros";
        int n = minDistance(word1, word2);
        System.out.println(n);
    }
}
