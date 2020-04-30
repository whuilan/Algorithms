package sword_to_offer;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * P284翻转字符串
 * 题目一：翻转单词顺序
 * 另见leetcode151
 */
public class Problem58 {
    // 法一（第一反应）：需要借助额外空间，面试时不宜采用
    public String ReverseSentence0(String str) {
        if(str == null){
            return null;
        }
        if(str.trim().equals("")){
            return str;
        }
        List<String> list = Arrays.asList(str.split(" "));
        Collections.reverse(list);
        return String.join(" ", list);
    }

    // 法二：不需要借助额外的空间（除了开始将输入转换为字符数组），
    // 两次翻转：第一步：翻转句子中的所有字符，第二步翻转每个单词中的字符
    // 普适方法，不管原字符串是什么（空字符串、一个空格、单词间隔多个空格），都能达到翻转单词顺序的目的
    public String ReverseSentence(String str) {
        if (str == null){
            return null;
        }
        char[] chars = str.toCharArray();
        int N = chars.length;
        // 第一步：先翻转整个字符串（此时每个单词内字符的顺序也颠倒了）
        reverseCharArray(chars, 0,  N - 1);
        int left = 0, right = 0;
        // 第二步：以空格为界，翻转每个单词，这样单词中的字符顺序又被翻转回来了
        while (right <= N){
            if (right == N || chars[right] == ' '){
                reverseCharArray(chars, left, right - 1);
                left = right + 1;
            }
            right++;
        }
        return String.valueOf(chars);
    }

    // 将字符数组的某个部分进行翻转/顺序颠倒
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
        String str = " ";
        // String str = "a good   boy";
        Problem58 problem58 = new Problem58();
        String reverseStr = problem58.ReverseSentence(str);
        System.out.println(reverseStr);
    }
}
