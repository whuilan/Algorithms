package textbook.chapter4_1;

import edu.princeton.cs.algs4.In;

/**
 * 寻找和给点起点连通的所有顶点
 */
public class TestSearch {
    public static void main(String[] args){
        Graph graph = new Graph(new In(args[0]));
        int s = Integer.parseInt(args[1]);
        DepthFirstSearch dfs = new DepthFirstSearch(graph,s);

        for(int v = 0; v < graph.V(); v++){
            if(dfs.marked(v)){
                System.out.print(v+" ");
            }
        }
        System.out.println();
        if(dfs.count() != graph.V()){
            System.out.print("Not ");
        }
        System.out.println("connected");
    }
}
