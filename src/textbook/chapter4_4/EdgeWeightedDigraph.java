package textbook.chapter4_4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import textbook.chapter1_3_3.Bag;
import textbook.chapter4_3.Edge;

/**
 * 加权有向图的数据类型
 */
public class EdgeWeightedDigraph {
    private final int V;
    private int E;
    private Bag<DirectedEdge>[] adj;  // 自己写的时候居然忘了这个，图的表示就是临接表数组呀！

    public EdgeWeightedDigraph(int V){
        this.V = V;
        this.E = 0;
        adj = (Bag<DirectedEdge>[]) new Bag[V];
        for(int i = 0; i < V; i++){
            adj[i] = new Bag<>();
        }
    }
    public EdgeWeightedDigraph(In in){
        this(in.readInt());
        int E = in.readInt();
        for(int i = 0; i < E;i++){
            int v = in.readInt();
            int w = in.readInt();
            double weight = in.readDouble();
            DirectedEdge e = new DirectedEdge(v, w, weight);
            addEdge(e);
        }
    }
    public void addEdge(DirectedEdge e){
        int v = e.from();
        adj[v].add(e);
        E++;
    }
    public int V(){
        return V;
    }
    public int E(){
        return E;
    }
    // 返回从顶点v指出的边
    public Iterable<DirectedEdge> adj(int v){
        return adj[v];
    }
    // 返回图中所有边
    public Iterable<DirectedEdge> edges(){
        Bag<DirectedEdge> bag = new Bag<>();
        for (int v = 0; v < V; v++){
            for(DirectedEdge e : adj[v]){
                bag.add(e);
            }
        }
        return bag;
    }
    public String toString(){
        String s = V + " vertices, " + E + " edges\n";
        for(int v = 0; v < V; v++){
            s += v + ": ";
            for(DirectedEdge e : adj[v]){
                s += e + "  ";
            }
            s += "\n";
        }
        return s;
    }

    public static void main(String[] args){
        In in = new In("tinyEWD.txt");
        EdgeWeightedDigraph dg = new EdgeWeightedDigraph(in);
        StdOut.println(dg);
    }
}
