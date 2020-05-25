package LeetCode;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 翻转字符串里的单词：
 * 说明：无空格字符构成一个单词。
 * 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 */
public class Solution151 {
    // 法一：借助内置的各种api:拆分（split）、翻转（reverse）和连接（join）
    // 时间复杂度：O(N),其中 N 为输入字符串的长度。
    // 空间复杂度：O(N)，用来存储字符串分割之后的结果。
    public String reverseWords1(String s){
        if (s == null){
            return null;
        }
        // 去掉首尾空格
        s = s.trim();
        // 以（多个）空格为分隔符将单词分割开来
        List<String> list = Arrays.asList(s.split("\\s+"));
        Collections.reverse(list);
        return String.join(" ", list); // 若list.size() = 0，则返回空字符串""
    }

    // 不用java内置库，自行编写对应的函数，在翻转之前就去掉多余的空格
    public String reverseWords2_1(String s) {
        if (s == null){
            return null;
        }
        s = s.trim();
        char[] charArray = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        // 去掉连续出现的多个空格（保留第一个）
        for (int i = 0; i < charArray.length; i++){
            char c = charArray[i];
            if (i > 0 && c == ' ' && charArray[i - 1] ==  ' '){
                continue;
            }
            sb.append(c);
        }
        int N = sb.length();
        // 整体翻转
        reverseStringBuilder(sb, 0, N - 1);
        // 翻转每个单词
        int start = 0, end = 0;
        while (end <= N){
            if ( end == N || sb.charAt(end) == ' '){
                reverseStringBuilder(sb, start, end - 1);
                start = end + 1;
            }
            end++;
        }
        return sb.toString();
    }

    private void reverseStringBuilder(StringBuilder sb, int lo, int hi){
        while (lo < hi){
            char c = sb.charAt(lo);
            sb.setCharAt(lo, sb.charAt(hi));
            sb.setCharAt(hi, c);
            lo++;
            hi--;
        }
    }

    // 法二：翻转每个单词过程中再去掉空格
    public String reverseWords2_2(String s) {
        if (s == null){
            return null;
        }
        // 删掉字符串首尾空格
        s = s.trim();
        char[] chars = s.toCharArray();
        int N = chars.length;
        // 第一步：先翻转整个字符串（此时每个单词内字符的顺序也颠倒了）
        reverseCharArray(chars, 0,  N - 1);
        int left = 0, right = 0;
        StringBuilder sb = new StringBuilder();
        // 第二步：以空格（可能有连续的多个空格）为界，翻转每个单词，这样单词中的字符顺序又被翻转回来了
        while (right <= N){
            if (right == N || chars[right] == ' '){
                reverseCharArray(chars, left, right - 1);
                for (int i = left; i < right; i++){
                    sb.append(chars[i]);
                }
                if (right != N && left < right){
                    sb.append(" ");
                }
                left = right + 1;
            }
            right++;
        }
        return sb.toString();
    }

    private void reverseCharArray(char[] chars, int start, int end){
        while (start < end){
            char t = chars[start];
            chars[start] =  chars[end];
            chars[end] = t;
            start++;
            end--;
        }
    }

    public static void main(String[] args){
        String s = " a good   boy";
        // String str = "";
        Solution151  solution151 = new Solution151();
        String reverseStr = solution151.reverseWords2_2(s);
        System.out.println(reverseStr);
    }
}
