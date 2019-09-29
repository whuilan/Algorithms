package textbook.chapter3_5;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdOut;
import textbook.chapter1_3_3.Queue;

public class LookupIndex {
    public static void main(String[] args){
        In in = new In(args[0]);
        String sp = args[1]; // 分割符
        ST<String, Queue<String>> st = new ST<>(); // 索引
        ST<String, Queue<String>> ts = new ST<>(); // 反向索引
        while (in.hasNextLine()){
            String[] a = in.readLine().split(sp);
            String key = a[0];
            for(int i=1; i < a.length; i++){
                String val = a[i];
                // 构造正向索引符号表
                if(!st.contains(key)){
                    st.put(key,new Queue<>());
                }
                st.get(key).enqueue(val);
                // 构造反向索引符号表
                if(!ts.contains(val)){
                    ts.put(val,new Queue<>());
                }
                ts.get(val).enqueue(key);
            }
        }

        String queryST = "Tin Men (1987)";
        if(st.contains(queryST)){
            StdOut.println(queryST);
            for(String s : st.get(queryST)){
                StdOut.print(" "+s);
            }
        }
        StdOut.println();
        String queryTS = "Bacon, Kevin";
        if(ts.contains(queryTS)){
            StdOut.println(queryTS);
            for(String s : ts.get(queryTS)){
                StdOut.print(" "+s);
            }
        }
        StdOut.println();
    }
}
