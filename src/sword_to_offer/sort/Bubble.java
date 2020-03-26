package sword_to_offer.sort;

/**
 * 比较次数为~n^2/2,交换次数最好0，最差~n^2/2
 */
public class Bubble {
    public void sort(Comparable[] a){
        if (a == null || a.length <= 1){
            return;
        }
        int n = a.length;
        for (int i = n; i > 0; i--){
            for (int j = 1; j < i; j++){
                if (less(a[j], a[j-1])){
                    exch(a, j , j - 1);
                }
            }
        }
    }

    private boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }

    private void exch(Comparable[] a, int i, int j){
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
