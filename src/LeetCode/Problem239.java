package LeetCode;

import java.util.Deque;
import java.util.LinkedList;

public class Problem239 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0 || k > nums.length){
            return null;
        }
        int[] maxValues = new int[nums.length-k+1];
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < nums.length; i++){
            // 队列当前头部的元素索引已经不属于当前滑动窗口，应该删除头部
            if (!deque.isEmpty() && i - deque.peekFirst() >= k){
                deque.pollFirst();
            }
            // 新加入的元素比队列当前尾部元素大时，尾部元素不可能时最大元素，因此删除队列尾部
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]){
                deque.pollLast();
            }
            deque.offer(i);
            if (i >= k - 1){
                int maxIndex = deque.peekFirst();
                maxValues[i-k+1] = nums[maxIndex];
            }
        }
        return maxValues;
    }
}
