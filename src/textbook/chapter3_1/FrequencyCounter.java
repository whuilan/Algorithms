package textbook.chapter3_1;

import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class FrequencyCounter {
    public static void main(String[] args){
        int minlen = Integer.parseInt(args[0]);
        // ST<String,Integer> st = new ST<>();
        // SequentialSearchST<String,Integer> st = new SequentialSearchST<>();
        BinarySearchST<String,Integer> st = new BinarySearchST<>(5000);
        while (!StdIn.isEmpty()){
            // 构造符号表并统计频率
            String word = StdIn.readString();
            if(word.length()<minlen)
                continue;
            if(!st.contains(word))
                st.put(word,1);
            else
                st.put(word,st.get(word)+1);
        }
        // 找出出现频率最高的单词
        String max = "";
        st.put(max,0);
        for(String word:st.keys()){
            if(st.get(word)>st.get(max))
                max = word;
        }
        StdOut.println(max+" "+st.get(max));
    }
}
