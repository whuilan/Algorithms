package exercises.chapter1_5;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Ex02QuickUnionUF {
    private int[] id;
    private int count;
    private int arrayAccessCount;
    public Ex02QuickUnionUF(int N){
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
        arrayAccessCount++;
        while (p!=id[p]){
            arrayAccessCount++;
            p = id[p];
        }
        return p;
    }
    public void union(int p,int q){
        int pRoot = find(p);
        int qRoot = find(q);
        if(pRoot==qRoot) return;
        id[pRoot] = qRoot;
        arrayAccessCount++;
        count--;
    }
    public boolean connected(int p,int q){
        return find(p) == find(q);
    }
    public static void main(String[] args){
        int N = StdIn.readInt();
        Ex02QuickUnionUF quickFindUf = new Ex02QuickUnionUF(N);
        while(!StdIn.isEmpty()){
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            StdOut.println("输入的整数对为："+p+" "+q);
            if(quickFindUf.connected(p,q)) {
                for(int i=0;i<N;i++){
                    StdOut.print(quickFindUf.id[i]+" ");
                }
                StdOut.println();
                StdOut.println("数组访问次数为："+quickFindUf.arrayAccessCount);
                continue;
            }
            quickFindUf.union(p,q);
            for(int i=0;i<N;i++){
                StdOut.print(quickFindUf.id[i]+" ");
            }
            StdOut.println();
            StdOut.println("数组访问次数为："+quickFindUf.arrayAccessCount);
        }
    }
}
