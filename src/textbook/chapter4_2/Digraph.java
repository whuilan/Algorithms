package textbook.chapter4_2;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import textbook.chapter1_3_3.Bag;

public class Digraph {
    private final int V;
    private int E;
    private Bag<Integer>[] adj;

    public Digraph(int V){
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for(int i = 0;i < V;i++){
            adj[i] = new Bag<>();
        }
    }
    public Digraph(In in){
        this(in.readInt());
        int E = in.readInt();
        for(int i = 0;i< E;i++){
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w);
        }
    }
    public void addEdge(int v, int w){
        adj[v].add(w);
        E++;
    }
    public int V(){
        return V;
    }
    public int E(){
        return E;
    }
    public Iterable<Integer> adj(int v){
        return adj[v];
    }
    public Digraph reverse(){
        Digraph digraph = new Digraph(V);
        for(int v = 0; v < V;v++){
            for(int w : adj(v)){
                digraph.addEdge(w, v);
            }
        }
        return digraph;
    }
    public String toString(){
        String s = V + " vertices, " + E +" edges\n";
        for(int v = 0; v < V;v++){
            s += v + ":";
            for(int w : adj(v)){
                s += w + " ";
            }
           s += "\n";
        }
        return s;
    }
    public static void main(String[] args){
        In in = new In(args[0]);
        Digraph digraph = new Digraph(in);
        StdOut.println(digraph.toString());
    }
}
