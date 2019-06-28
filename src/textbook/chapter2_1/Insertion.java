package textbook.chapter2_1;

import edu.princeton.cs.algs4.StdOut;

public class Insertion {
   public static void sort(Comparable[] a){
       int N = a.length;
       for(int i=1;i<N;i++){
           for(int j=i;j>0&&less(a[j],a[j-1]);j--){
               exch(a,j,j-1);
           }
       }
   }
    public static void sort(Comparable[] a,int lo,int hi){
        for(int i=lo+1;i<hi;i++){
            for (int j=i;j>lo&&less(a[j],a[j-1]);j--){
                exch(a,j,j-1);
            }
        }
    }
   public static boolean less(Comparable v,Comparable w){
       return v.compareTo(w)<0;
   }
   public static void exch(Comparable[] a,int i,int j){
       Comparable t = a[i];
       a[i] = a[j];
       a[j] = t;
   }
   public static void show(Comparable[] a){
       for(int i=0;i<a.length;i++){
           StdOut.print(a[i]+" ");
       }
       StdOut.println();
   }
   public static boolean isSorted(Comparable[] a){
       for(int i=1;i<a.length;i++){
           if(less(a[i],a[i-1])) return false;
       }
       return true;
   }
    public static void main(String[] args){
        //In in = new In();
        // String[] b = {"S","O","R","T","E","X","A","M","P","L","E"};
        Integer[] b = {1,3,5,2,4};
        sort(b);
        assert isSorted(b);
        show(b);
    }
}
