package sword_to_offer;

/**
 * P263数字在排序数组中出现的次数，时间复杂度为O(logN)
 */
public class Problem53 {
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

    public static void main(String[] args){
        int[] a = {1,2,3,3,3,3,4,5};
        Problem53 problem53 = new Problem53();
        int count = problem53.GetNumberOfK(a, 3);
        System.out.println(count);
    }
}
