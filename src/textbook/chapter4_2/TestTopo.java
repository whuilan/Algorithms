package textbook.chapter4_2;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.SymbolDigraph;
import edu.princeton.cs.algs4.Topological;

// 相关数据结构都使用官方提供的代码包中的
public class TestTopo {
    public static void main(String[] args){
        String filename = "jobs.txt";
        String separator = "/";
        SymbolDigraph sg = new SymbolDigraph(filename, separator);
        Topological topo =  new Topological(sg.digraph());
        for(int v : topo.order()){
            StdOut.println(sg.nameOf(v));
        }
    }
}
