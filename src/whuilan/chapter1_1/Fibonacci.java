package whuilan.chapter1_1;

import edu.princeton.cs.algs4.StdOut;

public class Fibonacci {
    public static long F(int N){
//        if(N == 0) return 0;
//        if(N == 1) return 1;
//        return F(N-1)+F(N-2);
        if(N==0) {return 0;}
        else if(N==1) {return 1;}
        else if(N==2) {return F(N-1)+F(N-2);}
        else{
            long[] arr = new long[N];
            arr[0] = 1;
            arr[1] = 1;
            for(int i=2;i<N;i++){
                arr[i] = arr[i-1]+arr[i-2];
            }
            return arr[N-1];
        }
    }
    public static void main(String[] args){
        for(int N=0;N<100;N++){
            StdOut.println(N+" "+F(N));
        }
    }
}
