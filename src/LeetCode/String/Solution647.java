package LeetCode.String;

/**
 * 回文子串的个数：
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 * 受第5题，找出最长回文子串的启发，可以用中心扩散法从头到尾遍历字符串
 */
public class Solution647 {
    private int count;

    public int countSubstrings(String s) {
        if (s == null || s.length() == 0){
            return 0;
        }
        for (int i = 0; i < s.length(); i++){
            palindrome(s, i, i); // 回文子串长度为奇数，i为正中心的元素索引
            palindrome(s, i, i+1); // 回文子串的长度为偶数，i和i+1是中间的两个元素
        }
        return count;
    }

    // 找出s中所有以（left+right)/2为中心的回文子串
    private void palindrome(String s, int left, int right){
        while (left >= 0 && right < s.length()){
            if (s.charAt(left) != s.charAt(right)){
                return;
            }
            count++;
            left--;
            right++;
        }
    }

    public static void main(String[] args){
        Solution647 solution647 = new Solution647();
        int num = solution647.countSubstrings("aaa");
        System.out.println(num);
    }
}
