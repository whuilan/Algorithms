package textbook.chapter4_1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import textbook.chapter1_3_3.Stack;

/**
 * 使用深度优先搜索来查找图中的所有路径（从起点到所有顶点）
 */
public class DepthFirstPaths {
    private boolean[] marked; // 每一个顶点与起点s是否连通
    private int[] edgeTo; // 一颗用父链接表示的以s为根且含有所有与s连通的顶点的树
    private final int s; // 起点s

    public DepthFirstPaths(Graph g, int s){
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        this.s = s;
        dfs(g, s);
    }
    private void dfs(Graph g, int v){
        marked[v] = true;
        for(int w : g.adj(v)){
            if(!marked[w]){
                edgeTo[w] = v; // 表示v-w是第一次访问w时经过的边，也是从起点s-w路径上的最后一条边
                dfs(g, w);
            }
        }
    }
    public boolean hasPathTo(int v){
        return marked[v];
    }
    public Iterable<Integer> pathTo(int v){
        if(!hasPathTo(v)){
            return null;
        }
        Stack<Integer> path = new Stack<>();
        for(int x = v; x != s; x = edgeTo[x]){
            path.push(x);
        }
        path.push(s);
        return path;
    }

    public static void main(String[] args){
        Graph graph = new Graph(new In(args[0]));
        int s = Integer.parseInt(args[1]);
        DepthFirstPaths dfp = new DepthFirstPaths(graph, s);
        for(int v = 0; v < graph.V(); v++){
            StdOut.print(s + " to " + v + ": ");
            for(int x : dfp.pathTo(v)){
                if(x == s){
                    StdOut.print(x);
                }else {
                    StdOut.print("-" + x);
                }
            }
            StdOut.println();
        }
    }
}
