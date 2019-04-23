package whuilan.chapter1_2;

import edu.princeton.cs.algs4.In;

public class Ex15 {
    public static int[] readInts(String filename){
        In in = new In(filename);
        String all = in.readAll();
        String[] str = all.split("\\s+");
        int len = str.length;
        int[] res = new int[len];
        for(int i=0;i<len;i++){
            res[i] = Integer.parseInt(str[i]);
        }
        return res;
    }

    public static void main(String[] args){
        int[] a = readInts("tinyW.txt");
        for(int i=0;i<a.length;i++){
            System.out.print(a[i]+" ");
        }
    }
}
