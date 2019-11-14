package textbook.chapter4_1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * 使用深度优先搜索来检测无向图中的环：给定的图是无环图吗？
 */
public class Cycle {
    private boolean[]  marked;
    private boolean hasCycle;

    public Cycle(Graph g){
        marked = new boolean[g.V()];
        for(int s = 0; s < g.V(); s++){
            if(!marked[s]){
                dfs(g, s, s);
            }
        }
    }
    private void dfs(Graph g, int v, int u){
        marked[v] = true;
        for(int w : g.adj(v)){
            if(!marked[w]){
                dfs(g, w, v);
            }
            else if(w != u){
                hasCycle = true;
                return;
            }
        }
    }
    public boolean hasCycle(){
        return hasCycle;
    }

    public static void main(String[] args){
        Graph g = new Graph(new In(args[0]));
         Cycle cycle = new Cycle(g);
        StdOut.println(cycle.hasCycle);
    }
}
