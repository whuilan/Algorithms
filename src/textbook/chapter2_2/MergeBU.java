package textbook.chapter2_2;

import edu.princeton.cs.algs4.StdOut;

public class MergeBU {
    private static Comparable[] aux;
    private static boolean less(Comparable v,Comparable w){
        return v.compareTo(w)<0;
    }
    public static void sort(Comparable[] a){
        int N = a.length;
        aux = new Comparable[N];
        for (int sz=1;sz<N;sz+=sz){
            for(int lo=0;lo<N-1;lo+=sz+sz){ // 书上是lo<N-sz,防止N为奇数时数组索引溢出
                merge(a,lo,lo+sz-1,Math.min(lo+2*sz-1,N-1)); // 如果N是偶数的话可以直接取lo+2*sz-1，奇数时最后一组数组索引会超出范围
            }
        }
    }
    public static void merge(Comparable[] a,int lo,int mid,int hi){
        int i=lo,j=mid+1;
        for(int k=lo;k<=hi;k++){
            aux[k] = a[k];
        }
        for(int k=lo;k<=hi;k++){
            if(i>mid){
                a[k] = aux[j++];
            }
            else if(j>hi){
                a[k] = aux[i++];
            }
            else if(less(aux[j],aux[i])){
                a[k] = aux[j++];
            }
            else{
                a[k] = aux[i++];
            }
        }
    }
    public static void show(Comparable[] a){
        for(int i=0;i<a.length;i++){
            StdOut.print(a[i]+" ");
        }
        StdOut.println();
    }
    public static void main(String[] args){
        // Integer[] a = {6,4,2,3,1,5,0};
        Character[] a = {'A','B','C','A','E','G','F'};
        sort(a);
        show(a);
    }
}
