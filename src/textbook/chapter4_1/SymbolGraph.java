package textbook.chapter4_1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdOut;

public class SymbolGraph {
    private ST<String,Integer> st; // 字符串表示的顶点 - 索引表示的顶点
    private String[] keys; // 索引 - 字符串
    private Graph graph;

    public SymbolGraph(String filename, String sp){
        st = new ST<>();
        // 第一次遍历输入流数据，构造符号表（索引），将字符串表示的顶点名映射为索引表示的顶点
        In in = new In(filename);
        while (in.hasNextLine()){
            String[] names = in.readLine().split(sp);
            for(String name : names){
                if(!st.contains(name)){
                    st.put(name,st.size()); // st.size()刚好就是索引，此处巧妙
                }
            }
        }
        // 构造反向索引数组，由索引获得顶点名
        keys = new String[st.size()];
        for(String  name : st.keys()){
            keys[st.get(name)] = name;
        }
        // 构造图
        graph = new Graph(st.size());
        // 第二次遍历输入流数据，构造图
        in = new In(filename);
        while (in.hasNextLine()){
            String[] names = in.readLine().split(sp);
            String s = names[0];
            for(int i = 1; i < names.length; i++){
                graph.addEdge(st.get(s),st.get(names[i]));
            }
        }
    }
    public boolean contains(String s){
        return st.contains(s);
    }
    public int index(String s){
        return st.get(s);
    }
    public String name(int v){
        return keys[v];
    }
    public Graph G(){
        return graph;
    }

    public static void main(String[] args){
        String filename = args[0];
        String sp = args[1];
        SymbolGraph symbolGraph = new SymbolGraph(filename, sp);
        Graph graph = symbolGraph.G();
        String query = "Bacon, Kevin";
        if(symbolGraph.contains(query)){
            StdOut.println(query);
            int v = symbolGraph.index(query);
            for(int w : graph.adj(v)){
                String name = symbolGraph.name(w);
                StdOut.println(" "+name);
            }
        }
    }
}
