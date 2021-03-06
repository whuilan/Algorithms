package textbook.chapter4_4;


import textbook.chapter1_3_3.Stack;
import textbook.chapter4_2.Topological;

public class AcyclicLP {
    public DirectedEdge[] edgeTo;
    public double[] distTo;

    public AcyclicLP(EdgeWeightedDigraph G, int s){
        edgeTo = new DirectedEdge[G.V()];
        distTo = new double[G.V()];
        for(int v = 0; v < G.V(); v++){
            distTo[v] = Double.NEGATIVE_INFINITY;
        }
        distTo[s] = 0.0;
        Topological topo = new Topological(G);
        for(int v : topo.order()){
            relax(G, v);
        }
    }
    private void relax(EdgeWeightedDigraph G, int v){
        for(DirectedEdge e : G.adj(v)){
            int w = e.to();
            if(distTo[w] < distTo[v] + e.weight()){
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
            }
        }
    }
    public double distTo(int v){
        return distTo[v];
    }
    public boolean hasPathTo(int v){
        return distTo[v] < Double.NEGATIVE_INFINITY;
    }
    public Iterable<DirectedEdge> pathTo(int v){
        if(!hasPathTo(v)){
            return null;
        }
        Stack<DirectedEdge> path = new Stack<>();
        for(DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]){
            path.push(e);
        }
        return path;
    }
}
