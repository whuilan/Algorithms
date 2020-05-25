package sword_to_offer;

/**
 * P266 0~n-1中缺失的数字，其实就是在排序数组中找出第一个值和下标不相等的元素
 * 用二分查找法，时间复杂度为为对数级别：O(logN)
 */
public class Problem53_2 {
    public static int missingNumber(int[] nums) {
        if(nums == null || nums.length == 0){
            return -1;
        }
        int lo = 0, hi = nums.length - 1;
        while (lo <= hi){
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == mid){
                lo = mid + 1;
            }
            else{
                if (mid > lo && nums[mid - 1] != mid - 1){
                    hi = mid - 1;
                }else {
                    return mid;
                }
            }
        }
        // 边界不能忘掉！加入缺失的数字就是最后一个数字，那么在上面的循环内部就不会有返回！
        if (lo == nums.length){
            return nums.length;
        }
        // 无效的输入，比如数组不是按照要求排序的，或者有数字不在0~n-1范围之内
        return -1;
    }

    public static int missingNumber2(int[] nums) {
        if (nums != null && nums.length > 0){
            int lo = 0, hi = nums.length;
            while (lo < hi){
                int mid = lo + (hi - lo) / 2;
                if (nums[mid] == mid){
                    lo = mid + 1;
                }else {
                    hi = mid;
                }
            }
            return lo;
        }
        return -1;
    }

    public static void main(String[] args){
       int[] a = {0,1,2};
       int m = missingNumber(a);
       System.out.println(m);
    }
}
