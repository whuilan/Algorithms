package whuilan.chapter1_1;

public class Ex16 {
    public static String exR1(int n){
        if(n<=0) return "";
        return exR1(n-3)+n+exR1(n-2)+n;
    }
    public static void main(String[] args){
        String s = exR1(6);
        System.out.println(s);
    }
}
