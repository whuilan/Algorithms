package textbook.chapter5_1;

import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 高位优先的字符串排序
 */
public class MSD {
    private static int R = 256;  // 基数
    private static final int M = 15; // 小数组的切换阈值
    private static String[] aux;
    private static int charAt(String s, int d){
        // 将字符串中字符索引转化为数组索引
        if(d < s.length()){
            return s.charAt(d);
        }else {
            return -1;
        }
    }
    public static void sort(String[] a){
        int N = a.length;
        aux = new String[N];
        sort(a, 0, N-1, 0);
    }
    // 以第d个字符为键将a[lo]至a[hi]排序
    private static void sort(String[] a, int lo, int hi, int d){
        // 对于较小的子数组切换至插入排序
        if(hi <= lo + M){
            insertion(a, lo, hi, d);
            return;
        }
        int[] count = new int[R + 2];
        // 计算频率
        for(int i = lo; i <= hi; i++){
            int index = charAt(a[i], d);
            count[index + 2] ++;
        }
        // 将频率转换为索引
        for(int r = 0; r < R + 1; r++){
            count[r + 1] += count[r];
        }
        // 元素分类
        for (int i = lo; i <= hi; i++){
            int index = count[charAt(a[i],d) + 1]++;
            aux[index] = a[i];
        }
        // 回写
        for(int i = lo; i <= hi; i++){
            a[i] = aux[i];
        }

        // 递归地以每个字符为键进行排序
        for(int r = 0; r < R; r++){
            sort(a, lo + count[r], lo + count[r + 1] - 1, d + 1);
        }
    }
    private static void insertion(String[] a, int lo, int hi, int d) {
        for (int i = lo; i <= hi; i++)
            for (int j = i; j > lo && less(a[j], a[j-1], d); j--)
                exch(a, j, j-1);
    }

    // exchange a[i] and a[j]
    private static void exch(String[] a, int i, int j) {
        String temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    // is v less than w, starting at character d
    private static boolean less(String v, String w, int d) {
        // assert v.substring(0, d).equals(w.substring(0, d));
        for (int i = d; i < Math.min(v.length(), w.length()); i++) {
            if (v.charAt(i) < w.charAt(i)) return true;
            if (v.charAt(i) > w.charAt(i)) return false;
        }
        return v.length() < w.length();
    }

    public static void main(String[] args){
        String[] a = StdIn.readAllStrings();
        sort(a);
        for(int i = 0; i < a.length; i++){
            StdOut.println(a[i]);
        }
    }
}
