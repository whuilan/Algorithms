package textbook.chapter4_2;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import textbook.chapter1_3_3.Queue;

public class KosarajuSCC {
    private boolean[] marked;
    private int[] id;
    private int count;

    public KosarajuSCC(Digraph dg){
        marked = new boolean[dg.V()];
        id = new int[dg.V()];
        Digraph reverseDg = dg.reverse();
        DepthFirstOrder dfo = new DepthFirstOrder(reverseDg);
        for(int v : dfo.reversePost()){
            if(!marked[v]){
                dfs(dg, v);
                count++;
            }
        }
    }
    private void dfs(Digraph dg, int v){
        marked[v] = true;
        id[v] = count;
        for(int w : dg.adj(v)){
            if(!marked[w]){
                dfs(dg, w);
            }
        }
    }
    // v和w是强连通的吗
    public boolean stronglyConnected(int v, int w){
        return id[v] == id[w];
    }
    // 图中强连通分量的总数
    public int count(){
        return count;
    }
    // v所在强连通分量的标识符
    public int id(int v){
        return id[v];
    }

    public static void main(String[] args){
        Digraph dg = new Digraph(new In("tinyDG.txt"));
        KosarajuSCC kscc = new KosarajuSCC(dg);
        int count = kscc.count;
        StdOut.println(count + " strong components");
        Queue<Integer>[] scc =(Queue<Integer>[]) new Queue[count];
        for(int i = 0;i< count;i++){
            scc[i] = new Queue<>();
        }
        for(int v = 0;v < dg.V();v++){
            scc[kscc.id[v]].enqueue(v);
        }
        for(int i = 0;i < count;i++){
            for(int v : scc[i]){
                StdOut.print(v + " ");
            }
            StdOut.println();
        }
        // 该方法遍历次数太多
//        for(int i = 0;i < count;i++){
//            for(int v = 0; v < dg.V();v++){
//                if(kscc.id[v] == i){
//                    StdOut.print(v + " ");
//                }
//            }
//            StdOut.println();
//        }
    }
}
