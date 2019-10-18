package textbook.chapter4_2;

import textbook.chapter1_3_3.Queue;
import textbook.chapter1_3_3.Stack;
import textbook.chapter4_1.Graph;

/**
 * 有向图中基于深度优先搜索的顶点排序
 */
public class DepthFirstOrder {
    private boolean[] marked;
    private Queue<Integer> pre;  // 所有顶点的前序排序，即dfs()的调用顺序
    private Queue<Integer> post; // 所有顶点的后序排序，即顶点遍历完成的顺序
    private Stack<Integer> reversePost; // 所有顶点的逆后序排序

    public DepthFirstOrder(Digraph g){
        marked = new boolean[g.V()];
        pre = new Queue<>();
        post = new Queue<>();
        reversePost = new Stack<>();

        for(int v = 0; v < g.V(); v++){
            if(!marked[v]){
                dfs(g, v);
            }
        }
    }
    private void dfs(Digraph g, int v){
        marked[v] = true;
        pre.enqueue(v);
        for(int w : g.adj(v)){
            if(!marked[w]){
                dfs(g, w);
            }
        }
        post.enqueue(v);
        reversePost.push(v);
    }
    public Iterable<Integer> pre(){
        return pre;
    }
    public Iterable<Integer> post(){
        return post;
    }
    public Iterable<Integer> reversePost(){
        return reversePost;
    }
}
