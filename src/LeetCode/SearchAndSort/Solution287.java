package LeetCode.SearchAndSort;

import java.util.Arrays;

/**
 * 寻找重复数：给定一个包含n+1个整数的数组 nums，其数字都在1到n之间（包括1和 n），
 * 可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
 * 说明：1.不能更改原数组（假设数组是只读的）。
 *      2.只能使用额外的 O(1) 的空间。
 *      3.时间复杂度小于 O(n^2) 。
 *      4.数组中只有一个重复的数字，但它可能不止重复出现一次。
 * 分析：就是剑指面试题3_2，注意复习。
 *      要求不能更改原数组喔，所以不能使用排序，不能有额外空间，所以也不能用哈希表，
 *      核心：用二分查找找到某个范围内整数的个数；分析可见剑指
 *      时间复杂度为O(NlogN)，空间复杂度为O(1)
 * 备注：本题的场景和限制是极其特殊的，实际工作中和绝大多数算法问题都不会用「时间换空间」。
 *      这题二分和快慢指针都不是常规思路。面试的时候最好提一嘴，因为有各种限制，才用二分这种耗时的做法
 *      此外这种方法不能保证找出所有重复的元素（不过题目说了只有一个元素重复）
 */
public class Solution287 {
    // 法一（第一反应）：快排然后遍历（错啦，快排会修改原数组！违反了限制1，但是可以瞅瞅这种思路具体的代码）
    public int findDuplicate0(int[] nums) {
        if(nums == null || nums.length < 2){
            return -1;
        }
        Arrays.sort(nums);
        for(int i = 1; i < nums.length; i++){
            if(nums[i] == nums[i - 1]){
                return nums[i];
            }
        }
        return -1; // 输入不符合要求，不存在重复的元素
    }

    // 法二：二分查找某个范围内整数的个数，数组分为[min...mid]和[mid+1...max]两部分
    public int findDuplicate(int[] nums) {
        if(nums == null || nums.length < 2){
            return -1;
        }
        int min = 1, max = nums.length - 1; // 易错！数组元素的范围，不是索引！起始值不要写成0了！
        while (min < max){
            int mid = min + (max - min) / 2; // min,max,mid单词类似，写的时候要看清楚
            int cnt = countRange(nums, min, mid);
            if (cnt > mid - min + 1){ // 重复元素在[min...mid]范围
                max = mid;
            }else {
                min = mid + 1; // 重复元素在[mid+1...max]范围
            }
        }
        return min;
    }

    private int countRange(int[] nums, int min, int max){
        int cnt = 0;
        for (int num : nums){
            if (num >= min && num <= max){
                cnt++;
            }
        }
        return cnt;
    }

    public static void main(String[] args){
        int[] nums = {1,3,4,2,2};
        Solution287 solution287 = new Solution287();
        int repeatNum = solution287.findDuplicate(nums);
        System.out.println(repeatNum);
    }
}
