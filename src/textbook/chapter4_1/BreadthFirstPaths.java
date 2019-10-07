package textbook.chapter4_1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import textbook.chapter1_3_3.Queue;
import textbook.chapter1_3_3.Stack;

/**
 * 使用广度优先搜索得到起点s与其他所有顶点的最短路径
 */
public class BreadthFirstPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    public BreadthFirstPaths(Graph g, int s){
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        this.s = s;
        bfs(g, s);
    }
    private void bfs(Graph g, int s){
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(s);
        marked[s] = true;
        while (!queue.isEmpty()){
            int v = queue.dequeue();
            for(int w : g.adj(v)){
                if(!marked[w]){
                    marked[w] = true;
                    edgeTo[w] = v;
                    queue.enqueue(w);
                }
            }
        }
    }
    public boolean hasPathTo(int v){
        return marked[v];
    }
    public Iterable<Integer> pathTo(int v){
        Stack<Integer> stack = new Stack<>();
        for(int x = v; x != s; x = edgeTo[x]){
            stack.push(x);
        }
        stack.push(s);
        return stack;
    }
    // 测试用例
    public static void main(String[] args){
        Graph g = new Graph(new In(args[0]));
        int s = Integer.parseInt(args[1]);
        BreadthFirstPaths bfp = new BreadthFirstPaths(g, s);
        for(int v = 0; v < g.V();v++){
            if(bfp.hasPathTo(v)){
                StdOut.print(s + " to " + v + ": ");
                for(int w : bfp.pathTo(v)){
                    if(w == s){
                        StdOut.print(w);
                    }else {
                        StdOut.print("-"+w);
                    }
                }
                StdOut.println();
            }
        }
    }
}
