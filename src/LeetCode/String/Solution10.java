package LeetCode.String;

/**
 * 正则表达式匹配hard（剑指上的原题！还想了老半天，思考的过程中慢慢回忆起来了但是写得有很多bug！）
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * 易错点：1 时刻注意索引范围判断，不能超出范围
 *        2 当p的下一个为*时也是要分匹配和不匹配两种情况讨论的！如果不匹配只能是跳过
 *        p的当前字符，即i, j+2比较，不能是匹配时的三种情况
 */
public class Solution10 {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null){
            return false;
        }
        return matchCore(s, p, 0, 0);
    }

    private boolean matchCore(String s, String p, int i, int j){
        // s,p都走完了，即字符串的所有字符匹配整个模式，说明匹配成功
        if (i == s.length() && j == p.length()){
            return true;
        }
        // s还有字符没匹配，模式p已经走完了
        if (i < s.length() && j == p.length()){
            return false;
        }
        // 模式当前字符的下一个字符为'*'
        if (j + 1 < p.length() && p.charAt(j + 1) == '*'){
            // 当前字符匹配
            if (i != s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.')){
                return matchCore(s, p, i, j+2) || // 重复0次
                        matchCore(s, p, i+1, j+2) || // 重复1次
                        matchCore(s, p, i+1, j); // 重复1次以上
            }else {
                return matchCore(s, p, i, j+2);
            }
        }
        if (i != s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.')){
            return matchCore(s, p, i+1, j+1);
        }
        return false;
    }
}
