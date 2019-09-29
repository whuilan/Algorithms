package textbook.chapter3_5;


import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdOut;

import java.io.File;

public class FileIndex {
    public static void main(String[] args){
        ST<String, SET<File>> st = new ST<>(); // 注意是SET而不是java.util的Set，不然20行处会显示Set为抽象类，无法初始化
        for(String filename : args){
            File file = new File(filename);
            In in = new In(file);
            while(!in.isEmpty()){
                String word = in.readString();
                if(!st.contains(word)){
                    st.put(word,new SET<>());
                }
                SET<File> set = st.get(word);
                set.add(file);
            }
        }

        String[] querys = {"age","best","was"};
        for(String query : querys){
            if(st.contains(query)){
                StdOut.println(query);
                for(File file : st.get(query)){
                    StdOut.println(" "+file.getName());
                }
            }
        }
    }
}
