package LeetCode.String;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 找到字符串中所有的异位词：给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，
 * 返回这些子串的起始索引。
 * 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
 */
public class Solution438 {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if(p == null || s == null || p.length() == 0
                || s.length() == 0 || p.length() > s.length()){
            return res;
        }

        int pLen = p.length(), sLen = s.length();
        HashMap<Character, Integer> pMap = new HashMap<>();
        HashMap<Character, Integer> winMap = new HashMap<>();
        for(int i = 0; i < pLen; i++){
            char c = p.charAt(i);
            pMap.put(c, pMap.getOrDefault(c, 0) + 1);
        }

        int valid = 0;
        int left = 0, right = 0;
        while(right < sLen){
            char r = s.charAt(right);
            right++;
            if(pMap.containsKey(r)){
                winMap.put(r, winMap.getOrDefault(r, 0) + 1);
                if(winMap.get(r).compareTo(pMap.get(r)) == 0){
                    valid++;
                }
            }
            if(right - left == pLen){
                if(valid == pMap.size()){
                    res.add(left);
                }
                char l = s.charAt(left);
                left++;
                if(pMap.containsKey(l)){
                    if(winMap.get(l).compareTo(pMap.get(l)) == 0){
                        valid--;
                    }
                    winMap.put(l, winMap.get(l) - 1);
                }
            }
        }
        return res;
    }
}
