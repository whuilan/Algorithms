package LeetCode.String;

/**
 * 最长回文子串（回文字符串：即是一个正读和反读都一样的字符串，如level, noon
 * 输入"babd"，则最长回文子串为“bab"）medium
 */
public class Solution5 {
    // 法一：暴力法，这种求字符串中满足某个条件的某个子串，明显可以用暴力法，枚举出所有子串，
    // (每个字符都可和它后面的1个/2个...k个字符(一直到字符串最后一个字符)组成一个子串),
    // 再一一检查每个子串是否符合条件！时间复杂度为O(n^3)，空间复杂度为O(1)。
    public String longestPalindrome1(String s) {
        if (s == null || s.length() < 2){
            return s;
        }
        char[] c = s.toCharArray();
        int begin = 0; // **初始化最长回文子串的起始索引
        int longestLen = 1; // 初始化最长回文子串的长度为1（至少为1）
        // 枚举所有长度大于1的子串 charArray[i..j]，i,j分别为子串的起始和结束索引
        for (int i = 0; i < c.length - 1; i++){
            for (int j = i + 1; j < c.length; j++){
                // 只需要对长度大于目前已知的最大回文子串长度的子串进行判断
                if ((j-i+1) > longestLen && isPalindrome(c, i, j)){
                    longestLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin+longestLen);
    }

    // 验证子串 s[left..right] 是否为回文串
    private boolean isPalindrome(char[] c, int start, int end){
        while (start < end){
            if (c[start] != c[end]){
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    // 法二：动态规划，不必每次都从头判断某个子串是不是回文串，一个字符串是不是回文串取决于
    // 它的首尾字符是否相等，以及去掉首尾字符之后的子字符串是否是回文串。假设dp[i][j]表示
    // s[i...j]范围的子串是否为回文串，则dp[i][j] = (s[i]==s[j]) && dp[i+1][j-1]，
    // 不必关心s[i+1...j-1]范围的子串如何判断是否为回文串，因为这就是状态呀！由状态转移方程求得
    // 此外要注意索引不能越界，显然转移到最后，最简单的情况就是i==j或i,j相邻，i,j之间没有字符了，
    // 或者i,j之间只有一个字符，此时dp[i][j]完全由s[i]和s[j]是否相等决定。时间复杂度O(n^2)
    // 空间复杂度O(n^2)
    public String longestPalindrome2_1(String s) {
        if (s == null || s.length() < 2){
            return s;
        }
        int N = s.length();
        char[] c = s.toCharArray();
        int maxLen = 1;
        int begin = 0;
        boolean[][] dp = new boolean[N][N];
        // 初始化
        // 对角线上表示只有一个字符的子串，肯定是回文串。
        for (int i = 0; i < N; i++){
            dp[i][i] = true;
        }
        for (int i = 0; i < N - 1; i++){
            dp[i][i+1] = c[i] == c[i+1];
            if (dp[i][i+1] == true && maxLen < 2){
                maxLen = 2;
                begin = i;
            }
        }
        // 用状态转移方程填表（以对角线的方式来填充）
        for (int k = 2; k < N; k++){
            for (int i = 0; i < N - k; i++){
                dp[i][i+k] = ((c[i] == c[i+k]) && dp[i+1][i+k-1]);
                if (dp[i][i+k] && k+1 > maxLen){
                    maxLen = k+1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin+maxLen);
    }

    // 2_1写得有点复杂了，比较容易出错，其实对于子串s[i+1...j-1]，因为要构成子串
    // 如果j-1-(i+1)+1 < 2，那么就不需要去判断子串了，得到j-i<3，即j-i+1<4，也就是
    // 当串的长度为1、2、3时其实就不需要判断子串了。为1即i=j，一定是回文子串，为2、3
    // 即i,j相邻或中间间隔一个数字，那么就只需要判断首尾字符是否相等。
    public String longestPalindrome2_2(String s) {
        if(s == null || s.length() < 2){
            return s;
        }
        int begin = 0;
        int maxLen = 1;
        int N = s.length();
        boolean[][] dp = new boolean[N][N];
        // 初始化，对角线上的元素表示串只包含一个字符，那么肯定是回文
        for (int i = 0; i < N; i++){
            dp[i][i] = true;
        }
        for (int j = 1; j < N; j++){
            for (int i = 0; i < j; i++){
                if (j - i < 3){
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                }
                else {
                    dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i+1][j-1];
                }
                if (dp[i][j] && (j-i+1 > maxLen)){
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin+maxLen);
    }

    // 法三：中心扩散法，其实就是我最开始想到的方法，找到回文子串的中心，再从中心往两边判断字符是否
    // 相等，直到不相等，就确定了一个回文子串。这种方法需要用O(n)的时间遍历字符串（因为每个字符都有
    // 可能是回文串的中心），对于遍历到的每个字符，又需要用O(n)的时间向两边扩散判断，因此时间复杂度
    // 为O(n^2)，但空间复杂度为O(1)，是三种方法里最优的啦！但是我没有处理好子串长度为奇数和偶数时的
    // 差异，执行时经常有用例不能通过，需要反复修改。
    public String longestPalindrome3(String s) {
        if (s == null || s.length() < 2){
            return s;
        }
        String res = s.charAt(0) + ""; // 将最长回文初始化为第一个字符
        for (int i = 0; i < s.length() - 1; i++){
            String oddPalindrome = extend(s, i, i); // 回文子串长度为奇数，i在正中间，如bab
            String evenPalindrome = extend(s, i, i+1); // 回文子串长度为偶数，如baab
            String maxPalindrome = oddPalindrome.length() > evenPalindrome.length() ?
                    oddPalindrome : evenPalindrome;
            if (maxPalindrome.length() > res.length()){
                res = maxPalindrome;
            }
        }
        return res;
    }

    // 以(left+right)/2处的字符为中心向两边扩散，返回最长回文子串
    private String extend(String s, int left, int right){
        while (left >=0 && right < s.length()){
            if (s.charAt(left) != s.charAt(right)) {
                break;
            }
            left--;
            right++;
        }
        // 终止循环时left和right两端的字符要么不相等要么超出索引，因此不应该包含在返回回文子串的范围中
        return s.substring(left+1, right);
    }

    public static void main(String[] args){
        String s = "aaa";
        Solution5 solution5 = new Solution5();
        String result = solution5.longestPalindrome3(s);
        System.out.println(result);
    }
}
