package textbook.chapter4_4;

import textbook.chapter1_3_3.Stack;

/**
 * 寻找加权有向图中的环
 */
public class EdgeWeightedDirectedCycle {
    public boolean[] marked;
    public boolean[] onStack;
    public DirectedEdge[] edgeTo;
    public Stack<DirectedEdge> cycle;

    public EdgeWeightedDirectedCycle(EdgeWeightedDigraph G){
        marked = new boolean[G.V()];
        onStack  = new boolean[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        for(int v = 0; v < G.V(); v++){
            if(!marked[v]){
                dfs(G, v);
            }
        }
    }
    private void dfs(EdgeWeightedDigraph G, int v){
        marked[v] = true;
        onStack[v] = true;
        for(DirectedEdge e : G.adj(v)){
            int w = e.to();
            if(cycle != null){
                return;
            }
            else if(!marked[w]){
                edgeTo[w] = e;
                dfs(G, w);
            }
            else if(onStack[w]){
                cycle = new Stack<>();
                DirectedEdge x;
                for( x = e; x.from() != w; x = edgeTo[e.from()]){
                    cycle.push(x);
                }
                cycle.push(x);
                return;
            }
        }
        onStack[v] = false;
    }
    public boolean hasCycle(){
        return cycle != null;
    }
    public Iterable<DirectedEdge> edges(){
       return cycle;
    }
}
