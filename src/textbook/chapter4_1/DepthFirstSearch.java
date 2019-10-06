package textbook.chapter4_1;

public class DepthFirstSearch {
    private boolean[] marked;
    private int count;

    public DepthFirstSearch(Graph graph,int s){
        marked = new boolean[graph.V()];
        dfs(graph,s);
    }
    private void dfs(Graph graph,int v){
        marked[v] = true;
        count++;
        for(int w : graph.adj(v)){
            if (!marked[w]){
                dfs(graph,w);
            }
        }
    }
    public boolean marked(int w){
        return marked[w];
    }
    public int count(){
        return count;
    }
}
