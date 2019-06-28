package exercises.chapter1_1;

public class Ex23 {
    public static int gcd(int p,int q){
        // System.out.println(p+" "+q);
        if(q == 0) return p;
        int r = p % q;
        System.out.println(p+" "+q);
        return gcd(q,r);
    }

    public static void main(String[] args){
        // int[] arr = StdIn.readAllInts();
        // System.out.println(arr[0]+"||"+arr[1]);
        int res = gcd(105,24);
        System.out.print("这两个数的最大公约数为："+res);
    }
}
