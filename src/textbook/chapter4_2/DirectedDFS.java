package textbook.chapter4_2;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * 有向图的可达性
 */
public class DirectedDFS {
    private boolean[] marked;

    public DirectedDFS(Digraph g, int s){
        marked = new boolean[g.V()];
        dfs(g, s);
    }
    public DirectedDFS(Digraph g, Iterable<Integer> sources){
        marked = new boolean[g.V()];
        for(int s : sources){
            if(!marked[s]){
                dfs(g, s);
            }
        }
    }
    private void dfs(Digraph g, int v){
        if(!marked[v]){
            marked[v] = true;
            for(int w : g.adj(v)){
                dfs(g, w);
            }
        }
    }
    public boolean marked(int v){
        return marked[v];
    }

    public static void main(String[] args){
        Digraph g = new Digraph(new In("tinyDG.txt"));
        int source = 0;
        Bag<Integer> sources = new Bag<>();
        sources.add(1);
        sources.add(2);
        sources.add(6);
        DirectedDFS reachable = new DirectedDFS(g, sources);
        for(int v = 0; v < g.V(); v++){
            if(reachable.marked(v)){
                StdOut.print(v + " ");
            }
        }
        StdOut.println();
    }
}
