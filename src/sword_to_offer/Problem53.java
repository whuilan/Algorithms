package sword_to_offer;

/**
 * P263数字在排序数组中出现的次数，时间复杂度为对数级别O(logN)
 */
public class Problem53 {
    // 法一：写两个二分查找方法分别查找k第一次出现的位置和最后一次出现的位置
    public int GetNumberOfK(int [] array , int k) {
        if (array == null || array.length == 0){
            return 0;
        }
        int firstK = firstIndex(array, k);
        int lastK = lastIndex(array, k);
        return firstK == -1 ? 0 : lastK - firstK + 1;
    }

    private int firstIndex(int[] nums, int target){
        int lo = 0, hi = nums.length - 1;
        while(lo <= hi){
            int mid = lo + (hi - lo) / 2;
            if(nums[mid] < target){
                lo = mid + 1;
            }else if(nums[mid] > target){
                hi = mid - 1;
            }else{
                // 不返回，而是继续在左侧查找
                hi = mid - 1;
            }
        }
        // 检查越界
        if(lo >= nums.length || nums[lo] != target){
            return -1;
        }
        // 返回查找区间左端点
        return lo;
    }

    private int lastIndex(int[] nums, int target){
        int lo = 0, hi = nums.length - 1;
        while(lo <= hi){
            int mid = lo + (hi - lo) / 2;
            if(nums[mid] < target){
                lo = mid + 1;
            }else if(nums[mid] > target){
                hi = mid - 1;
            }else{
                // 不返回，继续在右侧查找
                lo = mid + 1;
            }
        }
        // 检查越界情况
        if(hi < 0 || nums[hi] != target){
            return -1;
        }
        // 返回查找区间右端点
        return hi;
    }

    // 最开始的查找左右边界的方法，也挺容易理解的~
    private int getFirstK(int[] a, int k){
        int lo = 0, hi = a.length - 1;
        while (lo <= hi){
            int mid = lo + (hi - lo) / 2;
            if (a[mid] > k){
                hi = mid - 1;
            }
            else if (a[mid] < k){
                lo = mid + 1;
            }
            else {
                if (mid > lo && a[mid - 1] == k){
                    hi = mid - 1;
                }else {
                    return mid;
                }
            }
        }
        return -1;
    }

    private int getLastK(int[] a, int k){
        int lo = 0, hi = a.length - 1;
        while (lo <= hi){
            int mid = lo + (hi - lo) / 2;
            if (a[mid] > k){
                hi = mid - 1;
            }
            else if (a[mid] < k){
                lo = mid + 1;
            }
            else {
                if (mid < hi && a[mid + 1] == k){
                    lo = mid + 1;
                }else {
                    return mid;
                }
            }
        }
        return -1;
    }

    // 只写一个二分查找的方法，分别查找k和k+1在排序数组中应该插入的位置
    public int GetNumberOfK2(int [] array , int k) {
        if (array != null || array.length != 0){
            int kStart = getIndexOfK(array, k);
            int kAddOndeStart = getIndexOfK(array, k + 1);
            if (kStart < array.length && array[kStart] == k){
                return kAddOndeStart - kStart;
            }
        }
        return 0;
    }

    // 返回一个数k在一个排序数组中应该插入的位置，若原数组中包含这个元素，
    // 则返回它在数组中第一次出现时的索引
    private int getIndexOfK(int[] a, int k){
        int lo = 0, hi = a.length; // 注意k可能比数组中的元素都要大，此时应该插入到a.length索引处
        while (lo < hi){
            int mid = lo + (hi - lo) / 2;
            if (a[mid] >= k){
                hi = mid;
            }
            else{
                lo = mid + 1;
            }
        }
        return lo;
    }

    public static void main(String[] args){
        int[] a = {1,2,3,3,3,5};
        Problem53 problem53 = new Problem53();
        int count = problem53.GetNumberOfK2(a, 3);
        System.out.println(count);
    }
}
