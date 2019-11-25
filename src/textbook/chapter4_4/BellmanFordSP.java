package textbook.chapter4_4;

import textbook.chapter1_3_3.Queue;

/**
 * 基于队列的Bellman-Ford算法：解决一般加权有向图中的单点最短路径问题（可能有负权重和环）
 */
public class BellmanFordSP {
    private DirectedEdge[] edgeTo;  // 从起点到某个顶点的最后一条边
    private double[] distTo[];      // 从起点到某个顶点的路径长度
    private Queue<Integer> queue;   // 即将被放松的顶点
    private boolean[] onQ;          // 该顶点是否存在于队列中
    private int cost;               // relax()的调用次数
    private Iterable<DirectedEdge> cycle; // edgeTo[]中的负权重有向环（如果有的话）

    public BellmanFordSP(EdgeWeightedDigraph g, int s){

    }
}
