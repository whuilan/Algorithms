package textbook.chapter3_1;

import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdOut;

public class BehaviorTest {
    public static void main(String[] args){
        ST<String,Integer> st = new ST<>();
        String[] arr = {"S","E","A","R","C","H","E","X","A","M","P","L","E"};
        for(int i=0;i<arr.length;i++){
            String key = arr[i];
            st.put(key,i);
        }
        for(String s:st.keys()){
            StdOut.println(s+" "+st.get(s));
        }
    }
}
