package sword_to_offer.sort;

/**
 * 选择/冒泡排序，需要大约n^2/2次比较和N次交换，不需要额外的空间(即空间复杂度
 * 为O(1)，称为原地排序)
 */
public class Selection {
    public static void sort(Comparable[] a){
        int N = a.length;
        for(int i = 0; i < N; i++){
            int minIndex = i;
            for(int j = i + 1; j < N; j++){
                if(less(a[j], a[minIndex])){
                    minIndex = j;
                }
            }
            exch(a, i, minIndex);
        }
    }

    private static boolean less(Comparable v, Comparable w){
       return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j){
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args){
        Integer[] a = {1,3,4,2,5};
        sort(a);
        System.out.println("Finish");
    }

}
