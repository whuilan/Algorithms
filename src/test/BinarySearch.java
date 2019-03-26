package test;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class BinarySearch {

    private BinarySearch() { }

    public static int indexOf(int[] a,int key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) {
                hi = mid - 1;
            } else if (key > a[mid]) {
                lo = mid + 1;
            }
            else return mid;
        }
        return  -1;
    }

    @Deprecated
    public static int rank(int key, int[] a) {
        return indexOf(a, key);
    }

    public static void main(String[] args){
        In in = new In(args[0]);
        int[] whitelist = in.readAllInts();

        Arrays.sort(whitelist);

        while(!StdIn.isEmpty()){
            int key = StdIn.readInt();
            if(BinarySearch.indexOf(whitelist,key)==-1)
                StdOut.println(key);
        }
    }
}
