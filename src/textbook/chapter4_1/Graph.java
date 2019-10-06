package textbook.chapter4_1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import textbook.chapter1_3_3.Bag;

public class Graph {
    private final int V; // 顶点数目
    private int E; // 边的数目
    private Bag<Integer>[] adj; // 邻接表数组
    public Graph(int V){
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[])new Bag[V]; // 初始化邻接表数组为含有V个背包的数组
        // 记住要将每一个数组元素初始化为一条链表(背包)，否则每个数组元素默认值就为空，是不能执行add操作的
        for(int v = 0;v < V;v++){
            adj[v] = new Bag<>();
        }
    }
    public Graph(In in){
        this(in.readInt());
        int E = in.readInt();
        for(int i = 0; i < E; i++){ // 注意我们的输入文件的格式是成对的顶点(即一条边)
            // 读取并添加一条边
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w);
        }

    }
    public int V(){
        return V;
    }
    public int E(){
        return E;
    }
    public void addEdge(int v,int w){
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }
    public Iterable<Integer> adj(int v){
        return adj[v];
    }
    public String toString(){
        String s = V + " vertices, " + E + " edges\n";
        for(int v = 0; v < V; v++){
            s += v + ":";
            for(int w:adj(v)){
                s += w + " ";
            }
            s += "\n";
        }
        return s;
    }

    public static void main(String[] args){
        In in = new In(args[0]);
        Graph graph = new Graph(in);
        StdOut.println(graph.toString());
    }
}
