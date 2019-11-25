package textbook.chapter4_4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import textbook.chapter1_3_3.Stack;
import textbook.chapter2_4.IndexMinPQ;

/**
 * 单点最短路径的Dijkstra算法：给定起点到树中所有顶点的最短路径
 */
public class DijkstraSP {
    private DirectedEdge[] edgeTo;
    private double[] distTo;
    private IndexMinPQ<Double> pq; // 保存需要被放松的顶点并确认下一个即将被放松的顶点

    public DijkstraSP(EdgeWeightedDigraph dg, int s){
        edgeTo = new DirectedEdge[dg.V()];
        distTo = new double[dg.V()];
        pq = new IndexMinPQ<>(dg.V());
        for(int v = 0; v < dg.V(); v++){
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;
        pq.insert(s, 0.0);  // 用起点和权重0.0初始化pq, 从起点开始放松，也可以直接对起点进行放松: relax(dg, s)
        while (!pq.isEmpty()){
            relax(dg, pq.deleteMin()); // 对优先队列中离起点最近的顶点进行松弛并将其加入最短路径树
        }
    }
    private void relax(EdgeWeightedDigraph dg, int v){
        for(DirectedEdge e : dg.adj(v)){
            int w = e.to();
            if(distTo[w] > distTo[v] + e.weight()){
                edgeTo[w] = e;
                distTo[w] = distTo[v] + e.weight();
                if(pq.contains(w)){
                    pq.change(w, distTo[w]);
                }
                else {
                    pq.insert(w, distTo[w]);
                }
            }
        }
    }
    // 返回起点到给定顶点v的路径长度/距离
    public double distTo(int v){
        return distTo[v];
    }
    // v是否是从起点s可达的
    public boolean hasPathTo(int v){
        return distTo[v] < Double.POSITIVE_INFINITY;
    }
    // 返回起点到给定顶点v的路径
    public Iterable<DirectedEdge> pathTo(int v){
        if(!hasPathTo(v)){
            return null;
        }
        Stack<DirectedEdge> path = new Stack<>();
        for(DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]){
            path.push(e);
        }
        return path;
    }

    public static void main(String[] args){
        In in = new In("tinyEWD.txt");
        EdgeWeightedDigraph dg = new EdgeWeightedDigraph(in);
        int s = 0;
        DijkstraSP sp = new DijkstraSP(dg, s);
        for(int v = 0; v < dg.V(); v++){
            StdOut.print(s + " to " + v);
            StdOut.printf(" (%4.2f):",sp.distTo(v));
            if(sp.hasPathTo(v)){
                for(DirectedEdge e : sp.pathTo(v)){
                    StdOut.print(e + " ");
                }
            }
            StdOut.println();
        }
    }
}
