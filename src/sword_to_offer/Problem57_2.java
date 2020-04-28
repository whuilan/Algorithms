package sword_to_offer;

import java.util.ArrayList;

/**
 * 题目二：和为s的连续正数序列
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
            while (lo < middle){
                int currentSum = getSumOfSeq(list);
                if (sum > currentSum){
                    hi++;
                    list.add(hi);
                }
                else if (sum < currentSum){
                    list.remove(0);
                    lo++;
                }
                else {
                    lists.add(new ArrayList<>(list));
                    hi++;
                    list.add(hi);
                }
            }

        }
        return lists;
    }

    private int getSumOfSeq(ArrayList<Integer> list){
        int sum = 0;
        for (int num : list){
            sum += num;
        }
        return sum;
    }

    public static void main(String[] args){
        Problem57_2 problem57_2 = new Problem57_2();
        ArrayList<ArrayList<Integer>> lists = problem57_2.FindContinuousSequence(9);
        System.out.println("success");
    }
}
