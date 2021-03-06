package sword_to_offer;

import java.util.ArrayList;

/**
 * 题目二：和为s的连续正数序列
 * 这个题的双指针类似滑动窗口，左右边界指针都在向前滑动。而判断排序数组中的两数之和，
 * 以及和为零的三个数组用的双指针是往中间靠拢的。
 *
 */
public class Problem57_2 {
    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
        if (sum > 2){
            int lo = 1, hi = 2;
            int middle = (sum + 1) / 2;
            ArrayList<Integer> list = new ArrayList<>();
            list.add(lo);
            list.add(hi);
            int curSum = lo + hi;
            while (lo < middle){
                // int currentSum = getSumOfSeq(list);
                if (curSum < sum){
                    hi++;
                    list.add(hi);
                    curSum += hi;
                }
                else if (curSum > sum){
                    list.remove(0);
                    curSum -= lo;
                    lo++;
                }
                else {
                    lists.add(new ArrayList<>(list));
                    hi++;
                    list.add(hi);
                    curSum += hi;
                }
            }

        }
        return lists;
    }

    public static void main(String[] args){
        Problem57_2 problem57_2 = new Problem57_2();
        ArrayList<ArrayList<Integer>> lists = problem57_2.FindContinuousSequence(9);
        System.out.println("success");
    }
}
