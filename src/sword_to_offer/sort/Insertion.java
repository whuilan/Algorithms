package sword_to_offer.sort;

/**
 * 插入排序（忘了！就是按升序的顺序一个一个挪动）,平均：~N^2/4次比较以及~N^2/4次交换，
 * 最坏：~N^2/2次比较以及~N^2/2次交换，最好：N-1次比较和0次交换。适用于于部分有序数组和
 * 小规模数组。对于随机排序的无重复主键的数组，冒泡、插入和选择排序的时间复杂度都是平方级别。
 */
public class Insertion {
    public static void sort(Comparable[] a){
        int N = a.length;
        for(int i = 1; i < N; i++){
            for(int j = i; j > 0 && less(a[j], a[j-1]); j--){
                exch(a, j, j - 1);
            }
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
        Integer[] a = {8,5,7,9,1,10,0,4,6,3,2};
        sort(a);
        System.out.println("Finish");
    }
}
