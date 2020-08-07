package LeetCode.String;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 在字符串中查找另一个字符串的排列
 */
public class Solution567 {
    public static boolean checkInclusion(String s1, String s2) {
        if(s1 == null || s2 == null || s1.length() == 0
                || s2.length() == 0 || s1.length() > s2.length()){
            return false;
        }

        int s1Len = s1.length(), s2Len = s2.length();
        HashMap<Character, Integer> s1Map = new HashMap<>();
        HashMap<Character, Integer> winMap = new HashMap<>();
        for(int i = 0; i < s1Len; i++){
            char c = s1.charAt(i);
            s1Map.put(c, s1Map.getOrDefault(c, 0) + 1);
        }

        int valid = 0;
        int left = 0, right = 0;
        while(right < s2Len){
            char r = s2.charAt(right);
            right++;
            if(s1Map.containsKey(r)){
                winMap.put(r, winMap.getOrDefault(r, 0) + 1);
                if(winMap.get(r).compareTo(s1Map.get(r)) == 0){
                    valid++;
                }
            }
            if(right - left == s1Len){
                if(valid == s1Map.size()){
                    return true;
                }
                char l = s2.charAt(left);
                left++;
                if(s1Map.containsKey(l)){
                    if(winMap.get(l).compareTo(s1Map.get(l)) == 0){
                        valid--;
                    }
                    winMap.put(l, winMap.get(l) - 1);
                }
            }
        }
        return false;
    }

    // 一开始自己的做法，有点混乱
    public boolean checkInclusion2(String s1, String s2) {
        int len1 = s1.length(), len2 = s2.length();
        if (len1 == 0 || len2 == 0 || len1 > len2){
            return false;
        }

        char[] s1CharArray = s1.toCharArray();
        char[] s2CharArray = s2.toCharArray();

        int[] windowFreq = new int[128];
        int[] s1Freq = new int[128];
        for(char c : s1CharArray){
            s1Freq[c]++;
        }

        int left = 0, right = 0;
        while (right < len2){
            char rightChar = s2CharArray[right];
            // 当前字符不是s2中的字符，直接跳过
            if (s1Freq[rightChar] == 0){
                right++;
                left = right;
                Arrays.fill(windowFreq, 0);
                continue;
            }
            // 当前字符是s2中的字符，但是这个字符的出现次数已经够了
            if (windowFreq[rightChar] == s1Freq[rightChar]){
                left = right;
                right++;
                Arrays.fill(windowFreq, 0);
                windowFreq[rightChar] = 1;
                continue;
            }
            right++;
            if (right - left == len1){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args){
        String s1 = "ab",s2 = "edboaoo";
        boolean b = checkInclusion(s1, s2);
        System.out.println(b);
    }
}
