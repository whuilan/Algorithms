package LeetCode.String;

import java.util.HashMap;

/**
 * 最小覆盖子串：给你一个字符串 S、一个字符串 T 。请你设计一种算法，可以在 O(n) 的时间复杂度内，
 * 从字符串 S 里面找出：包含T所有字符的最小子串。
 */
public class Solution76 {
    // labuladong解法，比官解稍微直接、容易理解一点
    public String minWindow(String s, String t) {
        if(s == null || t == null || s.length() == 0 || t.length() == 0
                || s.length() < t.length()){
            return "";
        }
        int sLen = s.length(), tLen = t.length();

        HashMap<Character, Integer> winMap = new HashMap<>();
        HashMap<Character, Integer> tMap = new HashMap<>();
        for(int i = 0; i < tLen; i++){
            char c = t.charAt(i);
            tMap.put(c, tMap.getOrDefault(c, 0) + 1);
        }

        int minLen = sLen + 1;
        int begin = 0;
        int valid = 0;

        int left = 0, right = 0;
        while(right < sLen){
            char r = s.charAt(right);
            right++;
            // r是t中的字符，更新winMap及valid
            if(tMap.containsKey(r)){
                winMap.put(r, winMap.getOrDefault(r, 0) + 1);
                // 坑！Integer不能用==比较，要用compareTo
                if(winMap.get(r).compareTo(tMap.get(r)) == 0){
                    valid++;
                }
            }
            while(valid == tMap.size()){
                if(right - left < minLen){
                    minLen = right - left;
                    begin = left;
                }
                char l = s.charAt(left);
                left++;
                // r是t中的字符，更新winMap及valid
                if(tMap.containsKey(l)){
                    if(winMap.get(l).compareTo(tMap.get(l)) == 0){
                        valid--;
                    }
                    winMap.put(l, winMap.get(l) - 1);
                }
            }
        }
        return minLen == sLen + 1 ? "" : s.substring(begin, begin+minLen);
    }

    // 力扣官解，valid含义是字符的个数而不是种类数
    public String minWindow2(String s, String t) {
        int sLen = s.length(),tLen = t.length();
        if(sLen == 0 || tLen == 0 || tLen > sLen){
            return "";
        }
        // 将字符串转换为数组,方便取出字符（也可也不用）
        char[] sCharArray = s.toCharArray();
        char[] tCharArray = t.toCharArray();

        // 创建数组来统计每个字符出现的次数, 以字符为索引（其实是字符的ASCII码值）
        // 数组中的值即为对应字符出现的次数（也可以用HashMap）
        int[] windowFreq = new int[128];
        int[] tFreq = new int[128];
        for(char c : tCharArray){
            tFreq[c]++;
        }

        // 初始化变量
        int minLen = sLen + 1; // 初始化为一个不可能的值
        int begin = 0;
        int valid = 0; // t中的字符在s中出现的次数
        int left = 0, right = 0;
        // 开始滑动窗口
        while(right < sLen){
            char rightChar = sCharArray[right];
//            if(tFreq[rightChar] == 0){ // 这个字符不是t中的字符，直接跳过
//                right++;
//                continue;
//            }
            // 更新valid
            if(windowFreq[rightChar] < tFreq[rightChar]){
                valid++;
            }
            windowFreq[rightChar]++;
            right++;
            // 找到一个包含t中所有字符的窗口/子串，开始移动左指针来缩小窗口，寻找最小的子串
            while(valid == tLen){
                if(right - left < minLen){
                    minLen = right - left;
                    begin = left;
                }
                char leftChar = sCharArray[left];
//                if(tFreq[leftChar] == 0){ // 这个字符不是t中的字符，直接跳过
//                    left++;
//                    continue;
//                }
                // 如果此时刚好满足窗口包含所有字符，向左移动就一个就不满足了
                if(windowFreq[leftChar] == tFreq[leftChar]){
                    valid--;
                }
                windowFreq[leftChar]--;
                left++;
            }
        }
        return minLen == sLen + 1 ? "" : s.substring(begin, begin+minLen);
    }
}
