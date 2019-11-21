package textbook.chapter4_4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * 优先级限制下的并行任务调度问题的关键路径方法：转化为无环加权有向图中的最长路径问题
 */
public class CPM {
    public static void main(String[] args){
        In in = new In("jobsPC.txt");
        int N = in.readInt();
        in.readLine();
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(2*N + 2);
        int s = 2 * N, t = 2 * N + 1;
        for(int i = 0; i < N;i++){
            String[] a = in.readLine().split("\\s+");
            double duration = Double.parseDouble(a[0]);
            G.addEdge(new DirectedEdge(i, i + N, duration)); // 任务的起始顶点和结束顶点
            G.addEdge(new DirectedEdge(s, i, 0.0)); // 由起点指向任务起始顶点的权重为0的边
            G.addEdge(new DirectedEdge(i + N, t, 0.0)); // 由任务结束顶点指向重点的权重为0的边
            for(int j = 1; j < a.length;j++){
                int successor = Integer.parseInt(a[j]);
                G.addEdge(new DirectedEdge(i + N, successor, 0.0)); // 任务的结束顶点指向较低优先级的起始顶点
            }
        }
        AcyclicLP lp = new AcyclicLP(G, s);
        StdOut.println("Start time:");
        for(int i = 0; i < N; i++){
            StdOut.printf("%4d: %5.1f\n", i, lp.distTo[i]);
        }
        StdOut.printf("Finish time: %5.1f\n", lp.distTo(t));
    }
}
