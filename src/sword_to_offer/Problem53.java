package sword_to_offer;

/**
 * P263数字在排序数组中出现的次数，时间复杂度为O(logN)
 */
public class Problem53 {
    // 法一：写两个二分查找方法分别查找k第一次出现的位置和最后一次出现的位置
    public int GetNumberOfK(int [] array , int k) {
        if (array == null || array.length == 0){
            return 0;
        }
        int firstK = getFirstK(array, k);
        int lastK = getLastK(array, k);
        if (firstK == -1 && lastK == -1){
            return 0;
        }
        return lastK - firstK + 1;
    }

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

    // 返回一个数k在一个排序数组中应该插入的位置，若原数组中包含这个元素，则返回它在数组中的索引
    private int getIndexOfK(int[] a, int k){
        int lo = 0, hi = a.length;
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
        int[] a = {1,2,3,3,3,3,4,5};
        Problem53 problem53 = new Problem53();
        int count = problem53.GetNumberOfK2(a, 3);
        System.out.println(count);
    }
}
