package textbook.chapter3_5;

import edu.princeton.cs.algs4.SeparateChainingHashST;

import java.util.HashMap;

public class SparseVecotr {

    // private HashMap<Integer, Double> st;
    private SeparateChainingHashST<Integer, Double> st;
    public SparseVecotr(){
        st = new SeparateChainingHashST<>();
    }
    public int size(){
        return st.size();
    }
    public void put(int i, double x){
        if(x == 0){
            st.delete(i);
        }
        st.put(i,x);
    }
    public double get(int i){
        if(st.contains(i)){
            return st.get(i);
        }
        else {
            return 0.0;
        }
    }
    public double dot(double[] that){
        double sum = 0.0;
        for(int i : st.keys()){
            sum += this.get(i) * that[i];
        }
        return sum;
    }

    public static void main(String[] args){
        int N = 5;
        SparseVecotr[] a = new SparseVecotr[N];
        double[] x = {0.05, 0.04, 0.36, 0.37, 0.19};
        double[] b = new double[N];
        for(int i = 0;i < N;i++){
          a[i].put(i,0.1);

        }
    }
}
