package sword_to_offer;

/**
 * P82旋转数组的最小数字
 */
public class Problem11 {
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
                hi= mid;
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
