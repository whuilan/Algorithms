package sword_to_offer.sort;

/**
 * 三向切分的快速排序（基于荷兰国旗优化的快速排序）；适合存在大量重复元素的数组排序!
 */
public class Quick3way {
    public static void sort(Comparable[] a){
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi){
        if (hi <= lo){
            return;
        }
        int lt = lo, i = lo + 1, gt = hi;  // i为扫描指针，没有j!
        Comparable v = a[lo];
        while (i <= gt){
            int cmp = a[i].compareTo(v);
            if(cmp < 0){
                exch(a, lt++, i++);
            }
            else if(cmp > 0){
                exch(a, i, gt--);
            }
            else {
                i++;
            }
        }
        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
    }

    private static void exch(Comparable[] a, int i, int j){
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args){
        String[] a = {"R", "B", "W", "W", "R", "W", "B","R","R","W","B","R"};
        sort(a);
        System.out.println("Quick3way finish!");
    }
}
