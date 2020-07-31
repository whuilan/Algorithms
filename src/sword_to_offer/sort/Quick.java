package sword_to_offer.sort;

import edu.princeton.cs.algs4.StdRandom;

import java.util.*;

/**
 * 快速排序，快排平均所需要的时间也与NlogN成正比(~2NlogN次比较)，空间复杂为:O(logn)，
 * 快排最好的情况就是每次都正好能将数组对半分，最优时间复杂度为O(nlogn),空间：O(logn)
 * 最差的情况就是数组本来就是有序的或者倒序的，最差时间复杂度为O(n^2),空间复杂度为O(n)
 * 比较次数多于归并排序,但在内循环中移动数据的次数更少，因此快速排序比归并排序要快。
 * 改进：1 排序小数组(5-15)时切换到插入排序
 * 2 三取样切分
 * 3 熵最优的排序，适用于含有大量重复元素的数组排序，又称为荷兰国旗问题
 * 打乱顺序见：leetcode215
 */
public class Quick {
    private static Random random = new Random();

    public static void sort(Comparable[] a){
        Collections.shuffle(Arrays.asList(a));  // 打乱a中元素的顺序，消除对输入的依赖
        sort(a, 0, a.length - 1);
        // 注意：如果传进来的时int[]，则打乱数组排序的方法如下，且在最前面需要引入import java.util.*;
//        List<Integer> list = new ArrayList<>();
//        for (int val : arr){
//            list.add(Integer.valueOf(val));
//        }
//        Collections.shuffle(list);
//        for (int i = 0;i < arr.length; i++){
//            arr[i] = list.get(i);
//        }
    }

    private static void sort(Comparable[] a, int lo, int hi){
        if(hi <= lo){
            return;
        }
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi){
        int i = lo, j = hi + 1;    // 左右扫描指针
        // 还可以在这里打乱顺序，随机选择一个元素作为v，方法为：
        int randomIdx = random.nextInt(hi-lo+1) + lo;
        exch(a, lo, randomIdx);
        Comparable v = a[lo];      // 切分元素
        while (true){
            // 扫描左右，检查扫描是否结束并交换元素
            while (less(a[++i], v)){
                if(i == hi){
                    break;
                }
            }
            while (less(v, a[--j])){
                if(j == lo){
                    break;
                }
            }
            if(i >= j){
                break;
            }
            exch(a, i , j);
        }
        exch(a, lo, j);   // 将切分元素v放入合适的位置
        return j;         // a[lo..j - 1] <= a[j] <= a[j + 1..hi]
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
        // String[] a = {"Q", "U", "I", "C", "K", "S", "O","R","T","E","X","A","M","P","L","E"};
        Integer[] a = {2,3,6,5,4};
        sort(a);
        System.out.println("Finish!");
    }
}
