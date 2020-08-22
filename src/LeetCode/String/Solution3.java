package LeetCode.String;

import java.util.HashMap;

/**
 * 无重复的最小子串（同剑指48）
 */
public class Solution3 {
    // 写法一，刷剑指时候的写法
    public static int lengthOfLongestSubstring1(String s) {
        if (s == null || s.length() == 0){
            return 0;
        }
        // 用map来保存已经遍历过的字符及其索引位置
        HashMap<Character, Integer> map = new HashMap<>();
        // 设置一个变量来保存最大长度，初始化为0，随着遍历过程而改变
        int maxLen = 0;
        // i,j分表表示滑动窗口的左边界和右边界指针
        for (int i = 0, j = 0; j < s.length(); j++){
            char c = s.charAt(j);
            if (map.containsKey(c) && map.get(c) >= i){
                i = map.get(c) + 1;
            }
            map.put(c, j);
            maxLen = Math.max(maxLen, j - i + 1);
        }
        return maxLen;
    }

    public int lengthOfLongestSubstring(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }
        // 用来记录当前窗口范围内的字符及其索引
        HashMap<Character, Integer> map = new HashMap<>();
        int maxLen = 0;
        int left = 0, right = 0;
        while(right < s.length()){
            char c = s.charAt(right);
            // 字符已经存在于当前窗口中，缩小窗口来去重
            if(map.containsKey(c) && map.get(c) >= left){
                left = map.get(c)+1;
            }
            // 将c添加到窗口中，map中总是放入最新的索引
            map.put(c, right);
            right++;
            if(right - left > maxLen){
                maxLen = right - left;
            }
        }
        return maxLen;
    }
}
