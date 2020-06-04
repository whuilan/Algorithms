package sword_to_offer;

import java.util.HashMap;
import java.util.Map;

/**
 * P236最长不含重复字符的子字符串（即leetcode第3题无重复字符的最长子串）
 * 解题思路：滑动窗口（窗口通常是在数组/字符串中由开始和结束索引定义的一系列元素的集合）
 * 注意当当前字符在map中出现过时，不要盲目更新起始索引，因为它还有可能并不在滑动窗口所
 * 表示的字符串内，即上一次出现的索引小于窗口起始索引i，此时无影响，不应该更新
 * 时间复杂度为O(n)
 */
public class Problem48 {

    public static int lengthOfLongestSubstring(String s) {
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

    public static void main(String[] args){
        String s = "pww";
        int maxLen = lengthOfLongestSubstring(s);
        System.out.println(maxLen);
    }
}
