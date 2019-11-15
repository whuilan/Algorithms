package textbook.chapter4_2;

import edu.princeton.cs.algs4.StdOut;
import textbook.chapter4_4.EdgeWeightedDigraph;
import textbook.chapter4_4.EdgeWeightedDirectedCycle;

/**
 * 优先级限制下的调度问题，也即拓扑排序(相关数据结构都使用自己写的)
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
    // 重载： 处理加权有向图的拓扑排序
    public Topological(EdgeWeightedDigraph g){
        EdgeWeightedDirectedCycle ec = new EdgeWeightedDirectedCycle(g);
        if(!ec.hasCycle()){
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
        for(int v :topo.order()){
            StdOut.print(v + " ");
        }
        for(int v : topo.order()){
            StdOut.println(sg.name(v));
        }
    }
}
