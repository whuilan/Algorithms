package LeetCode.Array;

import java.util.*;

/**
 * 携程春招笔试的第二道编程题：判断一个数组中是否有一个数等于另外两位数字之和
 */
public class Solution1_2 {
    /*请完成下面这个函数，实现题目要求的功能
    当然，你也可以不按照下面这个模板来作答，完全按照自己的想法来 ^-^
    ******************************开始写代码******************************/
    // 空间换时间，借助HashMap哈希表，时间复杂度为O(n^2)，空间复杂度为O(n)
    static boolean sumInteger(List<Integer> arrs){
        if (arrs != null && arrs.size() >= 3){
            int N = arrs.size();
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < N; i++){
                map.put(arrs.get(i), i);
            }
            for (int i = 0; i < arrs.size(); i++){
                int target = arrs.get(i);
                for (int j = 0; j < N; j++){
                    if (j == i){
                        continue;
                    }
                    int complement = target - arrs.get(j);
                    if (map.containsKey(complement) && map.get(complement) != i
                            && map.get(complement) != j){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // 考试时采用的暴力解法：三层循环，结果运行时间超时了没有通过
    static boolean sumInteger0( List<Integer> arrs) {
        for (int i = 0;i < arrs.size(); i++){
            int a = arrs.get(i);
            for (int j = 0; j < arrs.size(); j++){
                if (j == i){
                    continue;
                }
                int b = arrs.get(j);
                for (int k = 0; k < arrs.size(); k++){
                    if (k == i || k == j){
                        continue;
                    }
                    int c = arrs.get(k);
                    if (a == b + c){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    /******************************结束写代码******************************/


    public static void main(String[] args){
        boolean res;

        Scanner inputs = new Scanner(System.in);
        List<Integer> arrs = new LinkedList<>();
        while(inputs.hasNextInt())
        {
            arrs.add(inputs.nextInt());
        }

        res = sumInteger(arrs);
        System.out.println(String.valueOf(res ? 1 : 0));
    }
}
