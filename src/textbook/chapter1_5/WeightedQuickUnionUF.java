package textbook.chapter1_5;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class WeightedQuickUnionUF {
    private int[] id;
    private int[] size;
    private int count;
    public WeightedQuickUnionUF(int N){
        id = new int[N];
        for(int i=0;i<N;i++){
            id[i] = i;
        }
        size = new int[N];
        for(int i=0;i<N;i++){
            size[i] = 1;
        }
        count = N;
    }
    public int count(){
        return count;
    }
    public int find(int p){
        while (p!=id[p]){
            p = id[p];
        }
        return p;
    }
    public void union(int p,int q){
        int pRoot = find(p);
        int qRoot = find(q);
        if(pRoot == qRoot) return;
        if(size[pRoot]<size[qRoot]){
            id[pRoot] = qRoot;
            size[qRoot]+=size[pRoot];
        }else{
            id[qRoot] = pRoot;
            size[pRoot]+=size[qRoot];
        }
        count--;
    }
    public boolean connected(int p,int q){
        return find(p) == find(q);
    }
    public static void main(String[] args){
        int N = StdIn.readInt();
        WeightedQuickUnionUF wUF = new WeightedQuickUnionUF(N);
        while (!StdIn.isEmpty()){
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if(wUF.connected(p,q)) continue;
            wUF.union(p,q);
            StdOut.println(p+" "+q);
        }
        StdOut.println(wUF.count+" components");
    }
}
