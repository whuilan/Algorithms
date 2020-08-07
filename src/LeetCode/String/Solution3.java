package LeetCode.String;

import java.util.HashMap;

/**
 * 无重复的最小子串（同剑指48）
 */
public class Solution3 {
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
