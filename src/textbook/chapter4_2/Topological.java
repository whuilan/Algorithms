package textbook.chapter4_2;

import edu.princeton.cs.algs4.StdOut;

/**
 * 拓扑排序
 */
public class Topological {
    private Iterable<Integer> order;   // 顶点的拓扑顺序

    public Topological(Digraph g){
        DirectedCycle dc = new DirectedCycle(g);
        if(!dc.hasCycle()){
            DepthFirstOrder dfo = new DepthFirstOrder(g);
            order = dfo.reversePost();
        }
    }
    // g是有向无环图吗？
    public boolean isDAG(){
        return order != null;
    }
    // 返回拓扑有序的所有顶点
    public Iterable<Integer> order(){
        return order;
    }

    public static void main(String[] args){
        String filename = "jobs.txt";
        String separator = "/";
        SymbolDigraph sg = new SymbolDigraph(filename, separator);
        Topological topo = new Topological(sg.G());
        for(int v : topo.order()){
            StdOut.println(sg.name(v));
        }
    }
}
