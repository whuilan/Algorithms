package textbook.chapter4_4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 货币兑换中的套汇问题：等价于加权有向图中的负权重环检测问题
 */
public class Arbitrage {
    public static void main(String[] args){
        // 难点： 将输入汇率文件格式转化成有向图模型
        // In StdIn = new In("rates.txt");
        int V = StdIn.readInt();
        String[] names = new String[V];   // 把每一行第一个单词提取出来组成数组
        EdgeWeightedDigraph g = new EdgeWeightedDigraph(V);
        for(int v = 0; v < V; v++){
            names[v] = StdIn.readString();
            for(int w = 0; w < V; w++){
                double rate = StdIn.readDouble();
                double weight = -Math.log(rate);// 将权重设置为每个汇率的自然对数并取反
                DirectedEdge e = new DirectedEdge(v, w, weight); // 此处较为巧妙
                g.addEdge(e);
            }
        }
        BellmanFordSP sp = new BellmanFordSP(g, 0);
        if(sp.hasNegativeCycle()){
            double stake = 1000.0;
            for(DirectedEdge e : sp.negativeCycle()){
                StdOut.printf("%10.5f %s", stake, names[e.from()]);
                stake *= Math.exp(-e.weight());
                StdOut.printf(" = %10.5f %s\n", stake, names[e.to()]);
            }
        }
        else {
            StdOut.println("No arbitratge opportunity");
        }
    }
}
