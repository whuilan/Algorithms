package sword_to_offer;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

/**
 * P288题目一：滑动窗口的最大值
 * 暴力解法：依次遍历数组，在每个大小为k的窗口查找最大值(时间复杂度O(k))，总时间复杂度为O(nk)
 * 双端队列解法：将有可能成为滑动窗口最大值的数的索引存入双端队列尾部，时间复杂度O(n)
 */
public class Problem59 {
    // 借助双端队列/两端开口队列
    public static ArrayList<Integer> maxInWindows(int [] num, int size)
    {
        ArrayList<Integer> list = new ArrayList<>();
        if (num != null && num.length != 0 && size > 0 && size <= num.length) {
            Deque<Integer> deque = new LinkedList<>();
            // 窗口滑动的过程就类似于队列的进入和删除元素的过程！
            for (int i = 0; i < num.length; i++){
                // 队列当前头部的元素索引已经不属于当前滑动窗口，应该删除头部
                if (!deque.isEmpty() && i - deque.peekFirst() >= size){
                    deque.pollFirst();
                }
                // 新加入的元素比队列当前尾部元素大时，尾部元素不可能时最大元素，因此删除队列尾部
                while (!deque.isEmpty() && num[i] >= num[deque.peekLast()]){
                    deque.pollLast();
                }
                deque.offerLast(i);
                if (i >= size - 1){
                    int maxIndex = deque.peekFirst();
                    list.add(num[maxIndex]);
                }
            }
        }
        return list;
    }

    public static void main(String[] args){
        int[] nums = {2,3,4,2,6,2,5,1};
        ArrayList<Integer> list = maxInWindows(nums, 3);
        for (int num : list){
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
