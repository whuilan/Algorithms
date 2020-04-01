package sword_to_offer;

/**
 * P267 数组中数值和下标相等的元素
 */
public class Problem53_3 {
    public static int numSameAsIndex(int[] nums){
        if (nums == null || nums.length == 0){
            return -1;
        }
        int lo = 0, hi = nums.length - 1;
        while (lo <= hi){
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] > mid){
                hi = mid - 1;
            }
            else if (nums[mid] < mid){
                lo = mid + 1;
            }else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args){
        int[] nums = {-3,-1,1,3,5};
        int s = numSameAsIndex(nums);
        System.out.println(s);
    }
}
