package textbook.chapter5_1;

import edu.princeton.cs.algs4.Alphabet;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 三向字符串快速排序
 */
public class Quick3string {
    // 为了调试跟踪书上的示例，用lowercase的26个字符集作为字母表
    private static Alphabet lowercase = new Alphabet().LOWERCASE;
    private static int charAt(String s, int d){
        if(d < s.length()){
            char c = s.charAt(d);
            return lowercase.toIndex(c);
        }else {
            return -1;
        }
    }
    public static void sort(String[] a){
        sort(a, 0, a.length - 1, 0);
    }
    private static void sort(String[] a, int lo, int hi, int d){
        // 掉了第一步判断！
        if(hi <= lo){
            return;
        }
        int lt = lo, gt = hi;
        int i = lo + 1;
        // 以字符数组第一个元素的第d位字符为比较的标准
        int v = charAt(a[lo], d);
        while (i <= gt){
            int t = charAt(a[i], d);
            if(t < v) {
                exch(a, lt++, i++);
            }
            else if(t > v){
                exch(a, i, gt--);
            }
            else {
                i++;
            }
        }
        /*
         循环结束后得到三个子数组，满足a[lo..lt-1] < v = a[lt..gt] < a[gt..hi]
         接下来对递归地对三个子数组进行排序
         */
        sort(a, lo, lt - 1, d);
        // 在“等于”子数组即a[lt..gt]中忽略首字母，只要字符串还未结束继续从下一位开始循环
        if(v >= 0){
            sort(a, lt, gt, d + 1);
        }
        sort(a, gt + 1, hi, d);
    }
    // 交换a[i]和a[j]
    private static void exch(String[] a, int i, int j){
        String temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    public static void main(String[] args){
        String[] a = StdIn.readAllStrings();
        sort(a);
        for(int i = 0; i < a.length; i++){
            StdOut.println(a[i]);
        }
    }
}
