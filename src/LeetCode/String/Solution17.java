package LeetCode.String;

import java.util.ArrayList;
import java.util.List;

/**
 * 电话号码的字母组合：给定一个仅包含数字2-9的字符串，返回所有它能表示的字母组合。
 * 注意审题呀！人家没说相同数字对应的字母不能重复呀！你自己加那么多戏干啥？？
 * 另外2-9对应的字符不用想的那么麻烦，用什么循环呀，数字和字符转换呀什么的来求，
 * 直接暴力法手动添加就可以了。就是个全排列的题！和第46以及剑指第38题一毛一样
 * 其实就是深度优先搜索dfs的暴搜，时间和空间复杂度均为：3^n+4^m，其中n为输入字符
 * 串中2，3...等的位数，m为7和9的位数
 */
public class Solution17 {
    private List<String> res;
    private String[] strs = {"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};

    public List<String> letterCombinations(String digits) {
        res = new ArrayList<>();
        if (digits == null || digits.length() == 0){
            return res;
        }
        StringBuilder sb = new StringBuilder();
        permute(digits, 0, sb);
        return res;
    }

    private void permute(String digits, int digit, StringBuilder sb){
        if (digit == digits.length()){
            res.add(sb.toString());
            return;
        }
        int index = digits.charAt(digit) - '0';
        String chars = strs[index];
        for (int i = 0; i < chars.length(); i++){
            sb.append(chars.charAt(i));
            permute(digits, digit + 1, sb);
            sb.deleteCharAt(sb.length() - 1); // 重要！不要漏！
        }
    }

    public static void main(String[] args){
        Solution17 solution17 = new Solution17();
        List<String> combines = solution17.letterCombinations("23");
        for(String s : combines){
            System.out.print(s + " ");
        }
        System.out.println();
    }
}
