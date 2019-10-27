package textbook.chapter4_3;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;
import textbook.chapter1_3_3.Queue;

/**
 * 最小生成树的Prim算法的延时实现
 */
public class LazyPrimMST {
    private boolean[] marked; // 最小生成树的顶点
    private Queue<Edge> mst; // 最小生成树的边
    private MinPQ<Edge> pq; // 横切边（包括失效的边）
    private double weight;

    public LazyPrimMST(EdgeWeightedGraph ewg){
        marked = new boolean[ewg.V()];
        mst = new Queue<>();
        pq = new MinPQ<>();

        visit(ewg, 0);
        while (!pq.isEmpty()){
            Edge e = pq.delMin(); // 从pq中得到权重最小的横切边
            int v = e.either(), w = e.other(v);
            if(marked[v] && marked[w]){
                continue;          // 跳过失效的边（边的两个顶点都在树中，不是横切边）
            }
            mst.enqueue(e);        // 将权重最小的横切边加入树中
            weight += e.weight();
            // 将横切边中另外一个未被标记的顶点（v或w）加入到树中
            if(!marked[v]){
                visit(ewg, v);
            }
            if(!marked[w]){
                visit(ewg, w);
            }
        }
    }
    // 标记顶点v并将所有连接v和未被标记的顶点的边加入横切边优先队列pq
    public void visit(EdgeWeightedGraph ewg, int v){
        marked[v] = true;
        for( Edge e : ewg.adj(v)){
            if(!marked[e.other(v)]){
                pq.insert(e);
            }
        }
    }
    // 返回最小生成树的所有边
    public Iterable<Edge> edges(){
        return mst;
    }
    // 返回最小生成树的权重（延时）
    public double weight(){
//        double weight = 0;
//        for(Edge e : mst){
//            weight += e.weight();
//        }
        return weight;
    }

    public static void main(String[] args){
        In in = new In("tinyEWG.txt");
        EdgeWeightedGraph ewg = new EdgeWeightedGraph(in);
        LazyPrimMST mst = new LazyPrimMST(ewg);
        for(Edge e : mst.edges()){
            StdOut.println(e);
        }
        StdOut.println(mst.weight());
    }
}
