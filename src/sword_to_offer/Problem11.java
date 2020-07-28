package sword_to_offer;

/**
 * P82旋转有序数组的最小数字:
 * 二分查找的延伸应用，可以用来查找某个数字在一个排序数组中应该插入的位置
 */
public class Problem11 {
    // 面试的时候要问面试官是否存在重复元素！
    // 不包含重复元素
    public int findMin1(int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }
        int lo = 0, hi = nums.length - 1;
        while (lo < hi){
            int mid = lo + (hi - lo) / 2;
            // 记住是和最后一个元素比较（这样就算数组旋转0个数字也不会出错）
            if (nums[mid] > nums[hi]){
                lo = mid + 1; // 如果中间元素比右端点大，说明它属于未旋转的较大的部分
            }
            else{
                hi = mid; // 中间元素小于等于右端点，说明它就是旋转了的较小部分中的某个数（并且可能就是最小的数字）
            }
        }
        return nums[lo]; // 当lo==hi时就是最小元素了
    }

    // 包含重复元素，注意会出现nums[lo]=nums[mid]=nums[hi]的情况,
    // 如{1,1,1,0,1}和{1,0,1,1,1}下·的情况，此时应切换到顺序查找
    public int findMin2(int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }
        int lo = 0, hi = nums.length - 1;
        while (lo < hi){
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == nums[lo] && nums[mid] == nums[hi]){
                return sequentialFind(nums, lo, hi);
            }
            else if (nums[mid] <= nums[hi]){
                hi = mid;
            }
            else{
                lo = mid + 1;
            }
        }
        return nums[lo];
    }

    private int sequentialFind(int[] nums, int lo, int hi){
        for (int i = lo; i < hi; i++){
            if (nums[i] > nums[i + 1]){
                return nums[i + 1];
            }
        }
        return nums[lo];
    }



    // 书上的解法
    public int minNumberInRotateArray(int [] array) {
        if(array == null || array.length == 0){
            return 0;
        }
        int lo = 0, hi = array.length - 1;
        int mid = 0;  // 防止特例，即数组0个元素被搬到末尾，旋转后仍然为数组本身
        while (array[lo] >= array[hi]){
            if(hi - lo == 1){
                mid = hi;
                break;
            }
            mid = lo + (hi - lo) / 2;
            if (array[mid] == array[lo] && array[mid] == array[hi]){
                return sequencedSort(array, lo, hi);
            }

            if (array[mid] >= array[lo]){
                lo = mid;
            }
            else if (array[mid] <= array[hi]){
                hi = mid;
            }
        }
        return array[mid];
    }

    private int sequencedSort(int[] a, int l, int h){
        int min = a[l];
        for(int i = l + 1;i <= h; i++){
            if(a[i] < min){
                min = a[i];
            }
        }
        return min;
    }

    public static void main(String[] args){
        int[] a = {1, 2, 3, 4, 5, 6,};
        Problem11 problem11 = new Problem11();
        int minNum = problem11.minNumberInRotateArray(a);
        System.out.println(minNum);
    }
}
