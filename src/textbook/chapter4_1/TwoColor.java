package textbook.chapter4_1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class TwoColor {
    private boolean[] marked;
    private boolean[] color;
    private boolean isTwoColorable = true;

    public TwoColor(Graph g){
        marked = new boolean[g.V()];
        color = new boolean[g.V()];
        for(int s = 0; s < g.V(); s++){
            if(!marked[s]){
                dfs(g, s);
            }
        }
    }
    private void dfs(Graph g, int v){
        marked[v] = true;
        for(int w : g.adj(v)){
            if(!marked[w]){
                color[w] = !color[v];
                dfs(g, w);
            }
            else if(color[w] == color[v]){
                isTwoColorable = false;
            }
        }
    }
    public boolean isBipartite(){
        return isTwoColorable;
    }

    public static void main(String[] args){
        Graph g = new Graph(new In(args[0]));
        TwoColor twoColor = new TwoColor(g);
        boolean isBipartite = twoColor.isBipartite();
        if(isBipartite){
            StdOut.println("The graph is bipartite." );
        }else {
            StdOut.println("The graph is not bipartite");
        }
    }
}
