package textbook.chapter2_3;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Quick3way {
    private static int exchNum;
    public static void sort(Comparable[] a){
        // StdRandom.shuffle(a);
        sort(a,0,a.length-1);
    }
    private static void sort(Comparable[] a,int lo,int hi){
        if(hi<=lo) return;
        int lt=lo,i=lo+1,gt=hi;
        Comparable v = a[lo];
        while(i<=gt){
            int cmp = a[i].compareTo(v);
            if(cmp<0){
               exch(a,lt++,i++);
            }
            else if(cmp>0){
                exch(a,i,gt--);
            }
            else{
                i++;
            }
            exchNum++;
        }
        sort(a,lo,lt-1);
        sort(a,gt+1,hi);
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
        Integer[] a = {2,3,1,1,2,3,2,3,2,1,2,3};
        sort(a);
        show(a);
        StdOut.print("The exchange tims of Quick3way is "+exchNum);
    }
}
