package sword_to_offer;

/**
 * P74面试题10，斐波那契数列
 */
public class Problem10 {
    public static int Fibonacci(int n) {
        if(n <= 1){
            return n;
        }
        int fibNMinusTwo = 0;
        int fibNMinusOne = 1;
        int fibN = 0;
        for(int i = 2; i <= n; i++){
            fibN = fibNMinusOne + fibNMinusTwo;
            fibNMinusTwo = fibNMinusOne;
            fibNMinusOne = fibN;
        }
        return fibN;
    }

    public static void main(String[] args){
        int fib = Fibonacci(6);
        System.out.println(fib);
    }
}
