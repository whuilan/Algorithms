package LeetCode.StackAndQueue;

import java.util.Stack;

/**
 * 字符串解码：给定一个经过编码的字符串，返回它解码后的字符串。
 * 难点：中括号中还嵌套了中括号时的解码
 */
public class Solution294 {
    public static String decodeString(String s) {
        StringBuilder res = new StringBuilder();
        Stack<Integer> repTimes = new Stack<>();
        Stack<String> pre = new Stack<>();
        int curReTimes = 0;
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            // c为数字则直接更新当前重复次数
            if (c >= '0' && c <= '9'){
                // 重复数字不一定是个位数，有可能是几十、几百！
                curReTimes = curReTimes * 10 + (c - '0');
            }
            // c为左括号时将当前的curReTimes和res压入相应的栈中，并将它们清空、置零，它们都在当前'['之前
            // 出现，则说明curReTimes即代表当前[]内字符串的重复次数，res为上一个[到当前[之间的字符串
            else if(c == '['){
                repTimes.push(curReTimes);
                pre.push(res.toString());
                // 清空
                curReTimes = 0;
                res = new StringBuilder();

            }
            else if(c == ']'){
                // 取出当前[]重复次数
                int times = repTimes.pop();
                StringBuilder temp = new StringBuilder();
                while (times > 0){
                    temp.append(res);
                    times--;
                }
                res = new StringBuilder(pre.pop()).append(temp);
            }
            // c为字母则直接添加到res末尾
            else {
                res.append(c);
            }
        }
        return res.toString();
    }

    public static void main(String[] args){
        String encodedStr = "3[a]2[bc]";
        String rawStr = decodeString(encodedStr);
        System.out.println(rawStr);
    }
}
