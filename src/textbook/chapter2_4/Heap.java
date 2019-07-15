package textbook.chapter2_4;

import edu.princeton.cs.algs4.StdOut;

public class Heap {
    public static void sort(Comparable[] a){
        int N = a.length-1;
        for(int k=N/2;k>0;k--){
            sink(a,k,N);
        }
        while (N>1){
            exch(a,1,N);
            N--;
            sink(a,1,N);
        }
    }
    private static void sink(Comparable[] a,int k,int N){
        while (2*k<=N){
            int j = k*2;
            if(j < N && less(a[j],a[j+1]))
                j++;
            if(!less(a[k],a[j]))
                return;
            exch(a,k,j);
            k=j;
        }
    }
    private static boolean less(Comparable p,Comparable q){
        return p.compareTo(q)<0;
    }
    private static void exch(Comparable[] a,int i,int j){
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    public static void main(String[] args){
        Integer[] t = {2,4,5,1,6,7,11,9,8,3,10};
        int l = t.length;
        Integer[] a = new Integer[l+1];
        for(int i=0;i<l;i++){
            a[i+1] = t[i];
        }
        sort(a);
        for(int i=1;i<a.length;i++){
            StdOut.print(a[i]+" ");
        }
    }
}
