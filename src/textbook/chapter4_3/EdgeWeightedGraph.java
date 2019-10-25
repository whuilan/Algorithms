package textbook.chapter4_3;

import com.sun.org.apache.regexp.internal.RE;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import textbook.chapter1_3_3.Bag;

/**
 * 加权无向图的数据类型
 */
public class EdgeWeightedGraph {
    private final int V;
    private int E;
    private Bag<Edge>[] adj;  // 邻接表

    public EdgeWeightedGraph(int V){
        this.V = V;
        this.E = 0;
        adj = (Bag<Edge>[]) new Bag[V];
        for(int i = 0; i < V;i++){
            adj[i] = new Bag<>();
        }
    }
    // 从输入流中读取一幅图
    public EdgeWeightedGraph(In in){
        this(in.readInt());
        int E = in.readInt();
        for(int i = 0; i < E;i++){
            int v = in.readInt();
            int w = in.readInt();
            double weight = in.readDouble();
            Edge edge = new Edge(v, w, weight);
            addEdge(edge);
        }
    }
    public int V(){
        return V;
    }
    public int E(){
        return E;
    }
    private void addEdge(Edge edge){
        int v = edge.either();
        int w = edge.other(v);
        adj[v].add(edge);
        adj[w].add(edge);
        E++;
    }
    // 返回和v相关联的所有边
    public Iterable<Edge> adj(int v){
        return adj[v];
    }
    // 返回图的所有边
    public Iterable<Edge> edges(){
        Bag<Edge> bag = new Bag<>();
        for(int v = 0; v < V;v++){
            for(Edge e : adj[v]){
                if(e.other(v) > v){
                    bag.add(e);
                }
            }
        }
        return bag;
    }
    // 对象的字符串表示
    public String toString(){
        String s = V + " vertices, " + E + " edges\n";
        for(int v = 0; v < V; v++){
            s += v + ":";
            for(Edge e : adj[v]){
                s += e + " ";
            }
            s += "\n";
        }
        return s;
    }

    public static void main(String[] args){
        In in = new In("tinyEWG.txt");
        EdgeWeightedGraph ewg = new EdgeWeightedGraph(in);
        StdOut.println(ewg);
    }
}
