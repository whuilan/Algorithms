package textbook.chapter2_3;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import textbook.chapter2_1.Insertion;

public class Quick {
    public static void sort(Comparable[] a){
        StdRandom.shuffle(a);
        sort(a,0,a.length-1);
    }
    public static void sort(Comparable[] a, int lo,int hi){
        // if(hi<=lo) return;
        if(hi<=lo+10){ // 10是我随便取的
            Insertion.sort(a,lo,hi);
            return;
        }
        int j = partition(a,lo,hi);
        sort(a,lo,j-1);
        sort(a,j+1,hi);
    }
    public static int partition(Comparable[] a, int lo,int hi){
        // 将数组切分为a[lo..j-1],a[j],a[j+1..hi]
        int i = lo, j=hi+1;    // 左右扫描指针
        Comparable v = a[lo];  // 切分元素
        while (true){
            // 切分左右，检查扫描是否结束并交换元素
            while(less(a[++i],v)){
                if(i==hi)
                    break;
            }
            while (less(v,a[--j])){
                if(j==lo)
                    break;
            }
            if(i>=j)
                break;
            exch(a,i,j);
        }
        exch(a,lo,j); // 将v=a[j]放入正确的位置，即其左边的元素都小于等于v，右边的元素都大于等于v。
        return j;     // a[lo,j-1]<=a[j]<=a[j+1,hi]达成
    }
    private static boolean less(Comparable p,Comparable q){
        return p.compareTo(q)<0;
    }
    private static void exch(Comparable[] a,int i,int j){
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
    private static void show(Comparable[] a){
        for(int i=0;i<a.length;i++){
            StdOut.print(a[i]+" ");
        }
        StdOut.println();
    }
    public static void main(String[] args){
        Integer[] a = {3,2,6,0,1,4,7,5};
        sort(a);
        show(a);
    }
}
