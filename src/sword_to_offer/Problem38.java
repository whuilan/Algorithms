package sword_to_offer;

import java.util.ArrayList;
import java.util.Collections;

/**
 * P197字符串的排列
 * 时间复杂度都不可能低于 O(N!)，因为穷举整棵决策树是无法避免的。这也是回溯算法的一个特点，不
 * 像动态规划存在重叠子问题可以优化，回溯算法就是纯暴力穷举，复杂度一般都很高。
 */
public class Problem38 {
    public ArrayList<String> Permutation(String str) {
        ArrayList<String> list = new ArrayList<>();
        if (str != null && str.length() > 0){
            char[] chars = str.toCharArray();
            Permutation(chars, 0, list);
            Collections.sort(list);  // 题目要求按字典序打印
        }
        return list;
    }

    private void Permutation(char[] chars, int start, ArrayList<String> list){
        if (start == chars.length - 1){
            String str = String.valueOf(chars);
            list.add(str);
            return;
        }
        for (int i = start; i < chars.length; i++){
            // 避免和i后面的相同值交换
            if (i > start && chars[i] == chars[start]){
                continue;
            }
            swap(chars, start, i);
            Permutation(chars, start + 1, list);
            swap(chars, start, i); // 需要保证i处的字符确定再与下一个j+1的字符交换，因此每一次循环最后需要交换回来
        }
    }

    // 交换chars数组中位置为i、j处的值
    private void swap(char[] chars, int i, int j){
        char t = chars[i];
        chars[i] = chars[j];
        chars[j] = t;
    }

    public static void main(String[] args){
        String string = "abc";
        Problem38 problem38 = new Problem38();
        ArrayList<String> arrayList = problem38.Permutation(string);
        for(String s : arrayList){
            System.out.print(s + " ");
        }
        System.out.println();
    }
}
