package sword_to_offer;

/**
 * P259数组中的逆序对数目
 */
public class Problem51 {

    private long N = 0;
    private int[] aux;

    public int InversePairs(int [] array) {
        if (array == null || array.length == 0){
            return 0;
        }
        aux = new int[array.length]; // 辅助数组
        mergeSort(array, 0, array.length - 1);
        return (int) (N % 1000000007);
    }

    private void mergeSort(int[] a, int lo, int hi){
        if (hi <= lo){
            return;
        }
        int mid = lo + (hi - lo) / 2;
        mergeSort(a, lo, mid);
        mergeSort(a, mid + 1, hi);
        merge(a, lo, mid, hi);
    }

    private void merge(int[] a, int lo, int mid, int hi){
        int i = lo, j = mid + 1;
        for (int k = lo;k <= hi;k++){
            aux[k] = a[k];
        }
        for (int k = lo;k <= hi;k++){
            if (i > mid){
                a[k] = aux[j++];
            }
            else if (j > hi){
                a[k] = aux[i++];
            }
            else if (aux[i] <= aux[j]){
                a[k] = aux[i++];
            }
            else {
                a[k] = aux[j++];
                N += mid - i + 1;
            }
        }
    }

    private void merge_2(int[] a, int lo, int mid, int hi){
        for (int i = lo; i <= hi; i++){
            aux[i] = a[i];
        }
        int i = mid, j = hi;  // 两个子数组的指针
        for (int k = hi; k >= lo; k--){
            if (i < lo){
                a[k] = aux[j--];
            }
            else if (j < mid + 1){
                a[k] = aux[i--];
            }
            else if (aux[i] <= aux[j]){
                a[k] = aux[j--];
            }
            else {
                a[k] = aux[i--];
                N += j - mid;
            }
        }
    }

    public static void main(String[] args){
        int[] arr = {6,6,7,8,6,4};
        Problem51 problem51 = new Problem51();
        int n = problem51.InversePairs(arr);
        System.out.println(n);
    }
}
