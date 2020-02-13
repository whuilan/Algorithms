package sword_to_offer.sort;

/**
 * 自顶向下的归并排序：对于长度为N的任意数组，自顶向下的归并排序所需的时间和NlogN成正比，
 * 缺点：使用的额外空间和N成正比
 */
public class Merge {

    private static Comparable[] aux;   // 归并所需的辅助数组

    public static void sort(Comparable[] a){
        aux = new Comparable[a.length];   // 一次性分配空间
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi){
        // 将数组a[lo..hi]排序
        if(hi <= lo){
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);         // 将左半边排序
        sort(a, mid + 1, hi);  // 将右半边排序
        merge(a, lo, mid, hi);
    }

    private static void merge(Comparable[] a, int lo, int mid, int hi){
        // 将a[lo..mid]和a[mid+1..hi]归并
        int i = lo, j = mid + 1;  // 注意i,j为辅助数字aux左半边和右半边的起始指针
        for(int k = lo;k <= hi; k++){
            aux[k] = a[k];
        }
        for(int k = lo; k <= hi; k++){
            if(i > mid){
                a[k] = aux[j++];
            }
            else if (j > hi){
                a[k] = aux[i++];
            }
            else if(less(aux[j], aux[i])){
                a[k] = aux[j++];
            }
            else {
                a[k] = aux[i++];
            }
        }
    }

    private static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }

    public static void main(String[] args){
        String[] a = {"M", "E", "R", "G", "E", "S", "O","R","T","E","X","A","M","P","L","E"};
        sort(a);
        System.out.println("Finish!");
    }
}