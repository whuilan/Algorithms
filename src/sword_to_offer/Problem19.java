package sword_to_offer;

import edu.princeton.cs.algs4.StdOut;

/**
 * P124 正则表达式匹配，核心思想：递归，类似18_2，不停地代入第一个字符进行递归调用
 */
public class Problem19 {
    public boolean match(char[] str, char[] pattern)
    {
        if(str == null || pattern == null){
            return false;
        }

        return matchCore(str, pattern, 0, 0);
    }

    private boolean matchCore(char[] str, char[] pattern, int strIndex, int patIndex){
        // 字符串指针和模式指针都到达末尾(最后一个字符也比较过了)，说明匹配成功
        if (strIndex == str.length && patIndex == pattern.length){
            return true;
        }
        // 模式指针已经到达末尾，而字符串却还有没有被比较的字符，说明匹配失败
        // 注意一种特殊情况，即str={}, pattern={'.','*},此时是strIndex = str.length而patIndex != pattern.length
        if (strIndex != str.length && patIndex == pattern.length){
            return false;
        }
        // 模式中第一个字符的后一个字符（即第二个字符）为'*'
        if (patIndex + 1 < pattern.length && pattern[patIndex + 1] == '*'){
            // 当前字符匹配，可以与3种匹配方式
            if (strIndex != str.length && (str[strIndex] == pattern[patIndex] || pattern[patIndex] == '.')){
                return matchCore(str, pattern, strIndex, patIndex + 2)  // *前的字符出现0次
                        || matchCore(str, pattern, strIndex + 1, patIndex + 2)  // 1次
                        || matchCore(str, pattern, strIndex + 1, patIndex); // 出现很多次
            }else {   // 当前字符不匹配，则忽略*前的字符
                return matchCore(str, pattern, strIndex, patIndex + 2);
            }

        }
        // 模式字符串的第二个字符不是'*',如果当前字符串中的字符和模式中的字符匹配，
        // 则进行下一个字符的比较，不匹配则直接返回false
        if (strIndex != str.length && (str[strIndex] == pattern[patIndex] || pattern[patIndex] == '.')){
            return matchCore(str, pattern, strIndex + 1, patIndex + 1);
        }else {
            return false;
        }
    }

    // 法二，cyc解法，没看懂
    public static boolean match_v2(char[] str, char[] pattern)
    {
        int m = str.length, n = pattern.length;
        boolean[][] dp = new boolean[m + 1][n + 1];

        dp[0][0] = true;
        for (int i = 1; i <= n; i++)
            if (pattern[i - 1] == '*')
                dp[0][i] = dp[0][i - 2];

        for (int i = 1; i <= m; i++)
            for (int j = 1; j <= n; j++)
                if (str[i - 1] == pattern[j - 1] || pattern[j - 1] == '.')
                    dp[i][j] = dp[i - 1][j - 1];
                else if (pattern[j - 1] == '*')
                    if (pattern[j - 2] == str[i - 1] || pattern[j - 2] == '.') {
                        dp[i][j] |= dp[i][j - 1]; // a* counts as single a
                        dp[i][j] |= dp[i - 1][j]; // a* counts as multiple a
                        dp[i][j] |= dp[i][j - 2]; // a* counts as empty
                    } else
                        dp[i][j] = dp[i][j - 2];   // a* only counts as empty

        return dp[m][n];
    }

    public static void main(String[] args){
        char[] str = {};
        char[] pattern = {'.', '*'};
        char[] pattern2 = {'a', '.', 'a'};
        Problem19 problem19 = new Problem19();
        boolean isMatch = problem19.match(str, pattern);
        if(isMatch){
            StdOut.println("match");
        }else {
            StdOut.println("not match");
        }
    }
}
