package sword_to_offer;

/**
 * P266 0~n-1中缺失的数字
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
       int[] a = {0,1,3,4,5};
       int m = missingNumber2(a);
       System.out.println(m);
    }
}
