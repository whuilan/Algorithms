package LeetCode;

/**
 * 查找字符串数组中的最长公共前缀
 */
public class Solution14 {
    // 自己写的，棒棒的~时间复杂度为O(n * min(len))，即数组中最短的字符串的长度*数组字符串个数
    public static String longestCommonPrefix1(String[] strs) {
        if (strs == null || strs.length == 0){
            return "";
        }
        String firstStr = strs[0];
        for (int i = 0; i < firstStr.length();i++){
            char c = firstStr.charAt(i);
            for (int j = 1; j < strs.length; j++){
                if (strs[j].length() == i || strs[j].charAt(i) != c){
                    return firstStr.substring(0, i);
                }
            }
        }
        return firstStr;
    }

    // 参考官方解法一，时间复杂度为O(S),S为所有字符串的字符数目之和
    public static String longestCommonPrefix2(String[] strs) {
        if (strs == null || strs.length == 0){
            return "";
        }
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++){
            while (strs[i].indexOf(prefix) != 0){
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()){
                    return "";
                }
            }
        }
        return prefix;
    }

    public static void main(String[] args){
        String[] strs = {"dog","racecar","car"};
        String samePre = longestCommonPrefix2(strs);
        System.out.println(samePre);
    }
}
