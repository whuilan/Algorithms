package textbook.chapter1_5;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QuickUnionUF {
    private int[] id;
    private int count;
    public QuickUnionUF(int N){
        id = new int[N];
        for(int i=0;i<N;i++){
            id[i] = i;
        }
        count = N;
    }
    public int count(){
        return count;
    }
    private int find(int p){
        while (p!=id[p]){
            p = id[p];
        }
        return p;
    }
    public void union(int p,int q){
        int pRoot = find(p);
        int qRoot = find(q);
        if(pRoot==qRoot) return;
        id[pRoot] = qRoot;
        count--;
    }
    public boolean connected(int p,int q){
        return find(p) == find(q);
    }

    public static void main(String[] args){
        int N = StdIn.readInt();
        QuickUnionUF quickUnionUF = new QuickUnionUF(N);
        while(!StdIn.isEmpty()){
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if(quickUnionUF.connected(p,q)) continue;
            quickUnionUF.union(p,q);
            StdOut.println(p+" "+q);
        }
        StdOut.println(quickUnionUF.count()+" components");
    }
}
