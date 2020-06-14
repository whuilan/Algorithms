package LeetCode.SearchAndSort;

/**
 * 搜索旋转排序数组：在旋转递增排序数组中搜索一个给定的目标值，如果数组中存在这个目标值，
 * 则返回它的索引，否则返回-1
 */
public class Solution33 {
    /*
    我的思路：先用二分查找找到最小的数字在哪，然后把数组分为旋转和未旋转两个部分，这两个部分就都是
    排序的了，根据要查找的值的范围分别在这两个部分里又用二分查找来查找，一共两次二分查找，
    时间复杂度为O(logn)
     */
    public int search1(int[] nums, int target) {
        if (nums == null || nums.length == 0){
            return -1;
        }
        // 先用二分查找找到数组中最小的数字
        int lo = 0, hi = nums.length - 1;
        while (lo < hi){
            int mid = lo + (hi - lo) / 2;
            // 假如在找最小数字的二分查找过程中找到了target就直接返回，这一步不要掉
            if (nums[mid] == target){
                return mid;
            }
            if (nums[mid] > nums[hi]){
                lo = mid + 1;
            }else {
                hi = mid;
            }
        }
        // 执行完上述while循环后，lo即为数组中最小元素的索引
        int N = nums.length;
        if (target <= nums[N - 1]){
            return binarySearch(nums, lo, N - 1, target);
        }
        return  binarySearch(nums, 0, lo - 1, target);
    }

    private int binarySearch(int[] nums, int start, int end, int target){
        int lo = start, hi = end;
        while (lo <= hi){
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] < target){
                lo = mid + 1;
            }
            else if (nums[mid] > target){
                hi = mid - 1;
            }
            else {
                return mid;
            }
        }
        return -1;
    }

    /*
    思路二：直接对旋转数组进行二分查找target。此时由于排序数组经过了旋转，应该不能直接根据nums[mid]和
    target的关系确定是在mid左边找还是右边找，因此可以分情况讨论！看mid是属于左半边还是右半边
     */
    public int search2(int[] nums, int target) {
        if (nums == null || nums.length == 0){
            return -1;
        }
        int lo = 0, hi = nums.length - 1;
        while (lo <= hi){
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == target){
                return mid;
            }
            // 先根据nums[mid]和nums[hi]的大小关系判断nums[mid]是在左边的有序列还是右边的有序列中
            if (nums[mid] > nums[hi]){  // 中间元素属于左半部分，比较大的一边
                // 再判断target在mid的左边还是右边，据此调整左右边界lo和hi,缩小范围
                if (target >= nums[lo] && target < nums[mid]){
                    // 注意要同时满足这两个条件才能说明target在左半边
                    hi = mid - 1;
                }else {
                    lo = mid + 1;
                }
            }
            else { // 中间元素属于右半部分，比较小的一边
                if (target > nums[mid] && target <= nums[hi]){
                    lo = mid + 1;
                }else {
                    hi = mid - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args){
        int[] nums = {4,5,6,7,0,1,2};
        Solution33 solution33 = new Solution33();
        int index = solution33.search2(nums, 3);
        System.out.println(index);
    }
}
