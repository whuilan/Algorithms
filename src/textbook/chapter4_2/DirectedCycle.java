package textbook.chapter4_2;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import textbook.chapter1_3_3.Stack;

/**
 * 寻找有向环
 */
public class DirectedCycle {
    private boolean[] marked;
    private int[] edgeTo;
    private Stack<Integer> cycle;

    public DirectedCycle(Digraph g){
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        for(int v = 0; v < g.V(); v++){
            if(!marked[v] && !hasCycle()){
                dfs(g, v);
            }
        }
    }
    private void dfs(Digraph g,int v){
        marked[v] = true;
        for(int w : g.adj(v)){
            if(hasCycle()){
                return;
            }
            if(!marked[w]){
                edgeTo[w] = v;
                dfs(g, w);
            }
            else {
                cycle = new Stack<>();
                for(int x = v; x != w; x = edgeTo[x]){
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
    }
    public boolean hasCycle(){
        return cycle != null;
    }
    public Iterable<Integer> cycle(){
        return cycle;
    }

    public static void main(String[] args){
        In in = new In("tinyDG.txt");
        Digraph g = new Digraph(in);
        DirectedCycle dc = new DirectedCycle(g);
        if(dc.hasCycle()){
            StdOut.println("Directed Cycle:");
            for(int v : dc.cycle()){
                StdOut.print(v + " ");
            }
            StdOut.println();
        }
        else{
            StdOut.println("No directed cycle");
        }
    }
}
