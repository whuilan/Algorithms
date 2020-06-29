package LeetCode.Array;

import java.util.PriorityQueue;
import java.util.Random;

/**
 *  数组中的第K个最大元素
 *  在未排序的数组中找到第 k 个最大的元素。请注意，
 *  你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *  核心：其实就是返回从小到大排序后倒数第k个数，顺数的索引为n-k（不是n-k+1!）
 *  法一：用【快速选择】算法，时间复复杂度为O(n)，复习剑指39-41
 *  法二：最小堆，维护一个含有k个元素的最小堆，题目问的就是堆顶元素，时间复杂度O(nlogk)
 */
public class Solution215 {
    private Random random = new Random();

    public int findKthLargest(int[] nums, int k) {
        int N = nums.length;
        int lo = 0, hi = N - 1;
        while (lo < hi){  // 注意这里不能取等号！不然如果最后是lo=hi=length-1，带入到partition会超出数组索引
            int j = partition(nums, lo, hi);
            if (j < N - k){
                lo = j + 1;
            }
            else if (j > N - k){
                hi = j - 1;
            }else {
                return nums[j]; // 刚好等于k-1就直接返回
            }
        }
        return nums[lo]; // 没有找到j=k-1，但是这个元素一定是存在的，所以就是最后一次的lo=hi
    }

    // 大于nums[lo]的放左边，小于nums[lo]的放右边
    private int partition(int[] nums, int lo, int hi){
        int i = lo, j = hi + 1;
        // 注意这里应该打乱数组，在区间中随便选择一个点作为比较元素v，避免最坏情况
        int randomIdx = random.nextInt(hi-lo+1) + lo;
        exch(nums, lo, randomIdx);
        int v = nums[lo];
        while (true){
            while (nums[++i] < v){
                if (i == hi){
                    break;
                }
            }
            while (v < nums[--j]){}
            if (i >= j){
                break;
            }
            exch(nums, i, j);
        }
        exch(nums, lo, j);
        return j;
    }

    private void exch(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // 法二：最小堆
    public int findKthLargest2(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(k+1); // 初始化为k+1巧妙!其实也可以不用初始化...
        for (int num : nums){
            pq.offer(num);
            if (pq.size() > k){
                pq.poll();
            }
        }
        return pq.peek();
    }

    public static void main(String[] args){
        int[] nums = {1,2};
        Solution215 solution215 = new Solution215();
        int KthNum = solution215.findKthLargest(nums, 1);
        System.out.println(KthNum);
    }
}
