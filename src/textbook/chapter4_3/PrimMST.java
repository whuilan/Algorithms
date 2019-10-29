package textbook.chapter4_3;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.StdOut;
import textbook.chapter1_3_3.Queue;

/**
 * 最小生成树的Prim算法（即时版本）
 */
public class PrimMST {
    private Edge[] edgeTo;    // edgeTo[v] = 将v连接到树中的最小的边,即顶点v距离树最近的边
    private double[] distTo;  // distTo[v] =  edgeTo[v].weight()
    private boolean[] marked; // 顶点v是否在树中
    private IndexMinPQ<Double>  pq; // 有效的横切边

    public PrimMST(EdgeWeightedGraph ewg){
        edgeTo = new Edge[ewg.V()];
        distTo = new double[ewg.V()];
        marked = new boolean[ewg.V()];
        pq = new IndexMinPQ<>(ewg.V());
        for(int v = 0;v < ewg.V();v++){
              distTo[v] = Double.POSITIVE_INFINITY;
        }
        for(int v = 0;v < ewg.V();v++){
            if(!marked[v]){
                prim(ewg, v);
            }
        }
    }
    private void prim(EdgeWeightedGraph ewg, int s){
        distTo[s] = 0.0;
        pq.insert(s,distTo[s]);
        while (!pq.isEmpty()){
            int v = pq.delMin();
            scan(ewg, v);
        }
    }
    private void scan(EdgeWeightedGraph ewg, int v){
        marked[v] = true;
        for(Edge e : ewg.adj(v)){
            int w = e.other(v);
            if(marked[w]){
                continue;
            }
            if(e.weight() < distTo[w]){
                distTo[w] = e.weight();
                edgeTo[w] = e;
                if(pq.contains(w)){
                    pq.changeKey(w, distTo[w]);
                }else{
                    pq.insert(w, distTo[w]);
                }
            }
        }
    }

    public Iterable<Edge> edges(){
        Queue<Edge> mst = new Queue<>();
        for(int v = 0; v < edgeTo.length;v++){
            Edge e = edgeTo[v];
            if(e != null){
                mst.enqueue(e);
            }
        }
        return mst;
    }
    public double weight(){
        double weight = 0.0;
        for(Edge e : edges()){
            weight += e.weight();
        }
        return weight;
    }

    public static void main(String[] args){
        In in = new In("tinyEWG.txt");
        EdgeWeightedGraph ewg = new EdgeWeightedGraph(in);
        PrimMST mst = new PrimMST(ewg);
        for(Edge e : mst.edges()){
            StdOut.println(e);
        }
        StdOut.printf("%.5f\n", mst.weight());
    }
}