package exercises.chapter1_5;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Ex01QuickFindUF {
    private int[] id;
    private int count; // 连通分量个数
    private int arrayAccessCount = 0;
    public Ex01QuickFindUF(int N){
        id = new int[N];
        for(int i=0;i<N;i++){
            id[i] = i;
        }
        count = N;
    }
    public int count(){
        return count;
    }
    public int find(int p){
        arrayAccessCount++;
        return id[p];
    }
    public void union(int p,int q){
        int pID = find(p);
        int qID = find(q);
        if(pID==qID) return;
        for(int i=0;i<id.length;i++){
            arrayAccessCount++;
            if(id[i]==pID){
                id[i]=qID;
                arrayAccessCount++;
            }
        }
        count--;
    }
    public boolean connected(int p,int q){
        return find(p)==find(q);
    }
    public static void main(String[] args){
        int N = StdIn.readInt();
        Ex01QuickFindUF quickFindUf = new Ex01QuickFindUF(N);
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
