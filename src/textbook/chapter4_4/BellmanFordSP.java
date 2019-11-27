package textbook.chapter4_4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import textbook.chapter1_3_3.Queue;
import textbook.chapter1_3_3.Stack;

/**
 * 基于队列的Bellman-Ford算法：解决一般加权有向图中的单点最短路径问题（可能有负权重和环）
 * 本类要么会找到一条从起点到任意其他顶点的最短路径要么会找到一个从七点可达的负权重环
 */
public class BellmanFordSP {
    private DirectedEdge[] edgeTo;  // 从起点到某个顶点的最后一条边
    private double[] distTo;      // 从起点到某个顶点的路径长度
    private Queue<Integer> queue;   // 即将被放松的顶点
    private boolean[] onQ;          // 该顶点是否已经存在于队列中，防止将顶点重复插入队列
    private int cost;               // relax()的调用次数
    private Iterable<DirectedEdge> cycle; // edgeTo[]中的负权重有向环（如果有的话）

    public BellmanFordSP(EdgeWeightedDigraph g, int s){
        edgeTo = new DirectedEdge[g.V()];
        distTo = new double[g.V()];
        onQ = new boolean[g.V()];
        queue = new Queue<>();
        for(int v = 0; v < g.V(); v++){
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;
        queue.enqueue(s);
        onQ[s] = true;
        while (!queue.isEmpty() && !hasNegativeCycle()){
           int v = queue.dequeue();
           onQ[v] = false;
           relax(g, v);
        }
    }
    private void relax(EdgeWeightedDigraph g, int v){
        for(DirectedEdge e : g.adj(v)){
            int w = e.to();
            if(distTo[w] > distTo[v] + e.weight()){
                edgeTo[w] = e;
                distTo[w] = distTo[v] + e.weight();
                if(!onQ[w]){
                    queue.enqueue(w);
                    onQ[w] = true;
                }
            }
            // 每调用V次relax()方法后即调用findNegativeCycle()方法来查找目前最短路径上是否存在负权重环,如果找到则程序终止
            if(cost++ % g.V() == 0){
                findNegativeCycle();
                if(hasNegativeCycle()){
                    return;
                }
            }
        }
    }
    private void findNegativeCycle(){
        int V = edgeTo.length;
        EdgeWeightedDigraph spt = new EdgeWeightedDigraph(V);
        for(int v = 0; v < V; v++){
            if(edgeTo[v] != null){
                spt.addEdge(edgeTo[v]);
            }
        }
        EdgeWeightedDirectedCycle cy = new EdgeWeightedDirectedCycle(spt);
        cycle = cy.cycle;
    }
    public boolean hasNegativeCycle(){
        return cycle != null;
    }
    public Iterable<DirectedEdge> negativeCycle(){
        return cycle;
    }
    public double distTo(int v){
        return distTo[v];
    }
    public boolean hasPathTo(int v){
        return distTo[v] < Double.POSITIVE_INFINITY;
    }
    public Iterable<DirectedEdge> pathTo(int v){
        if(!hasPathTo(v)){
            return null;
        }
        Stack<DirectedEdge> path  = new Stack<>();
        for(DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]){
            path.push(e);
        }
        return path;
    }

    public static void main(String[] args){
        In in = new In("tinyEWDnc.txt");
        EdgeWeightedDigraph g = new EdgeWeightedDigraph(in);
        int s = 0;
        BellmanFordSP sp = new BellmanFordSP(g, s);
        for(int v = 0; v < g.V(); v++){
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
