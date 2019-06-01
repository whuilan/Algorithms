package textbook.chapter1_5;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QuickFindUF {
    private int[] id; // 分量id(以触点作为索引)
    private int count; // 分量数目
    /**构造函数：以整数标识（0到N-1）初始化N个触点*/
    public QuickFindUF(int N){
       id = new int[N];
       for(int i=0;i<N;i++){
           id[i] = i; // 初始化时，每个触点都构成了一个只含有它自己的分量
       }
       count = N;
    }
    /**返回连通分量的数量*/
    public int count(){
        return count;
    }
    /**返回触点p(0到N-1）所在的分量的标识符*/
    public int find(int p){
        return id[p];
    }
    /**在p和q之间添加一条连接，即将p和q归并到同一个分量中*/
    public void union(int p,int q){
        int pID = find(p);
        int qID = find(q);
        // 如果p和q已经在相同的分量中就不采取任何行动
        if(pID==qID) return;
        /*遍历数组id[],将p所在分量上所有触点的id[]值（即该分量的标识符）都变为q所在分量的标识符，
        以此将p和q归并到同一个分量中*/
        for(int i=0;i<id.length;i++){
            if(id[i]==pID){
                id[i]=qID;
            }
        }
        count--; // 每次合并分量的操作都会使分量总数减一
    }
    /**判断p和q是否存在于同一个分量中，即它们各自所在的分量的标识符是否相同*/
    public boolean connected(int p,int q){
        return find(p)==find(q);
    }

    public static void main(String[] args){
        int N = StdIn.readInt();
        QuickFindUF quickFindUf = new QuickFindUF(N);
        while(!StdIn.isEmpty()){
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if(quickFindUf.connected(p,q)) continue;
            quickFindUf.union(p,q);
            StdOut.println(p+" "+q);
        }
        StdOut.println(quickFindUf.count()+" components");

    }

}
