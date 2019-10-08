package textbook.chapter4_1;

/**
 * 使用深度优先搜索找出图中所有的连通分量
 */
public class CC {
    private boolean[] marked;
    private int[] id;
    private int count;

    public CC(Graph g){
        marked = new boolean[g.V()];
        id = new int[g.V()];
        for(int v = 0; v < g.V(); v++){
            if(!marked[v]){
                dfs(g, v);
                count++;
            }
        }
    }
    private void dfs(Graph g, int v){
        marked[v] = true;
        id[v] = count;
        for(int w : g.adj(v)){
            if(!marked[w]){
                dfs(g, w);
            }
        }
    }
    public boolean connected(int v, int w){
        return id[v] == id[w];
    }
    public int count(){
        return count;
    }
    public int id(int v){
        return id[v];
    }
}
