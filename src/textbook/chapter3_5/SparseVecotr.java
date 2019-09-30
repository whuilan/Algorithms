package textbook.chapter3_5;

import edu.princeton.cs.algs4.SeparateChainingHashST;
import edu.princeton.cs.algs4.StdOut;

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
        }else{
            st.put(i,x);
        }
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
            sum += this.get(i) * that[i]; // this指代的是实例化之后的SparseVector对象，而不是st,this.get()会跳转到24行执行
        }
        return sum;
    }

    public static void main(String[] args){
        int N = 5;
        SparseVecotr[] a = new SparseVecotr[N];
        for(int i = 0; i < N;i++){
            a[i] = new SparseVecotr();
        }
        double[] a0 = {0, 0.9, 0, 0, 0};
        double[] a1 = {0, 0, 0.36, 0.36, 0.18};
        double[] a2 = {0, 0, 0, 0.9, 0};
        double[] a3 = {0.9, 0, 0, 0, 0};
        double[] a4 = {0.47, 0, 0.47, 0, 0};
        double[] x = {0.05, 0.04, 0.36, 0.37, 0.19};
        double[] b = new double[N];
        for(int i = 0;i < N;i++){
            switch (i){
                case 0:
                   for(int j=0;j<N;j++){
                       a[i].put(i,a0[j]);
                   }
            }
        }
        StdOut.println("test");
    }
}
