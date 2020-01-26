package textbook.chapter5_4;

import textbook.chapter1_3_3.Bag;
import textbook.chapter1_3_3.Stack;
import textbook.chapter4_2.Digraph;
import textbook.chapter4_2.DirectedDFS;

/**
 * 算法5.9 正则表达式的模式匹配（grep）: 非确定有限状态自动机（NFA）
 */
public class NFA {
    private char[] re;  // 模式字符串
    private int M;      // 模式字符串的长度
    private Digraph G;  // 用来表示NFA中空转换的有向图

    public NFA(String regexp){
        // 根据指定的正则表达式regexp构造NFA(即由正则表达式中所有空转换组成的有向图)
        re = regexp.toCharArray();
        M = re.length;
        G = new Digraph(M + 1);  // 含有接受状态M
        Stack<Integer> op = new Stack<>();  // 将左括号(和或操作符|的索引压入栈中保存
        // 依次检查正则表达式
        for(int i = 0; i < M; i++){
            int lp = i;  // lp表示当前最近的左括号
            if(re[i] == '(' || re[i] == '|'){  // 将(和|压入栈保存
                op.push(i);
            }
            else if(re[i] == ')'){   // 遇到一个左括号时，弹出操作符
                int or = op.pop();
                if (re[or] == '|'){
                    lp = op.pop();  // 若or为或操作符|，则继续弹出左括号(，默认没有重复出现的|（多向或）
                    G.addEdge(lp, or + 1);
                    G.addEdge(or, i);
                }else {
                    lp = or;   // 更新左括号索引lp
                }
            }
            if(i < M - 1 && re[i + 1] == '*'){  // 对闭包操作符的处理
                G.addEdge(lp, i + 1);
                G.addEdge(i + 1, lp);
            }
            // 写到这儿不会写了，怎么为每个元字符添加一条空转换，即指向下一个状态的有向边
            if(re[i] == '(' || re[i] == '*' || re[i] == ')'){  // 就是用if把三种情况写出来呗
                G.addEdge(i, i + 1);
            }
        }
    }

    public boolean recognizes(String txt){
        // NFA是否能够识别txt(模拟NFA的运行)
        Bag<Integer> pc = new Bag<>();
        DirectedDFS dfs = new DirectedDFS(G, 0);
        for(int v = 0; v < G.V(); v++){
            if(dfs.marked(v)){
                pc.add(v);  // 用从状态0通过空转换可达到的状态来初始化pc
            }
        }

        for(int i = 0; i < txt.length(); i++){
            // 记录自动状态机在检查当前输入字符之后可能到达的所有状态集合
            Bag<Integer> match = new Bag<>();
            for(int v : pc){
                if(v < M){
                    if(re[v] == txt.charAt(i) || re[v] == '.'){ // 出现匹配，进行匹配转换
                        match.add(v + 1);
                    }
                }
            }
            pc = new Bag<>(); // 更新与txt[i+1]进行匹配的状态集合pc和dfs
            dfs = new DirectedDFS(G, match);
            for(int v = 0; v < G.V(); v++){
                if(dfs.marked(v)){
                    pc.add(v);
                }
            }
        }

        for(int v : pc){  // 疑问：为啥一定要在比较完txt中所有的字符之后才来看pc中是否含有接受状态
            if (v == M){
                return true;
            }
        }
        return false;
    }


}
