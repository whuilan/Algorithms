package textbook.chapter4_2;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import textbook.chapter1_3_3.Stack;

/**
 * 寻找有向环
 */
public class DirectedCycle {
    private boolean[] marked;
    private boolean[] onStack; // 调用递归的栈上的所有顶点（还未完成完整的dfs()）
    private int[] edgeTo;
    private Stack<Integer> cycle;

    public DirectedCycle(Digraph g){
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        onStack = new boolean[g.V()];
        for(int v = 0; v < g.V(); v++){
            if(!marked[v] && cycle == null){
                dfs(g, v);
            }
        }
    }
    private void dfs(Digraph g,int v){
        marked[v] = true;
        onStack[v] = true;
        for(int w : g.adj(v)){
            if(cycle != null){
                return;
            }
            else if(!marked[w]){
                edgeTo[w] = v;
                dfs(g, w);
            }
            else if(onStack[w]){
                cycle = new Stack<>();
                for(int x = v; x != w; x = edgeTo[x]){
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
        onStack[v] = false;
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
        // SymbolDigraph sg = new SymbolDigraph("jobs.txt", "/");
        // DirectedCycle dc = new DirectedCycle(sg.G());
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
