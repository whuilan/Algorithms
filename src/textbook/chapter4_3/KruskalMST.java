package textbook.chapter4_3;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;
import textbook.chapter1_3_3.Queue;
import textbook.chapter1_5.WeightedQuickUnionUF;

public class KruskalMST {
    private Queue<Edge> mst;   // 最小生成树的所有边
    private double weight;

    public KruskalMST(EdgeWeightedGraph ewg){
        mst = new Queue<>();
        MinPQ<Edge> pq = new MinPQ<>();  // 将原图的所有边按权重排序
        for(Edge e : ewg.edges()){
            pq.insert(e);
        }
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(ewg.V());  // 识别连通分量（是否形成环）
        while (!pq.isEmpty() && mst.size() < ewg.V() - 1){
            Edge minEdge = pq.delMin();
            int v = minEdge.either(), w = minEdge.other(v);
            if(uf.connected(v, w)){
                continue;
            }
            mst.enqueue(minEdge);  // 将边添加到最小生成树中
            weight += minEdge.weight();
            uf.union(v, w);   // 合并分量
        }
    }
    public Iterable<Edge> edges(){
        return mst;
    }
    public double weight(){
        return weight;
    }
    public static void main(String[] args){
        In in = new In("tinyEWG.txt");
        EdgeWeightedGraph ewg = new EdgeWeightedGraph(in);
        KruskalMST mst = new KruskalMST(ewg);
        for(Edge e : mst.edges()){
            StdOut.println(e);
        }
        StdOut.println(mst.weight());
    }
}
