package sword_to_offer;

import java.util.ArrayList;

/**
 * P280题目一：和为s的两个数字（美团面试题！）
 * 输入一个递增排序的数组和一个数字S，在数组中查找两个数，
 * 使得他们的和正好是S，如果有多对数字的和等于S，输出两个数的乘积最小的。
 * 数组有序就可以想到双指针！时间复杂度为O(n)，空间复杂度为O(1)。
 * 另见leetCode1和1_2
 */
public class Problem57 {
    public static ArrayList<Integer> FindNumbersWithSum(int [] array, int sum) {
        ArrayList<Integer> list = new ArrayList<>();
        if (array != null && array.length >= 2){
            int i = 0, j = array.length - 1;
            while (i < j){
                int currentSum = array[i] + array[j];
                if (sum > currentSum){
                    i++;
                }
                else if(sum < currentSum){
                    j--;
                }
                else {
                    list.add(array[i]);
                    list.add(array[j]);
                    break;
                }
            }
        }
        return list;
    }

    public static void main(String[] args){
        int[] a = {1,2,4,7,11,15};
        ArrayList<Integer> list = FindNumbersWithSum(a, 15);
        for(int i : list){
            System.out.println(i);
        }
    }
}
